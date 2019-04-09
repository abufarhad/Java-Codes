import java.net.*;
import java.io.*;



public class ObjectServer 
{
	public static void main(String args[]) 
        {
		int port = 2002;
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket s = ss.accept();

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			FrameClass frame = (FrameClass) ois.readObject();
			if (frame != null) 
                        {
				System.out.println(frame.seq+" "+frame.data+" "+frame.ack+" "+frame.tailer);
			}
                        

			s.close();
			ss.close();
		}
                catch (Exception e) 
                {
			System.out.println(e);
		}
	}
}