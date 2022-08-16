public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Thread name : "+Thread.currentThread());

        // create thread1 :
        // 2 ways of creating a thread1

        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();



        MyThread[] threads = {thread1, thread2};

//        thread1.setDaemon(true);
//        thread2.setDaemon(true);

        thread1.start();
//        thread1.start();
        thread2.start();

//        thread1.setPriority(1);
//        thread2.setPriority(1);




        // how wait function works :
        // Min(thread execution time, default time for which main have to wait)

        for(Thread ithThread : threads){
            System.out.println("Inside for loop : "+"Thread : "+Thread.currentThread()+" : time = "+System.currentTimeMillis());
            waitForMe(ithThread);
            System.out.println("Completed for loop : "+"Thread : "+Thread.currentThread()+" : time = "+System.currentTimeMillis());
        }
        System.out.println("Total Time : ");

//        Thread.sleep(10);

//        synchronized (thread1){
//            System.out.println("Thread waiting started : "+System.currentTimeMillis());
//            thread1.wait(50);
//
//            System.out.println("Thread waiting completed : "+System.currentTimeMillis());
//        }
        System.out.println("After starting a new thread1");
        System.out.println("Main Function Ends.");

        // start -> start0() -> run
    }

    private static void waitForMe(Thread thread) throws InterruptedException {
        synchronized (thread){
            thread.wait(50);
        }
    }

    private static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("Inside run function : thread : "+Thread.currentThread());
            System.out.println("Thread started :");
            int sum = 0;
            for(int i=0;i <10; i++){
                sum += i;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Sum : "+sum);
        }
    }
}