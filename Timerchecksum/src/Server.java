import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;

    private int port;

    private Server(int port) {
        this.port = port;
    }

    private void start() {
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started!");
            System.out.println("Waiting for a client ...");

            while (true) {

                socket = serverSocket.accept();
                System.out.println("Client accepted!");


                inputStream = new ObjectInputStream(socket.getInputStream());
                int i = 0;
                while (socket.isConnected()) {
                    Frame receivedFrame = (Frame) inputStream.readObject();
                    System.out.println("Received: " + receivedFrame.getData());
                    Frame ackFrame = new Frame("");
                    ackFrame.setSeq(i);
                    if(!CheckSum.getCheckSum(receivedFrame.getData()).equals(receivedFrame.getTailer()))
                    {
                        ackFrame.setAcknowledgement('r');
                    }
                    else
                    {
                        ackFrame.setAcknowledgement('a');
                        i++;
                    }
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(ackFrame);

                }
                socket.close();

            }

        } catch (Exception e) {
            System.out.println("Server Closed");

        }

    }


    public static void main(String[] args) {
        Server server = new Server(5000);
        server.start();


    }
}
