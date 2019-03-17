import java.util.Vector;

public class BALA {


    public static Customer[] customers;
    public static FloorClerk[] floorClerks;
    public static StorageClerk[] storageClerks;
    public volatile static boolean SisOpen = true;
    public volatile static boolean FisOpen = true;

    public volatile static Vector<Customer> customersThatNeedsTicket = new Vector<Customer>();
    public volatile static Vector<Customer> customersThatNeedsItem = new Vector<Customer>();
    public volatile static Vector<FloorClerk> availableFloorClerks = new Vector<FloorClerk>();
    public volatile static Vector<StorageClerk> availableStorageClerks = new Vector<StorageClerk>();
    public volatile static int helped =0;
    public static int nCustomer = 18;
    public static int nFloorClerk = 2;
    public static int nStoreageClerk = 4;
    public volatile static int bigItemCustomers =0;

    public static void printCusList(Vector<Customer> v) {
        System.out.println("customer list");
        for (int i = 0; i < v.size(); i++) {
            System.out.print(v.elementAt(i).customerID + ", ");
        }
        System.out.println();
    }

    public static void printFloList(Vector<FloorClerk> v) {

        System.out.println("floor clerk");
        for (int i = 0; i < v.size(); i++) {
            System.out.print(v.elementAt(i).fID + ", ");
        }
        System.out.println();
    }

    public static void printStoList(Vector<StorageClerk> v) {

        System.out.println("storage clerk");
        for (int i = 0; i < v.size(); i++) {
            System.out.print(v.elementAt(i).sID + ", ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        String arg;
        int nCustomer = 18;
        int nFloorClerk = 2;
        int nStoreageClerk = 4;
        int idx=0;
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


        customers = new Customer[nCustomer];
        floorClerks = new FloorClerk[nFloorClerk];
        storageClerks = new StorageClerk[nStoreageClerk];

        //initialze all customers
        for (int i = 0; i < nCustomer; i++) {
            Customer newCustomer = new Customer(i);
            customers[i] = newCustomer;
            customers[i].start();
        }
        //initialize all floor clerks
        for (int i = 0; i < nFloorClerk; i++) {
            FloorClerk newFloorClerk = new FloorClerk(i);
            floorClerks[i] = newFloorClerk;
            floorClerks[i].start();
        }
        //intialize all storgae clerks
        for (int i = 0; i < nStoreageClerk; i++) {
            StorageClerk newStorageClerk = new StorageClerk(i);
            storageClerks[i] = newStorageClerk;
            storageClerks[i].start();
        }


//        Vector<String> v = new Vector<String>();
//        System.out.println(v.size());
//        System.out.println(v.);
//
//        v.add("apple");
//
//        System.out.println(v.size());
//        System.out.println(v.);
    }//main

}//class
