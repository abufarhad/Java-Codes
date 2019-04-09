
/*
public class peterson {
	private static int c=0;
	public static int turn;
	public static int i=0,j=1;
			
	 
	public static boolean[] flag= new boolean[2];
	
	
	public static void inc()
	{
		c++;
	}
	public static void dec()
	{
		c--;
	}
	
	public static void main(String[] args) {
		
		Thread obj1 = new Thread(new Runnable() {
			public void run()
			{
				flag[0]=true;
				turn=1;
				while(flag[turn] && turn==1);
					
					for(int i=0;i<100000000;i++)
					{
						inc();
					}
				
				flag[0]=false;
			}
		});
		Thread obj2 = new Thread(new Runnable() {
			public void run()
			{
				flag[1]=true;
				turn=0;
				while(flag[turn] && turn==0);
					
				for(int i=0;i<100000000;i++)
				{
				  dec();
				}
				
				flag[1]=false;
				
			}
		});
		
		obj1.start();
		obj2.start();
		try {
			obj1.join();
			obj2.join();
			System.out.println("Counter = "+c);
		}
		catch(InterruptedException e) {
			
		}
	}
}


*/

public class mutex_lock {
	private static int c=0;
	private static boolean available = true;
	public static void inc()
	{
		c++;
	}
	public static void dec()
	{
		c--;
	}
	public static void acquire() 
	{
		while(!available);
		
		available = false;
		
	}
	public static void release()
	{
		available = true;
	}
	
	public static void main(String[] args) {
		
		Thread obj1 = new Thread(new Runnable() {
			public void run()
			{
				acquire();			
				for(int i=0;i<1000000;i++)
				{
					inc();
				}
				release();
			}
		});
		Thread obj2 = new Thread(new Runnable() {
			public void run()
			{
				acquire();
				for(int i=0;i<1000000;i++)
				{
					dec();
				}
				release();
			}
		});
		
		obj1.start();
		obj2.start();
		try {
			obj1.join();
			obj2.join();
			System.out.println("Counter = "+c);
		}
		catch(InterruptedException e) {
			
		}
	}
}


