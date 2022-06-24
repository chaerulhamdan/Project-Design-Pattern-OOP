package Model;

public class Transaction {

	private String transactionId;
	private String userName;
	private int foodId;
	private String foodName;
	private int foodPrice;
	private int quantity;
	
	public Transaction(String transactionId, String userName, int foodId, String foodName, int foodPrice,
			int quantity) {
		super();
		this.transactionId = transactionId;
		this.userName = userName;
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.quantity = quantity;
	}
	
	public final String getTransactionId() {
		return transactionId;
	}
	public final void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public final String getUserName() {
		return userName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
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
	public final int getFoodPrice() {
		return foodPrice;
	}
	public final void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	public final int getQuantity() {
		return quantity;
	}
	public final void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
