package Model;

public abstract class Food {
	private int foodId;
	private String foodName;
	private String foodType;
	private int foodPrice;
	
	public Food(int foodId, String foodName, String foodType, int foodPrice) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodType = foodType;
		this.foodPrice = foodPrice;
	}
	
	public final int getFoodId() {
		return foodId;
	}
	public final void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public final String getFoodName() {
		return foodName;
	}
	public final void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public final String getFoodType() {
		return foodType;
	}
	public final void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public final int getFoodPrice() {
		return foodPrice;
	}
	public final void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	
	public abstract int totalPrice(int quantity);
	
}
