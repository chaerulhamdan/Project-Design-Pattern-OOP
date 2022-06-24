package Model;

public class Burger extends Food{
	
	private String burgerBun;

	public Burger(int foodId, String foodName, String foodType, int foodPrice, String burgerBun) {
		super(foodId, foodName, foodType, foodPrice);
		this.burgerBun = burgerBun;
	}

	public final String getBurgerBun() {
		return burgerBun;
	}

	public final void setBurgerBun(String burgerBun) {
		this.burgerBun = burgerBun;
	}

	@Override
	public int totalPrice(int quantity) {
		// TODO Auto-generated method stub
		int tax = 5000;
		return getFoodPrice()*quantity+tax;
	}
	
	
}
