import java.io.IOException;

public class Main {

    public static boolean allowAccess = false;
    public static void main(String[] args) throws IOException {


        //create login gui
        LoginView lv = new LoginView();
        Thread t= new Thread();
        while (!lv.loggedIn) {
            try {
                t.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println(lv.loggedIn);
        }//while not logged in, stuck here

        //user has logged in
        System.out.println("welcome " + lv.un);



    }
}
