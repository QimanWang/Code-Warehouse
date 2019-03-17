import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Customer extends Thread {

   public static long time = System.currentTimeMillis();
   public static Semaphore mutex = new Semaphore(1);
   public static Semaphore ticketAndPay = new Semaphore(0);
   public static Semaphore getFromStorage = new Semaphore(0);
   public static Semaphore need3StorageCLerkKelp = new Semaphore(0);
   public static Semaphore capacity = new Semaphore(0);
   public static Semaphore studentsEnteredLightStorage = new Semaphore(0);
   public static Semaphore studentsEnteredHeavyStorage = new Semaphore(0);
   public static Semaphore displayGrades = new Semaphore(0);
   public static Semaphore groupTime = new Semaphore(0);
   public static Semaphore group = new Semaphore(0);
   public static int grouped = 0;
   public static int ticketLineCapacity = 0;
   public static int lightStorageCapacity = 0;
   public static int heavyStorageCapacity = 0;
   public static int counter = 0;
   public volatile static Vector<Customer> needTicket = new Vector<Customer>(BALA.TICKET_LINE_CAPACITY);
   public volatile static Vector<Customer> needLightItem = new Vector<Customer>(BALA.TICKET_LINE_CAPACITY);
   public volatile static Vector<Customer> needHeavyItem = new Vector<Customer>(BALA.TICKET_LINE_CAPACITY);
   public volatile static Vector<Customer> customerGroup = new Vector<Customer>(BALA.NUM_OF_Customer);

   public void msg(String m) {
      System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
   }

   // Default constructor
   public Customer(int id) {
      setName("Customer " + id);
   }

   public void run() {

      msg("A student has just arrived and is looking around");

      try {
         Thread.sleep((long) (Math.random() * 1000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      try {
         Supervisor.clerkArrive.acquire();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      getTicketFromFloorCLerkAndPay();

      try {
         Thread.sleep((long) (Math.random() * 1000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      try {
         getFromStorage.acquire();
      } catch (InterruptedException e2) {
         // TODO Auto-generated catch block
         e2.printStackTrace();
      }

      exam2();

      try {
         Thread.sleep((long) (Math.random() * 1000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      try {
         need3StorageCLerkKelp.acquire();
      } catch (InterruptedException e2) {
         // TODO Auto-generated catch block
         e2.printStackTrace();
      }

      exam3();

      try {
         Thread.sleep((long) (Math.random() * 1000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      try {
         displayGrades.acquire();
      } catch (InterruptedException e2) {
         // TODO Auto-generated catch block
         e2.printStackTrace();
      }

      displayGrades();

      group();

   }

   public void getTicketFromFloorCLerkAndPay() {
      try {
         mutex.acquire();
         ticketLineCapacity++;
         if (ticketLineCapacity <= BALA.NUM_OF_Customer) {
            needTicket.add(this);
            msg(getName() + " enters the classroom for exam 1.");
         }
         if (ticketLineCapacity == BALA.TICKET_LINE_CAPACITY) {
            mutex.release();
            if (capacity.getQueueLength() > BALA.TICKET_LINE_CAPACITY - 2)
               capacity.release();
         } else {
            mutex.release();
            capacity.acquire();
         }
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needTicket.size() == BALA.TICKET_LINE_CAPACITY)
         ticketAndPay.release();

      try {
         Supervisor.handOutExam1.acquire();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needTicket.contains(this))
         msg(getName() + " starts taking exam 1.");

      try {
         ticketAndPay.acquire();
      } catch (InterruptedException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      capacity.release();

   }

   public void exam2() {

      try {
         mutex.acquire();
         lightStorageCapacity++;
         if (lightStorageCapacity <= BALA.TICKET_LINE_CAPACITY) {
            needLightItem.add(this);
            msg(getName() + " enters the classroom for exam 2.");
         }
         if (lightStorageCapacity == BALA.TICKET_LINE_CAPACITY) {
            mutex.release();
            if (capacity.getQueueLength() > BALA.TICKET_LINE_CAPACITY - 2)
               capacity.release();
         } else {
            mutex.release();
            capacity.acquire();
         }
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needLightItem.size() == BALA.TICKET_LINE_CAPACITY)
         studentsEnteredLightStorage.release();

      try {
         Supervisor.handOutExam2.acquire();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needLightItem.contains(this))
         msg(getName() + " starts taking exam 2.");

      try {
         getFromStorage.acquire();
      } catch (InterruptedException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      capacity.release();

      if (needTicket.contains(this) && needLightItem.contains(this)) {
         try {
            displayGrades.acquire();
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

   }

   public void exam3() {

      try {
         mutex.acquire();
         heavyStorageCapacity++;
         if (heavyStorageCapacity <= BALA.TICKET_LINE_CAPACITY) {
            needHeavyItem.add(this);
            msg(getName() + " enters the classroom for exam 3.");
         }
         if (heavyStorageCapacity == BALA.TICKET_LINE_CAPACITY) {
            mutex.release();
            if (capacity.getQueueLength() > BALA.TICKET_LINE_CAPACITY - 2)
               capacity.release();
         } else {
            mutex.release();
            capacity.acquire();
         }
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needHeavyItem.size() == BALA.TICKET_LINE_CAPACITY)
         studentsEnteredHeavyStorage.release();

      try {
         Supervisor.handOutExam3.acquire();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (needHeavyItem.contains(this))
         msg(getName() + " starts taking exam 3.");

      try {
         need3StorageCLerkKelp.acquire();
      } catch (InterruptedException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      capacity.release();

   }

   public void displayGrades() {

      try {
         Customer.mutex.acquire();
         counter++;
         if (Customer.needTicket.contains(this))
            msg(getName() + " recieved a " + ((int) (Math.random() * (90)) + 10) + " on Exam 1.");
         else
            msg(getName() + " recieved a 0 on Exam 1.");

         if (Customer.needLightItem.contains(this))
            msg(getName() + " recieved a " + ((int) (Math.random() * (90)) + 10) + " on Exam 2.");
         else
            msg(getName() + " recieved a 0 on Exam 2.");

         if (Customer.needHeavyItem.contains(this))
            msg(getName() + " recieved a " + ((int) (Math.random() * (90)) + 10) + " on Exam 3. \n");
         else
            msg(getName() + " recieved a 0 on Exam 3. \n");
         Customer.mutex.release();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (counter == BALA.NUM_OF_Customer) {
         while (groupTime.hasQueuedThreads())
            groupTime.release();
      }

   }

   public void group() {

      try {
         groupTime.acquire();
      } catch (InterruptedException e2) {
         // TODO Auto-generated catch block
         e2.printStackTrace();
      }

      try {
         mutex.acquire();
         grouped++;
         customerGroup.add(this);
         if (grouped % BALA.GROUP_SIZE != 0) {
            if (grouped > 13) {
               msg(getName() + " and " + customerGroup.get(customerGroup.indexOf(this) - 1).getName()
                     + " leave together.");
               mutex.release();
               while (group.hasQueuedThreads())
                  group.release();
            }
            mutex.release();
            group.acquire();
         } else {
            msg(getName() + ", " + customerGroup.get(customerGroup.indexOf(this) - 1).getName() + ", and "
                  + customerGroup.get(customerGroup.indexOf(this) - 2).getName() + " leave together.");
            mutex.release();
            group.release();
         }

      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (group.getQueueLength() == 0)
         Supervisor.timeToLeave.release();

   }
}
