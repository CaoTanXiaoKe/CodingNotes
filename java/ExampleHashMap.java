import java.util.*;

public class ExampleHashMap {
	// 用Map声明引用HashMap对象
	Map calendar = new HashMap(); 
	// 将元素加入到Map引用的对象当中
	public ExampleHashMap(String d[], String i[])
	{
		for(int x = 0; x < d.length; x++)
		{
			calendar.put(d[x], i[x]); 
		}
	}
	public static void main(String[ ] args)
	{
		// 待加入的数据
		String[ ] dates = {"10/31/01", "01/01/01", "03/05/01", "02/04/01"}; 
		String[ ] items = {"Halloween", "New Years", "Birthday", "Anniversary"}; 
		// 创建对象实例
		ExampleHashMap example = new ExampleHashMap(dates, items); 
		// 输出Map引用对象中的key和value对
		System.out.println("map= " + example.calendar);
		// 将Map中的 key/value 对应映射成set集合 
		Set mappings = example.calendar.entrySet(); 
		System.out.println("object \t\t\tkey\t\tvalue");
		// 通过set集合对元素进行遍历， 得到key和value
		for(Iterator i = mappings.iterator(); i.hasNext(); )
		{
			Map.Entry me = (Map.Entry)i.next(); 
			Object ok = me.getKey();
			Object ov = me.getValue(); 
			System.out.print(me + "\t");
			System.out.println(ok + "\t");
			System.out.println(ov);
		}
	}
}
