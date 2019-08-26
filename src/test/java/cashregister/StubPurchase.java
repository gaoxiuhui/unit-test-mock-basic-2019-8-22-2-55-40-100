package cashregister;

public class StubPurchase extends Purchase  {
	
	public StubPurchase() {
		super(new Item[0]);
	}
	public String asString() {
		return "egg\t1.0\n";
	}
}
