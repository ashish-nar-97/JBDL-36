public class ThreadUsingRunnable extends Main implements Runnable{

    public static void main(String[] args) {
        System.out.println("Inside main function : "+Thread.currentThread());

        ThreadUsingRunnable threadUsingRunnable = new ThreadUsingRunnable();

        Thread thread = new Thread(threadUsingRunnable);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Inside run function : "+Thread.currentThread());
    }
}
