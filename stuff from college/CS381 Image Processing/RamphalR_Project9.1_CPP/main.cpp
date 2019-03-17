#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

class Morphology{
private:
    int numRowsImg;
    int numColsImg;
    int minValImg;
    int maxValImg;

    int numRowsStrctElem;
    int numColsStrctElem;
    int minValStrctElem;
    int maxValStrctElem;
    int rowOrigin;
    int colOrigin;
    int rowFrameSize;
    int colFrameSize;
    int** imgAry; //size numRowsIMG+2*rowFrameSize by numColsIMG+2*colFrameSize
    int** morphAry; //size numRowsIMG+2*rowFrameSize by numColsIMG+2*colFrameSize
    int** structElem; //size numRowsStrctElem by numColsStrctElem
    int** neighborAry;

public:
    Morphology(ifstream&,ifstream&);
    ~Morphology();
    void loadImage(ifstream&);
    void loadStruct(ifstream&);
    void computeFrame();
    void zeroFrame(int**);
    void dilation(int,int,int**); //param = pixel x,y coordinates
    void erosion(int,int,int**);
    void closing();
    void opening();
    void prettyPrint();
    void outputResult(ofstream&);
    void zeroMorphAry();
    void loadStructNeighbors(int,int,int**); //load the structured element shape surrounding and including p(i,j)
    void runAlgorithm(ofstream&,char*);
};

Morphology::Morphology(ifstream& imagefile, ifstream& structelemfile){
    imagefile >> numRowsImg >> numColsImg >> minValImg >> maxValImg;
    structelemfile >> numRowsStrctElem >> numColsStrctElem >> minValStrctElem >> maxValStrctElem >> rowOrigin >> colOrigin;
    computeFrame();
    imgAry = new int*[numRowsImg + 2*rowFrameSize];
    for(int i=0; i<(numRowsImg + 2*rowFrameSize); i++){
        imgAry[i] = new int[numColsImg + 2*colFrameSize];
    }
    zeroFrame(imgAry);

    morphAry = new int*[numRowsImg + 2*rowFrameSize];
    for(int i=0; i<(numRowsImg + 2*rowFrameSize); i++){
        morphAry[i] = new int[numColsImg + 2*colFrameSize];
    }
    structElem = new int*[numRowsStrctElem];
    for(int i=0; i<numRowsStrctElem; i++){
        structElem[i] = new int[numColsStrctElem];
    }

    loadImage(imagefile);
    loadStruct(structelemfile);

    neighborAry = new int*[numRowsStrctElem];
    for(int i=0; i<numRowsStrctElem; i++){
        neighborAry[i] = new int[numColsStrctElem];
    }
}

Morphology::~Morphology(){
    if(imgAry != nullptr) delete[] imgAry;
    if(morphAry != nullptr) delete[] morphAry;
    if(structElem != nullptr) delete[] structElem;
}

void Morphology::loadImage(ifstream& infile){
    int row = rowFrameSize, col = colFrameSize;
    while(infile >> imgAry[row][col]){
        morphAry[row][col] = imgAry[row][col];
        col++;
        if(col >= numColsImg + colFrameSize){
            col = colFrameSize;
            row++;
        }
    }
}

void Morphology::loadStruct(ifstream& infile) {
    int row = 0, col = 0;
    while(infile >> structElem[row][col]){
        col++;
        if(col >= numColsStrctElem){
            col = 0;
            row++;
        }
    }
}

void Morphology::computeFrame(){
    rowFrameSize = numRowsStrctElem;
    colFrameSize = numColsStrctElem;
}

void Morphology::zeroFrame(int** array) {
    for(int r=0; r<rowFrameSize; r++){ //half is different if rowFrameSize is even or odd
        for(int c=0; c<colFrameSize; c++){
            array[r][c] = 0;
            array[numRowsImg+rowFrameSize+r][numColsImg+colFrameSize+c] = 0;
        }
    }
}

void Morphology::zeroMorphAry(){
    for(int i=0; i < numRowsImg + rowFrameSize; i++){
        for(int j=0; j< numColsImg + colFrameSize; j++){
            morphAry[i][j] = 0;
        }
    }
}

void Morphology::loadStructNeighbors(int row, int col, int** source){
    int r = 0, c;
    for(int i=row-rowOrigin; i < numRowsStrctElem; i++){
        c=0;
        for(int j=col-colOrigin; j < numColsStrctElem; j++){
            neighborAry[r][c] = source[i][j];
            c++;
        }
        r++;
    }
}

void Morphology::dilation(int row, int col, int** sourceAry){
    if(sourceAry[row][col] != 0){
        int r=0, c;
        for(int i=row - rowOrigin; i < row-rowOrigin+numRowsStrctElem; i++){
            c=0;
            for(int j=col-colOrigin; j < col-colOrigin+numColsStrctElem; j++){
                if(structElem[r][c] != 0){
                    morphAry[i][j] = 1;
                }
                c++;
            }
            r++;
        }
    }
}

void Morphology::erosion(int row, int col, int** sourceAry){
    if(sourceAry[row][col] != 0) {
        loadStructNeighbors(row, col, sourceAry);
        for (int i = 0; i < numRowsStrctElem; i++)
            for (int j = 0; j < numColsStrctElem; j++) {
                if(structElem[i][j] != 0) {
                    if (sourceAry[row - rowOrigin + i][col - colOrigin + j] != structElem[i][j]) {
                        morphAry[row][col] = 0;
                        return;
                    }
                }
            }
        morphAry[row][col] = 1;
    }
}

void Morphology::closing(){
    for(int i=rowFrameSize; i < numRowsImg + rowFrameSize; i++){
        for(int j=colFrameSize; j < numColsImg + colFrameSize; j++){
            dilation(i,j,imgAry);
        }
    }
    int** tempAry = new int*[numRowsImg + 2*rowFrameSize];
    for(int i=0; i < numRowsImg + 2*rowFrameSize; i++){
        tempAry[i] = new int[numColsImg + 2*colFrameSize];
    }

    for(int i=0; i < numRowsImg + 2*rowFrameSize; i++){
        for(int j=0; j < numColsImg + 2*colFrameSize; j++){
            tempAry[i][j] = morphAry[i][j];
        }
    }

    zeroMorphAry();
    zeroFrame(tempAry);

    for(int i=rowFrameSize; i < numRowsImg + rowFrameSize; i++){
        for(int j=colFrameSize; j < numColsImg + colFrameSize; j++){
            erosion(i,j,tempAry);
        }
    }

    delete[] tempAry;
}

void Morphology::opening(){
    for(int i=rowFrameSize; i < numRowsImg + rowFrameSize; i++){
        for(int j=colFrameSize; j < numColsImg + colFrameSize; j++){
            erosion(i,j,imgAry);
        }
    }

    int** tempAry = new int*[numRowsImg + 2*rowFrameSize];
    for(int i=0; i < numRowsImg + 2*rowFrameSize; i++){
        tempAry[i] = new int[numColsImg + 2*colFrameSize];
    }

    for(int i=0; i < numRowsImg + 2*rowFrameSize; i++){
        for(int j=0; j < numColsImg + 2*colFrameSize; j++){
            tempAry[i][j] = morphAry[i][j];
        }
    }

    zeroMorphAry();
    zeroFrame(tempAry);

    for(int i=rowFrameSize; i < numRowsImg + rowFrameSize; i++){
        for(int j=colFrameSize; j < numColsImg + colFrameSize; j++){
            dilation(i,j,tempAry);
        }
    }

    delete[] tempAry;
}

void Morphology::prettyPrint(){
    for(int r = rowFrameSize; r < numRowsImg; r++){
        for(int c = colFrameSize; c < numColsImg; c++){
            if(morphAry[r][c] == 0) cout << "  ";
            else cout << morphAry[r][c] << ' ';
        }
        cout << endl;
    }
}

void Morphology::outputResult(ofstream &outfile) {
    outfile << numRowsImg << ' ' << numColsImg << ' ' << minValImg << ' ' << maxValImg;
    for(int r = rowFrameSize; r < numRowsImg + rowFrameSize; r++){
        outfile << endl;
        for (int c = colFrameSize; c < numColsImg + colFrameSize; c++){
            outfile << morphAry[r][c] << ' ';
        }
    }
}


void Morphology::runAlgorithm(ofstream& outfile, char* operation){
    prettyPrint();

    cout << endl << endl << "Modified Image:\n\n";

    if(strcmp(operation,"dilation") == 0){
        for(int i=rowFrameSize; i<numRowsImg+rowFrameSize; i++){
            for(int j=colFrameSize; j<numColsImg+colFrameSize; j++){
                dilation(i,j,imgAry);
            }
        }
    }

    else if(strcmp(operation,"erosion") == 0){
        for(int i=rowFrameSize; i<numRowsImg+rowFrameSize; i++){
            for(int j=colFrameSize; j<numColsImg+colFrameSize; j++){
                erosion(i,j,imgAry);
            }
        }
    }

    else if(strcmp(operation,"closing") == 0){
        closing();
    }

    else if(strcmp(operation,"opening") == 0){
        opening();
    }

    else{
        cerr << "Error: Invalid operation specified. Aborting...\n";
        exit(1);
    }

    prettyPrint();
    outputResult(outfile);
}

int main(int argc, char** argv) {
    if(argc != 5){
        cout << "Usage: " << argv[0] << " image_file struct_elem_file output_file operation\n";
        return 0;
    }

    ifstream imagefile; imagefile.open(argv[1]);
    ifstream structfile; structfile.open(argv[2]);
    ofstream outfile; outfile.open(argv[3]);

    Morphology m(imagefile,structfile);

    m.runAlgorithm(outfile, argv[4]);

    imagefile.close(); structfile.close(); outfile.close();

    return 0;
}