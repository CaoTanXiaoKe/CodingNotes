import java.util.*;
/**
 * Title: <br>
 * Description: <br>
 * @author ChenWenKe
 * @version 
 */
public class ExampleTreeMap {
	Map calendar = new TreeMap(); 
	public ExampleTreeMap(String d[], String i[])
	{
		for(int x = 0; x < d.length; x++)
			calendar.put(d[x], i[x]); 
	}
	public static void main(String[ ] args)
	{
		String[ ] dates = {"10/31/01", "01/01/01", "03/05/01", "02/04/01"}; 
		String[ ] items = {"Halloween", "New Years", "Birthday", "Anniversary"}; 
		ExampleTreeMap example = new ExampleTreeMap(dates, items);
		System.out.println("map= " + example.calendar);
		Set mappings = example.calendar.entrySet();
		System.out.println("object\t\t\tkey\t\tvalue");
		for (Iterator i = mappings.iterator(); i.hasNext(); )
		{
			Map.Entry me = (Map.Entry)i.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			System.out.println(me + "\t");
			System.out.println(ok + "\t");
			System.out.println(ov);			
		}
		
	}
}
