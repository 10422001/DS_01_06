public class MultiThread1 extends Thread{


    @Override
    public void run() {
        System.out.println("Current Thread(in run) is running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new MultiThread1();
//        Thread t1 = new Thread();

        System.out.println("Current Thread is running: " + Thread.currentThread().getName());
        t1.start();
    }
}
