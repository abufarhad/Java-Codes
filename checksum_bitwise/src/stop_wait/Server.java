package stop_wait;

import checksum_bitwise.CheckSumBitwise;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//@SuppressWarnings("Duplicates")
public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println(">> Server started");
        System.out.println("Enter data: ");
        Scanner scanner = new Scanner(System.in);

        String msg = scanner.nextLine();
        
        ServerSocket serverSocket = new ServerSocket(9991);

        Socket socket = serverSocket.accept();

        InputStream streamFromClient = socket.getInputStream();
        
        OutputStream streamToClient = socket.getOutputStream();


        int seq = 0;


        if (msg.length() % 2 == 1) {
            char c = 4; // c is EOT character
            msg += c;
        }

        for (int i = 0; i < msg.length() - 1; i += 2) {

            StringBuilder sb = new StringBuilder();

            sb.append(msg.charAt(i));
            sb.append(msg.charAt(i + 1));
            
            //co


            Frame frame = new Frame();
            frame.setSeq(seq);
            frame.setData(sb.toString()); 
            
            frame.setTailer(CheckSumBitwise.getCheckSum(frame.getData()));
            seq++;


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(streamToClient);

            objectOutputStream.writeObject(frame);

            //Acknowledgement from Receiver

            ObjectInputStream objectInputStream = new ObjectInputStream(streamFromClient);


            Frame ackFrame = null;

            try {
                ackFrame = (Frame) objectInputStream.readObject();

                System.out.println("Acknowldege received for sequence " + ackFrame.getAck());
                System.out.println("Tailor: " + ackFrame.getTailer());
            } 
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
