import java.io.*;

public class Student implements Serializable{ // 串行化 
	int number = 1; 
	String name;
	Student(int number, String n1)
	{
		this.number  =  number; 
		this.name = n1; 
	}
	
	Student() 
	{
		this(0, ""); 
	}
	
	void save(String fname)
	{
		try 
		{
			FileOutputStream fout = new FileOutputStream("fname"); 
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this); 
			out.close(); 
		}
		catch(FileNotFoundException fe) { }
		catch(IOException ioe) { } 
	}
	
	void display(String fname)
	{
		try
		{
			FileInputStream fin = new FileInputStream(fname); 
			ObjectInputStream in = new ObjectInputStream(fin); 
			Student u1 = (Student)in.readObject(); 				// 读取对象 
			System.out.println(u1.getClass().getName() + " " + u1.getClass().getInterfaces()[0]); 
			System.out.println(" " + u1.number + " " + u1.name); 
			in.close(); 
		}
		catch(FileNotFoundException fe) { }
		catch(IOException ioe) { }
		catch(ClassNotFoundException ioe) { }
	}
	
	public static void main(String arg[ ] )
	{
		String fname = "student.obj"; 
		Student s1 = new Student(1, "Wang");
		s1.save(fname); 
		s1.display(fname); 
	}
}
