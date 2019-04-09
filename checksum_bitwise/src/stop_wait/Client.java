package stop_wait;

import checksum_bitwise.CheckSumBitwise;

import java.io.*;
import java.net.Socket;

@SuppressWarnings("Duplicates")

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 9991);

        InputStream streamFromServer = socket.getInputStream();
        OutputStream streamToServer = socket.getOutputStream();

        PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(streamToServer, "UTF-8"), true);

        int i = 0;

        while (true) {

            try {

                ObjectInputStream objectInputStream = new ObjectInputStream(streamFromServer);

                Frame frame = (Frame) objectInputStream.readObject();


                System.out.println("Sequence: " + frame.getSeq());
                System.out.println("Data: " + frame.getData());
                System.out.println("Tailor: " + frame.getTailer());

                System.out.println();

                //Sending acknowledgment frame

                Frame ackFrame = new Frame();

                ackFrame.setAck(frame.getSeq());
                ackFrame.setTailer(CheckSumBitwise.getCheckSum(ackFrame.getAck()));

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(streamToServer);

                objectOutputStream.writeObject(ackFrame);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            } catch (EOFException e) {
                break;
            }

        }

    }
}
