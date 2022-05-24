/*------------------------------------------------------
My name: Jordan BEARD
My student number:5981219
My course code: CSIT121
My email address:jmb086@uowmail.edu.au
Assignment number: 3
-------------------------------------------------------*/

import java. util. Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.System;
import java.util.Arrays; 

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Serializable;
import java.io.ObjectOutputStream;

public class OrderingSystem implements Serializable{																		//creating main OrderingSystem class
	
	//initialising ArrayList<>'s for storing customers, orders and parts
										//Textbook says its ok to declare/initialise without ArrayList type in second < >
		private ArrayList<ComputerPart> parts = new ArrayList<>();
		private ArrayList<Customer> customers = new ArrayList<>();


//main method	
	public static void main(String[] args){ 
	
	OrderingSystem os = new OrderingSystem();
	
	CreateOrderingSystem ser = new CreateOrderingSystem();
	
	ser.openFile();
	ser.addRecords(os);
	ser.closeFile();

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
		//class for serialization
class CreateOrderingSystem implements Serializable{
	
	//tracking variables
	private OrderingSystem os;
	private static ObjectOutputStream output;
	
	//open file to create and open serialisation destination
	public static void openFile(){
		
		//try/catch block to catch common exception
		try{
			
		//creating output file and setting it as stream destination
			output = new ObjectOutputStream(
				Files.newOutputStream(Paths.get("compshop.ser")));
		
		//Catching setup exception
		}catch(IOException ioE){
			System.err.println("Error opening file. Terminating.");
			System.exit(1); //Terminate program
		}	
	}
	
	//creating add records to file method
	public static void addRecords(OrderingSystem os){
		
		try{
			//write given object to file using output
			output.writeObject(os);
		}
		//catch IO exception
		catch(IOException ioE){
			System.err.println("Error writing to file. Terminating");
		}
		//catch general exceptions
		catch(Exception eE){
			System.err.println("Invalid input.");
		}
	}
	
	//method to close file  when complete
	public static void closeFile(){
		try{
			//make sure file and path is open
			if(output != null){
				output.close();
			}	
			//catch IO exception
		}catch(IOException ioE){
			System.err.println("Error closing file. Terminating.");
		}
	}	
	
}


//===================================================================================================================

interface Compatible{

	public boolean isCompatible(ComputerPart cp);

}

abstract class ComputerPart implements Compatible, Serializable{
	
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
	
	public String getForSummary(){
		
			return String.format("%s%s, %s%s, %s%s, %s%s", "Product ID: ", getID(),
				"Brand: ", getBrand(),  "Model: ", getModel(), "Price: ","$"+ getPrice());
	}
	
	public String getDetails(){
		
			return String.format("%s%s \n%s%s \n%s%s \n%s%s", "Product ID: ", getID(),
				"Brand: ", getBrand(),  "Model: ", getModel(), "Price: ","$"+ getPrice());
	}
		
//to string override for computer class	
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %s%s, %s%s, %s%s, %s%s, %s%s", "Memory: ", getID(), "Brand: ", getBrand(),
			"Model: ", getModel(), "Size: ", getSize(), "Socket Type: ", getSocketType(), "Price: ","$"+ getPrice());
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s \n%s%s", "Memory: ", getID(), "Brand: ", getBrand(),
			"Model: ", getModel(), "Size: ", getSize(), "Socket Type: ", getSocketType(), "Price: ","$"+ getPrice());
	}

//contract with interface
	@Override
	public boolean isCompatible(ComputerPart cp){
	
		return true;
		
	}

//Memory class to string override	
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s %s%s %s%s %s%s %s%s","Monitor: ", getID(), "Brand: ", getBrand(),
			 "Model: ", getModel(), "Size: ", getSize(), "Price: ", "$"+getPrice());
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s","Monitor: ", getID(), "Brand: ", getBrand(),
			 "Model: ", getModel(), "Size: ", getSize(), "Price: ", "$"+getPrice());
	}

//to string override for Monitor
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %s%s, %s%s, %s%s, %s%s","Motherboard: ", getID(), "Brand: ",
							getBrand(), "Socket Type: ", "Model: ", getModel(), "Price: ","$"+ getPrice());
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s","Motherboard: ", getID(), "Brand: ",
							getBrand(), "Socket Type: ", "Model: ", getModel(), "Price: ","$"+ getPrice());
		
	}
	
	public abstract boolean isCompatible(ComputerPart cp);
		
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %S%S, %s%s, %s%s, %s%s ","Motherboard: ", getID(), "Brand: ", 
			getBrand(),  " Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
				
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%S%S \n%s%s \n%s%s \n%s%s ","Motherboard: ", getID(), "Brand: ", 
			getBrand(),  " Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
				
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
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %S%S, %s%s, %s%s, %s%s ","Motherboard: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%S%S \n%s%s \n%s%s \n%s%s ","Motherboard: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
		
	}
	
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %s%s, %s%s, %s%s, %s%s", "CPU: ", getID(),
								"Brand: ", getBrand(), "Core Number: ", getCoreNumber(),
								"Model: ", getModel(), "Price: ","$"+ getPrice());
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s", "CPU: ", getID(),
								"Brand: ", getBrand(), "Core Number: ", getCoreNumber(),
								"Model: ", getModel(), "Price: ","$"+ getPrice());
		
	}


//to string override for CPU class	
	@Override
	public String toString(){
		 
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %S%S, %s%s, %s%s, %s%s ","CPU: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
				
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%S%S \n%s%s \n%s%s \n%s%s ","CPU: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
				
	}
	
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %S%S, %s%s, %s%s, %s%s ","CPU: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%S%S \n%s%s \n%s%s \n%s%s ","CPU: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
		
	}
	
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s,  %s%s, %s%s, %s%s ","Graphics Card: ", getID(), "Brand: ", getBrand(),  "Model: ", getModel(), "Price: ", "$"+getPrice());
		
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s  \n%s%s \n%s%s \n%s%s ","Graphics Card: ", getID(), "Brand: ", getBrand(),  "Model: ", getModel(), "Price: ", "$"+getPrice());
		
		
	}

//Graphics Card to string override
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %s%s, %s%s, %s%s, %s%s ","Graphics Card: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s ","Graphics Card: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
	}

//Graphics Card to string override
	@Override
	public String toString(){
		return String.format("%s", getID());
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
	
	public String getForSummary(){
		
		return String.format("%s%s, %s%s, %s%s, %s%s, %s%s ","Graphics Card: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
	}
	
	public String getDetails(){
		
		return String.format("%s%s \n%s%s \n%s%s \n%s%s \n%s%s ","Graphics Card: ", getID(), "Brand: ", 
			getBrand(),  "Model: ", getModel(), "Socket: ", getSocket(), "Price: ", "$"+getPrice());
		
	}
	
	@Override
	public String toString(){
		return String.format("%s", getID());
	}
}

//===================================================================================================================

class Order implements Serializable{																						//creating Order class
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
	
	public String getDetails(){
		
		String totalPrice = Integer.toString((int) this.totalPrice);
		String order = "=====++  Ordered Parts  ++===== \n\n";
		
		for(int i = 0; i < parts.size(); i++){
			order += parts.get(i).getForSummary() + "\n";
		}
		
		return String.format("%n%4s%s%n %n %s%n %4s%s%n",
			"Order ID: ", ORDER_ID,
			order, 
			"Total Price: $", totalPrice);
		
		
	}
		

//Order toString() override	
	@Override
	public String toString(){
		
		return String.format(ORDER_ID);
		
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
	
	@Override
	public double getTotalPrice(){
		
		return super.getTotalPrice();
		
	}
	
//accessor
	public double getDiscount(){
		return discount;
	}
	
	public String getDetails(){
		String totalPrice = Integer.toString((int) getTotalPrice());
		String order = "=====++  Ordered Parts  ++===== \n\n";
		
		for(int i = 0; i < getComputerParts().size(); i++){
			order += getComputerParts().get(i).getForSummary() + "\n";
		}
		 
		double finalPrice = ((double)getTotalPrice())* getDiscount();
		
		return String.format("%n%4s%s%n %n%4s%n%s%4s%n%s%.2f%n",
			"Order ID: ", getOrderID(), 
			order, 
			"Total Price: $", totalPrice, "Total with Member Discount: $", finalPrice );
		
	}
	
//Order toString() override	
	@Override
	public String toString(){
		
		
		return String.format(getOrderID());
	} 


}

//===================================================================================================================

class Customer implements Serializable{																					//creating Customer class	
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
		return String.format("%4s%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s",
			 "ID: ", CUSTOMER_ID, "Name: ", name,
			"Gender: ", gender, "Mobile Number: ", mobileNumber, "Delivery Adress: ", deliveryAddress);
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
		
		return String.format("%4s%s%n%4s%s%n%4s%s%n%4s%s%n%4s%s","ID: ", getCustID(), "Name: ", getName(),
			"Gender: ", getGender(), "Mobile Number: ", getMobile(), "Delivery Adress: ", getDeliveryAddress());
	}
}

//===================================================================================================================




