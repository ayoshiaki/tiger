package tiger.temp;

public class TempList {
   public Temp head;
   public TempList tail;
   public TempList(Temp h, TempList t) {head=h; tail=t;}
   public TempList(TempList h, TempList t) 
   {
	   TempList temp=null;
	   while (h!=null)
	   {
		   temp=new TempList(h.head,temp);
		   h=h.tail;
	   }
	   tail=t;
	   while (temp!=null)
	   {
		   tail=new TempList(temp.head,tail);
		   temp=temp.tail;
	   }
	   head=tail.head;
	   tail=tail.tail;
   }
	public void print()
	{
		System.out.println("*********************");
		TempList temp=this;
		while (temp!=null)
		{
			System.out.print(temp.head+" ");
			temp=temp.tail;
			
		}
		System.out.println("");
		System.out.println("*********************");
	}
}

