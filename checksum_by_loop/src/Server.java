
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Server {
    
    public static int carry = 0;
    public static char Add(char a, char b){
        int x = a - '0';
        int y = b - '0';
        int c = x + y + carry;
        carry = c / 2;
        int ans = c % 2;
        return (char) (ans + '0');
    }
    public static String findCheckSum(String in){
        char[] s = in.toCharArray();
        char[] b = {'0', '0', '0', '0'};

        for(int i = s.length - 1; i >= 0; i -= 4){
            for(int j = 3, k = 0; j >=0; j--, k++){
                b[j] = Add(b[j], s[i-k]); 
            }
        }

        String check_sum = "";
        for(int i=0; i<4; i++){
            if(b[i] == '0'){
                check_sum = check_sum + "1";
            }
            else{
                check_sum = check_sum + "0";
            }
        }

        return check_sum;
    }

    public static boolean CheckSumChecker(String frame){
        char[] s = frame.toCharArray();
        char[] b = {'0', '0', '0', '0'};

        for(int i = s.length - 1; i >= 0; i -= 4){
            for(int j = 3, k = 0; j >=0; j--, k++){
                b[j] = Add(b[j], s[i-k]);
            }
        }

        for(int i=0; i < 4; i++){
            //System.out.println("b "+b[i]);
            if(b[i] == '0'){
             return false;  
            }
        }
        return true;
    }


    
    public static String ConvertBinary(String s){
        char[] c = s.toCharArray();
        String x = "";
        int a;
        for(int i = 0; i < c.length; i++){
            a = (int)c[i];
            x += String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0');
        }
        return x;
    }
    
    public static String ConvertIntToBinary(int a){
        return String.format("%4s", Integer.toBinaryString(a)).replace(' ', '0');
    }
    
    public static String DecodeMessage(String s){
        String x = "", ans = "";
        char[] m = s.toCharArray();
        for(int i = 0; i < m.length; i+=8){
            //Dividing into 8 bits sequence
            for(int j = 0; j < 8; j++){
                x += m[i+j];
            }
            //deconding it into character
            int a = Integer.parseInt(x, 2);
            ans += (char)a;
            x = "";
        }
        return ans;
    }
    
    public static char Cipher(char Text){
        if(Text >= 'A' && Text <= 'Z'){
            int ascii = (int)Text;
            ascii += 10; //key = 10, shifting 10 right
            ascii = ascii % 91;
            if(ascii < 65){
                ascii += 65;
            }
            return (char)(ascii);
            //CipherText += (char)(ascii);
        } else if(Text >= 'a' && Text <= 'z'){
            int ascii = (int)Text;
            ascii += 10; //key = 10, shifting 10 right
            ascii = ascii % 123;
            if(ascii < 97){
                ascii += 97;
            }
            return (char)(ascii);
        } 
            return Text;
    }
    
    public static char Decipher(char CipherText){
        int ascii;
            if(CipherText >= 'A' && CipherText <= 'Z'){
                ascii = (int)CipherText;
                ascii -= 10; //key = 10, shifting 10 left
                ascii = ascii % 90;
                if(ascii < 65){
                    ascii += 26;
                }
                return (char)(ascii);
            } else if(CipherText >= 'a' && CipherText <= 'z'){
                ascii = (int)CipherText;
                ascii -= 10; //key = 10, shifting 10 right
                ascii = ascii % 122;
                if(ascii < 97){
                    ascii += 26;
                }
                return (char)(ascii);
            }
        return CipherText;
    }
    
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket s = new ServerSocket(1254);
        System.out.println("Server -> input messege ..\n");
        Socket s2 = s.accept(); //Wait and accept a connection
        s2.setSoTimeout(1000); //time to wait for acknowledgement

        //Get a communication stream associated with the socket
        ObjectOutputStream dos = new ObjectOutputStream(s2.getOutputStream());

        //Get an input file handle from the socket and read the input
        ObjectInputStream dis = new ObjectInputStream(s2.getInputStream());
        String decision;
        //stop and wait
        int seqNo = 0, acknowledgeNo = 0, loop = 1;
        
        Frame frame, data;
        
        while (loop-- != 0) {
            // Send
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input Here: ");
            String input = scanner.nextLine();
            int SIZE = input.length();
            
            while(SIZE != 0){
                seqNo++;
                frame = new Frame();
                frame.seq = seqNo;
                frame.ack = 0;
                frame.data = ConvertIntToBinary((int)(Cipher(input.charAt(seqNo-1)))); 
                frame.data = String.format("%8s", frame.data).replace(' ', '0');
                frame.checkSum = findCheckSum(ConvertIntToBinary(frame.seq)+ConvertIntToBinary(frame.ack)+frame.data);
               
                System.out.println("-------Data----------");
                System.out.println("Data: "+input.charAt(seqNo-1));
                System.out.println("Comp Data: "+Cipher(input.charAt(seqNo-1)));
                System.out.println("Sequence: "+frame.seq);
                System.out.println("Acknowledgement: "+frame.ack);
                System.out.println("Data: "+frame.data);
                System.out.println("CheckSum Bit: "+frame.checkSum);
                
                System.out.println("Do you want to Send this frame? Y/N(0 to break out of loop)");
                
                decision = scanner.nextLine();
                
                if(decision.equalsIgnoreCase("Y")){
                    dos.writeObject(frame);
                } else if(decision.equalsIgnoreCase("0")){
                    break;
                }
                try{
                    //receive a string
                    data = (Frame) dis.readObject();
                    System.out.println("\nReceived Sequence: "+data.seq);
                    System.out.println("Acknowledgement: "+data.ack);
                    System.out.println("Received Data: "+data.data);
                    System.out.println("CheckSum Bit: "+data.checkSum);

                    if(CheckSumChecker(ConvertIntToBinary(data.ack)+data.checkSum) == true){
                        System.out.println("No Error\n");
                    } else {
                        System.out.println("Error\n");
                    }
                } catch(SocketTimeoutException e){
                    System.out.println("No acknowledgement found. Resend? Press Y to resend, N to skip this frame, 0 to terminate");
                    decision = scanner.nextLine();
                    if(decision.equalsIgnoreCase("Y")){
                        dos.writeObject(frame);
                        System.out.println("Frame Sent");
                    } else if(decision.equalsIgnoreCase("0")){
                        break;
                    }
                }
                SIZE--;
            }
        }
        
        //ending
        frame = new Frame();
        frame.seq = 0; //denotes ending
        frame.ack = 0;
        frame.data = ConvertIntToBinary(0); 
        frame.checkSum = findCheckSum(ConvertIntToBinary(frame.seq)+ConvertIntToBinary(frame.ack)+frame.data);
        dos.writeObject(frame);

        //Close the connection, but not the server socket
        dos.close();
        s2.close();
        s.close();
        dis.close();
    }
}