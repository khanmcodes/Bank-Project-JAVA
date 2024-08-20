//Name: Khan Muhammad
//CMS: 023-22-0199
//Program: Bankpro.java
//Date Created: 26-2-2023
//Date Modified: 16-4-2023

//Note before leaving: 

import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;

class database				// DATABASE CLASS
{
	int x=0;
	ArrayList<Date> dwDate;
	ArrayList<String> dwTask;
	ArrayList<Integer> dwAmount;
	
	public database()
	{
       		this.dwDate = new ArrayList<Date>();
        		this.dwTask = new ArrayList<String>();
        		this.dwAmount = new ArrayList<Integer>();
    	}

	void T_data(Date transDate, String transTask, int transAmount)
	{	
		x++;
		this.dwDate.add(transDate);
	        	this.dwTask.add(transTask);
	        	this.dwAmount.add(transAmount);
	}
		
	void T_display()
	{
		System.out.println(" AMOUNT\t\t TRANSACTION\t\tDATE/TIME\n");

		for(int i=0; i<x; i++)
		{
			if(this.dwTask.get(i).equals("Deposit"))
			{
				System.out.println("  "+this.dwAmount.get(i)+"\t\t "+((char)24)+" "+this.dwTask.get(i)+"\t\t"+this.dwDate.get(i));
			}

			else if(this.dwTask.get(i).equals("Withdraw"))
			{
				System.out.println("  "+this.dwAmount.get(i)+"\t\t "+((char)25)+" "+this.dwTask.get(i)+"\t\t"+this.dwDate.get(i));
			}

			

			/* 	<----------	   for testing transactions
			
			for(int i : dwAmount)
			{
				System.out.println(i);
			}

			for(String t : dwTask)
			{
				System.out.println(t);
			}
	
			for(Date d : dwDate)
			{
				System.out.println(d);
			}
		
			*/
		}
	}
}

class Admin						// ADMIN CLASS
{
	static Bankpro bankObj = new Bankpro();
	database DB = new database();		

	String username;
	String passcode;
	String name;
	int age;
	String dob;
	String phone;
	String type;
	int balance;	

	int iban;

	Admin[] r = new Admin[50]; 
	
	static int requests=0;

	Admin() {}

	Admin(String u, String p, String n, int a, String dob, String ph, String t, int b)
	{
		username = u;
		passcode = p;
		name = n;
		age = a;
		this.dob = dob;
		phone = ph;
		type = t;
		balance = b;
		requests+=1;
	}

	public static Scanner input = new Scanner(System.in);


	//String adminUN = "khanm12345";
	//String adminP = "qwe123";

	String adminUN = "admin";
	String adminP = "admin";

	void ibanGenerator(Bankpro user)
	{

	} 

	void adminLandPage()					// ADMINS LANDING PAGE 
	{	
		bankObj.cls();
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.print("\n Username: ");
		String adminUser = input.nextLine();		
		System.out.print(" Passcode: ");
		String adminPass = input.nextLine();

		if((!adminUser.equals(adminUN)) || (!adminPass.equals(adminP)))
		{
			System.out.println("\n Account Verification Unsuccessful!");
			System.out.print("\n Press [Enter] to try again...\t");
			input.nextLine();
			adminLandPage();
		}
		
		else
		{
			System.out.println("\n Account Verification Successful!");
			System.out.print("\n Press [Enter] to continue...\t");
			input.nextLine();
			adminDash();
		}
	}

	void adminDash()
	{
		bankObj.cls();
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.println("\n 1. Account Requests  ("+requests+")");		
		System.out.println(" 2. List Of Users");
		System.out.println(" 3. Recent Activities");
		System.out.println(" 4. Log Out");
		int opt = 0;		
		
		while(opt!=1 && opt!=2 && opt!=3 && opt!=4)
		{		
			System.out.print("\n Select..\t");

			try
			{
				opt = input.nextInt();
			}

			catch(InputMismatchException e)
			{
				System.out.println("\n Invalid input. Please enter a number.");
				input.next();	//consumes invalid entered input
			}
			
			if(opt!=1 && opt!=2 && opt!=3 && opt!=4)
			{
				System.out.println("\n Invalid option. Please enter 1 to 4.");
			}
		}

		if(opt == 1) {accountReq();}
		else if(opt == 2) {listOfUsers();}
		else if(opt == 3) {recentAct();}
		else if(opt == 4) {input.nextLine();}
	}

	void accountReq()
	{
		bankObj.cls();
		System.out.println("\n\t\t\t=== BANK AL-NASEEB ===");

		System.out.println("\n------------------------------------------------------------------------------");
		System.out.println(" "+requests+" Pending Requests");
		System.out.println("------------------------------------------------------------------------------");
		
		if(requests!=0)
		{
			System.out.println("\n REQUEST#\tUSERNAME\t\tFULL NAME\t\tACCOUNT TYPE\n");
			for(int i=0; i<requests; i++)
			{
				System.out.println("    "+(i+1)+"  \t\t "+r[i].username+"\t\t\t"+r[i].name+"\t\t "+r[i].type);
			}
		}

		
		System.out.println("\n------------------------------------------------------------------------------");
		System.out.println(" "+bankObj.users+" Approved Requests");
		System.out.println("------------------------------------------------------------------------------");
		
		System.out.println("\n USER#\t\tUSERNAME\t\tFULL NAME\t\tACCOUNT TYPE\n");

		for(int i=0; i<bankObj.users; i++)
		{
			System.out.println("   "+(i+1)+"   \t\t "+bankObj.b[i].username+"\t\t\t"+bankObj.b[i].name+"\t\t "+bankObj.b[i].type);
		}

		System.out.println("\n\n------------------");
		input.nextLine();

		boolean userFound = false;
		while(!userFound)
		{
			System.out.print("Type a username: ");
			String openUser = input.nextLine();

			for(int i=0; i<requests; i++)
			{
				if(openUser.equals(r[i].username))
				{
					userData(i);
					userFound = true;
					break;
				}
			}
	
			for(int i=0; i<bankObj.users; i++)
			{
				if(openUser.equals(bankObj.b[i].username))
				{
					userData(i);
					userFound = true;
					break;
				}
			}

			System.out.println(" This username was not found in the list, Please try again.");
		}
	}

	void userData(int index)
	{
		bankObj.cls();
		System.out.println("\n\t\t=== BANK AL-NASEEB ===");
		System.out.println("\n ACCOUNT DETAILS:");
		System.out.println("\n--------------------------");
		System.out.println(" Personal Information: ");
		System.out.println("--------------------------\n");
		System.out.println(" Full Name\t :     "+bankObj.b[index].name);
		System.out.println(" Age\t\t :     "+bankObj.b[index].age);
		System.out.println(" Date Of Birth\t :     "+bankObj.b[index].dob);
		System.out.println(" Phone Number\t :     "+bankObj.b[index].phone);
		System.out.println("\n--------------------------");
		System.out.println(" Account Information: ");
		System.out.println("--------------------------\n");
		System.out.println(" Account Type\t :     "+bankObj.b[index].type);
		System.out.println(" Account Number\t :     "+bankObj.b[index].iban);
		System.out.println(" Current Balance :     "+bankObj.b[index].balance);
		System.out.println("\n--------------------------");
		System.out.println(" Confidential Information: ");
		System.out.println("--------------------------\n");
		System.out.println(" Username\t :     "+bankObj.b[index].username);
		System.out.println(" Passcode\t :     "+bankObj.b[index].passcode);
	}

	void listOfUsers()
	{
		bankObj.cls();
		System.out.println("\n\t\t     === BANK AL-NASEEB ===\n");
		System.out.println("-----------------------------------------------------------------");
		System.out.println(" USERNAME\t\tFULL NAME\t\tACCOUNT TYPE");
		System.out.println("-----------------------------------------------------------------");
		
		for(int i=0; i<bankObj.users; i++)
		{
			System.out.println("  "+bankObj.b[i].username+"\t\t"+bankObj.b[i].name+"\t\t "+bankObj.b[i].type);
		}
	
		System.out.println("\n\n------------------");
		input.nextLine();

		boolean userFound = false;
		while(!userFound)
		{
			System.out.print("Type a username for more details: ");
			String openUser = input.nextLine();

			for(int i=0; i<requests; i++)
			{
				if(openUser.equals(r[i].username))
				{
					userData(i);
					userFound = true;
					break;
				}
			}
	
			for(int i=0; i<bankObj.users; i++)
			{
				if(openUser.equals(bankObj.b[i].username))
				{
					userData(i);
					userFound = true;
					break;
				}
			}

			if(!userFound)
				System.out.println(" This username was not found in the list, Please try again.");
		}

		System.out.println("\n------------------\n");
		System.out.print("\nPress [ENTER] For Admin Dashboard...  ");
		input.nextLine();
		adminDash();
	}

	void recentAct()
	{
		
	}
}

class Bankpro					// MAIN CLASS
{
	String name;
	int age;
	String dob;
	String phone;
	String type;
	int iban;
	int balance;
	private int trans;
	
	String username;
	String passcode;
	static int users = 0;
	 
	public static Scanner input = new Scanner(System.in);
	database DB = new database();		

	Bankpro(){}

	Bankpro(String u, String p, String n, int a, String dob, String ph, String t, int b)
	{
		users+=1;
		this.username = u;
		this.passcode = p;
		this.name = n;
		this.age = a;
		this.dob = dob;
		this.phone = ph;
		this.type = t;
		this.balance = b;
			
		if(this.type.equals("Saving"))
		{
			this.trans = 3;	
			this.minBal = 500;
		}

		else
		{
			this.minBal = 1000;
		}
	}	
	
	public static Bankpro[] b = new Bankpro[30];

	public static Bankpro userLogin = new Bankpro();

	Admin admin = new Admin();

	public static void cls()			//CLS Method
	{  
    		try
		{
       			if (System.getProperty("os.name").contains("Windows"))
			{
            			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        			}
		
			else
			{
            			new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        			}

    		}
	
		catch (IOException | InterruptedException ex)
		{
        			// handle exception
    		}
	}	

	void mainLandPage()					// MAIN LANDING PAGE 
	{
		cls();
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.println("\n 1. Admin Portal");
		System.out.println(" 2. Costumer Portal");
		int opt = 0;		
		
		while(opt!=1 && opt!=2)
		{		
			System.out.print("\n Select Portal (1/2)\t");

			try
			{
				opt = input.nextInt();
			}

			catch(InputMismatchException e)
			{
				System.out.println("\n Invalid input. Please enter a number.");
				input.next();	//consumes invalid entered input
			}
			
			if(opt!=1 && opt!=2)
			{
				System.out.println("\n Invalid option. Please enter 1 or 2.");
			}
		}
			//if(opt == 1) {admin.adminDash();}
			if(opt == 1) {admin.adminLandPage();}
			else if(opt == 2) {costLandPage();}
	}

	

	void costLandPage()					// COSTUMERS LANDING PAGE 
	{
		cls();
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.println("\n 1. Log In");
		System.out.println(" 2. Create an account!");
		System.out.println(" 3. Back\n");
		int opt = 0;		
		
		while(opt!=1 && opt!=2 && opt!=3)
		{		
			System.out.print("\n Select.. \t");

			try
			{
				opt = input.nextInt();
			}

			catch(InputMismatchException e)
			{
				System.out.println("\n Invalid input. Please enter a number.");
				input.next();	//consumes invalid entered input
			}
			
			if(opt!=1 && opt!=2)
			{
				System.out.println("\n Invalid option. Please enter 1 to 3");
			}
		}

		if(opt == 1) {login();}
		else if(opt == 2) {newAcc();}
		else if(opt == 3) {mainLandPage();}
	}

	void login()					// LOGIN METHOD
	{
		cls();
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.print("\n Username: ");
		input.nextLine();
		this.username = input.nextLine();
		//System.out.print("\n Passcode: ");
		//this.passcode = input.nextLine();
		
		while (true) 
		{
			System.out.print("\n Passcode: ");
			this.passcode = input.nextLine();
	    	
			if(!this.passcode.matches("\\d{4}"))  
			{
	       			System.out.println("\n Invalid input. Please enter a 4-digit number");
	    		}
		
			else
				break;
		}

		loginCheck(this.username, this.passcode);
	}

	void newAcc()
	{
		cls();
		
		String f, l, at="Current", u, p1, p2, ph;
		int mD;
		
		System.out.println("\n=== BANK AL-NASEEB ===");
		System.out.println("\n Please enter your information below...");
		System.out.print("\n First Name : ");
		input.nextLine();
		f = input.nextLine();
		System.out.print("\n Last Name : ");
		l = input.nextLine();

		System.out.print("\n DOB (DD/MM/YYYY) : ");
		dob = input.nextLine();
		int dd = Integer.parseInt(dob.substring(0,2));
		int mm = Integer.parseInt(dob.substring(3,5));
		int yyyy = Integer.parseInt(dob.substring(6,10));
		LocalDate birthdate = LocalDate.of(yyyy, mm, dd);
      		LocalDate today = LocalDate.now();
      		age = Period.between(birthdate, today).getYears();
		
		System.out.print("\n Phone# : +92-");
		ph = input.nextLine();
		System.out.println("\n Account Type : \n");
		System.out.println(" 1) Current Account");
		System.out.println(" 2) Saving Account");
		System.out.println(" 3) Fixed Deposit Account");
		System.out.println(" 4) Foreign Currency Account");
		System.out.print("\n Select... ");
		int opt = input.nextInt();
		
		switch(opt)
		{
			case 1:	 {at = "Current"; break;}
			case 2: {at = "Saving"; break;}
		}

		input.nextLine();
		
		while(true)
		{
			System.out.print("\n Username : ");
			u = input.nextLine();
			
			boolean usernameExists = false;

			for(int i=0; i<users; i++)
			{
				if(b[i].username.equals(u))
				{
					usernameExists = true;
					break;
				}
			}

			if(usernameExists)
			{
				System.out.println("\n Username Already Exists! Try a different one.");
			}
			
			else
			{
				break;
			}
		}
		

		do
		{
			while (true) 		//Taking Password
			{
				System.out.print("\n Passcode: ");
				p1 = input.nextLine();
	    	
				if(!p1.matches("\\d{4}"))  
				{
	       				System.out.println("\n Invalid input. Please enter a 4-digit number");
	    			}
		
				else {break;}
			}

			while (true) 		//Confirming Password
			{
				System.out.print("\n Re-Enter Passcode: ");
				p2 = input.nextLine();
	    	
				if(!p2.matches("\\d{4}"))  
				{
	       				System.out.println("\n Invalid input. Please enter a 4-digit number");
	    			}
		
				else {break;}
			}

			if(!p1.equals(p2))
			{
				System.out.println("\n Passcode Didn't Match!");
				System.out.println("\n Enter Again..");
			}
		}

		while(!p1.equals(p2));


		while(true)
		{
			System.out.print("\n Min. Deposit : ");
			mD = input.nextInt();
			mDtest(mD, at);
			if(mD<userLogin.minBal)
			{
				System.out.println("\n Please insert minimum deposit of "+userLogin.minBal);
			}
		
			else {	break;	}
		}
		
		
		admin.r[admin.requests] = new Admin(u, p1, f+" "+l, age, dob, "+92"+ph, at, mD);

		System.out.println("\n Your Account's Request Has Been Submitted!\n\n NOTE: You will be able to log in to your account once your request has been accepted by the authorities.");
		System.out.print("\n\n Press any key to Exit...  ");		
		input.nextLine();
		input.nextLine();
		this.mainLandPage();
	}

	int minBal;

	void mDtest(int minDeposit, String accType)
	{
		if(accType.equals("Current"))
		{
			userLogin.minBal = 1000;
		}

		else if(accType.equals("Saving"))
		{
			userLogin.minBal = 500;
			userLogin.trans = 6;
		}
	}

	void loginCheck(String u, String p)		// LOGIN VERIFY METHOD
	{
		int found = 0;

		for(int i=0; i<users; i++)
		{
			if(b[i].username.equals(u))
			{
				if(b[i].passcode.equals(p))
				{
					System.out.println("\n LOGIN SUCCESSFULL!...");
					found = 1;
					this.userLogin = b[i];
					break;
				}

				else
				{
					found = 2;
					System.out.println("\n INCORRECT PASSWORD!");
					System.out.print("\n\n Press [ENTER] key to go back...");
					input.nextLine();
					//input.nextLine();
					break;
				}
			}

			else
			{
				found = 0;
			}

		}

		if(found == 0)
		{
			System.out.println("\n ACCOUNT DOESN'T EXIST!");
			System.out.print("\n\n Press [ENTER] key to go back...");
			input.nextLine();
			//input.nextLine();
			//break;
		}

		else if(found == 1)
		{
			System.out.print("\n Press [ENTER] key to continue...");
			input.nextLine();
			//input.nextLine();
			bankInterface(this.userLogin);
		}
	}

	void bankInterface(Bankpro user)			// BANK INTERFACE METHOD
	{
		cls();
		this.userLogin = user;
		System.out.println("\n=========== BANK AL-NASEEB ===========");
		System.out.println("\n "+userLogin.name+"\t       Balance = "+userLogin.balance);
		if(user.type.equals("Saving"))
		{
			System.out.println("\t\t       Transactions Left = "+userLogin.trans);
		}
		
		System.out.println("\n 1. Cash Deposit");
		System.out.println(" 2. Cash Withdraw");
		System.out.println(" 3. Transaction History");
		System.out.println(" 4. Log out");
		System.out.print("\n Select...  ");
		int opt=input.nextInt();
		
		switch(opt)
		{
			case 1: {userLogin.deposit(); break;}

			case 2: 
				{
					try
					{
						if(userLogin.type.equals("Saving") && userLogin.trans == 0)
						{
							throw new Exception("\n Sorry, No Remaining Transactions Left!");
						}
	
						else if(userLogin.balance==userLogin.minBal)
						{
							throw new Exception("\n Sorry, seems like your account balance has reached the minimum balance amount.");
						}
			
						else {userLogin.withdraw(); break;}
					}

					catch(Exception e)
					{
						System.out.println(e.getMessage());
						System.out.print("\n Press [ENTER] key to go back...\t");
						input.nextLine();
						input.nextLine();
						bankInterface(this.userLogin);
					}

					break;
				}

			case 3: {userLogin.tHistory(); break;}

			case 4: 
				{
					System.out.print("\n Are you sure you want to Log out? (Y/N)  ");
					char Opt = input.next().charAt(0);
				
					if(Opt=='Y' || Opt=='y')
					{
						input.nextLine();
						//landPage();
						break;
					}

					else if(Opt=='N' || Opt=='n')
					{
						bankInterface(userLogin);
					}
				}

			default: {System.out.println("\n UNKNOWN COMMAND!");}
		}
	}

	void deposit()					// CASH DEPOSIT METHOD
	{
		cls();
		System.out.println("\n=========== BANK AL-NASEEB ===========");
		System.out.println("\n "+userLogin.name+"\t       Balance = "+userLogin.balance);
		System.out.print("\n Enter Amount: ");
		int Dmoney = input.nextInt();
		System.out.println("\n\n DEPOSIT SUCCESSFULL!");

		System.out.print("\n Press [ENTER] key to get the receipt... ");
		input.nextLine();
		input.nextLine();
		this.statement('D', Dmoney);
	}

	void withdraw()					// CASH WITHDRAW METHOD
	{	
		int Wmoney=0;

		do
		{
			cls();
			System.out.println("\n=========== BANK AL-NASEEB ===========");
			System.out.println("\n "+userLogin.name+"\t       Balance = "+userLogin.balance);
			System.out.print("\n 1. 500                     4. 10,000");
			System.out.print("\n 2. 1000                    5. 25,000");
			System.out.print("\n 3. 5000                    6. 50,000");

			System.out.print("\n\n Select Any number or press [0] for\n custom withdraw amount...  ");
			int opt = input.nextInt();
			
			if(opt!=0)
			{
				switch(opt)
				{
					case 1: {Wmoney = 500; break;}
					case 2: {Wmoney = 1000; break;}
					case 3: {Wmoney = 5000; break;}
					case 4: {Wmoney = 10000; break;}
					case 5: {Wmoney = 25000; break;}
					case 6: {Wmoney = 50000; break;}
				}
			}

			else
			{
				while(true)
				{
					System.out.print("\n Enter Amount: ");
					Wmoney = input.nextInt();

					if(Wmoney>50000)
					{
						System.out.println("\n ERROR: Requested Withdraw Amount Exceeded The Maximum Withdraw Amount Of PKR 50,000");
					}
		
					else 
						break;
				}
			}

			if(Wmoney>=userLogin.balance)
			{
				System.out.println("\n ERROR: Unsufficiant Balance.");
				System.out.print("\n Press [ENTER] to Try Again... ");
				input.nextLine();
				input.nextLine();
				withdraw();
			}

			else if(userLogin.balance-Wmoney<userLogin.minBal)
			{
				System.out.println("\n ERROR: Requested Withdraw Amount Exceeded The Minimum Balance Required");
				System.out.print("\n Press [ENTER] to Try Again... ");
				input.nextLine();
				input.nextLine();
				withdraw();
			}
		}

		while((Wmoney>userLogin.balance)&&(userLogin.balance-Wmoney<userLogin.minBal));
		
		userLogin.trans-=1;
		System.out.println("\n\n WITHDRAWAL SUCCESSFULL!");
		System.out.println("\n Please Collect The Money Below..");
		System.out.print("\n Press [ENTER] key to get the receipt... ");
		input.nextLine();
		input.nextLine();
		this.statement('W', Wmoney);
	}

	void tHistory()
	{
		cls();
		System.out.println("\n=========== BANK AL-NASEEB ===========");
		System.out.println("\n--------------------------------------");
		System.out.println("\t   TRANSACTION HISTORY");
		System.out.println("--------------------------------------\n");
		userLogin.DB.T_display();
		System.out.println("\n--------------------------------------\n");
		
		System.out.print("\n Press any key to continue...");
		input.nextLine();
		input.nextLine();
		bankInterface(userLogin);
	}

	void statement(char opt, int amount)				// STATEMENT METHOD
	{
		cls();
		Date today = new Date();
		
		if(opt == 'W')
		{
			System.out.println("\n=========== BANK AL-NASEEB ===========");
			System.out.println("\n--------------------------------------");
			System.out.println("\t   CASH WITHDRAW");
			System.out.println("--------------------------------------");
			System.out.println("\n    "+today);
			System.out.println("    BANK AL-NASEEB, SUKKUR, SINDH.");
			System.out.println("\n    USER\t\t"+userLogin.name);
			System.out.println("    WITHDRAWAL\t\t"+amount+" PKR");
			System.out.println("\n    OLD BAL\t\t"+userLogin.balance+" PKR");
			System.out.println("    NEW BAL\t\t"+(userLogin.balance-=amount)+" PKR");
			System.out.println("\n--------------------------------------");
			userLogin.DB.T_data(today, "Withdraw", amount);
			System.out.print("\n\n Press any key to continue...");
			input.nextLine();
			bankInterface(userLogin);
		}

		if(opt == 'D')
		{
			System.out.println("\n============ BANK AL-NASEEB ============");
			System.out.println("\n----------------------------------------");
			System.out.println("\t       CASH DEPOSIT");
			System.out.println("----------------------------------------");
			System.out.println("\n    "+today);
			System.out.println("    BANK AL-NASEEB, SUKKUR, SINDH.");
			System.out.println("\n    USER\t\t"+userLogin.name);
			System.out.println("    DEPOSIT\t\t"+amount+" PKR");
			System.out.println("\n    OLD BAL\t\t"+userLogin.balance+" PKR");
			System.out.println("    NEW BAL\t\t"+(userLogin.balance+=amount)+" PKR");
			System.out.println("\n----------------------------------------");
			userLogin.DB.T_data(today, "Deposit", amount);
			System.out.print("\n\n Press any key to continue...");
			input.nextLine();
			bankInterface(userLogin);	
		}
	}

	public static void main(String args[])
	{
		b[0] = new Bankpro("siraj7", "7070", "Siraj Ahmed", 21, "13/11/2001", "+923174806368", "Current", 92000);
		b[1] = new Bankpro("khanm9", "6969", "Khan Muhammad", 18, "29/11/2004",  "+923174806123",  "Saving", 6000);
		b[2] = new Bankpro("shezzy", "2929", "Jahanzeb Sheikh", 18, "01/09/2004",  "+923174806876",  "Saving", 600000);
		b[3] = new Bankpro("asawar", "2936", "Rana Asawar", 19, "20/10/2003",  "+923174806222",  "Current", 100000);
		
		int i=0;

		do
		{
			Bankpro user = new Bankpro();
			user.mainLandPage();
		}
		
		while(i==0);
	}
}

/*

Current Account: A current account is generally used for business transactions. It offers unlimited transactions and allows the account holder to deposit and withdraw money as frequently as needed. Some banks require a minimum balance to be maintained in the account. (PKR 1000 in this program)

Savings Account: A savings account is designed for individuals who want to save their money and earn interest on their deposits (However my bank is hilal, we dont do interest here.). It usually has a lower minimum balance requirement and offers limited transactions. (PKR 500 of minimum balance and 3 transactions in this program)

Pre Defined Bank Accounts that you can use:

Username: siraj7
Password: 7070

Username: khanm9
Password: 6969

Username: shezzy
Password: 2929

Username: asawar
Password: 2936

For Admin Login Use:

Username: admin
Password: admin

*/
