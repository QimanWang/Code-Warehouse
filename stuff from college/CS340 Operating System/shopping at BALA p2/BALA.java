
public class BALA {
    public final static int NUM_OF_STUDENTS = 10;
    public final static int NUM_OF_CLERKS = 2;

    public final static int ROOM_CAPACITY = 8;
    public final static int GROUP_SIZE = 3;
    public static FloorClerk[] floorClerks = new FloorClerk[NUM_OF_CLERKS];
    public static Customer[] customers = new Customer[NUM_OF_STUDENTS];

    public static void main(String args[]) {

        String arg;
        int nCustomer = 18;
        int nFloorClerk = 2;
        int nStoreageClerk = 4;
        int idx = 0;
        while (idx < args.length && args[idx].startsWith("-")) {
            arg = args[idx++];
//            System.out.println("arg: " + arg);
            if (arg.equals("-c")) {
//                System.out.println("-i detected");
                nCustomer = Integer.parseInt(args[idx++]);
//
            }//num customer

            else if (arg.equals("-f")) {
//
                nFloorClerk = Integer.parseInt(args[idx++]);
//
            }//num of floor clerks

            else if (arg.equals("-s")) {

                nStoreageClerk = Integer.parseInt(args[idx++]);

            }


        }//while to find args

        for (int i = 0; i < NUM_OF_STUDENTS; i++)
            customers[i] = new Customer(i + 1);

        for (int i = 0; i < NUM_OF_STUDENTS; i++)
            customers[i].start();

//        for (int i = 0; i < NUM_OF_CLERKS; i++)
        floorClerks[0].start();

        try {
            for (int i = 0; i < NUM_OF_STUDENTS; i++)
                customers[i].join();
            for (int i = 0; i < NUM_OF_CLERKS; i++)
                floorClerks[i].join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
