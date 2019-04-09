import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    Socket socket;

    public Client(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendFrame(String s, boolean force) 
    {
        Random random = new Random();
        Frame sendingFrame = new Frame(s);
        sendingFrame.setTailer(CheckSum.getCheckSum(sendingFrame.getData()));

        boolean toss = random.nextBoolean();

        if(toss & !force)
        {
            sendingFrame.setData(ErrorGenerator.generateError(sendingFrame.getData()));
        }
        try {

            outputStream.writeObject(sendingFrame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Frame getFrame()
    {
        Frame frame;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            frame = (Frame) inputStream.readObject();
            return frame;
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static int counter = 0;
    static boolean runTimer = false;

    public static void startCounting()
    {
        runTimer = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runTimer)
                {
                    counter++;
                    try {
                        Thread.sleep(1000);
                        System.out.println(counter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }

    public static void stopCounting()
    {
        runTimer = false;
        counter = 0;
    }


    public static void main(String[] args) {
        String inp = "ABCDEF";

        Client client = new Client("127.0.0.1", 5000);
        if(inp.length()%2 != 0)
            inp += "@";
        for (int i = 0; i < inp.length() - 1; i += 2) {
            String s = inp.substring(i, i + 2);

            client.sendFrame(s, true);
            startCounting();
           
            while (true)
            {
            if(counter >=3)
            {
                Frame receivedFrame = client.getFrame();
                if(receivedFrame.getAcknowledgement() == 'r')
                {
                    System.out.println("Checksum didn't match. Sending frame again");
                    client.sendFrame(s, false);
                }
                else
                {
                    stopCounting();
                    break;

                }
            }}

            if(counter<=3)
            {
                client.sendFrame(s, true);
               // client.sendFrame(s, false);
            }
            stopCounting();


            Frame receivedFrame = client.getFrame();
            System.out.println("Acknowledgement Received For");
            System.out.println("Seq No: " + receivedFrame.getSeq() + "\n");

        }


    }
}
