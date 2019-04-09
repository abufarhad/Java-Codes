import java.util.LinkedList;
import java.util.Queue;

public class OSLab_Semaphore {
    public static int counter = 0;

    public static void inc() {
        counter++;
    }

    public static void dec() {
        counter--;
    }


    public static int s = 1;
    public static Queue<Thread> tq;

    public static void waitSemaphore()
    {
        s--;
        while (s<0)
        {
            Thread c = Thread.currentThread();
            tq.add(c);
            c.suspend();

        }
    }
    public static void signalSemaphore()
    {
        s++;
        if(s>0)
        {
            Thread t = tq.remove();
            t.resume();
        }
    }


    public static void main(String[] args) {

        tq = new LinkedList<Thread>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                waitSemaphore();
                for (int i = 0; i <= 100000; i++) {
                    inc();
                }
                signalSemaphore();

            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {


                waitSemaphore();
                for (int i = 0; i <= 100000; i++) {
                    dec();
                }
                signalSemaphore();

            }
        });

        t1.start();
        t2.start();

        tq = new LinkedList<Thread>();




        try {
            t1.join();
            t2.join();
            System.out.println("Val = " + counter);
        } catch (Exception e) {

        }
    }

}
