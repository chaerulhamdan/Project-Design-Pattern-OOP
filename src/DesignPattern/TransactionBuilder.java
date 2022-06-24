package DesignPattern;
import Model.Transaction;

public class TransactionBuilder {
	
	private String transactionId;
	private String userName;
	private int foodId;
	private String foodName;
	private int foodPrice;
	private int quantity;
	
	public final TransactionBuilder setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public final TransactionBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public final TransactionBuilder setFoodId(int foodId) {
		this.foodId = foodId;
		return this;
	}
	public final TransactionBuilder setFoodName(String foodName) {
		this.foodName = foodName;
		return this;
	}
	public final TransactionBuilder setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
		return this;
	}
	public final TransactionBuilder setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}
	
	public Transaction build() {
		return new Transaction(
				this.transactionId,
				this.userName,
				this.foodId,
				this.foodName,
				this.foodPrice,
				this.quantity
		);
	}
}
