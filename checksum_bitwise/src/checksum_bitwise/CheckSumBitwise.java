package checksum_bitwise;

@SuppressWarnings("ALL")
public class CheckSumBitwise {

    public static int add(int num1, int num2) {

        while (num2 != 0) {

            int carry = num1 & num2;

            num1 = num1 ^ num2;

            num2 = carry << 1;
        }

        if (num1 > 255) {
            num1 = num1 - 256 + 1;
        }

        return num1;

    }

    //method when msg is character string
    public static String getCheckSum(String msg)
    {

        byte[] stringBytes = msg.getBytes(); // c= 10101010, o=1010100  

        int sum = 0;

        for (int i = 0; i < stringBytes.length; i++) {

            sum = add(sum, stringBytes[i]);

        }

        int checksum = (int) Math.pow(2, 8) - sum - 1;

//        System.out.println(checksum);

        return intToBinaryString(checksum);

    }

    public static String getCheckSum(int a){

        return intToBinaryString(a);
    }



    public static String intToBinaryString(int checksum) {

//        return Integer.toBinaryString(checksum);

        return String.format("%8s", Integer.toBinaryString(checksum)).replace(' ', '0');
    }

    /*
    public static String stringToBinaryString(String s) 
    {
        String temp = s;
        byte[] bytes = s.getBytes();

        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) 
        {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1); // check if first number is a 0 or a 1 if a 0 then 0 if a 1 then 1
                // val & 128 is bit AND operator
                val <<= 1; // a zero is added at the end
            }
        }
        return binary.toString();
    }
    */

    public static int  hasError(String data, String checksum) {

        //Checksum is in binary

        String newChecksum = getCheckSum(data);

        if (newChecksum.equals(checksum)){
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {


        //String msg = "hello world hello";

    }

    public static int getSeq() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class getCheckSum {

        public getCheckSum() {
        }
    }
}
