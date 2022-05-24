/*------------------------------------------------------
Assignment number: 3
-------------------------------------------------------*/

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSFrame extends JFrame implements ActionListener {
	
//tracking attributes 
	private final JPanel basePanel;
	private final JButton[] buttons;
	private ComputerPart selected;
	private Order presentOrder = new Order();
	private ArrayList<ComputerPart> parts = new ArrayList<>();
	private DefaultListModel <ComputerPart> currentOrderList = new DefaultListModel<>();
	private DefaultListModel <Order> orderHistoryList = new DefaultListModel<>();
	private ArrayList<Customer> customers = new ArrayList<>();
	private String currentOrderString = "";
	private JFrame frame;
	private Customer cust = null;
	private static Formatter output; //outputs text to a file
	

	public static void main(String[] args){
		//creating instance of GUI
		CSFrame cf = new CSFrame("Ordering System");
		
	}
	
	public static void openFile(Customer cust){
		
		//create and open file using customer ID for name. Catch common exceptions
		try{
			output = new Formatter(cust.getCustID() + ".txt");
		}catch(SecurityException securityException){
			
			System.err.println("Write permission denied. Terminating.");
			
			
		}catch(FileNotFoundException fileNotFoundException){
			
			System.err.println("Error opening file. Terminating.");
			
			
		}
	}
	
	public static void addRecords(Customer cust){
		
		//Build string and add to created file catching common exceptions.
		try{
			String records = "Customer order records \n";
		
			records += cust.toString() + "\n ===================================== \n";
		
			for(Order ord: cust.getOrders()){
			
				records += ord.getDetails() + "\n ===================================== \n";
			
			}
		
			output.format(records);
			
			
		}catch(FormatterClosedException formatterClosedException){
			System.err.println("Error writing to file. Terminating.");
		}catch(NoSuchElementException nsee){
			
			System.err.println("Invalid input. Terminating");
		}
		
	}
	
	public static void closeFile(){
		//if output exists close 
		if(output != null)
			output.close();	
	}

//GUI constructor
	public CSFrame(String s){
	
	//super class constructor
		super(s);
	
	//declaring Ordering System instance to be initialized through serialisation
		OrderingSystem os = null;
		
	//attempting to initialise os through serialization with try/catch statements
        try{
		
		//instantiation of ObjectInputStream for deserialisation from current directory
            ObjectInputStream osInput = new ObjectInputStream(Files.newInputStream(Paths.get("compshop.ser")));
     
	//initialising and casting input to Ordering System
            os = (OrderingSystem) osInput.readObject();
                   
        }catch(IOException | ClassNotFoundException ie){
	//if exception print and exit system.
            System.err.println(ie);
            System.exit(1);
        }

		//OrderingSystem os = new OrderingSystem();
		customers = os.getCustomers();
		
		//tracking the exit button and allowing for more buttons if necessary
		buttons = new JButton[1];
	
		//creating new window for GUI
		frame = new JFrame(); 
		frame.setTitle("Ordering System"); // sets frame title
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit app on close button
		
		//restrict frame to set size
		frame.setResizable(false);
		frame.setSize(900,900); //sets x and y dimension of frame
		
		//add panel for components 
		basePanel = new JPanel();
		basePanel.setLayout(new GridLayout(0,2,5,5)); //set 2 columns with undefined rows for use as needed
		Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
		basePanel.setBorder(padding); // create padding inset from frame
		
		
		//add base panel to frame
		frame.add(basePanel);
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				//building list of available parts and responsive part info window
				
		JPanel itemPanel = new JPanel(new GridLayout(1,2)); //creating panel for separated info		
		ArrayList<ComputerPart> i = os.getComputerParts(); //retrieve computer parts from ordering system
		ComputerPart[] cp = new ComputerPart[i.size()]; //create empty array for parts
		
		
		JTextArea partDetails = new JTextArea();  //new text area for specific part info
		partDetails.setEditable(false);			  //make info uneditable
		
		
		//Assuming there is data in list
		JList<ComputerPart> partsList = new JList<>(i.toArray(cp)); //create JList from computer part array (now non empty)
		partsList.setToolTipText("Available parts for order");      //assisting order
		JScrollPane partListPane = new JScrollPane();				//create scroll pane for JList
		partListPane.setViewportView(partsList);					//add parts to scroll pane
		partsList.setLayoutOrientation(JList.VERTICAL);				//set vertical orientation	
		
		
		
		
		partsList.addListSelectionListener(		//adding new list event action listner
			new ListSelectionListener(){
				
				@Override
				public void valueChanged(ListSelectionEvent event){
				
				if(cust!=null){	//if cust logged in i.e. not null
					
						
						selected = partsList.getSelectedValue();	//get selected computer part 
						String selectedItem = partsList.getSelectedValue().getDetails();  //get details from selected item
				
						partDetails.setText(selectedItem); // add selectedItem to part infro scroll pane.
						partDetails.setToolTipText("Highlighted part details"); //assisting order process
					
					
					//if not logged in send info box requesting log in
				}else{
					JOptionPane.showMessageDialog(frame, String.format("Please log before beginning order."));	
				}	
			}
		});
		
		
		
		//creating border option for text box
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						//building list for items on current order and its information
		
		
		JPanel itemPanel2 = new JPanel(new GridLayout(1,2)); // new panel so part details and incompatible parts can be included, 1 row 2 columns
		
		JTextArea orderedPartDetails = new JTextArea();		//text area for part info
		orderedPartDetails.setEditable(false);
		orderedPartDetails.setToolTipText("Current order part information");
		
		JTextArea orderedPartCompat = new JTextArea();		//text area for order part compatiblity 
		orderedPartCompat.setEditable(false);
		orderedPartCompat.setToolTipText("Incompatible parts");  //user assistance
		
		JScrollPane orderedPartCompatScroll = new JScrollPane(orderedPartCompat);		//making text areas scrollable
		JScrollPane orderedPartDetailsScroll = new JScrollPane(orderedPartDetails);
		
		itemPanel2.add(orderedPartDetailsScroll);										//adding to panel 
		itemPanel2.add(orderedPartCompatScroll);
		
		//Assuming there is data in list
		JList<ComputerPart> order = new JList<>(currentOrderList);	//creating list from current order parts
		JScrollPane currOrder1 = new JScrollPane(order);			//making list scrollable
		order.setLayoutOrientation(JList.VERTICAL);					//set vertical 
		
		order.addListSelectionListener(		//adding new list event action listner
			new ListSelectionListener(){
				
				@Override
				public void valueChanged(ListSelectionEvent event){ //if value changed do following
					
					int index = order.getSelectedIndex(); //get index of item from list
					
					String warning =""; //instantiate warning string
					int f = 0;			//instantiate tracker
					String inCompParts = "";	//instantiate part list string
					
					if(cust!=null){				//if logged in
						if (index != -1) {		//if item selected
							selected = order.getSelectedValue();        //get part
							String selectedItem = order.getSelectedValue().getDetails();  //get part details
							orderedPartDetails.setText(selectedItem);		//add details to text area
							
									//compare part to all items in list and check compatibilty
							for(int i=0; i < presentOrder.getComputerParts().size(); i++){
								
								boolean comp = true;
								ComputerPart part2 = presentOrder.getComputerParts().get(i);
								comp = selected.isCompatible(part2); //check and change to false if incopatible
					
				//if incompatible add to string for printing otherwise ignore
								if(comp == false){
											
									inCompParts += part2.getBrand()+": "+part2.getID()+" \n";
									f++;	 //add to tracker to know whether to include in text area
								}	
							}
							if(f > 0){
				
				//only add to string if incompatible parts found for initial part
								warning += "-------------------    Incompatible Parts    ------------------- \n"+ inCompParts;
								f = 0; 
								orderedPartCompat.setText(warning);
								
							}else{
								//if no incompatible parts found make text area empty
								orderedPartCompat.setText("");
							}
							
							
						}
					}else{ //if cust not selected than request login
						JOptionPane.showMessageDialog(frame, String.format("Please log before beginning order."));		
					}
				}
			}
		);
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			//creating current order total pane for bottom right of the window
		
		JTextArea currentTotal = new JTextArea("\nCurrent Order Total: $" + presentOrder.getTotalPrice(), 0, 30);
		currentTotal.setFont(new Font("Arial Black", Font.BOLD, 20));
		currentTotal.setEditable(false);
		currentTotal.setToolTipText("Current order total. Does not including member discount.");
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//creating add item to order button
		
		JButton addItem = new JButton("Add Item to Order"); //create JButton
		
		addItem.addActionListener(  //add action listener
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event){
					int index = partsList.getSelectedIndex(); //get selected part list index
					
					if(cust!=null){ //if customer logged on continue 
						if(index != -1){
							selected = partsList.getSelectedValue();
							currentOrderList.addElement(selected); //add selected part to order list
							presentOrder.addComputerPart(selected); //add part to order
							currentTotal.setText("\nCurrent Order Total: $" + presentOrder.getTotalPrice()); //update current order total price
						
						}
					}else{ //if cust is null request login
						JOptionPane.showMessageDialog(frame, String.format("Please log before beginning order."));	
					}	
				}
			}
		);
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//buidling remove part from order option
		
		JButton removePart = new JButton("Remove Part"); //create JButton
		removePart.setToolTipText("Remove highlighted part from order");
		
		removePart.addActionListener(  //add action listner
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event){
					
					int index = order.getSelectedIndex();
					
					if (index != -1) { //if an item is selected 
					
						currentOrderList.removeElementAt(index); //remove item from list 
						orderedPartDetails.setText(""); //clear infro text areas
						orderedPartCompat.setText("");
						presentOrder.removeComputerPart(index); //remove item from order
						currentTotal.setText("\nCurrent Order Total: $" + presentOrder.getTotalPrice()); //update current total 
					}
				}
			}
		);
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		//creating button panel for login options
		
		JPanel logPanel = new JPanel(new GridLayout(2,1));
		
		//text area for customer details upon loging
		JTextArea customerDetails = new JTextArea("", 0, 15);
		customerDetails.setToolTipText("Current customer information");
		customerDetails.setEditable(false);
		Box box = Box.createHorizontalBox();
		box.add(new JScrollPane(customerDetails));
		
		
			
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
		//member login process

		JButton memberLog = new JButton("Member Login"); //create memebr login button
		memberLog.setToolTipText("Login as member with this button");
		
		memberLog.addActionListener( new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				JFrame frame1 = new JFrame(); //create new login window
				frame1.setResizable(false);
		
				JPanel basePanel = new JPanel();	//new panel for elements
				basePanel.setLayout(new GridLayout(0,1,5,5)); //1 column, undefined rows for use as necessary
				frame1.add(basePanel);						//add to base panel 
		
				Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10); //add padding to base panel
				basePanel.setBorder(padding);
		
				frame1.setVisible(true); //make frame visible
			
			
				frame1.setSize(400,150);			//set size
				frame1.setTitle("Member Login");	//set window title
			
				JLabel memLog = new JLabel("Enter Customer Number"); //create label requesting customer input
			
				memLog.setBounds(5,0,100,50);					//formatting label	
				memLog.setFont(new Font(null, Font.PLAIN,14)); 
			
			
				JTextField customerNumber = new JTextField("");		//new text area to retrieve customer input
			
				JButton login = new JButton("Login"); 		// new button for login 
				basePanel.add(memLog);						//add elements to base panel
				basePanel.add(customerNumber);
				basePanel.add(login);
			
				login.addActionListener(	//action listener for loging button pressed
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event){
						
							//tracker
							boolean found = false;
						
					
							for (int i = 0; i < customers.size(); i++){
					
							//get id for each customer in array
								String z = customers.get(i).getCustID();
					
							//testing equality
								if (customerNumber.getText().equals(z)){
									found = true;
									
									
							//update tracker
									cust = customers.get(i);
									presentOrder = new DiscountOrder();
									
								//if customer found add previous orders to array
								 for(Order ord : cust.getOrders())
									orderHistoryList.addElement(ord);
									
							//print user to info panel
									customerDetails.setText(customers.get(i).toString());
									customerDetails.setEditable(false);
									
								}
							}
							
							frame1.dispose(); //close login frame 
							
							if(found){			
								//if customer found display welcome window
								JOptionPane.showMessageDialog(frame1, String.format("Welcome!"));
								
							}else{ //if not found request loging as new user
								JOptionPane.showMessageDialog(frame1, String.format("User not found. Please log in as new user"));
							}								
						}
					}
				);
			}
		});
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			//creating new customer loging process
	
		JButton newLog= new JButton("New Customer Login"); //create button 
		newLog.setToolTipText("New customers login with this button");
		
		newLog.addActionListener( //add action lister to button
			new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				JFrame frame1 = new JFrame();  // as above create frame and panel for components
				frame1.setResizable(false);
		
				JPanel basePanel = new JPanel();
				basePanel.setLayout(new GridLayout(0,1,5,5));
				frame1.add(basePanel);
		
				Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
				basePanel.setBorder(padding);
		
				frame1.setVisible(true); //make frame visible
			
				frame1.setTitle("New Customer");
				frame1.setSize(400,200); //sets x and y dimension of frame
			
				JLabel newLog = new JLabel("New Customer Details");
			
				newLog.setBounds(5,0,100,50);
				newLog.setFont(new Font(null, Font.PLAIN,18));
				
				String[] genderOp = {"Gender", "Male", "Female", "Other"};
				
			
				JTextField customerName = new JTextField("Full Name"); //adding components required for login
				JComboBox<String> customerGender = new JComboBox<>(genderOp);
				customerGender.setSelectedIndex(0);
				JTextField customerAddress = new JTextField("Address");
				JTextField customerPhone = new JTextField("Phone #");
			
				JButton login = new JButton("Create Customer and Login"); //new button for login attempt
			
				basePanel.add(newLog);					//add componenets to base panel 
				basePanel.add(customerName);
				basePanel.add(customerGender);
				basePanel.add(customerAddress);
				basePanel.add(customerPhone);
				basePanel.add(login);
			
			
			
			
				login.addActionListener(			//action listener for login request
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event){
							
							ExClass e = new ExClass(); //create exception class to test
							boolean test = e.exMethod(customerName.getText(),(String)customerGender.getSelectedItem(),customerPhone.getText(), customerAddress.getText()); //test user input 
							
							if(test){ //if test passed 
							
								cust = new Customer(customerName.getText(), (String)customerGender.getSelectedItem(),    //create customer
										customerPhone.getText(), customerAddress.getText());
							
								customers.add(cust); //add customer to customer list
							
								presentOrder = new Order();			// create regular order 
								
								customerDetails.setText(cust.toString()); //set customer info pane details
								
								frame1.dispose();   //close frame 
								
								JOptionPane.showMessageDialog(frame1, String.format("Welcome!"));  //show welcome window
								
								
							}else{
								JOptionPane.showMessageDialog(frame1, String.format("Please input valid information"));   //if test failed request valid input
							}									
						}
					}
				);
			}	
		});
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		
		JLabel currOrderLab = new JLabel("Current Order");				//create info labels
		JLabel selectedPart = new JLabel("Selected Part Details");
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		//creating submit order button and action
		

		JButton submitOrder = new JButton("Submit Order");    //create JButton
		submitOrder.setToolTipText("Submit the current order here");
		
		submitOrder.addActionListener( //action listener for button pressed
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event){
							
							if(cust!=null){ //if customer logged in
							
								cust.addOrder(presentOrder); //add order to customer 
								JOptionPane.showMessageDialog(frame, String.format(presentOrder.getDetails()));  //display order details in new window
								orderHistoryList.addElement(presentOrder); //add order to list for historical viewing
							
								currentOrderList.removeAllElements();		//remove lemmenets from current order
								orderedPartDetails.setText("");				//set info panes to empty 
								orderedPartCompat.setText("");
							
								if(presentOrder instanceof DiscountOrder){		//create new order based on current customer status
									presentOrder = new DiscountOrder();
								}else{
									presentOrder = new Order();
								}
								currentTotal.setText("\nCurrent Order Total: $" + presentOrder.getTotalPrice()); //update current total
							}
						}
					}
		);
			


			
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//create previous order display window labels
		
		JLabel prevOrderLab = new JLabel("Previous Orders"); //create labels 
		JLabel prevOrderPartLab = new JLabel("Previous Order's Details");


		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				//create previous order display window
		
	
		JTextArea prevOrderDetails1 = new JTextArea();   //text area for previous order 
		prevOrderDetails1.setEditable(false);
		prevOrderDetails1.setToolTipText("Highlighted order in detail.");
		JScrollPane prevOrderDetails = new JScrollPane(prevOrderDetails1);  //make scrollable
		
		//Assuming there is data in your list
		JList<Order> orderHistory = new JList<>(orderHistoryList);  //create previous order list
		orderHistory.setToolTipText("Current customers previous orders.\nSelect order number for more information.");
		JScrollPane orderHistoryScroll = new JScrollPane(orderHistory);
		orderHistory.setLayoutOrientation(JList.VERTICAL);
		
		orderHistory.addListSelectionListener( //add action listener if item selected add order information to tet area
			new ListSelectionListener(){
				
				@Override
				public void valueChanged(ListSelectionEvent event){
					int index = orderHistory.getSelectedIndex();
					if (index != -1) {
						Order selected = orderHistory.getSelectedValue();
						String selectedItem = orderHistory.getSelectedValue().getDetails();
						prevOrderDetails1.setText(selectedItem);
					}	
				}
			}
		);
		
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//remove order functionality
		
		JButton deleteOrder = new JButton("Delete Order");   //create button
		deleteOrder.setToolTipText("Cancel/delete highlighted order");
		
		deleteOrder.addActionListener( //add action listener for button pressed
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event){ //if an item is secelected remove order from list and customer, then set order details window to empty.
					
					int index = orderHistory.getSelectedIndex();
					
					if (index != -1) {
						orderHistoryList.removeElementAt(index);
						cust.removeOrder(index);
						prevOrderDetails1.setText("");
					}
				}
			}
		);
			
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//empty panel for aesthetic design
		
		JButton exportCreater = new JButton("EXPORT ORDERS");
		exportCreater.setToolTipText("Export all users previous orders to text file");
		
		exportCreater.addActionListener(  //add action listner
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event){
					
					if (cust != null) { //if cust exists 
						openFile(cust);
						addRecords(cust);
						closeFile();
						
						//check file was created if so let user know 
						File tempFile = new File(cust.getCustID()+".txt");
						boolean exists = tempFile.exists();
						
						 if (exists){
							 JOptionPane.showMessageDialog(frame, String.format("Your order history has been exported to " + cust.getCustID() + ".txt"));
						 }
					}
				}
			}
		);
		
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		//creating clear red exit button with big clear writing.
		
		JButton exitButton= new JButton("EXIT");
		exitButton.setBackground(Color.RED);
		exitButton.setFont(new Font("Arial Black", Font.BOLD, 30));
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(this);
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			//adding components to panels (where necessary) and base panel
			
		logPanel.add(memberLog);
		logPanel.add(newLog);
		
		itemPanel.add(partListPane);
		itemPanel.add(partDetails);	
		
		basePanel.add(logPanel);
		basePanel.add(box);
				
		basePanel.add(addItem);
		basePanel.add(itemPanel);	
		basePanel.add(currOrderLab);
		basePanel.add(selectedPart);
		basePanel.add(currOrder1);
		basePanel.add(itemPanel2);
		basePanel.add(removePart);
		basePanel.add(submitOrder);
		basePanel.add(prevOrderLab);
		basePanel.add(prevOrderPartLab);
		basePanel.add(orderHistoryScroll);
		basePanel.add(prevOrderDetails);
		basePanel.add(deleteOrder);
		basePanel.add(exportCreater);
		basePanel.add(exitButton);
		basePanel.add(currentTotal);
		
		
		buttons[0] = exitButton;
			
		frame.setVisible(true); 						//make frame visible
		ImageIcon image = new ImageIcon("Trash.jpg");   //make icon 
		frame.setIconImage(image.getImage());			//set icon	

	}
	
	
	@Override    //general action perforemed method only ended up using for exit
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==buttons[0]){	
			System.exit(1);		
		}
	}
}

class ExClass{
	
	//exception class built for testing new customer login rules. i.e. info has been changed from directions and has not been left empty 
	public boolean exMethod(String name, String gender, String number, String address) throws InputMismatchException{
		boolean test = true;
		try{	
			if(name.equals("") ||
				name.equals("Full Name")||
				gender.equals("") || gender.equals("Gender")||
				number.equals("") ||
				number.equals("Phone #") ||
				address.equals("") ||
				address.equals("Address")){
					
					throw new InputMismatchException();
			}
								
		}catch (InputMismatchException e){
			
			test = false;
		}
		
		return test;	
	}	
}


class OrderingSystem implements Serializable{																		//creating main OrderingSystem class
	
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



