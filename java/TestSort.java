import java.util.*;

public class TestSort {
	public static void main(String[ ] args)
	{
		List a1 = new ArrayList(); 
		a1.add(new Integer(1)); 
		a1.add(new Integer(3));
		a1.add(new Integer(2)); 
		System.out.println(a1);
		Collections.sort(a1);
		System.out.println(a1);
	}
}
