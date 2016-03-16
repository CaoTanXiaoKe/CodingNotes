interface Animal{
	void soundOff();
}
class Elephant implements Animal{
	public void soundOff(){
		System.out.println("Trumpet"); 
	}
}
class Lion implements Animal{
	public void soundOff()
	{
		System.out.println("Roar"); 
	}
}
class Alpha{
	static Animal get(String choice)
	{
		if (choice.equalsIgnoreCase("meat eater")) 
		{
			return new Lion(); 
		}
		else 
		{
			return new Elephant(); 
		}
	}
	public static void main (String[] args)
	{
		Lion l = (Lion)Alpha.get("meat eater"); 
		l.soundOff(); 
	}
}