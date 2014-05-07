package tiger.temp;

public class Temp {
	private static int count;
	public int num;

	public boolean compareTo(Temp e2)
	{
		if (this.num<e2.num)
		{
			return false;
		}
		return true;
	}
	public String toString() {
		return "t" + num;
	}

	public Temp() {
		num = count++;
	}

	public Temp(int num) {
		this.num = num;
	}
	
}
