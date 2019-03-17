import javax.swing.*;

//this code takes the idea from
//sun Microsystems 1995-1997
public class ParseCmdLine {

    int i = 0;
    int j = 0;


    String arg;
    String inputfile = "";
    String outputfile = "";
    boolean flaglog = false;


    //this runs through the input argument list and
    //find the commands that are inputted.
    public void Pmain(String[] args) {
//        System.out.println("args pointer:"+args);
        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
//            System.out.println("arg: " + arg);
            if (arg.equals("-i")) {
//                System.out.println("-i detected");
                inputfile = args[i++];
                if (inputfile.endsWith(".txt")) {
                    System.out.println("input file: "+inputfile);
                }
                else{
//                    SwingControlDemo  swingControlDemo = new SwingControlDemo();
//                    swingControlDemo.showFileChooserDemo();
                    JFileChooser chooser=new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.showSaveDialog(null);

                    String path=chooser.getSelectedFile().getAbsolutePath();
                    String filename=chooser.getSelectedFile().getName();

                    System.out.println("user selected:" + filename);
                    inputfile = filename;
                }
//                System.out.println("input file: " + inputfile);
            }//inputfile

            else if (arg.equals("-o")) {
//                System.out.println("-o detected");
                outputfile = args[i++];
//                System.out.println("output file: " + outputfile);
            }//output filee

            else if (arg.equals("-l")) {
                flaglog = true;
//                System.out.println("flag log turned on");
            }


            }//while to find args
    }//main
}//class
