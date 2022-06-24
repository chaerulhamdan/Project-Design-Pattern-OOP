package Model;

public class Sandwich extends Food{

	private String breadType;

	public Sandwich(int foodId, String foodName, String foodType, int foodPrice, String breadType) {
		super(foodId, foodName, foodType, foodPrice);
		this.breadType = breadType;
	}

	public final String getBreadType() {
		return breadType;
	}

	public final void setBreadType(String breadType) {
		this.breadType = breadType;
	}

	@Override
	public int totalPrice(int quantity) {
		// TODO Auto-generated method stub
		int tax = 3000;
		return getFoodPrice()*quantity+tax;
	}
	
	
}
