public class CheckSum {

    private static int add(int x, int y) {
        // Iterate till there is no carry


        while (y != 0) {
            // carry now contains common
            //set bits of x and y
            int carry = (x & y);


            // Sum of bits of x and y where at
            //least one of the bits is not set
            x = (x ^ y);


            // Carry is shifted by one so that adding
            // it to x gives the required sum
            y = (carry << 1);
        }

        if (x > 255)
            x = x - 255; //101011111

        return x;
    }


    public static String getCheckSum(String d) {


        byte p[] = d.getBytes(); //abcd p[0] = 8 bit binary of a
        int sum = 0;
        for (byte aP : p) { //for(int i = 0; i<p.lenght(); i++) p[i]
            sum = add(sum, aP); //1 2 3 4 ==> 1 3 6 10 //11110000 00001111


        }

        int comp = (int) Math.pow(2, 8) - sum - 1;
        return Integer.toBinaryString(comp);

    }
}
