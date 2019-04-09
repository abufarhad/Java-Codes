import java.net.*;
import java.io.*;
class Client 
{
    public static void main(String [] args) throws IOException
    {
        Socket s=new Socket( "localhost", 1234);
        System.out.println("Connection Established own!!");
        
        DataOutputStream dos= new DataOutputStream( s.getOutputStream());
        dos.writeUTF("Connection done from Client \n");
        //dos.writeByte(1);
        
        DataInputStream din= new DataInputStream(s.getInputStream() );
        //String line=din.readLine();
        //System.out.println(line);
        
        String st=din.readUTF();
        System.out.println(st);
        
        dos.close();
        din.close();
        s.close();
        
    }
}