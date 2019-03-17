
public class BALA {
    public final static int NUM_OF_Customer = 10;
    public static int NUM_OF_FloorClerks = 2;
    public static int TICKET_LINE_CAPACITY;
    public final static int GROUP_SIZE = 3;
    public static Supervisor supervisor = new Supervisor();
    public static Customer[] customers = new Customer[NUM_OF_Customer];
    public static FloorClerk[] floorClerks = new FloorClerk[NUM_OF_FloorClerks];
    public static void main(String args[]) {
        TICKET_LINE_CAPACITY = NUM_OF_Customer;
        for (int i = 0; i < NUM_OF_Customer; i++)
            customers[i] = new Customer(i + 1);

        for (int i = 0; i < NUM_OF_Customer; i++)
            customers[i].start();


        for (int i = 0; i < NUM_OF_FloorClerks; i++)
            floorClerks[i] = new FloorClerk(i + 1);

        for (int i = 0; i < NUM_OF_FloorClerks; i++)
            floorClerks[i].start();



        supervisor.start();

        try {
            for (int i = 0; i < NUM_OF_Customer; i++)
                customers[i].join();
            supervisor.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
