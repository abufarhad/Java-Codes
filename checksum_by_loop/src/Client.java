
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {

    public static int carry = 0;

    public static char Add(char a, char b) {
        int x = a - '0';
        int y = b - '0';
        int c = x + y + carry;
        carry = c / 2;
        int ans = c % 2;
        return (char) (ans + '0');
    }

    public static String findCheckSum(String in) {
        char[] s = in.toCharArray();
        char[] b = {'0', '0', '0', '0'};

        for (int i = s.length - 1; i >= 0; i -= 4) {
            for (int j = 3, k = 0; j >= 0; j--, k++) {
                b[j] = Add(b[j], s[i - k]);
            }
        }
        String check_sum = "";
        for (int i = 0; i < 4; i++) {
            if (b[i] == '0') {
                check_sum = check_sum + "1";
            } else {
                check_sum = check_sum + "0";
            }
        }

        return check_sum;
    }

    public static boolean CheckSumChecker(String frame) {
        char[] s = frame.toCharArray();
        char[] b = {'0', '0', '0', '0'};

        for (int i = s.length - 1; i >= 0; i -= 4) {
            for (int j = 3, k = 0; j >= 0; j--, k++) {
                b[j] = Add(b[j], s[i - k]);
            }
        }
        for (int i = 0; i < 4; i++) {
            
            if (b[i] == '0') {
                return false;  
            }
        }
        return true;
    }

    public static String ConvertBinary(String s) {
        char[] c = s.toCharArray();
        String x = "";
        int a;
        for (int i = 0; i < c.length; i++) {
            a = (int) c[i];
            x += String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0');
        }
        return x;
    }

    public static String ConvertIntToBinary(int a) {
        return String.format("%4s", Integer.toBinaryString(a)).replace(' ', '0');
    }

    public static String DecodeMessage(String s) {
        String x = "", ans = "";
        char[] m = s.toCharArray();
        for (int i = 0; i < m.length; i += 8) {
            //Dividing into 8 bits sequence
            for (int j = 0; j < 8; j++) {
                x += m[i + j];
            }
            //deconding it into character
            int a = Integer.parseInt(x, 2);
            ans += (char) a;
            x = "";
        }
        return ans;
    }

    public static char Cipher(char Text) {
        if (Text >= 'A' && Text <= 'Z') {
            int ascii = (int) Text;
            ascii += 10; //key = 10, shifting 10 right
            ascii = ascii % 91;
            if (ascii < 65) {
                ascii += 65;
            }
            return (char) (ascii);
            //CipherText += (char)(ascii);
        } else if (Text >= 'a' && Text <= 'z') {
            int ascii = (int) Text;
            ascii += 10; //key = 10, shifting 10 right
            ascii = ascii % 123;
            if (ascii < 97) {
                ascii += 97;
            }
            return (char) (ascii);
        }
        return Text;
    }

    public static char Decipher(char CipherText) {
        int ascii;
        if (CipherText >= 'A' && CipherText <= 'Z') {
            ascii = (int) CipherText;
            ascii -= 10; //key = 10, shifting 10 left
            ascii = ascii % 90;
            if (ascii < 65) {
                ascii += 26;
            }
            return (char) (ascii);
        } else if (CipherText >= 'a' && CipherText <= 'z') {
            ascii = (int) CipherText;
            ascii -= 10; //key = 10, shifting 10 right
            ascii = ascii % 122;
            if (ascii < 97) {
                ascii += 26;
            }
            return (char) (ascii);
        }
        return CipherText;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Open your connection to a server, at port 1254
        Socket s1 = new Socket("localhost", 1254);
        System.out.println("Connected to Server");

        //Get an input file handle from the socket and read the input
        ObjectInputStream dis = new ObjectInputStream(s1.getInputStream());

        //Get a communication stream associated with the socket
        ObjectOutputStream dos = new ObjectOutputStream(s1.getOutputStream());

        // Send
        Frame frame, data;
        //stop and wait
        int acknowledgeNo = 0, loop = 1, SIZE = 0;
        String CipherText = "", PlainText = "";
        while (true) {
            //receive a string
            data = (Frame) dis.readObject();

            if (data.seq == 0 && data.ack == 0) {
                break;
            }
            System.out.println("\nReceived Data: " + DecodeMessage(data.data));
            System.out.println("Comp Data: "+Decipher(DecodeMessage(data.data).charAt(0)));
            System.out.println("Sequence: " + data.seq);
            System.out.println("Acknowledgement: " + data.ack);
            System.out.println("Data: " + data.data);
            System.out.println("CheckSum Bit: " + data.checkSum);
            CipherText  += DecodeMessage(data.data);
            PlainText += Decipher(DecodeMessage(data.data).charAt(0));
            if (CheckSumChecker(ConvertIntToBinary(data.seq) + ConvertIntToBinary(data.ack) + data.data + data.checkSum) == true) {
                System.out.println("No Error\n");
            } else {
                System.out.println("Error\n");
                continue;
            }

            if (acknowledgeNo != data.seq) {
                acknowledgeNo = data.seq;
            } else {
                continue;
            }

            frame = new Frame();
            frame.seq = 0; 
            frame.ack = acknowledgeNo;
            frame.data = null;
            frame.checkSum = findCheckSum(ConvertIntToBinary(frame.ack));
            dos.writeObject(frame);

            System.out.println("\nSent Sequence: " + frame.seq);
            System.out.println("Acknowledgement: " + frame.ack);
            System.out.println("Sent Data: " + frame.data);
            System.out.println("CheckSum Bit: " + frame.checkSum);
        }
        System.out.println("\nMain Data  Text: "+CipherText);
        //System.out.println("PlainText: "+PlainText);
        dis.close();
        s1.close();
        dos.close();
    }
}
