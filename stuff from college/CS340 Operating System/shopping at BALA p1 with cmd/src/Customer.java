public class Customer extends Thread {


    public int customerID;
    public volatile boolean needHelp;
    public volatile boolean needItem;
    public volatile int weight;
    public volatile int trueWeight;

    public Customer(int id) {
        this.customerID = id;
        needHelp = false;
        needItem = false;
    }

    public static long time = System.currentTimeMillis();


    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }

    public void run() {
        goToStore();
    }

    public void goToStore() {

        msg("Customer " + customerID + " has just arrived at the store");

        //waits until find something wants to buy
        lookAround();

        //wants to buy
        getTicketFromClerk();

        //busy wait until gets ticket
        while (needHelp) {
        }

        //once got ticket, now go pay
        payForTicket();

        //if light item, can leave, if heavy pick up at storage.
        if (weight > 6) {
            BALA.bigItemCustomers ++;
            takeBreak();
            goToStorage();

            //busy waits till customer gets item
            while(needItem){
            }

        }

//        joinCustomers();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }//go to Store

    //sleep for rand time, wakes up and interact with store clerk
    public void lookAround() {

        try {
            currentThread().sleep(((int) (Math.random() * (10)) + 1) * 1000);
        } catch (InterruptedException e) {
        }
        msg("Customer " + customerID + " found something to buy");
    }

    //customer is interested in something, add them to the ticket list
    public void getTicketFromClerk() {

        BALA.customersThatNeedsTicket.add(this);
        msg("Customer " + customerID + " got on line for ticket");
        needHelp = true;
    }

    public void payForTicket() {
        msg("Customer " + customerID + " now wants to pay");
        int p = currentThread().getPriority();
        currentThread().setPriority(p + 5);

        try {
            currentThread().sleep(((int) (Math.random() * (3)) + 1) * 1000);
        } catch (InterruptedException e) {
        }

        this.weight = (int) (Math.random() * (10)) + 1;
        msg("Customer " + customerID + " paid for item with weight "+ weight);
        currentThread().setPriority(NORM_PRIORITY);

    }//pay for ticket

    public void takeBreak() {
        yield();
        yield();

        try {
            currentThread().sleep(((int) (Math.random() * (5)) + 1) * 1000);
        } catch (InterruptedException e) {
        }
        msg("Customer " + customerID + " is taking a break");
    }//take break

    public void goToStorage(){
        BALA.customersThatNeedsItem.add(this);
        needItem = true;
        trueWeight = (int) (Math.random() * (10)) + 1;
    }
}//class
