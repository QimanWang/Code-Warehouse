public class FloorClerk extends Thread {

    public int fID;
    public volatile boolean isBusy;
    public static boolean fCanGoHome = false;

    public FloorClerk(int id) {
        this.fID = id;
        isBusy = false;
    }

    public static long time = System.currentTimeMillis();

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }//msg

    public void run() {
        enterStore();
    }


    public void enterStore() {


        msg("Floor clerk " + fID + " arrives at the store and starts working");

//        //enter available floor clerk list
//        BALA.availableFloorClerks.add(this);

        //while line have 0 customer, busy wait
        while (BALA.FisOpen) {
            if (!BALA.availableFloorClerks.contains(this)) {
                BALA.availableFloorClerks.add(this);
            }

            //busy wait there's no customer that needs help, and he's not next on the line to take customer
            while (BALA.availableFloorClerks.elementAt(0) != this) {
            }
            while (BALA.customersThatNeedsTicket.isEmpty()) {
            }//busy wait

            giveTicket();
            BALA.availableFloorClerks.add(this);


        }///while store is open
        fGoHome();
    }//in the store

    public void giveTicket() {

        //print the list
//        BALA.printCusList(BALA.customersThatNeedsTicket);
//        BALA.printFloList(BALA.availableFloorClerks);


        Customer beingHelpedCustomer = BALA.customersThatNeedsTicket.remove(0);
        BALA.availableFloorClerks.remove(0);
        msg("Floor clerk " + this.fID + " gave ticket to Customer " + beingHelpedCustomer.customerID);

        try {
            currentThread().sleep(((int) (Math.random() * (5)) + 1) * 1000);
        } catch (InterruptedException e) {
        }


        beingHelpedCustomer.needHelp = false;

    }//give ticket

    public void fGoHome() {
        for (int i = 0; i < BALA.floorClerks.length; i++) {
            BALA.floorClerks[i].interrupt();
            msg("Floor clerk " + BALA.floorClerks[i].fID + " is interrupted and exits the store.");
        }
    }
//
}//class floor clerk
