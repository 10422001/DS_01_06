public class MultiThread2 implements Runnable {


    @Override
    public void run() {
        System.out.println("Current Thread(in run) is running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
//        Thread t1 = new MultiThread1();
//        Thread t1 = new Thread(new MultiThread2());
        Thread t1 = new MultiThread1();

        System.out.println("Current Thread is running: " + Thread.currentThread().getName());
        t1.start();
        Thread t2 = new Thread(new MultiThread2());
        t2.start();
    }}
