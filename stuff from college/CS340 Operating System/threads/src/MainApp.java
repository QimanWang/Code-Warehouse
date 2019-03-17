public class MainApp {
    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE+"Hello from the main thread");

        //create new thread
        Thread anotherThread = new AnotherThread();
        //give name to the thread
        anotherThread.setName(" == another thread == ");
        //enable jvm to run the thread, start will call run
        anotherThread.start();
        //u dont know which thread will run first,
        //we can influence the priority, but the os will schedule it.

        //create an anymonous thread subclass
        new Thread(){
            public void run(){
                System.out.println(ThreadColor.ANSI_GREEN+"hello from anonymous class thread");
            }
        }.start();


        //create a new thread from runnable object
        Thread myRunnableThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED+"hello from anonymous class of run()");
            }
        });
        //most use runnable
        myRunnableThread.start();


        System.out.println(ThreadColor.ANSI_PURPLE+"hello again from the main thread");
        //will cause exception.
        //anotherThread.start();




    }
}
