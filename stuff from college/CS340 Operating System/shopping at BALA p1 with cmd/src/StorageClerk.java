public class StorageClerk extends Thread {

    public volatile boolean comingBack = true;
    public volatile boolean isFree;
    public int sID;


    public StorageClerk(int id) {
        this.sID = id;
        isFree = true;
    }

    public static long time = System.currentTimeMillis();

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }//msg

    public void run() {
        enterStore();
    }


    public void enterStore() {

        msg("storage clerk " + sID + " arrives at the storage and starts working");

//        //enter available floor clerk list
//        BALA.availableFloorClerks.add(this);

        //while line have 0 customer, busy wait
        while (BALA.SisOpen) {
            if (!BALA.availableStorageClerks.contains(this)) {
                BALA.availableStorageClerks.add(this);
            }

            //busy wait there's no customer that needs help, and he's not next on the line to take customer
            while (BALA.availableStorageClerks.elementAt(0) != this) {
            }
            while (BALA.customersThatNeedsItem.isEmpty()) {
            }//busy wait

            //give item
            giveItem();


            //if all helped
            System.out.println("helped" +BALA.helped);
            System.out.println("BigItemCusstomers" + BALA.bigItemCustomers);
            if(BALA.helped == BALA.bigItemCustomers){
                BALA.FisOpen = false;
                sGoHome();
                System.out.println("everyone is helped.");
            }


        }///while store is open

    }//in the store

    public void giveItem() {

        BALA.printCusList(BALA.customersThatNeedsItem);
        BALA.printStoList(BALA.availableStorageClerks);


        Customer beingHelpedCustomer = BALA.customersThatNeedsItem.remove(0);

        //if customer is normal weight
        if (beingHelpedCustomer.trueWeight < 6) {
            BALA.availableStorageClerks.remove(0);
            msg("Floor clerk " + this.sID + " brings normal item to Customer " + beingHelpedCustomer.customerID);

            try {
                currentThread().sleep(((int) (Math.random() * (2)) + 1) * 1000);
            } catch (InterruptedException e) {
            }

            beingHelpedCustomer.needItem = false;
            BALA.availableStorageClerks.add(this);
        } else {
            //wait until there is 2 clerks that can help with heavy item
            while (BALA.availableStorageClerks.size() < 2) {
            }

            StorageClerk s1 = BALA.availableStorageClerks.remove(0);
            StorageClerk s2 = BALA.availableStorageClerks.remove(0);
            msg("Floor clerk " + s1.sID + " and " + s2.sID + " brings Heavy item to Customer " + beingHelpedCustomer.customerID);

            int workTime = (int) (Math.random() * (4)) + 1;
            s1.doingWork(workTime);
            s2.doingWork(workTime);

            beingHelpedCustomer.needItem = false;
            BALA.helped++;

            BALA.availableStorageClerks.add(s1);
            BALA.availableStorageClerks.add(s2);

            //have both next storage clerk help


        }//heavy heavy item

    }//give item

    public void doingWork(int sleepTime) {
        try {
            currentThread().sleep((sleepTime * 1000));
        } catch (InterruptedException e) {
        }
    }//doing work

    public void sGoHome(){
        for(int i = 0; i < BALA.storageClerks.length; i++) {
            BALA.storageClerks[i].interrupt();
            msg("Storage clerk "+BALA.storageClerks[i].sID + " is interrupted and exits the classroom.");
        }
    }


}//class
