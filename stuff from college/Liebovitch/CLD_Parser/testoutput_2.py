#testoutput_1.py
#test to create data file

import math
# import matplotlib.pyplot as plt
# from scipy import stats
import numpy as np
# import random
# import time
import os


def filein (fname): #needed to open and read in a file
    numlines=0
    xin=[]
    f=open(fname,'r')
    for line in f:
        #print (line, end='')
        xin.append(line)
        numlines=numlines+1
    f.close()
    return xin,numlines


def getxnfirststring(fname):   #get n inputs from each line
    data,numlines=filein(fname)
#print (numlines)
#print (data)
    dataline=['0' for i in range(numlines)]
    for i in range(numlines):
        x=data[i]
        y=x.split('\t')
        y[-1]=y[-1].replace('\n','')
        dataline[i]=y
    #print ('\n\nascii-input',dataline)
    xdata=dataline[:]
    
    for i in range (numlines):
        inline=len(dataline[i])
        for j in range (1,inline):
            if xdata[i][j] != '':
                xdata[i][j]=eval(xdata[i][j])
            else:
                xdata[i][j]=None
    #print ('dataline',dataline)
    #print ('xdata',xdata)
    return xdata, numlines

def fileout (filename,filedata):
    f2=open(filename,'w')
    f2.write(filedata)
    f2.close()

#READ IN DATA
#----------------------------------------------------------------
#PART ONE
#read in c(i,j) and x_final from PROGRAM or here set in
a=[[0, 3, 2, -2, 1, 3, 3, -3, 3, 3, 2, 3, 2, 2, 1, 2, 2, 3, 2, 3, 2, 3, 1, 3],[-3,0,-2,2,-2,0,-2,2,0,-2,-1,-3,-1,-2,0,-2,0,0,0,-2,1,-2,0,-3],[2, -2, 0, -3, 0, 3, 2, -2, 1, 2, 2, 1, 2, 2, 0, 3, 2, 2, 2, 2, 2, 1, 1, 2],[-2,-2,3,0,0,0,-2,2,0,-2,-2,-2,-2,-1,0,-2,-2,0,-2,-2,0,-1,1,-3],[3, -3, 2, -2, 0, 1, 2, -2, 3, 2, 1, 2, 2, 2, 2, 2, 1, 3, 1, 2, 3, 2, 3, 2],[2, -2, 2, -2, 3, 0, 2, -2, 2, 2, 2, 3, 2, 3, 1, 3, 3, 2, 3, 2, 2, 2, 1, 3],[2, -2, 3, -3, 2, 3, 0, -2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 3, 3, 2, 1, 1, 1, 3],[-2,2,-3,3,-3,0,2,0,0,-1,-2,-2,-2,-1,-2,-2,-3,0,-3,-1,1,1,0,-3],[2, -2, 1, -1, 3, 1, 1, -1, 0, 1, 1, 1, 1, 1, 3, 1, 1, 1, 2, 1, 3, 1, 3, 1],[1, -1, 0, 0, 2, 1, 1, -1, 1, 0, 2, 2, 3, 3, 1, 2, 1, 0, 1, 1, 1, 0, 2, 3],[1, -1, 2, -2, 1, 0, 3, -3, 1, 2, 0, 1, 2, 3, 0, 1, 2, 0, 2, 2, 1, 0, 1, 3],[2, -2, 2, -2, 3, 1, 3, -3, 2, 2, 2, 0, 2, 2, 3, 3, 1, 1, 1, 1, 2, 0, 3, 1],[1, -1, 2, -2, 1, 2, 2, -2, 0, 1, 3, 1, 0, 3, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2],[2, -2, 2, -2, 1, 2, 2, -2, 0, 1, 2, 2, 3, 0, 1, 3, 0, 0, 0, 2, 2, 0, 2, 3],[2, -2, 0, 0, 3, 0, 0, 0, 0, 3, 0, 2, 0, 1, 0, 1, 2, 1, 2, 0, 3, 1, 2, 0],[1, -1, 1, -1, 1, 3, 2, -2, 0, 1, 2, 2, 2, 3, 1, 0, 2, 0, 2, 0, 1, 0, 2, 3],[0, 0, 0, 0, 0, 1, 1, -1, 1, 0, 0, 1, 1, 0, 2, 1, 0, 0, 3, 0, 2, 1, 0, 0],[2, -2, 0, 0, 1, 0, 2, -2, 1, 1, 1, 2, 0, 0, 2, 0, 0, 0, 1, 0, 1, 3, 3, 0],[1, -1, 0, 0, 1, 2, 1, -1, 0, 1, 1, 1, 1, 0, 2, 1, 0, 2, 0, 0, 1, 1, 1, 2],[2, -2, 1, -1, 2, 2, 1, -1, 2, 2, 2, 2, 2, 2, 0, 3, 2, 3, 3, 0, 1, 2, 1, 2],[3, -3, 1, -1, 3, 1, 1, -1, 3, 0, 1, 0, 0, 1, 3, 0, 2, 1, 2, 0, 0, 0, 1, 1],[1, -1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 3, 0, 2, 0, 0, 2, 0],[1, -1, 1, -1, 1, 0, 1, -1, 1, 3, 1, 1, 0, 1, 0, 0, 0, 3, 2, 1, 1, 2, 0, 1],[2, -2, 2, -2, 2, 1, 2, -2, 1, 3, 3, 3, 3, 3, 1, 0, 2, 1, 2, 1, 0, 0, 1, 0]]
b=[10 for i in range(24)]

#PART TWO
#read in bxy(x,y,h,w from pptx),bname(abbreviation), btext(full name)
filename=input('\n FILENAME')
data,numvar=getxnfirststring(filename)
print('\n numvar= ',numvar)
print('\n data= ',data)
#END READ IN DATA
#----------------------------------------------------------------

#COMPUTE needed [0,1] needed from PPTX
bx=[data[i][1] for i in range (numvar)]
by=[data[i][2] for i in range (numvar)]
wx=[data[i][4] for i in range (numvar)]
hy=[data[i][3] for i in range (numvar)]

#plot the variables
xp=['0' for i in range(numvar)]
yp=['0' for i in range(numvar)]
xp2=['0' for i in range(numvar)]
yp2=['0' for i in range(numvar)]
for i in range(numvar):
    xp[i]=(bx[i] + 0.5*wx[i])
    yp[i]=(by[i] + 0.5*hy[i])
maxx=max(xp)
maxy=max(yp)
for i in range(numvar):
    xp2[i]=0.9*xp[i]/maxx
    yp2[i]=.98-0.9*yp[i]/maxy

bxy=[[xp2[i],yp2[i]] for i in range(numvar)]
print ('\nbxy=  ',bxy)

#TRANSORM to STRINGS
astr=str(a)
bstr=str(b)
bxystr=str(bxy)
bnamestr=str([data[i][0] for i in range(numvar)])
btextstr=str([data[i][0] for i in range(numvar)])

#TEST: print to console
out=['0' for i in range(5)]
out[0]='#data'+ '\na = ' + astr
out[1]='\n\nb = ' + bstr
out[2]='\n\nbxy = ' + bxystr
out[3]='\n\nbname = ' + bnamestr
out[4]='\n\nbtext = ' + btextstr
output=out[0]+out[1]+out[2]+out[3]+out[4]
print ('\nOUTPUT\n',output)

savefile=input('\nSave to a file? (y/n Def.=n)')
if savefile=='y':
    fileoutput=input('\nfilename= ')
    if (os.path.isfile(fileoutput)):
        print ('\nALREADY EXISTS, CHOOSE ANOTHER NAME!')
    else:
        fileout (fileoutput,output)
