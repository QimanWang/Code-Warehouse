
public class AnotherThread extends Thread {
    //create new thread
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_BLUE + "hello from "+ currentThread().getName());

        //sleep, will wake up later, can be interrupted
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println(ThreadColor.ANSI_BLUE + " Anotehr thread woke me up");
        }
        System.out.println(ThreadColor.ANSI_BLUE + " 3 sec passed and now i'ms awake.");
    }
}
