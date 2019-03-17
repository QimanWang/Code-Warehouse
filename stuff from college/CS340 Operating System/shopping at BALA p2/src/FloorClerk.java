import java.util.concurrent.Semaphore;

public class FloorClerk extends Thread {

    public static Semaphore clerkArrive = new Semaphore(0);
    public static Semaphore handOutTciket = new Semaphore(0);


    public static long time = System.currentTimeMillis();
    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }

    // Default constructor
    public FloorClerk(int id) {
        setName("FloorClerk " + id);
    }

    public void run(){

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        msg("The floor clerk has arrived.");

        while (clerkArrive.hasQueuedThreads())
            clerkArrive.release();

       giveTicket();
        //give tickets

    }//run
    public void giveTicket(){

    }


}
