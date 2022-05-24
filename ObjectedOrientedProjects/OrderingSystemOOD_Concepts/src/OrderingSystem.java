/*------------------------------------------------------
Assignment number: 2
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OrderingSystem {																		//creating main OrderingSystem class
	
	//initialising ArrayList<>'s for storing customers, orders and parts
										//Textbook says its ok to declare/initialise without ArrayList type in second < >
		private ArrayList<ComputerPart> parts = new ArrayList<>();
		private ArrayList<Customer> customers = new ArrayList<>();


//main method	
	public static void main(String[] args){ 
	
	OrderingSystem os = new OrderingSystem();
	os.initialiseShop();

	}

//constructor	
	public OrderingSystem(){
		
		//declaring and initialising CPU part objects
		ComputerPart cpu1 = new IntelCPU( "i5", "9600K", 323);											
		ComputerPart cpu2 = new IntelCPU( "i7", "9600K", 462);
		ComputerPart cpu3 = new IntelCPU( "i7", "9600K", 396);
		ComputerPart cpu4 = new IntelCPU( "i9", "9600K", 591);
		ComputerPart cpu5 = new AMDCPU("4", "Ryzen 2200", 200);
		ComputerPart cpu6 = new AMDCPU("6", "Ryzen 3600", 310);
		ComputerPart cpu7 = new AMDCPU("8", "Ryzen 3700", 489);
		ComputerPart cpu8 = new AMDCPU("8", "Ryzen 5800", 669);
		
	//adding to ArrayList
		addComputerPart(cpu1);
		addComputerPart(cpu2);
		addComputerPart(cpu3);
		addComputerPart(cpu4);
		addComputerPart(cpu5);
		addComputerPart(cpu6);
		addComputerPart(cpu7);
		addComputerPart(cpu8);
	
	//creating motherboard part objects
		ComputerPart mot1 = new IntelMotherboard("Gigabyte", "H81M-DS2", 129);								//declaring and initialising Motherboard parts
		ComputerPart mot2 = new IntelMotherboard("Asus", "J40051-C", 169);
		ComputerPart mot3 = new IntelMotherboard( "Msi", "Mpg-2390", 225);
		ComputerPart mot4 = new IntelMotherboard( "Gigabyte", "Z490", 471);
		ComputerPart mot5 = new AMDMotherboard("Gigabyte", "B-450", 117);
		ComputerPart mot6 = new AMDMotherboard( "Asus", "A320I", 128);
		ComputerPart mot7 = new AMDMotherboard("Msi", "B450", 232);
		ComputerPart mot8 = new AMDMotherboard("Gigabyte", "X570S", 679);
		
	//adding to ArrayList	
		addComputerPart(mot1);
		addComputerPart(mot2);
		addComputerPart(mot3);
		addComputerPart(mot4);
		addComputerPart(mot5);
		addComputerPart(mot6);
		addComputerPart(mot7);
		addComputerPart(mot8);
		
		
	//declaring and initialising Monitor part objects
		ComputerPart mon1 = new Monitor("Acer", "24", "K242HYLB", 194);								
		ComputerPart mon2 = new Monitor("LG", "32", "32QN600", 506);
		ComputerPart mon3 = new Monitor("Asus", "16", "MB16ACZ", 429);
		ComputerPart mon4 = new Monitor("Msi", "27", "MP271QP", 399);
		ComputerPart mon5 = new Monitor("BenQ", "32", "PD3200Q", 653);
		ComputerPart mon6 = new Monitor("Philips", "27", "272M8CZ", 289);
		
	//adding to ArrayList	
		addComputerPart(mon1);
		addComputerPart(mon2);
		addComputerPart(mon3);
		addComputerPart(mon4);
		addComputerPart(mon5);
		addComputerPart(mon6);
		
	//declaring and initialising Memory part objects	
		ComputerPart mem1 = new Memory("DDR3", "Kingston", "8G", "KCP316ND8", 116);								
		ComputerPart mem2 = new Memory("DDR3", "ADATA", "16G", "AX4U360038G18", 189);
		ComputerPart mem3 = new Memory("DDR3", "G.Skill", "8G", "F3-10666CL9D", 96);
		ComputerPart mem4 = new Memory("DDR4", "Kingston", "8G", "KCP426SS8", 93);
		ComputerPart mem5 = new Memory("DDR4", "G. Skill", "16G", "F4-26666C18S", 158);
		ComputerPart mem6 = new Memory("DDR4", "Crucial", "32G", "CT32G4SFD832A", 259);
		
	//adding to ArrayList
		addComputerPart(mem1);
		addComputerPart(mem2);
		addComputerPart(mem3);
		addComputerPart(mem4);
		addComputerPart(mem5);
		addComputerPart(mem6);
		
	//declaring and initialising Graphics Card part objects	
		ComputerPart gc1 = new IntelGraphicsCard("Gigabyte", "GeForce RTX 3070", 1999);								//declaring and initialising GraphicsCard parts
		ComputerPart gc2 = new IntelGraphicsCard( "ASUS", "GeForce RTX 3070", 1899);
		ComputerPart gc3 = new IntelGraphicsCard("Msi", "GeForce RTX 3080", 3099);
		ComputerPart gc4 = new AMDGraphicsCard("Gigabyte", "Radeon RX 6900", 3699);
		ComputerPart gc5 = new AMDGraphicsCard("Asus", "Radeon RX 6900", 3199);
		ComputerPart gc6 = new AMDGraphicsCard("Msi", "Radeon RX 6900", 2699);
		
	//adding to ArrayList
		addComputerPart(gc1);
		addComputerPart(gc2);
		addComputerPart(gc3);
		addComputerPart(gc4);
		addComputerPart(gc5);
		addComputerPart(gc6);
		
	//declaring and initialising members	
		MemberCustomer amy = new MemberCustomer("Amy Bell", "Female", "04211111", "No. 1. NoName Street, NeverLand, 0000", "C000001"); 
		MemberCustomer bob = new MemberCustomer("Bob Brown", "Male", "04212222", "No. 2. NoName Street, NeverLand, 0000", "C000002");
		MemberCustomer cindy = new MemberCustomer("Cindy Ma", "Female", "04213333", "No. 3. NoName Street, NeverLand, 0000", "C000003"); 		
		MemberCustomer david = new MemberCustomer("David Hintz", "Male", "04214444", "No. 4. NoName Street, NeverLand, 0000", "C000004"); 
		MemberCustomer rex = new MemberCustomer("Rex White", "Male", "04215555", "No. 5. NoName Street, NeverLand, 0000", "C000005"); 
	
	//adding members to ArrayList	
		addCustomer(amy);
		addCustomer(bob);
		addCustomer(cindy);
		addCustomer(david);
		addCustomer(rex);
		
				
		
	}
	
//generate menu	
	private void initialiseShop(){
		
		//creating object of OrderingSystem
		OrderingSystem os = new OrderingSystem();
	
	//creating tracking system {menuChoice, currentCustomerArrayIndex, "tracked object removed", trackingOrderType}
		int[] curCustTrack = {0, -1, -1, -1};
		Order order = new Order();
		Order regularOrder  = new Order();
		Order memberOrder = new DiscountOrder();
		Customer customer = new Customer();
	
		do{
			os.printOptionMenu();

			if(curCustTrack[3] == 1){
				order = memberOrder;
			}else if(curCustTrack[3] == 2) {
				order = regularOrder;		
			} 
			
	//tracking
			if(curCustTrack[1] != -1){
				customer = os.customers.get(curCustTrack[1]);
			}
			
	
	//new scanner object
			Scanner in = new Scanner(System.in);
		
		
	//user input test for main menu (input is int and within correct int range)	
			while(curCustTrack[2]== -1){
			if (in.hasNextInt()){
				curCustTrack[0] = in.nextInt();
					if (curCustTrack[0] == 1 || curCustTrack[0] == 2 || curCustTrack[0] == 3 || curCustTrack[0] == 4 || curCustTrack[0] == 5 || curCustTrack[0] == 6 || curCustTrack[0] == 7){
					curCustTrack[2]= 1;
					}
					else {
						System.out.println("Please input valid choice between 1-7");
						//os.menuChoiceTest(os, curCustTrack);	
					}
			}else{ 
					System.out.println("Please input integer");
					in.nextLine();
					//os.menuChoiceTest(os, curCustTrack);
					}
				
		}
		curCustTrack[2]= -1;
		
	//using switch to apply user menu choice	
			switch (curCustTrack[0]){
	 
				case 1: 
	
					System.out.println();
					String custInBuffer = in.nextLine();
				
				//request ID
					System.out.print("Please input Customer ID: ");
					String custIn = in.nextLine();
					System.out.println();
				
					int tracker1 = 0;
					
					if (os.customers.size()>0){
				
				//for loop to search customers array
						for (int i = 0; i < os.customers.size(); i++){
					
						//get id for each customer in array
							String z = os.customers.get(i).getCustID();
					
						//testing equality
							if (custIn.equals(z)){
							
							//update tracker
								curCustTrack[1] = os.customers.indexOf(os.customers.get(i));
								curCustTrack[3] = 1;
								tracker1 = 1;
							
							//print user
								System.out.println("Welcome!");
								System.out.println(os.customers.get(i));
								//customer = os.customers.get(i);
								
								break;
							}
						}
		//ID not found
					}else{
						System.out.println("\n"+"Customer ID not found. \n\nPlease login as new user. ");
					}
		
		//ID not found		
					if(tracker1 == 0){
						
						System.out.println("\n"+"Customer ID not found. \n\nPlease login as new user. ");
					}
					break;
			
				case 2:{
					
					System.out.println("Please input personal information: name, gender, mobile number, delivery address");
	
	//retrieve user input
					in.nextLine();
					String userInf =  in.nextLine();
				
	//split user input by 'comma' and place in String array
					String[] userInfoArray = userInf.split(",");
	
	//create new customer object using array 
					Customer user = new Customer(userInfoArray[0], userInfoArray[1], userInfoArray[2], userInfoArray[3],"C" + ((int) (100000.0+ 900000.0*Math.random())));
	
	//add new customer to Customer ArrayList
					os.customers.add(user);
			
				//update trackers
					curCustTrack[1] = os.customers.indexOf(user); 
					curCustTrack[3] = 2;
					
					//customer = user;
				
					System.out.println("\n\n User:");
					System.out.print(os.customers.get(curCustTrack[1]));
				
					break;
				}
				case 3:
	//print Part options
					if(curCustTrack[1] != -1){
					
				
						customer = os.customers.get(curCustTrack[1]);
						
	//printing offered computer parts
						System.out.println();
						System.out.println("We offer the following Computer parts: ");
						System.out.println();
						os.printPartsList(parts);
				
				//looptracker	
						boolean isNeg = false;
						
						in.nextLine(); //buffer clearance
						
						do{
							System.out.println("Please select all computer part numbers seperated by comma (1,3,4) or '-1' for no purchase.");
	
			//add to order
							String userChoice2 = in.nextLine();
					
					//removing spaces, split and add to array
							userChoice2 = userChoice2.replaceAll("\\s","");
							String[] userChoiceArray = userChoice2.split(",");
				
				//input checker loop tracker
							boolean isInt = true;
						
							for(String part: userChoiceArray){
							
							//checking input
								isInt = os.testInt(part);
						
								if(isInt == true){
						
									int partChoice = Integer.parseInt(part);
						
									if((partChoice > 0 && partChoice <= parts.size()) || partChoice == -1){
						
									//exit tracker
										if(part.equals("-1")){
											isNeg = true;
										}else{
						
							//add part to order
											order.addComputerPart(parts.get(partChoice-1));
										}
									}else{
										System.out.println(part + " is an invalid choice, please make sure entry is integer only seperated by a comma.");
									}
						
								}else{
						
									System.out.println(part + " is an invalid choice, please make sure entry is integer only seperated by a comma.");
								}	
							}
							if(order.getComputerParts().size() > 0){
							
							//adding to valid order type
							
								if(curCustTrack[3] == 1){
									memberOrder = order;
								}else if(curCustTrack[3] == 2) {
									regularOrder = order;		
								}	
							}
							
						}while(isNeg==false);
				
					}else{
						System.out.println("\n Please login before creating order.");
					}
					
					break;
			
				case 4:
					if(curCustTrack[1] != -1 && curCustTrack[3] != -1){
						
						customer = os.customers.get(curCustTrack[1]);
				
			//print order and summary and ask user which option to remove
						System.out.println();
						System.out.println("Here is a summary of your order");
						System.out.println();
						
						double currentTotal = 0;
						String summaryTotal = "";
						
						for(int p = 0; p < order.getComputerParts().size(); p++){
							
							System.out.printf("%3d) : %s%n", p+1, order.getComputerParts().get(p));
						}
						
				//downcast to print correct prices for member type	
						if(order instanceof DiscountOrder){
							DiscountOrder dOrder = (DiscountOrder) order;
							currentTotal = order.getTotalPrice()*dOrder.getDiscount();
							summaryTotal = String.format("%s%.2f%s%.2f" ,"Total Price: $", order.getTotalPrice(), ",  with Member Discount: $", currentTotal);  
							
						}else{
							currentTotal = order.getTotalPrice();
							summaryTotal = String.format("%s%.2f" ,"Total Price: $", order.getTotalPrice());
						}
						
				//System.out.println(order);
						System.out.println();
						System.out.println(summaryTotal);
						System.out.println();
				
				//initialising variables for compatibility checker
						int k = 0;
						int f = 0;
					
						String warning ="";
					
						ComputerPart part1;
						ComputerPart part2;
										
				//commpatibility checker; nested for loop where each member checks its compatibility with higher members of array.
						for(int i=0;  i < order.getComputerParts().size(); i++){
						
							part1 = order.getComputerParts().get(i);
							
				//String for adding incompatible parts			
							String inCompParts = "";
						
							for (int j = i+1; j < order.getComputerParts().size(); j++){
								
								boolean comp = true;
								part2 = order.getComputerParts().get(j);
								comp = part1.isCompatible(part2);
					
				//if incompatible add to string for printing otherwise ignore
								if(comp == false){
									
									k++;
									f++;
									inCompParts += part2.getBrand()+": "+part2.getID()+" \n";
								}
							}
							if(f > 0){
				
				//only add to string if incompatible parts found for initial part
								warning += "\n ---------------------------- \n"+ part1.getBrand()+":"+part1.getID()+" is not compatible with: \n ---------------------------- \n"+ inCompParts;
								f = 0; 
								
							}
						}
				
				//only print if incompatible parts found
						if(k != 0){
							
							System.out.println("***Warning*** ");
							System.out.println(warning);
						
						}
						
						boolean exitReq = false;
						ArrayList<String> temp = new ArrayList<>();
						
						while(exitReq == false){
					
							System.out.println("Please input the computer part numbers you would like removed seperated by comma (e.g. 1,3,9) or '-1' for none.");
	
				//retrieve input
							String removeItem = in.next();
							removeItem = removeItem.replaceAll("\\s","");
							String[] partRemoveArray = removeItem.split(",");
							
				//include parts to be removed only if not double up
							for(int h = 0; h < partRemoveArray.length; h++){
								for(int l = 0; l < temp.size(); l++){
									if(partRemoveArray[h].equals(temp.get(l))){
										
										temp.remove(l);
									}
								
								}
							
							//testing if -1  for exit after complete and only include in remove array if not -1
								if(partRemoveArray[h].equals("-1")){
								
									exitReq = true;
								
								}else{
									temp.add(partRemoveArray[h]);	
								}
							
							}
						
						}
						
					
					//converting to int[] to sort
						int[]partRemoveIntArray = new int[temp.size()];
				
					//initialising trackers
						int count=0;
						boolean isInt2 = true;
				
					//testing valid input
						for(String part: temp){
					
							isInt2 = os.testInt(part);
					
							if(isInt2 == true){
								
								int partChoice = Integer.parseInt(part);
								partRemoveIntArray[count] = partChoice;
								count++;
									
						
							}else{
						
								System.out.println(part + "is an invalid choice, please make sure entry is integer only seperated by a comma.");
							}
						}
					
					//sorting array so that highest members can be removed first
						Arrays.sort(partRemoveIntArray);
					
					//removing highest members first to not allow out of bounds exception after removing lower index
						for(int removal = partRemoveIntArray.length-1; removal >= 0; removal--){
							
							if(partRemoveIntArray[removal] > 0 && partRemoveIntArray[removal] <= order.getComputerParts().size()){
								int tax = partRemoveIntArray[removal] - 1;
								order.removeComputerPart(tax);
							}

						}
						
						
						
					}else{
						System.out.println("\n Either no current order or Customer has not logged in.");
					}
				
					break;
			
				case 5:
			
					if(curCustTrack[1] != -1 && curCustTrack[3] != -1){
					
						customer = os.customers.get(curCustTrack[1]);
					
					
			//add order to orderList, print order 
			
			//checking orderTotal is > $0
						if(order.getTotalPrice() > 0){
							if(customer instanceof MemberCustomer){
							
							//downcast to discount order
								DiscountOrder disOrder = (DiscountOrder) order;
							
							//add order to order list
								customer.addOrder(disOrder);
								
							//print feedback
								System.out.println("Your order has been submitted, please see below for order details");
								int wat = customer.getOrders().indexOf(disOrder);
								System.out.println(customer.getOrders().get(wat));
									
							//reset order
								Order reset1 = new DiscountOrder();
								memberOrder = reset1;
									
									
									
							}else{
							
							//regular order same process as above
								customer.addOrder(order);
								System.out.println("Your order has been submitted, please see below for order details");
						
								int wat = customer.getOrders().indexOf(order);
								System.out.println(customer.getOrders().get(wat));
									
								Order reset2 = new Order();
								regularOrder = reset2;
									

									
							}
						}
						else{
							System.out.println("\n No parts in cart. Please select items if you would like to submit an order or type '7' to exit. :)");
							break;
						}
						
					}else{
						System.out.println("\n Either no current Order or Customer not logged in.");
							
					}
						break;
				
			
				case 6:
			
					if(curCustTrack[1] != -1){
				
				//personalised feedback
						customer = os.customers.get(curCustTrack[1]);
						System.out.println("The following is a complete list of "+ customer.getName()+"'s orders");
				
				//print orders
						for(Order ord: customer.getOrders()){
						
							System.out.println(ord);
							System.out.println();
							System.out.println();

						}

					//loop tracking 
						String userIn = "";
						boolean ordDelTrk =  false;
						in.nextLine(); //clear buffer
					
					//loop until user requests exit
						while(ordDelTrk == false){

						//check for orders
							if (customer.getOrders().size()>0){
					
						//request orderID
								System.out.print("Please input ID of order you would like removed or NONE to return to main menu: ");
						
						//clear  buffer
								userIn = in.nextLine();
								
						//remove spaces		
								userIn = userIn.replaceAll("\\s","");
								System.out.println();
						
						//if exit request change loop tracker
								if(userIn.equals("None") || userIn.equals("none") || userIn.equals("NONE")){
									ordDelTrk = true;
								}
						
						//compare user in to orders 
								for (int i = 0; i < customer.getOrders().size(); i++){
					
									String custOrder = customer.getOrders().get(i).getOrderID();
					
						//if equal then remove
									if (userIn.equals(custOrder)){
										
										customer.removeOrder(i);
										
										System.out.println("Order removed. This is the new list of orders:");
										
										
										for(Order ord: customer.getOrders()){
						//print order list
											System.out.println(ord);
											System.out.println();
										}
						
										System.out.println(); System.out.println();
						
										break;
									}
								}
				
							}else{
								System.out.println("\n"+"No orders found.");
								ordDelTrk = true;
							}
						}
					}else{
					
						System.out.println("\n Customer not logged in.");
							
					
					}
	
					break;
			
				case 7:
			//update userChoice to exit main methods while loop
					System.out.println();
					System.out.println("Exit Chosen. Thankyou for using our ordering system!");
					curCustTrack[0] = 7;
					break;
			
				default:
					break;					
			}
		}while (curCustTrack[0] != 7);
		
	}
		
			
	

//accessor	
	public ArrayList<Customer> getCustomers(){
		
		return customers;
	}
	
//accessor	
	public ArrayList<ComputerPart> getComputerParts(){
		
		return parts;
	}

//mutator 
	public void addCustomer(Customer c){
	
	//setting trackers
		boolean custExist = false;
		String customerID = "";
	
	//if no list just add 
		if(customers.size()==0){
			customers.add(c);
		}else{
			
	//if a list then compare to each member 
			for (int i = 0; i < customers.size(); i++){
			
	//retrieving ID's for testing
				String custIdTest = c.getCustID();
				String x = customers.get(i).getCustID();
	
	//if equal change trackers to indicate equality and to which part
				if (custIdTest.equals(x)){
					custExist = true;
					customerID = x;
				}
			}
			
	//if no equalities then add to list others indicate which customer 
			if(custExist == false){
				customers.add(c);
			}else{
				System.out.println("Customer already exists in system. ID: "+ customerID);
			}
		
		}
		
	}
	
//mutator
	public void addComputerPart(ComputerPart cp){
	
	//setting trackers
		boolean partExist = false;
		String partID = "";
	
	//if no list just add 
		if(parts.size()==0){
			parts.add(cp);
		}else{
			
	//if a list then compare to each member 
			for (int i = 0; i < parts.size(); i++){
				
	//retrieving ID's for testing	
				String partIdTest = cp.getID();
				String x = parts.get(i).getID();
	
	//if equal change trackers to indicate equality and to which part
				if (partIdTest.equals(x)){
					partExist = true;
					partID = parts.get(i).getBrand() + ": " +x;
				}
			}
			
	//if no equalities then add to list others indicate which part
			if(partExist == false){
				parts.add(cp);
			}else{
				System.out.println("Part already exists in system. ID: "+ partID);
			}
		
		}
		
		
	}
	
	
	//print methods for various Array List types
	public void printCustomerList(ArrayList<Customer> arrayList){
	//print ArrayList CPU	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%3d : %s%n", i+1, arrayList.get(i));
		}
	}
	
	public void printPartsList(ArrayList<ComputerPart> arrayList){
	//print ArrayList Motherboard	
		for(int i = 0; i < arrayList.size(); i++){
			System.out.printf("%3d : %s%n", i+1, arrayList.get(i));
		}
		
	}
	
	public void printOptionMenu(){
				
	//printing option menu
		System.out.println();
		System.out.println();
		System.out.println("Please make a selection from the following items");
		System.out.println("1: Login as an existing 'member customer'");
		System.out.println("2: Login as a new customer");
		System.out.println("3: Add computer parts");
		System.out.println("4: View/modify the current order");
		System.out.println("5: Submit the current order");
		System.out.println("6: Review/cancel submitted orders");
		System.out.println("7: Exit the online shop");
				
	}
	

//testing that the computer choices are integers	
	public boolean testInt(String str){
	
	//if null value
			if (str == null) {
				return false;
			}
	//if empty	
			int length = str.length();
			if (length == 0) {
				return false;
			}
	
	//checking negative to push next test one character along
			int i = 0;
			if (str.charAt(0) == '-') {
				if (length == 1) {
					return false;
				}

				i = 1;
			}
			
	//testing valid integer		
			for (; i < length; i++) {
				char c = str.charAt(i);
				if (c < '0' || c > '9') {
				return false;
				}
			}
			return true;
	}
	
	
}

//===================================================================================================================

interface Compatible{

	public boolean isCompatible(ComputerPart cp);

}

abstract class ComputerPart implements Compatible{
	
	//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String ID;
	private final String BRAND;
	private final String MODEL;
	private double price;

//<constructor>> for CompPart class	
	public ComputerPart(String brand, String model, double price, String prefix){
		
		if(price < 0){
			throw new IllegalArgumentException(
					"Invalid: negative price");
		}
		
		this.BRAND = brand;
		this.MODEL = model;
		this.price = price;
		this.ID	   = prefix + ((int) (10000.0+ 90000.0*Math.random()));
		
	}

//default <<constructor>> for CompPart class	
	public ComputerPart(){
	//not using 'this()' so ID can be empty
		this.BRAND = "";
		this.MODEL = "";
		this.price = 0;
		this.ID	   = "";

		
	}
	
//copy <<constructor>> for CompPart class	
	public ComputerPart(ComputerPart compPar){
		
	//not using 'this()' so ID can be copied
		this.BRAND = compPar.BRAND;
		this.MODEL = compPar.MODEL;
		this.price = compPar.price;
		this.ID	   = compPar.ID;
	}

//Necessary as defined in interface Compatible
	@Override 
	public abstract boolean isCompatible(ComputerPart cp);
	
//ID accessor	
	public String getID(){
		return ID;
	}


//price accessor	
	public double getPrice(){
		return price;
	}
	
//price mutator
	public void setPrice(double price){
		if(price < 0){
			throw new IllegalArgumentException(
					"Invalid: negative price");
		}
		 this.price = price;
	}

//brand accessor	
	public String getBrand(){
		return BRAND;
	}
	
//Model accessor
	public String getModel(){
		return MODEL;
	}
		
//to string override for computer class	
	@Override
	public String toString(){
		return String.format("%s%s%s%s%s%s%s%s", "Product ID: ", getID(),"| Brand:", getBrand(),  " |Model:", getModel(), "|  Price: ","$"+ getPrice());
	}
}

//===================================================================================================================

class Memory extends ComputerPart{																						//creating memory class	
//declaring variables CAPITALS for constant variables (No set methods for constants)
	private final String SOCKET;
	private final String SIZE;

//Memory <<constructor>>	
	public Memory(String socketType, String brand, String size, String model, int price){
		super(brand, model, price, "MEM" );
		
		
		this.SOCKET = socketType;
		this.SIZE = size;
	}

//Memory default constructor	
	public Memory(){
		super();
		this.SOCKET = "";
		this.SIZE = "";
	}
	
//Memory copy constructor	
	public Memory(Memory mem){
		super(mem.getBrand(), mem.getModel(), mem.getPrice(), "MEM");
		this.SOCKET = mem.getSocketType();
		this.SIZE = mem.getSize();
	}
	
//socket type accessor	
	public String getSocketType(){
		return SOCKET;
	}


//size accessor	
	public String getSize(){
		return SIZE;
	}

//contract with interface
	@Override
	public boolean isCompatible(ComputerPart cp){
	
		return true;
		
	}

//Memory class to string override	
	@Override
	public String toString(){
		return String.format("%15s%12s%s%s%s%s%s%s%s%s%s%s", "Memory: ", getID(), " |Brand:", getBrand(),
			" |Model:", getModel(), " |Size:", getSize(), " |Socket Type:", getSocketType(), "|  Price: ","$"+ getPrice());
	}
	

}

//===================================================================================================================

class Monitor extends ComputerPart{																						//creating Monitor class
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String SIZE;


//Monitor <<constructor>>	
	public Monitor(String brand, String size, String model, int price){
		super(brand, model, price, "MON");
		
		
		this.SIZE = size;
	}

//Monitor default <<constructor>>	
	public Monitor(){
		super();
		this.SIZE = "";

	}
	
//copy <<constructor>>
	public Monitor(Monitor mon){
		super(mon.getBrand(), mon.getModel(), mon.getPrice(), "MON");
		this.SIZE = mon.getSize();
	}


//size accessor	
	public String getSize(){
		return SIZE;
	}

//contract with interface
	@Override
	public boolean isCompatible(ComputerPart cp){
	
		return true;
		
	}

//to string override for Monitor
	@Override
	public String toString(){
		return String.format("%15s%12s%s%s%s%s%s%s%s%s","Monitor: ", getID(), " |Brand:", getBrand(),
			 " |Model:", getModel(), " |Size:", getSize(), "|  Price: ", "$"+getPrice());
	}
	
}

//===================================================================================================================

abstract class Motherboard extends ComputerPart{																					//creating motherboard class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	


	
	public Motherboard(String brand, String model, double price, String prefix){
		super(brand, model, price, prefix + "MOT");
		
	}
	
	public Motherboard(String brand, String model, double price){
		this(brand, model, price, "");
	}
//
	public Motherboard(){
		super();
		
	}
	
	
//copy constructor
	public Motherboard(Motherboard mb){
		super(mb.getBrand(),  mb.getModel(), mb.getPrice(), mb.getID().substring(0, 6));
		
		
	}
	
	public abstract boolean isCompatible(ComputerPart cp);
		
	@Override
	public String toString(){
		return String.format("%15s%12s%s%s%s%s%s%s%s%s","Motherboard: ", getID(), " |Brand:",
							getBrand(), " |Socket Type:", " |Model: ", getModel(), "|  Price: ","$"+ getPrice());
	}	
}

class AMDMotherboard extends Motherboard{
	
	private final String SOCKET;
	
//constructor
	public AMDMotherboard(String brand, String model, double price){
		
		super(brand, model, price, "AMD");
		
		this.SOCKET = "AMD";
	
	}
	
//default constructor
	public AMDMotherboard(){
		
		super();
		this.SOCKET = "";
	}
	
//copy constructor
	public AMDMotherboard(AMDMotherboard mot){
		
		this(mot.getBrand(), mot.getModel(), mot.getPrice());
		
	}
	
//accessor
	public String getSocket(){
		
		return SOCKET;
		
	}
	
//as defined in interface
	@Override
	public boolean isCompatible(ComputerPart cp){

      boolean b = true;
	  
//Using instanceof to test compatibility
			
		if (cp instanceof IntelCPU){
			b=false;
		}else if (cp instanceof IntelMotherboard){
			b=false;
		}else if (cp instanceof IntelGraphicsCard){
			b=false;
		}
		
		return b;

	}
	
	 @Override
	public String toString(){
		return String.format("%15s%12s%S%S%s%s%s%s%s%s ","Motherboard: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}
}
	

class IntelMotherboard extends Motherboard{
	
	private final String SOCKET;
		
	//constructor       
	public IntelMotherboard(String brand, String model, double price){
		
		super(brand, model, price, "INT");
		
		this.SOCKET = "Intel";
	
	}
	
	//constructor
	public IntelMotherboard(){
		
		super();	
		this.SOCKET= "";
	
	}	
	
//copy constructor
	public IntelMotherboard(IntelMotherboard mot){
		
		this(mot.getBrand(), mot.getModel(), mot.getPrice());
		
	}
	
//accessor
	public String getSocket(){
		
		return SOCKET;
		
	}
	
	@Override
	public boolean isCompatible(ComputerPart cp){
		
		boolean b = true;

//Using instanceof to test compatibility
		if (cp instanceof AMDCPU){
			b=false;
		}else if (cp instanceof AMDMotherboard){
			b=false;
		}else if (cp instanceof AMDGraphicsCard){
			b=false;
		}
		
		return b;
	}
	
	@Override
	public String toString(){
		return String.format("%15s%12s%S%S%s%s%s%s%s%s ","Motherboard: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}
	
}
//===================================================================================================================

abstract class CPU extends ComputerPart{																							//creating CPU class														

//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String CORE;


//<constructor>> for CPU class	
	public CPU(String brand, String model, double price, String core){
		
		super(brand, model, price, brand.toUpperCase().substring(0, 3) + "CPU");
		
		this.CORE = core;

	}

//using super rather than this so as not to call substring but send "CPU" as prefix to superclass
	public CPU(String model, double price, String core){
		
		super("", model, price, "CPU");
		this.CORE = core;
		

	}

//default <<constructor>> for CPU class	
	public CPU(){
		super();
		this.CORE = "";

	}

//necessary as defined in Compatible
	public abstract boolean isCompatible(ComputerPart cp);



//copy <<constructor>> for CPU class	
	public CPU(CPU cpu){
		
		super(cpu.getBrand(), cpu.getModel(), cpu.getPrice(), cpu.getID().substring(0, 6));
		this.CORE 			= cpu.getCoreNumber();
	}
	
//core number accessor
	public String getCoreNumber(){
		return CORE;
	}


//to string override for CPU class	
	@Override
	public String toString(){
		 
		return String.format("%15s%s%s%s%s%s%s%s%s%s", "CPU: ", getID(), " |Brand:", getBrand(), " |Core Number: ", getCoreNumber(), " |Model:", getModel(), "|  Price: ","$"+ getPrice());
	}	
}

class AMDCPU extends CPU{
	private final String SOCKET = "AMD";
	
//default constructor
	public AMDCPU(){
		super();
	}

//constructor 	
	public AMDCPU(String core, String model, double price){
		super("AMD", model, price, core);	
	}

//copy constructor 	
	public AMDCPU(AMDCPU  cpu){
		super(cpu.getBrand(), cpu.getModel(), cpu.getPrice(), cpu.getCoreNumber());		
	}
	
//accessor
	public String getSocket(){
		return SOCKET;
	}
		
	
//as defined in interface
	@Override
	public boolean isCompatible(ComputerPart cp){
		
		boolean b = true;

//Using instanceof to test compatibility	  
			
		if (cp instanceof IntelCPU){
			b=false;
		}else if (cp instanceof IntelMotherboard){
			b=false;
		}else if (cp instanceof IntelGraphicsCard){
			b=false;
		}
		
		return b;
		
	}
	
	@Override
	public String toString(){
		return String.format("%15s%12s%S%S%s%s%s%s%s%s ","CPU: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}	
}

class IntelCPU extends CPU{
	private final String SOCKET = "Intel";
	
//default constructor
	public IntelCPU(){
		super();

	}

//constructor 	
	public IntelCPU(String core, String model, double price){
		super("Intel", model, price, core );
	}

//copy constructor 	
	public IntelCPU(IntelCPU  cpu){
		super(cpu.getBrand(), cpu.getModel(), cpu.getPrice(), cpu.getCoreNumber() );
		
	}
	
//accessor
	public String getSocket(){
		return SOCKET;
	}
		
	
//as defined in interface	
	@Override
	public boolean isCompatible(ComputerPart cp){
		
		boolean b = true;	
	
//Using instanceof to test compatibility
	
		if (cp instanceof AMDCPU){
			b=false;
		}else if (cp instanceof AMDMotherboard){
			b=false;
		}else if (cp instanceof AMDGraphicsCard){
			b=false;
		}
	
		return b;
		
	}
	
	@Override
	public String toString(){
		return String.format("%15s%12s%S%S%s%s%s%s%s%s ","CPU: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}
	
}

//===================================================================================================================

abstract class GraphicsCard extends ComputerPart{																				//creating Graphics Card class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	

//Graphics Card <<constructor>>	
	public GraphicsCard(String brand, String model, double price, String prefix){
		super(brand, model, price, prefix + "GRA");
	}

//Graphics Card <<constructor>>	
	public GraphicsCard(String brand, String model, double price){
		super(brand, model, price, "GRA");
	}

//Graphics Card default <<constructor>>
	public GraphicsCard(){
		super();
	}

//copy <<constructor>>	
	public GraphicsCard(GraphicsCard gc){
		super(gc.getBrand(), gc.getModel(), gc.getPrice(), gc.getID().substring(0, 6));
	}

//contract with interface
	public abstract boolean isCompatible(ComputerPart cp);

//Graphics Card to string override
	@Override
	public String toString(){
		return String.format("%14s%12s%s%s%s%s%s%s ","Graphics Card: ", getID(), " |Brand:", getBrand(),  " |Model:", getModel(), "|  Price: ", "$"+getPrice());
	}	
}


class AMDGraphicsCard extends GraphicsCard{
	
	private final String SOCKET;
//<<constructor>>	
	public AMDGraphicsCard(String brand, String model, double price){
		super(brand, model, price, "AMD");
		
		this.SOCKET = "AMD";
		
		
	}
//default <<constructor>> 	
	public AMDGraphicsCard(){
		super();
		this.SOCKET = "";
		
	}

//copy constructor	
	public AMDGraphicsCard(AMDGraphicsCard gc){
		super(gc.getBrand(), gc.getModel(), gc.getPrice(), "AMD");
		
		this.SOCKET = gc.getSocket();
		
	}

//accessor
	public  String getSocket(){
		
		return this.SOCKET;
		
	}
	
	@Override
	public boolean isCompatible(ComputerPart cp){
		
		boolean b = true;

//Using instanceof to test compatibility
		
		if (cp instanceof IntelCPU){
			b=false;
		}else if (cp instanceof IntelMotherboard){
			b=false;
		}else if (cp instanceof IntelGraphicsCard){
			b=false;
		}
		
		return b;
		
	}

//Graphics Card to string override
	@Override
	public String toString(){
		return String.format("%14s%12s%S%S%s%s%s%s%s%s ","Graphics Card: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}	
	
}


class IntelGraphicsCard extends GraphicsCard{
	
	private final String SOCKET;
//<<constructor>>	
	public IntelGraphicsCard(String brand, String model, double price){
		super(brand, model, price, "INT");
		this.SOCKET = "Intel";
		
		
	}
//default <<constructor>> 	
	public IntelGraphicsCard(){
		super();
		this.SOCKET = "";
		
	}

//copy constructor	
	public IntelGraphicsCard(IntelGraphicsCard gc){
		super(gc.getBrand(), gc.getModel(), gc.getPrice(), "INT" );
		this.SOCKET = gc.getSocket();
		
	}

//accessor
	public  String getSocket(){
		
		return this.SOCKET;
		
	}
	
	@Override
	public boolean isCompatible(ComputerPart cp){
		
		boolean b = true;

//Using instanceof to test compatibility
	
		if (cp instanceof AMDCPU){
			b=false;
		}else if (cp instanceof AMDMotherboard){
			b=false;
		}else if (cp instanceof AMDGraphicsCard){
			b=false;
		}
		
	
		return b;
		
	}
	
	@Override
	public String toString(){
		return String.format("%14s%12s%S%S%s%s%s%s%s%s ","Graphics Card: ", getID(), " |Brand:", 
			getBrand(),  " |Model:", getModel(), "|Socket: ", getSocket(), "|  Price: ", "$"+getPrice());
	}
}

//===================================================================================================================

class Order {																						//creating Order class
//declaring variables CAPITALS for constant variables (No set methods for constants)																					//creating Order class
	private final String ORDER_ID;
	private ArrayList<ComputerPart> parts = new ArrayList<>();
	private double totalPrice = 0;
																	
//Order <<constructor>>	
	public Order(){
		ORDER_ID = "O" +  ((int) (10000.0+ 90000.0*Math.random()));
	}

//Order ID accessor
	public String getOrderID(){
		return ORDER_ID;
	}


//add parts method 	
	public void addComputerPart(ComputerPart cp){
		parts.add(cp);
		totalPrice += cp.getPrice();
		
	}


//remove parts method
	public void removeComputerPart(int i){
		totalPrice -= parts.get(i).getPrice();
		parts.remove(i);
		
	}
	
//accessor
	public ArrayList<ComputerPart> getComputerParts(){	
	return parts;
	}
	
//accessor
	public double getTotalPrice(){
		
		return totalPrice;
		
	}
		

//Order toString() override	
	@Override
	public String toString(){
		String totalPrice = Integer.toString((int) this.totalPrice);
		String order = "==================================++  Ordered Parts  ++================================== \n\n";
		
		for(int i = 0; i < parts.size(); i++){
			order +="("+(i+1)+")"+ parts.get(i) + "\n";
		}
		
		return String.format("%n%4s%s%n %n %s%n %4s%s%n",
			"Order ID: ", ORDER_ID,
			order, 
			"Total Price: $", totalPrice);
	} 
	
	
}

class DiscountOrder extends Order{
	
	private double discount;
	
//default constructor
	public DiscountOrder(){
		super();
		discount = 0.95;
		
	}

//constructor 
	public DiscountOrder(double d){
		super();
		discount = d;
	
	}
	
//accessor
	public double getDiscount(){
		return discount;
	}
	
//Order toString() override	
	@Override
	public String toString(){
		String totalPrice = Integer.toString((int) getTotalPrice());
		String order = "==================================++  Ordered Parts  ++================================== \n\n";
		
		for(int i = 0; i < getComputerParts().size(); i++){
			order += getComputerParts().get(i) + "\n";
		}
		 
		double finalPrice = ((double)getTotalPrice())* getDiscount();
		
		return String.format("%n%4s%s%n %n%4s%n%s%4s%n%s%.2f%n",
			"Order ID: ", getOrderID(), 
			order, 
			"Total Price: $", totalPrice, "Total with Member Discount: $", finalPrice );
	} 


}

//===================================================================================================================

class Customer {																					//creating Customer class	
//declaring variables CAPITALS for constant variables (No set methods for constants)	
	private final String CUSTOMER_ID;
	private String name;
	private String gender;
	private String mobileNumber;
	private String deliveryAddress;
	private ArrayList<Order> orders = new ArrayList<>();

	
//customer <<constructor>>	
	public Customer(String name, String gender, String mobileNo, String deliveryAddress, String ID){
		this.CUSTOMER_ID = ID;
		this.name = name;
		this.gender = gender;
		this.mobileNumber = mobileNo;
		this.deliveryAddress = deliveryAddress;
		
	}

//customer <<constructor>>	
	public Customer(String name, String gender, String mobileNo, String deliveryAddress){
		this(name, gender, mobileNo, deliveryAddress, "C" + ((int) (100000.0+ 900000.0*Math.random())));
		
	}
	

//customer default <<constructor>>	
	public Customer(){
	
	//using this for default constructor
		this("", "", "", "", "");

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
	
//orders accessor
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
//orders mutator
	public void addOrder(Order order){
		this.orders.add(order);
	}
	
//orders mutator
	public void removeOrder(int i){
		this.orders.remove(i);
	}
	

//Customer toString() override	
	@Override
	public String toString(){
		return String.format("%n%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n",
			" === Customer details === \n", "| ID: ", CUSTOMER_ID, "| Name: ", name,
			"| Gender: ", gender, "| Mobile Number: ", mobileNumber, "| Delivery Adress: ", deliveryAddress);
	}	
	
}


class MemberCustomer extends Customer{
	
	private final double DISCOUNT = 0.95; 
	
//default <<constructor>> 
	public MemberCustomer(){
		super();
			
	}

//<<constructor>> 	
	public MemberCustomer(String name, String gender, String mobile, String address, String ID){
		super(name, gender, mobile, address, ID);

	}

//discount accessor
	public double getDiscount(){
		return  DISCOUNT;
	}
	
	@Override
	public String toString(){
		
		return String.format("%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s%n","| ID: ", getCustID(), "| Name: ", getName(),
			"| Gender: ", getGender(), "| Mobile Number: ", getMobile(), "| Delivery Adress: ", getDeliveryAddress());
	}
}

//===================================================================================================================




