package simpleclient;
import java.net.Socket;
import java.io.*;


public class Simpleclient 
{
    public static void main(String[] args) throws IOException
    {
        Socket s1= new Socket("localhost",1234);
        
        InputStream sin=s1.getInputStream();
        DataInputStream din=new DataInputStream(sin);
        
        String st=new String(din.readUTF());
        System.out.println(st);
        din.close();
        sin.close();
        s1.close();
             
    }
    
}
