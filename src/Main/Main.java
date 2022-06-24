/*
 * 2440040822 - Chaerul Hamdan
 * 2440022675 - Stefan Sebastian Darmanto
 * 2440023223 - Jose Eurico Victor C
 * 2440022611 - Delon Marcely
 */

/*
 * untuk file SQL nya kami membuat 4 tabel
 * yaitu tabel user yang akan menampung data user
 * lalu tabel burger dan sandwich yang akan menampung menu menu makanan
 * dan tabel Transaction yang akan menampung transaction
 * ketiga tabel diatas terhubung nanti dengan tabel transaction
 * karena di tabel transaction kami membuat foreignkey dari masing masing primary key tabel
 * dengan susunan
 * 
 * Tabel user
 * PK -> userId
 * 
 * Tabel Burger
 * PK -> foodId
 * 
 * Tabel Sandwich
 * PK -> foodId
 * 
 * Tabel Transaction
 * PK -> transactionId
 * FK -> userId, foodId
 */

package Main;
import Model.*;
import DesignPattern.*;

/*
 *Kami membuat 3 package yaitu package main untuk menampung class Main
 *lalu package Model untuk menampung class model
 *dan package design pattern yang menampung 2 class yang berisi 2 design pattern
 *kami menggunakan 2 Design Pattern yaitu DP Singleton dan DP Builder
 */

/*
 * Untuk Design Pattern Singleton terletak pada class Connect.java pada package DesignPattern
 * dan untuk Design Pattern Builder terletak pada class TransactionBuilder.java pada package DesignPattern
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

/*Ini adalah beberapa import yang diperlukan yaitu result set untuk mengambil data pada setiap colom di database
 *lalu SQLException untuk mengexepsi SQL pada java
 *lalu Scanner untuk input
 *dan Vector untuk menyimpan data didalam vector 
 */

public class Main {
	
	Scanner input = new Scanner(System.in);
	Connect connect = Connect.getConnection();
	Vector<Food> food = new Vector<>();
	Vector<Transaction> transaction = new Vector<>();
	/*
	 * Kami membuat 2 vector yang pertama adalah vector yang diambil dari class Food
	 * yang dimana class Food ini kami jadikan sebagai superclass untuk diturunkan kepada
	 * class Burger dan class Sandwich, dimana vector ini akan menampung menu yang diambil
	 * dari database Burger dan database Sandwich
	 * 
	 * dan yang kedua adalah vector Transaction dimana vector ini nanti akan menampung isi dari keempat tabel untuk
	 * ditampilkan pada menu view
	 */
	
	public Main() {
		// TODO Auto-generated constructor stub
		boolean running = true;
		int option;
		do {
			loadTransaction();
			loadFoodMenu();
			do {
				/*
				 * Kami membuat 4 menu standar
				 * dimana menu pertama adalah menampilkan transaksi
				 * menu kedua adalah membeli makanan dan nanti akan diinsertkan ke dalam database
				 * dan menu ketiga adalah menghapus data yang dicari pada database
				 */
				System.out.println("========================================");
				System.out.println("		Restaurant Fast FOOD CSJ        ");
				System.out.println("========================================");
				System.out.println("1. View All Transaction");
				System.out.println("2. Buy Fast Food");
				System.out.println("3. Delete Transaction");
				System.out.println("4. Exit");
				System.out.print(">> ");
				option = input.nextInt();
				input.nextLine();
			} while (option < 1 || option > 4);
			switch (option) {
			case 1:
				viewAllTransaction();
				break;
			case 2:
				buyFastFood();
				break;
			case 3:
				deleteTransaction();
				break;
			case 4:
				System.out.println("Thanks for using this apps...");
				running = !running;
				break;
			}
		} while (running);
	}

	/*
	 * Untuk menu pertama adalah view all transaction
	 * dimana menu ini akan menampilkan output dengan format
	 * 
	 * transactionid | username | foodname | foodprice | quantity | totalprice |
	 */
	private void viewAllTransaction() {
		// TODO Auto-generated method stub
		if(transaction.isEmpty()) {
			System.out.println("No data found!!!!");
		} else {
			loadTransaction();
			int i = 1;
			String tbl = "| %-3d | %-4s | %-20s | %-25s | %-8d | %3d |%n";
			System.out.println("===================================================================================");
			System.out.println("                                 View All Transaction                              ");
			System.out.println("===================================================================================");
			for (Transaction transactionList : transaction) {
				if(food.get(transactionList.getFoodId()-1) instanceof Burger) {
					System.out.format(tbl, 
									  i,
									  transactionList.getTransactionId(),
									  transactionList.getUserName(),
									  transactionList.getFoodName(),
									  transactionList.getFoodPrice(),
									  transactionList.getQuantity(),
									  ((Burger)food.get(transactionList.getFoodId()-1)).totalPrice(transactionList.getQuantity()));
				} else {
					System.out.format(tbl, 
							  i,
							  transactionList.getTransactionId(),
							  transactionList.getUserName(),
							  transactionList.getFoodName(),
							  transactionList.getFoodPrice(),
							  transactionList.getQuantity(),
							  ((Sandwich)food.get(transactionList.getFoodId()-1)).totalPrice(transactionList.getQuantity()));
				}
				
				i++;
			}
			System.out.println("===================================================================================");
			System.out.println("Press Enter to Continue...");
			input.nextLine();
		}
	}
	
	/*
	 * Ini adalah method untuk mengambil data pada tabel yang ada pada database untuk dimasukan kedalam array
	 * disini kami menggunakan sql join untuk menggabungkan ketiga tabel lalu kami
	 * ambil data tersebut dan kami masukan kedalam variable menggunakan Result Set
	 */
	public void loadTransaction() {
		transaction.clear();
		String query = "SELECT tr.transactionid, us.username,bu.foodid, bu.foodname, bu.foodprice, tr.quantity FROM transaction tr \n"
				+ "JOIN user us ON tr.userid = us.userid \n"
				+ "JOIN burger bu ON bu.foodid = tr.foodid";
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
				String transactionId = rs.getString(1);
				String userName = rs.getString(2);
				int foodId = rs.getInt(3);
				String foodName = rs.getString(4);
				int foodPrice = rs.getInt(5);
				int quantity = rs.getInt(6);
				/*
				 * Lalu kami memasukan data yang sudah diambil tersebut kedalam vector tetapi
				 * disini kami tidak langsung menggunakan direct dari constructor pada class yang kami buat
				 * tetapi kami menggunakan design pattern Builder yang dimana design pattern ini bertujuan
				 * supaya kita tidak langsung direct ke constructor class yang dituju tetapi membuat class baru yaitu
				 * yang bernama TransactionBuilder untuk menggunakan design pattern ini
				 */
				Transaction transactions = new TransactionBuilder()
						   .setTransactionId(transactionId)
						   .setUserName(userName)
						   .setFoodId(foodId)
						   .setFoodName(foodName)
						   .setFoodPrice(foodPrice)
						   .setQuantity(quantity)
						   .build();
				transaction.add(transactions);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * lalu kami ulangi langkah yang sama bila sebelumnya kita menggabungkan tabel transaction dengan tabel user dan burger sekarang
		 * giliran tabel sandwich
		 */
		query = "SELECT tr.transactionid, us.username,sa.foodid, sa.foodname, sa.foodprice, tr.quantity FROM transaction tr \n"
				+ "JOIN user us ON tr.userid = us.userid \n"
				+ "JOIN sandwich sa ON sa.foodid = tr.foodid";
		rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
				String transactionId = rs.getString(1);
				String userName = rs.getString(2);
				int foodId = rs.getInt(3);
				String foodName = rs.getString(4);
				int foodPrice = rs.getInt(5);
				int quantity = rs.getInt(6);
				Transaction transactions = new TransactionBuilder()
						   .setTransactionId(transactionId)
						   .setUserName(userName)
						   .setFoodId(foodId)
						   .setFoodName(foodName)
						   .setFoodPrice(foodPrice)
						   .setQuantity(quantity)
						   .build();
				transaction.add(transactions);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Dan ini adalah menu kedua yaitu buy Fast Food dimana user akan didata untuk dimasukan kedalam
	 * database
	 */

	private void buyFastFood() {
		// TODO Auto-generated method stub
		String name = "";
		String email = "";
		int age = 0;
		/*
		 * disini kami input beberapa data dengan memakai beberapa validasi
		 * untuk tabel user pada database
		 */
		do {
			System.out.print("Input name [more than 5 character] : ");
			name = input.nextLine();
		}while(name.length() < 5);
		do {
			System.out.print("Input email [must constain '@' and end with '.com'] : ");
			email = input.nextLine();
		}while(!email.contains("@") && !email.endsWith(".com"));
		do {
			System.out.print("Input age [must more than 0] : ");
			age = input.nextInt();
			input.nextLine();
		}while(age < 0);
		/*
		 * lalu kami menampilkan menu yang diambil pada tabel burger dan sandwich yang berada pada database
		 * yang sudah disimpan kedalam vector terlebih dahulu
		 */
		foodMenu();
		int foodId;
		do {
			System.out.print("Choose your Food [1 - 10] :");
			foodId = input.nextInt();
			input.nextLine();
		} while (foodId < 1 || foodId > 10);
		int quantity;
		do {
			System.out.print("Input Quantity [Must greater than 0] : ");
			quantity = input.nextInt();
			input.nextLine();
		} while (quantity < 0);
		String tblInt = "| %-20s | %-25d |%n";
		String tblString = "| %-20s | %-25s |%n";
		System.out.println("===================================================");
		System.out.println("				  Detail Transaction               ");
		System.out.println("===================================================");
		System.out.format(tblString, "Name", food.get(foodId-1).getFoodName());
		System.out.format(tblInt, "Price", food.get(foodId-1).getFoodPrice());
		System.out.format(tblInt, "Quantity", quantity);
		if(food.get(foodId-1) instanceof Burger) {
			System.out.format(tblInt, "Total Price",((Burger)food.get(foodId-1)).totalPrice(quantity));
		} else {
			System.out.format(tblInt, "Total Price",((Sandwich)food.get(foodId-1)).totalPrice(quantity));
		}
		System.out.println("===================================================");
		
		//Insert ke database user
		String query = String.format("INSERT INTO user VALUES(NULL, '%s', '%s', %d)", name, email, age);
		connect.executeUpdate(query);
		
		//Insert ke database Transaction
		String transactionId = generateTransactionId();
		int userId = generateUserId();
		query = String.format("INSERT INTO transaction VALUES('%s', %d, %d, %d)", transactionId, userId, foodId, quantity);
		connect.executeUpdate(query);
		System.out.println("Sucessfull Inserted !");
		System.out.println("Please Enter to Continue......");
		input.nextLine();
	}
	
	/*
	 * Ini adalah method untuk mengambil userId terakhir atau yang paling besar untuk dipassing kedalam 
	 * database tabel transaction karena pada tabel transaction kita menggunakan foreign key dari user id
	 */
	public int generateUserId() {
		String query = "SELECT * FROM `user` ORDER BY userid DESC LIMIT 1";
		ResultSet rs = connect.executeQuery(query);
		int userId = 0;
		try {
			if(rs.next()){
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	/*
	 * dan ini adalah method untuk terus meningkatkan nilai transaction id
	 * dengan format TRXXX
	 * dimana XXX ini adalah sebuah angka jadi misal kita menginsert database transaction pertama maka
	 * transaction id nya adalah TR001
	 * lalu apabila kita insert lagi menjadi TR002
	 * begitupula seterusnya
	 */
	
	public String generateTransactionId() {
		String query = "SELECT * FROM `transaction` ORDER BY TransactionId DESC LIMIT 1";
		ResultSet rs = connect.executeQuery(query);
		String transactionId = "";
		try {
			if(rs.next()) {
				String transactionIdTemp = rs.getString(1);
				String takeNumber = transactionIdTemp.substring(2, transactionIdTemp.length());
				int number = Integer.parseInt(takeNumber);
				number++;
				if(number <= 9) {
					transactionId = "TR00" + number;
				} else if(number <=99 && number >=10) {
					transactionId = "TR0" + number;
				} else {
					transactionId = "TR" + number;
				}
			} else {
				transactionId = "TR001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionId;
	}
	
	/*
	 * Lalu ini adalah method untuk menampilkan data menu yang sudah dimasukan kedalam vector
	 */
	public void foodMenu() {
		loadFoodMenu();
		String tbl = "| %-3d | %-25s | %-8s | %-6d | %-10s |%n";
		System.out.println("====================================================================");
		System.out.println("             			     List Fast Food");
		System.out.println("====================================================================");
		System.out.println("| No. | Name                      | Type     | Price  | Atribut    |");
		System.out.println("====================================================================");
		for (Food foodList : food) {
			if(foodList instanceof Burger) {
				System.out.format(tbl, foodList.getFoodId(),
					foodList.getFoodName(),
					foodList.getFoodType(),
					foodList.getFoodPrice(),
					((Burger)foodList).getBurgerBun()
					);
			} else {
				System.out.format(tbl, foodList.getFoodId(),
						foodList.getFoodName(),
						foodList.getFoodType(),
						foodList.getFoodPrice(),
						((Sandwich)foodList).getBreadType()
						);
			}
		}
		System.out.println("====================================================================");
		
	}
	
	/*
	 * method untuk mengambil data dari setiap colom pada database dengan menggunakan ResultSet
	 * lalu kita memasukanya kedalam sebuah array
	 */
	public void loadFoodMenu() {
		food.clear();
		String query = "SELECT * FROM `burger`";
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
				int foodId = rs.getInt(1);
				String foodName = rs.getString(2);
				String foodType = rs.getString(3);
				int foodPrice = rs.getInt(4);
				String burgerBun = rs.getString(5);
				food.add(new Burger(foodId, foodName, foodType, foodPrice, burgerBun));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "SELECT * FROM `sandwich`";
		rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
				int foodId = rs.getInt(1);
				String foodName = rs.getString(2);
				String foodType = rs.getString(3);
				int foodPrice = rs.getInt(4);
				String burgerBun = rs.getString(5);
				food.add(new Burger(foodId, foodName, foodType, foodPrice, burgerBun));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Dan terakhir adalah sebuah method delete
	 * dimana nanti kita akan memilih sebuah id dari tabel transaction untuk didelete
	 * sehingga nanti data pada database yang dimana memiliki id yang sama seperti yang dipilih
	 * akan terdelete
	 */
	private void deleteTransaction() {
		// TODO Auto-generated method stub
		viewAllTransaction();
		int option;
		do {
			System.out.print("Input data you want to delete [1-"+transaction.size()+"] :");
			option = input.nextInt();
			input.nextLine();
		}while(option <1 || option > transaction.size());
		String deleteId = transaction.get(option-1).getTransactionId();
		String query = String.format("DELETE FROM transaction WHERE transactionid = '%s'",deleteId);
		connect.executeUpdate(query);
		System.out.println(deleteId+" success delete from table!!!!");
		System.out.println("Enter to continue");
		input.nextLine();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
