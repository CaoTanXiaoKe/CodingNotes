class ParentDog{
	public String dogName;
	public ParentDog(String dogName)
	{
		this.dogName = dogName; 
	}
}
class SonDog extends ParentDog
{
	public String dogName; 
	public SonDog(String dogName, String ParentDogName)
	{
		super(ParentDogName); 
		this.dogName = dogName; 
	}
}
public class RunDog{
	public static void main(String[] args)
	{
		ParentDog pDog; 
		SonDog sDogA, sDogB;
		pDog = new ParentDog("Jack"); 
		System.out.println("pDog的名字是：" + pDog.dogName); 
		sDogA = new SonDog("SonA", "JackA"); 
		sDogB = new SonDog("SonB", "JackB");
		pDog = sDogA;
		System.out.println("pDog的名字是：" + pDog.dogName); 
		System.out.println("sDogA的名字是：" + sDogA.dogName);
		System.out.println("sDogB的名字是：" + sDogB.dogName);
		pDog = sDogB; 
		System.out.println("pDog的名字是：" + pDog.dogName);
		System.out.println("sDogA的名字是：" + sDogA.dogName);
		System.out.println("sDogB的名字是：" + sDogB.dogName);
	}
}