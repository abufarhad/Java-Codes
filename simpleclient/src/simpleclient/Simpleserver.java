package simpleclient;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class Simpleserver 
{
    public static void main(String[] args) throws IOException 
    {
    System.out.println("Server is Ready and waitting for a client  \n");
    ServerSocket s =new ServerSocket(1234);
    Socket s1 =s.accept();
    
    OutputStream os=s1.getOutputStream();
    DataOutputStream dos= new DataOutputStream(os);
    
    dos.writeUTF("Connected Successful ");
    //System.out.println("Connected\n");
    dos.close();
    s1.close();
    
    }
}
