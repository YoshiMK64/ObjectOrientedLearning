/*------------------------------------------------------
Assignment number: 1
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Scanner;

public class OrderingSystem {																		//creating main OrderingSystem class
	
	//initialising ArrayList<>'s for storing customers, orders and parts
										//Textbook says its ok to declare/initialise without ArrayList type in second < >
										
		private ArrayList<Order> orderList = new ArrayList<>();
		private ArrayList<Customer> customerList = new ArrayList<>();
		private ArrayList<CPU> cpuList = new ArrayList<>();
		private ArrayList<Motherboard> motherboardList = new ArrayList<>();
		private ArrayList<Memory> memoryList = new ArrayList<>();
		private ArrayList<GraphicsCard> graphicsCardList = new ArrayList<>();
		private ArrayList<Monitor> monitorList = new ArrayList<>();

//main method	
	public static void main(String[] args){ 
	
	String forward = "I want to reverse this string";
	String reversed = "";
	
	for(int i = forward.length()-1; i >= 0 ; i--){
		char j = forward.charAt(i);
		
		String s = Character.toString(j);
		
		 reversed += s;
		

	}
	
	System.out.println(reversed);
	
	
	
	//int[] components represent {CPU, Motherboard, Memory, Graphics Card, Monitor, Menu choice}
	// simple 1D array to track the users choice for each computer part (if any removal wanted), 
	//currently only allows one choice for each part but could be extended to multiple by using ArrayList instead of int.	
		int[] orderTracking = {0,0,0,0,0,0};														
		Order custOrder = new Order();
		
	//creating object of OrderingSystem
		OrderingSystem os = new OrderingSystem();	
	//Using populate stock method to fill ArrayList's
		os.populateStock(os.cpuList, os.motherboardList, os.memoryList, os.graphicsCardList, os.monitorList);
		
	//request customer ID and check 
		Scanner in = new Scanner(System.in);

		System.out.print("\n Welcome. Please enter customer ID or type 'new' for new customer: ");
		String userIn = in.next();
	//testing and searching for previous customer	
		if (userIn.equals("New")||userIn.equals("new")||userIn.equals("NEW")){
			//print welcome and request customer details
				os.printWelcome(os.customerList);
			//insantiate order object for customer	
				custOrder = new Order(os.customerList.get(0));
		} else{
			if (os.customerList.size()>0){
				
				for (int i = 0; i < os.customerList.size(); i++){
					
					String x = os.customerList.get(i).getCustID();
					System.out.print(os.customerList.get(i));
					
					if (userIn.equals(x)){
						custOrder = new Order(os.customerList.get(i));
						break;
					}else{
						
						System.out.println("\n"+"Customer ID not found. New User. ");
					//print welcome and request customer details
						os.printWelcome(os.customerList);
					//insantiate order object for customer	
						custOrder = new Order(os.customerList.get(0));	
					}
				}
				
			}else{
				System.out.println("\n"+"Customer ID not found. New User. ");
			//print welcome and request customer details
				os.printWelcome(os.customerList);
			//insantiate order object for customer	
				custOrder = new Order(os.customerList.get(0));
			}
		}
		
		
			
			
		
		
		
		
		
	
		
	//run menu until user chooses exit	
		do{
			os.printOptionMenu();
			orderTracking = os.menuChoiceTest(os, orderTracking, custOrder);
	
		}while (orderTracking[5] != 8);
		
	}

//method to print main menu	
	public void printOptionMenu(){
		
	//instantiating scanner object
		Scanner in = new Scanner(System.in);
	
	//initialising local variable for user menu choice
		int userChoice = 0;
		
	//printing option menu
		System.out.println();
		System.out.println();
		System.out.println("Please make a selection from the following items");
		System.out.println("1: Add a CPU");
		System.out.println("2: Add a Motherboard");
		System.out.println("3: Add Memory");
		System.out.println("4: Add a Graphics Card");
		System.out.println("5: Add a Monitor");
		System.out.println("6: View/Modify the order");
		System.out.println("7: Submit order and exit");
		System.out.println("8: Exit without purchase");
				
	}

//	method to populate array lists with stock options
	public void populateStock(ArrayList<CPU> cpuList, ArrayList<Motherboard> motherboardList,
								ArrayList<Memory> memoryList, ArrayList<GraphicsCard> graphicsCardList,
								ArrayList<Monitor> monitorList){

	//declaring and initialising CPU part objects
		CPU cpu1 = new CPU("Inter", "i5", "9600K", 323);											
		CPU cpu2 = new CPU("Inter", "i7", "9600K", 462);
		CPU cpu3 = new CPU("Inter", "i7", "9600K", 396);
		CPU cpu4 = new CPU("Inter", "i9", "9600K", 591);
		CPU cpu5 = new CPU("AMD", "4", "Ryzen 2200", 200);
		CPU cpu6 = new CPU("AMD", "6", "Ryzen 3600", 310);
		CPU cpu7 = new CPU("AMD", "8", "Ryzen 3700", 489);
		CPU cpu8 = new CPU("AMD", "8", "Ryzen 5800", 669);
		
	//adding to CPU ArrayList
		cpuList.add(cpu1);
		cpuList.add(cpu2);
		cpuList.add(cpu3);
		cpuList.add(cpu4);
		cpuList.add(cpu5);
		cpuList.add(cpu6);
		cpuList.add(cpu7);
		cpuList.add(cpu8);
	
	//creating motherboard part objects
		Motherboard mot1 = new Motherboard("Inter", "Gigabyte", "H81M-DS2", 129);								//declaring and initialising Motherboard parts
		Motherboard mot2 = new Motherboard("Inter", "Asus", "J40051-C", 169);
		Motherboard mot3 = new Motherboard("Inter", "Msi", "Mpg-2390", 225);
		Motherboard mot4 = new Motherboard("Inter", "Gigabyte", "Z490", 471);
		Motherboard mot5 = new Motherboard("AMD", "Gigabyte", "B-450", 117);
		Motherboard mot6 = new Motherboard("AMD", "Asus", "A320I", 128);
		Motherboard mot7 = new Motherboard("AMD", "Msi", "B450", 232);
		Motherboard mot8 = new Motherboard("AMD", "Gigabyte", "X570S", 679);
		
	//adding to motherBoard ArrayList	
		motherboardList.add(mot1);
		motherboardList.add(mot2);
		motherboardList.add(mot3);
		motherboardList.add(mot4);
		motherboardList.add(mot5);
		motherboardList.add(mot6);
		motherboardList.add(mot7);
		motherboardList.add(mot8);
		
		
	//declaring and initialising Monitor part objects
		Monitor mon1 = new Monitor("Acer", "24", "K242HYLB", 194);								
		Monitor mon2 = new Monitor("LG", "32", "32QN600", 506);
		Monitor mon3 = new Monitor("Asus", "16", "MB16ACZ", 429);
		Monitor mon4 = new Monitor("Msi", "27", "MP271QP", 399);
		Monitor mon5 = new Monitor("BenQ", "32", "PD3200Q", 653);
		Monitor mon6 = new Monitor("Philips", "27", "272M8CZ", 289);
		
	//adding to Monitor ArrayList	
		monitorList.add(mon1);
		monitorList.add(mon2);
		monitorList.add(mon3);
		monitorList.add(mon4);
		monitorList.add(mon5);
		monitorList.add(mon6);
		
	//declaring and initialising Memory part objects	
		Memory mem1 = new Memory("DDR3", "Kingston", "8G", "KCP316ND8", 116);								
		Memory mem2 = new Memory("DDR3", "ADATA", "16G", "AX4U360038G18", 189);
		Memory mem3 = new Memory("DDR3", "G.Skill", "8G", "F3-10666CL9D", 96);
		Memory mem4 = new Memory("DDR4", "Kingston", "8G", "KCP426SS8", 93);
		Memory mem5 = new Memory("DDR4", "G. Skill", "16G", "F4-26666C18S", 158);
		Memory mem6 = new Memory("DDR4", "Crucial", "32G", "CT32G4SFD832A", 259);
		
	//adding to memory ArrayList
		memoryList.add(mem1);
		memoryList.add(mem2);
		memoryList.add(mem3);
		memoryList.add(mem4);
		memoryList.add(mem5);
		memoryList.add(mem6);
		
	//declaring and initialising Graphics Card part objects	
		GraphicsCard gc1 = new GraphicsCard("NVIDA", "Gigabyte", "GeForce RTX 3070", 1999);								//declaring and initialising GraphicsCard parts
		GraphicsCard gc2 = new GraphicsCard("NVIDA", "ASUS", "GeForce RTX 3070", 1899);
		GraphicsCard gc3 = new GraphicsCard("NVIDA", "Msi", "GeForce RTX 3080", 3099);
		GraphicsCard gc4 = new GraphicsCard("AMD", "Gigabyte", "Radeon RX 6900", 3699);
		GraphicsCard gc5 = new GraphicsCard("AMD", "Asus", "Radeon RX 6900", 3199);
		GraphicsCard gc6 = new GraphicsCard("AMD", "Msi", "Radeon RX 6900", 2699);
		
	//adding to graphicsCard ArrayList
		graphicsCardList.add(gc1);
		graphicsCardList.add(gc2);
		graphicsCardList.add(gc3);
		graphicsCardList.add(gc4);
		graphicsCardList.add(gc5);
		graphicsCardList.add(gc6);
		
	}

//print welcome and get customer details
	public void printWelcome(ArrayList<Customer> customerList){
	
	//instantiate scanner object
		Scanner in = new Scanner(System.in);
		
	//print welcome and request user input	
		System.out.println();
		System.out.println();
		System.out.println("                    Welcome to our computer shop!!");
		System.out.println("Please input personal information: name, gender, mobile number, delivery address");
	
	//retrieve user input
		String x = in.nextLine();
	
	//split user input by 'comma' and place in String array
		String[] userInfoArray = x.split(",");
	
	//create new customer object using array 
		Customer user = new Customer(userInfoArray[0], userInfoArray[1], userInfoArray[2], userInfoArray[3]);
	
	//add new customer to Customer ArrayList
		customerList.add(user);
		
		
		
	}

//apply menu options
	public int[] menuChoiceTest(OrderingSystem os, int[] userChoice, Order order) {
	
	//new scanner object
		Scanner in = new Scanner(System.in);
		
	//user input test for main menu (input is int and within correct int range)	
		if (in.hasNextInt()){
			userChoice[5] = in.nextInt();
				if (userChoice[5] == 1 || userChoice[5] == 2 || userChoice[5] == 3 || userChoice[5] == 4 || userChoice[5] == 5 || userChoice[5] == 6 || userChoice[5] == 7 || userChoice[5] == 8){
				}
				else {
					System.out.println("Please input valid choice between 1-8");
					os.menuChoiceTest(os, userChoice, order);	
				}
		}else{ 
				System.out.println("Please input integer");
				os.menuChoiceTest(os, userChoice, order);
				}
				
				
	//using switch to apply user menu choice	
		switch (userChoice[5]){
	
	//Print CPU List and add user to order unless -1 for exit 
			case 1: 
				System.out.println();
				System.out.println("We offer the following CPU products: ");
				System.out.println();
				
	//print CPU options
				os.printCPUList(os.cpuList);
				
				System.out.println("Please select a CPU or '-1' for no purchase.");
				userChoice[0] = in.nextInt();
				
	//(Didn't check user input as lecturer said we can assume correct)
					if (userChoice[0] == -1){
						break;
					}else{
						
	//add user choice to order
						order.addCPU(os.cpuList.get(userChoice[0] -1));
					}
				break;
			
			case 2:
	//print Motherboard options
				System.out.println();
				System.out.println("We offer the following Motherboard products: ");
				System.out.println();
				os.printMotherboardList(os.motherboardList);
				System.out.println("Please select a mMtherboard or '-1' for no purchase.");
	//add to order
				userChoice[1] = in.nextInt();
			
					if (userChoice[1] == -1){
						break;
					}else{
						order.addMot(os.motherboardList.get(userChoice[1] -1));
					}
				break;
			
			case 3:
	//print Memory options
				System.out.println();
				System.out.println("We offer the following Memory products: ");
				System.out.println();
				os.printMemoryList(os.memoryList);
				System.out.println("Please select a Memory or '-1' for no purchase.");
	//add to order
				userChoice[2] = in.nextInt();
			
					if (userChoice[2] == -1){
						break;
					}else{
						order.addMem(os.memoryList.get(userChoice[2] -1));
					}
				break;
			
			case 4:
	//print Graphics Card options
				System.out.println();
				System.out.println("We offer the following Graphics Card products: ");
				System.out.println();
				os.printGraphicsCardList(os.graphicsCardList);
				System.out.println("Please select a Graphics Card or '-1' for no purchase.");
	//add to order
				userChoice[3] = in.nextInt();
			
					if (userChoice[3] == -1){
						break;
					}else{
						order.addGC(os.graphicsCardList.get(userChoice[3] -1));
					}
				break;
			
			case 5:
	//print monitor options
				System.out.println();
				System.out.println("We offer the following Monitor products: ");
				System.out.println();
				os.printMonitorList(os.monitorList);
				System.out.println("Please select a Monitor or '-1' for no purchase.");
	//add to order
				userChoice[4] = in.nextInt();
					if (userChoice[4] == -1){
						break;
					}else{
						order.addMon(os.monitorList.get(userChoice[4] -1));
					}
				break;
			
			case 6:
	//print order and summary and ask user which option to remove
				System.out.println();
				System.out.println("Here is a summary of your order");
				System.out.println();
				System.out.println(order);
				System.out.println();
			
				System.out.println("Please input the product you would like removed (CPU, Motherboard, Graphics Card, Memory, Monitor or None)");
	//retrieve input
				String removeItem = in.next();
	//check input is one of the six options
			
				while (removeItem.equals("CPU") == false && removeItem.equals("Motherboard") == false && removeItem.equals("Graphics") == false && removeItem.equals("Memory") == false && removeItem.equals("Monitor") == false && removeItem.equals("None") == false){
					System.out.println("Please input valid option (CPU, Motherboard, Graphics Card, Memory, or None)");
					removeItem = in.next();
				}				
	//remove user option or exit if 'None'	
				if(removeItem.equals("CPU")){
					
			//checking that parts were ordered before removing and resetting tracker for that part type
					if(userChoice[0] != 0){
						order.removeCPU(os.cpuList.get(userChoice[0] -1));
						userChoice[0] = 0;
					} else {
						System.out.println();
						System.out.println("No CPU ordered");
						System.out.println();
					}
					
				}else if(removeItem.equals("Motherboard")){
					
					if (userChoice[1] != 0){
						order.removeMot(os.motherboardList.get(userChoice[1] -1));
						userChoice[1] = 0;
					}else{
						System.out.println();
						System.out.println("No Motherboard ordered");
						System.out.println();
					}
					
				}else if(removeItem.equals("Graphics")){
					
					if (userChoice[3] != 0){
						order.removeGC(os.graphicsCardList.get(userChoice[3] -1));
						userChoice[3] = 0;
					}else {
						System.out.println();
						System.out.println("No Graphics Card ordered");
						System.out.println();
					}
					
				}else if(removeItem.equals("Memory")){
					
					if (userChoice[2] != 0){
						order.removeMem(os.memoryList.get(userChoice[2] -1));
						userChoice[2] =0;
					}else{
						System.out.println();
						System.out.println("No Memory ordered");
						System.out.println();
					}
					
				}else if(removeItem.equals("Monitor")){
					
					if (userChoice[4] !=0){
						order.removeMon(os.monitorList.get(userChoice[4] -1));
						userChoice[4] = 0;
					}else{
						System.out.println();
						System.out.println("No Monitor ordered");
						System.out.println();
					}
				}
				break;
			
			case 7:
	//add order to orderList, print order 
				int check = 0;
				while(check == 0){
			
			//checking orderTotal is > $0
					if(order.getOrderTotal() > 0){
						os.orderList.add(order);
						System.out.println("Your order has been submitted, please see below for final order details");
						int x = os.orderList.indexOf(order);
						System.out.println(os.orderList.get(x));
						userChoice[5] = 8;
						check = 1;
					}
					else{
						System.out.println("\n No parts in cart. Please select items if you would like to submit an order or type '8' to exit. :)");
						break;
					}
				}
				break;
			
			case 8:
	//update userChoice to exit main methods while loop
				System.out.println("Exit Chosen. Thankyou for using our ordering system!");
				userChoice[5] = 8;
				break;
			
			default:
				break;				
		}
		
		return userChoice;
	}


//print methods for various Array List types
	public void printCPUList(ArrayList<CPU> arrayList){
	//print ArrayList CPU	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%d : %s%n", i+1, arrayList.get(i));
		}
	}
	
	public void printMotherboardList(ArrayList<Motherboard> arrayList){
	//print ArrayList Motherboard	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%d : %s%n", i+1, arrayList.get(i));
		}
		
	}
	
	public void printMemoryList(ArrayList<Memory> arrayList){
	//print ArrayList MemoryList	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%d : %s%n", i+1, arrayList.get(i));
		}
		
	}
	
	public void printGraphicsCardList(ArrayList<GraphicsCard> arrayList){
	//print ArrayList Graphics Card
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%d : %s%n", i+1, arrayList.get(i));
		}
		
	}
		
	public void printMonitorList(ArrayList<Monitor> arrayList){
	//print ArrayList Monitor	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%d : %s%n", i+1, arrayList.get(i));
		}
		
	}
		

}

class CPU {																							//creating CPU class														

//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String BRAND;
	private final String CORE_NUMBER;
	private final String MODEL;
	private int price;

//<constructor>> for CPU class	
	public CPU(String brand, String coreNumber, String model, int price){
		this.BRAND = brand;
		this.CORE_NUMBER = coreNumber;
		this.MODEL = model;
		this.price = price;
	}

//default <<constructor>> for CPU class	
	public CPU(){
		this.BRAND = "";
		this.CORE_NUMBER = "";
		this.MODEL = "";
		this.price = 0;
	}

//price accessor	
	public int getPrice(){
		return price;
	}
	
//price mutator
	public void setPrice(int price){
		 this.price = price;
	}

//brand accessor	
	public String getBrand(){
		return BRAND;
	}
	
//core number accessor
	public String getCoreNumber(){
		return CORE_NUMBER;
	}

//Model accessor
	public String getModel(){
		return MODEL;
	}
		
		
//to string override for CPU class	
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s","|Brand:", BRAND, " |Core Number: ", CORE_NUMBER, " |Model:", MODEL, "|  Price: ","$"+ price);
	}
	

	
}

class Motherboard {																					//creating motherboard class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String SOCKET_TYPE;
	private final String BRAND;
	private final String MODEL;
	private int price;
	
	public Motherboard(String socketType, String brand, String model, int price){
		this.BRAND = brand;
		this.SOCKET_TYPE = socketType;
		this.MODEL = model;
		this.price = price;
	}
	
	public Motherboard(){
		this.BRAND = "";
		this.SOCKET_TYPE ="";
		this.MODEL = "";
		this.price = 0;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void updatePrice(int price){
		 this.price = price;
	}
	
	public String getSocketType(){
		return SOCKET_TYPE;
	}
	
	public String getBrand(){
		return BRAND;
	}
	
	public String getModel(){
		return MODEL;
	}
	
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s","|Brand:", BRAND, " |Socket Type:", SOCKET_TYPE, " |Model: ", MODEL, "|  Price: ","$"+ price);
	}	
}

class Monitor {																						//creating Monitor class
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String SIZE;
	private final String BRAND;
	private final String MODEL;
	private int price;

//Monitor <<constructor>>	
	public Monitor(String brand, String size, String model, int price){
		this.BRAND = brand;
		this.SIZE = size;
		this.MODEL = model;
		this.price = price;
	}

//Monitor default <<constructor>>	
	public Monitor(){
		this.BRAND = "";
		this.SIZE = "";
		this.MODEL = "";
		this.price = 0;
	}

//price accessor	
	public int getPrice(){
		return price;
	}

//price mutator	
	public void setPrice(int price){
		 this.price = price;
	}

//size accessor	
	public String getSize(){
		return SIZE;
	}

//brand accessor
	public String getBrand(){
		return BRAND;
	}

//model accessor	
	public String getModel(){
		return MODEL;
	}
	
//to string override for Monitor
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s","|Brand:", BRAND, " |Size:", SIZE, " |Model:", MODEL, "|  Price: ", "$"+price);
	}
	
}

class Memory {																						//creating memory class	
//declaring variables CAPITALS for constant variables (No set methods for constants)
	private final String SOCKET_TYPE;
	private final String MODEL;
	private final String BRAND;
	private final String SIZE;
	private int price;

//Memory <<constructor>>	
	public Memory(String socketType, String brand, String size, String model, int price){
		this.SOCKET_TYPE = socketType;
		this.BRAND = brand;
		this.SIZE = size;
		this.MODEL = model;
		this.price = price;
	}

//Memory default constructor	
	public Memory(){
		this.SOCKET_TYPE = "";
		this.BRAND = "";
		this.SIZE = "";
		this.MODEL = "";
		this.price = 0;
	}

//price accessor	
	public int getPrice(){
		return price;
	}

//price mutator 	
	public void setPrice(int price){
		 this.price = price;
	}

//socket type accessor	
	public String getSocketType(){
		return SOCKET_TYPE;
	}

//brand accessor	
	public String getBrand(){
		return BRAND;
	}

//size accessor	
	public String getSize(){
		return SIZE;
	}

//Model accessor
	public String getModel(){
		return MODEL;
	}

//Memory class to string override	
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s%s%s","|Brand:", BRAND, " |Size:", SIZE, " |Model:", MODEL, " |Socket Type:", SOCKET_TYPE, "|  Price: ","$"+ price);
	}
	

}

class GraphicsCard {																				//creating Graphics Card class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String SOCKET_TYPE;
	private final String MODEL;
	private final String BRAND;
	private int price;

//Graphics Card <<constructor>>	
	public GraphicsCard(String socketType, String brand, String model, int price){
		this.SOCKET_TYPE = socketType;
		this.BRAND = brand;
		this.MODEL = model;
		this.price = price;
	}

//Graphics Card default <<constructor>>	
	public GraphicsCard(){
		this.SOCKET_TYPE = "";
		this.BRAND = "";
		this.MODEL = "";
		this.price = 0;
	}


//price accessor 	
	public int getPrice(){
		return price;
	}

//price mutator	
	public void setPrice(int price){
		 this.price = price;
	}

//socket type accessor	
	public String getSocketType(){
		return SOCKET_TYPE;
	}

//model accessor	
	public String getModel(){
		return MODEL;
	}

//brand accessor	
	public String getBrand(){
		return BRAND;
	}	

//Graphics Card to string override
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s ","|Brand:", BRAND, " |Socket Type:", SOCKET_TYPE, " |Model:", MODEL, "|  Price: ", "$"+price);
	}	
}

class Customer {																					//creating Customer class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String CUSTOMER_ID;
	private String name;
	private String gender;
	private String mobileNumber;
	private String deliveryAddress;

//customer <<constructor>>	
	public Customer(String name, String gender, String mobileNo, String deliveryAddress){
		this.CUSTOMER_ID = "C" + ((int) (10000.0+ 90000.0*Math.random()));
		this.name = name;
		this.gender = gender;
		this.mobileNumber = mobileNo;
		this.deliveryAddress = deliveryAddress;
	}

//customer default <<constructor>>	
	public Customer(){
		this.CUSTOMER_ID ="";
		this.name = "";
		this.gender = "";
		this.mobileNumber = "";
		this.deliveryAddress = "";
	}

//name mutator	
	public void setName(String name){
		this.name = name;
	}

//name accessor	
	public String getName(){
		return name;
	}

//gender mutator	
	public void setGender(String gender){
		this.gender = gender;
	}

//gender accessor	
	public String getGender(){
		return gender;
	}

//mobile mutator	
	public void setMobile(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

//mobile accessor	
	public String getMobile(){
		return mobileNumber;
	}

//address mutator	
	public void setDeliveryAddress(String deliveryAddress){
		this.deliveryAddress = deliveryAddress;
	}

//address accessor
	public String getDeliveryAddress(){
		return deliveryAddress;
	}

//ID accessor
	public String getCustID(){
		return CUSTOMER_ID;
	}

//Customer toString() override	
	@Override
	public String toString(){
		return String.format("%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n","| ID: ", CUSTOMER_ID, "| Name: ", name, "| Gender: ", gender, "| Mobile Number: ", mobileNumber, "| Delivery Adress: ", deliveryAddress);
	}	
	
}

class Order {																						//creating Order class
//declaring variables CAPITALS for constant variables (No set methods for constants)																					//creating Order class
	private final String ORDER_ID;
	private Customer customer;
	private ArrayList<String> orderList = new ArrayList<>();
	private int orderTotal = 0;																			
//Order <<constructor>>	
	public Order(Customer cust){
		this.customer = cust;
		ORDER_ID = "O" +  ((int) (10000.0+ 90000.0*Math.random()));
	}

//Order default <<constructor>>	
	public Order(){
		this.customer = null;
		ORDER_ID = "";
	}

//add parts methods that build string to add to order and update price 	
	public void addCPU(CPU cpu){
		String c =  "CPU          " + cpu;
		orderList.add(c);
		orderTotal += cpu.getPrice();
		
	}
	 
	public void addMot(Motherboard mot){
		String m = "Motherboard  " + mot;
		orderList.add(m);
		orderTotal += mot.getPrice();
		
	}
	
	public void addMon(Monitor mon){
		String m = "Monitor      " + mon;
		orderList.add(m);
		orderTotal += mon.getPrice();
	}
	
	public void addMem(Memory mem){
		String m = "Memory       "+ mem;
		orderList.add(m);
		orderTotal += mem.getPrice();
	}
	
	public void addGC(GraphicsCard gc){
		String g = "Graphics Card" +gc;
		orderList.add(g);
		orderTotal += gc.getPrice();
	}


//remove parts methods that build same string as add methods so that 
//item can be removed from order using ArrayList .remove() method and reduce price
	
	public void removeCPU(CPU cpu){
		String c ="CPU          " +  cpu;
		orderList.remove(c);
		orderTotal -= cpu.getPrice();
	}
	
	public void removeMot(Motherboard mot){
		String m = "Motherboard  " + mot;
		orderList.remove(m);
		orderTotal -= mot.getPrice();
	}
	
	public void removeMon(Monitor mon){
		String m = "Monitor      " + mon;
		orderList.remove(m);
		orderTotal -= mon.getPrice();
	}
	
	public void removeMem(Memory mem){
		String m = "Memory       " + mem;
		orderList.remove(m);
		orderTotal -= mem.getPrice();
	}
	
	public void removeGC(GraphicsCard gc){
		String g = "Graphics Card"+ gc;
		orderList.remove(g);
		orderTotal -= gc.getPrice();
	}

//Customer mutator	
	public void setCustomer(Customer cust){
		this.customer = cust;
	}

//Customer accessor	
	public Customer getCustomer(){
	return customer;	
	}

//order list mutator	
	public void setOrderList(ArrayList<String> OL){
		this.orderList = OL;
	}

//order list accessor	
	public ArrayList<String> getOrderList(){
		return orderList;
	}

//order total price mutator	
	public void setOrderTotal(int orderTotal){
		this.orderTotal = orderTotal;
	}

//order total price accessor	
	public int getOrderTotal(){
		return orderTotal;
	}

//Order ID accessor
	public String getOrderID(){
		return ORDER_ID;
	}

//Order toString() override	
	@Override
	public String toString(){
		String totalPrice = Integer.toString(orderTotal);
		String order = "==================================++  Ordered Parts  ++================================== \n\n";
		
		for(int i = 0; i < orderList.size(); i++){
			order += orderList.get(i) + "\n";
		}
		
		return String.format("%n%4s%s%n %n%4s%s%n %s%n %4s%s%n",
			"Order ID: ", ORDER_ID, 
			" === Customer details === ", customer,
			order, 
			"Total Price: $", totalPrice);
	} 
	
	
}

