import java.util.concurrent.Semaphore;

public class Supervisor extends Thread {
    public static long time = System.currentTimeMillis();
    public static Semaphore clerkArrive = new Semaphore(0);
    public static Semaphore handOutExam1 = new Semaphore(0);
    public static Semaphore handOutExam2 = new Semaphore(0);
    public static Semaphore handOutExam3 = new Semaphore(0);
    public static Semaphore timeToLeave = new Semaphore(0);

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }

    // Default constructor
    public Supervisor() {
        setName("SuperVisor");
    }

    public void run() {

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        msg("The SuperVisor has arrived.");

        while (clerkArrive.hasQueuedThreads())
            clerkArrive.release();

        exam1();

        exam2();

        exam3();

        try {
            timeToLeave.acquire();
            msg("The floor clerk leaves.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void exam1() {
        try {
            Customer.ticketAndPay.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < Customer.needTicket.size(); i++) {
            msg("The floor clerk gives " + Customer.needTicket.get(i).getName() + " exam 1.");
        }

        msg("Exam 1 has now begun...");
        msg("The floor clerk is sleeping throughout the exam.");

        while (handOutExam1.hasQueuedThreads())
            handOutExam1.release();

        try {
            sleep(800);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msg("Exam 1 is now over.");

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (Customer.ticketAndPay.hasQueuedThreads())
            Customer.ticketAndPay.release();

        while (Customer.getFromStorage.hasQueuedThreads())
            Customer.getFromStorage.release();

    }

    public void exam2() {
        try {
            Customer.studentsEnteredLightStorage.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < Customer.needLightItem.size(); i++) {
            msg("The floor clerk gives " + Customer.needLightItem.get(i).getName() + " exam 2.");
        }

        msg("Exam 2 has now begun...");
        msg("The floor clerk is sleeping throughout the exam.");

        while (handOutExam2.hasQueuedThreads())
            handOutExam2.release();

        try {
            sleep(800);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msg("Exam 2 is now over.");

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (Customer.getFromStorage.hasQueuedThreads())
            Customer.getFromStorage.release();

        while (Customer.need3StorageCLerkKelp.hasQueuedThreads())
            Customer.need3StorageCLerkKelp.release();
    }

    public void exam3() {
        try {
            Customer.studentsEnteredHeavyStorage.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < Customer.needHeavyItem.size(); i++) {
            msg("The floor clerk gives " + Customer.needHeavyItem.get(i).getName() + " exam 3.");
        }

        msg("Exam 3 has now begun...");
        msg("The floor clerk is sleeping throughout the exam.");

        while (handOutExam3.hasQueuedThreads())
            handOutExam3.release();

        try {
            sleep(800);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msg("Exam 3 is now over. \n");

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (Customer.need3StorageCLerkKelp.hasQueuedThreads())
            Customer.need3StorageCLerkKelp.release();

        while (Customer.displayGrades.hasQueuedThreads())
            Customer.displayGrades.release();

    }

}
