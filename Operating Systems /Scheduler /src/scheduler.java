import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class scheduler {

	
	private static String[] arrFiles = {"testdata1.txt", "testdata2.txt", "testdata3.txt", "testdata4.txt"};
	static int[] L1 = new int[100];
	static int[] L2 = new int[100];
	static int[] L3 = new int[100];
	static int[] L4 = new int[100];
	
	static int counter = 0;
	static int classCount = 0;

	
	
	public static void main(String args[])
	{
		System.out.println("Hello!");
		testdata(arrFiles[0]); //fill the array
		fillObj(L1, 1);
		
		
		 
		   testdata(arrFiles[1]);
		   fillObj(L2, 2);
		   
		   testdata(arrFiles[2]);
		   fillObj(L3, 3);
		
		   testdata(arrFiles[3]);
		   fillObj(L4, 4);
		
		
		
		testdata(arrFiles[1]);
		testdata(arrFiles[2]);
		testdata(arrFiles[3]);
		
		
		
		//printData(a);
	}

		
	public static void testdata(String arrFiles2)
	{
		File file = new  File(arrFiles2); //open the correct file 
		
		BufferedReader reader = null;

						
		try
		{
				reader = new BufferedReader(new FileReader(file));
				String text = null;
	
				
				// do a sys print to make sure i copied the numbers correctly 
				
				
				while((text = reader.readLine()) != null)
				{
					int hold = Integer.parseInt(text);
					if(arrFiles2 == "testdata1.txt")
					{
						L1[counter] = hold;
					}
					
					else if(arrFiles2 == "testdata2.txt")
					{
						L2[counter] = hold;
					}
					
					else if(arrFiles2 == "testdata3.txt")
					{
						L3[counter] = hold;
					}
					else
					{
						L4[counter] = hold;
					}
					
					//System.out.println(L1[counter]);
					counter ++; //counter has total number of variables 
					
				}
				counter = 0;
				//after the list is full send it to the right function to do the algorithm 
	
		}

		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

		
	}
	
	public static void fillObj(int[] list, int num)
	{
		ArrayList<listClass> L = new ArrayList<listClass>();
		int x = 0;
		
		for(int cnt = 0; cnt < (list.length / 3); cnt++)
		{
			listClass xx = new listClass();
			
				xx.setPid(list[x]);
				x = x+1;
				xx.setBu(list[x]);
				x = x+1;
				xx.setPri(list[x]);
				x = x+1;
				
				L.add(xx);
			
		}
		
		
		listClass li = new listClass(); //this li is made so that you can print the 
		
		System.out.println("list before algorithms");

		for(int cntrx = 0; cntrx < L.size(); cntrx++)
		{
			li = L.get(cntrx);
			String pr = li.getFullInfo();
		
			
			if(pr != null)
			{
				System.out.println(pr);
			}
			
		}
		
		
		//work on algorithm 1 
		
		firstCome(L);
		 shortJob(L);
		 //timeQuant(L, 30);
		 //timeQuant(L, 60);
		 
	}
	
	static void shortJob(ArrayList<listClass> L) //pretty much sort by burst time 
	{
		
		int calculator = 0;
		int [] a = new int[100];
				
				
		Collections.sort(L, new Comparator<listClass>()
		{
			public int compare(listClass s1, listClass s2)
			{
				return Integer.valueOf(s1.burst).compareTo(s2.burst); //so i sorted it by burst
				
			}
		});
		
		listClass li = new listClass(); //this li is made so that you can print the 
		
		System.out.println("after short job algorithms");

		for(int cntrx = 0; cntrx < L.size(); cntrx++)
		{
			li = L.get(cntrx);
			String pr = li.getFullInfo();
		
			if(pr != null)
			{
				System.out.println(pr);
			}
			
		}
		
		for(int cntrx = 0; cntrx < L.size(); cntrx++)
		{
			li = L.get(cntrx);
			calculator = calculator + li.getBu();
			a[cntrx] = calculator;
		}
		
		//System.out.println(calculator);
		int all = 0;
		int countValid = 0; 
		
		for(int i = 0; i < a.length; i++)
		{
			all = all + a[i];
			if(a[i] != 0)
			{
				countValid++;
			}
		}
		System.out.println("the total time it took: " + all + "		average: " + (all/countValid));

	}
	
	static void firstCome(ArrayList<listClass> L)
	{
		int calculator = 0; //this will be used to calculate everything 
		int[] a = new int[100];
		
		listClass li = new listClass();
		
		
		System.out.println("First come first serve algorithm");
		
		for(int cntrx = 0; cntrx < L.size(); cntrx++)
		{
			li = L.get(cntrx);
			calculator = calculator + li.getBu();
			a[cntrx] = calculator;
		}
		
		for(int cntrx = 0; cntrx < L.size(); cntrx++)
		{
			li = L.get(cntrx);
			String pr = li.getFullInfo();
		
			if(pr != null)
			{
				System.out.println(pr);
			}
			
		}
		
		int all = 0;
		int valid = 0;
		
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] != 0)
			{
				valid++;
			}
			
			all = a[i] + all; 
		}
		
		System.out.println("the total time it took: " + all + "		average: " + (all/valid));
		//System.out.println(calculator);
	}
	
}


class listClass
{
	listClass() //the default constructor 
	{
		
		
	}
	
	int pid;
	int burst;
	int priority;
	
	public String getFullInfo()
	{
		if((pid == 0) && (burst == 0) && (priority == 0))
		{	
			return(null);
			
		}
		
		else
		{
			return("pid: " + pid + "	burst: " + burst + "	priority: " + priority);
			
		}
	}
	
	public int getPid()
	{
		return pid;
	}
	
	public int getBu()
	{
		return burst;
	}
	
	public void setPid(int pi)
	{
		this.pid = pi;
	}
	
	public void setPri(int pri)
	{
		this.priority = pri;
	}
	
	public void setBu(int burst)
	{
		this.burst = burst;
	}
	
}

