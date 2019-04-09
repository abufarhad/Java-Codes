public class OSLab_MutexLock {
    public static int counter = 0;
    public static void inc() {
        counter++;
    }
    public static void dec() {
        counter--;
    }
    public static boolean locked = false;
    public static void lock()
    {
        locked = true;
    }
    public static void unlock()
    {
        locked = false;
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (locked)
                {
                    //wait
                }
                lock();
                for (int i = 0; i <= 100000; i++)
                {
                    inc();
                }
                unlock();


            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (locked)
                {

                }
                lock();
                for (int i = 0; i <= 100000; i++) {
                    dec();
                }
                unlock();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("Val = " + counter);
        } catch (Exception e) {
        }
    }
}
