import java.util.*;

public class ExampleHashMap {
	// ��Map��������HashMap����
	Map calendar = new HashMap(); 
	// ��Ԫ�ؼ��뵽Map���õĶ�����
	public ExampleHashMap(String d[], String i[])
	{
		for(int x = 0; x < d.length; x++)
		{
			calendar.put(d[x], i[x]); 
		}
	}
	public static void main(String[ ] args)
	{
		// �����������
		String[ ] dates = {"10/31/01", "01/01/01", "03/05/01", "02/04/01"}; 
		String[ ] items = {"Halloween", "New Years", "Birthday", "Anniversary"}; 
		// ��������ʵ��
		ExampleHashMap example = new ExampleHashMap(dates, items); 
		// ���Map���ö����е�key��value��
		System.out.println("map= " + example.calendar);
		// ��Map�е� key/value ��Ӧӳ���set���� 
		Set mappings = example.calendar.entrySet(); 
		System.out.println("object \t\t\tkey\t\tvalue");
		// ͨ��set���϶�Ԫ�ؽ��б����� �õ�key��value
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
