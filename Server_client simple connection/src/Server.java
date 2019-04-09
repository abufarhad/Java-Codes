import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Server
{
    public static void main( String [] args ) throws IOException
    { 
        String s="Hello ";
        ServerSocket server=new ServerSocket(1234);
        System.out.println("Server Waitting for a client request.....");
        Socket skt=server.accept();
        System.out.println("Connection Done own!! ");
        
        /* 
        // For user input 
        Scanner s= new Scanner (System.in);
        String msg= s.nextLine();
        System.out.println( msg );
        */
        
        DataOutputStream dos=new DataOutputStream(skt.getOutputStream());
        dos.writeUTF("Connection Done from Server");
        
        //dos.writeByte(1);
        //dos.flush();
        
        DataInputStream din=new DataInputStream (skt.getInputStream());
        String st=new String ( din.readUTF());
        System.out.println(st);
        
        
                
        din.close();
        dos.close();
        skt.close();
        
        
    }
}