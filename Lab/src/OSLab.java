public class OSLab {
    public static int counter = 0;

    public static void inc() {
        counter++;
    }

    public static void dec() {
        counter--;
    }

    public static boolean[] flag = {false, false};
    public static int turn;

    public static void main(String[] args) {


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                flag[0] = true;
                turn = 1;

                while (flag[1] && turn == 1) {
                    // busy wait
                }
                for (int i = 0; i <= 100000; i++)
                    inc();
                flag[0] = false;
            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                flag[1] = true;
                turn = 0;
                while (flag[0] && turn == 0)
                {
                    // busy wait
                }
                for (int i = 0; i <= 100000; i++) {
                    dec();
                }
                flag[1] = false;

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
