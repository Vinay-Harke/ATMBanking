package com.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Scanner;
import com.Services.AccountServiceInterface;
import com.Services.UserServices;
import com.exception.InvalidAccountNoException;
import com.exception.InvalidAnswerException;
import com.exception.InvalidFundException;
import com.exception.InvalidPasswordException;
import com.exception.InvalidSecurityQuesException;
import com.exception.InvalidUserNameException;
import com.providers.BusinessObjectProviders;
import com.servicesImpl.SignInAnnotaion;



public class ClientCode {
	
	public static void  main (String [] args){
		//instnace variables of an class Clent Code
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		
		String userName=null,passWord=null,securityQuestion=null,answer=null;
		int choice=0,accNo=0;
		float accBal,amt;
		
		//Creation Of Object For New User
		UserServices user1=BusinessObjectProviders.getUserObject();
		
		//Creation Of Object For New Account
		AccountServiceInterface services;
		
		//For Annotation 
		Class c=user1.getClass();
		Class arg[]=new Class[2];
		arg[0]=String.class;
		arg[1]=String.class;
		
		try {
			Method m=c.getMethod("signIn",arg);
			SignInAnnotaion s=m.getAnnotation(SignInAnnotaion.class);
			System.out.println("Annotaion Of This SignIn Class  Are "+"name :"+s.name()+", id : "+s.id());
		} 
		catch (NoSuchMethodException | SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// FOR Reflection Of Class
		try {
			Class c2=Class.forName("com.servicesImpl.UserValidation");
			Field[] f = c2.getDeclaredFields();
			for(int i=0;i<f.length;i++) {
				System.out.println("The Variables Of The Class .........."+f[i]);
			}
			Method[] m=c.getDeclaredMethods();
			for(int i=0;i<m.length;i++) {
				System.out.println("The Methods Of The Class .........."+m[i]);
			}
			Constructor [] con=c.getDeclaredConstructors();
			for(int i=0;i<con.length;i++) {
				System.out.println("The Constructors Of The Class .........."+con[i]);
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//do-while loop for end user for choosing option from menu given
		do{
			System.out.println("\n\n ############################### Choose One From Following Options #######################################\n\n");
			System.out.println("1.) SignUp ");  
			System.out.println("2.) SignIn ");
			System.out.println("3.) ForgetPassWord");
			System.out.println("4.) Exit");
			System.out.print("Enter Here : ");
			choice=sc.nextInt();
			
			switch(choice) {  //start of outer switch case
				case 1: 
					//Gather data for Signing Up New USer
					System.out.print("Enter Username : ");
					userName=sc.next();
					System.out.print("Enter Password : ");
					passWord=sc.next();
					System.out.print("Enter Security Question : ");
					securityQuestion=sc.next();
					System.out.print("Enter Answer Of Security Question : ");
					answer=sc.next();
					
					// end user can OPEN ACCOUNT only when he is signed up 
					System.out.println("\n\n################################### Enter Your Choice ##############################################\n\n");
					System.out.println("1.) Open New Account ");
					System.out.println("2.) Exit ");
					System.out.print("Enter Here Your Choice : ");
					int choice1=sc.nextInt();
					
					//switch case for opening account or exit 
					switch(choice1) {//start of inner switch case
						case 1:
							//Gather Data For Opening An New Account For A new User
							System.out.print("Enter An Account Type Do You Want : ");
							String accType=sc.next();
							
							System.out.print("Enter An Amount With Which You Want To Open Account : ");
							accBal=sc.nextFloat();
							
							user1 = BusinessObjectProviders.getUserObject();
							services = BusinessObjectProviders.getAccountObject();
							
							try {
								//Call to the open account function this function opens an account and returns a new Account Number
								accNo=services.openAccount(accType, accBal); 
								
								//Call to the signup function this function insert data into UserDetails Database 
								System.out.println(user1.signUp(userName, passWord, securityQuestion, answer, accNo));//call to the SignUp function
							}
							catch(InvalidPasswordException | SQLException  e) {
								e.printStackTrace();
							}
							finally {
								System.out.println("####################### THANK YOU ###############################");
							}
							break;
							
						case 2:
							return;
					}//end of inner switch case
					break; //end of case 1
				case 2:
					int choice2=0,result=0;
					//Gather Data For signing in 
					System.out.print("Enter Username : ");
					userName=sc.next();
					
					System.out.print("Enter Password : ");
					passWord=sc.next();
					
					user1 = BusinessObjectProviders.getUserObject();
					services = BusinessObjectProviders.getAccountObject();
					
					try {
						//Call to the signIn function this function returns 1 if successfully signed in
						result = user1.signIn(userName, passWord);
						accNo=user1.getAccountNo(userName);
					} 
					catch (InvalidUserNameException | InvalidPasswordException | SQLException e) {
					
						e.printStackTrace();
					}
					finally {
						System.out.println("####################### THANK YOU ###############################");
					}
					
					if(result==1) {
						System.out.println("---------------------------------- You Are Signed In Sucessfully !! ------------------------------------");
						do{
							//the following operation are possible only when an end user signed in successfully 
							//As user is signed in successfully we can do deposit/withdraw/check balance/inquiry/details/update password operations
							
							System.out.println("\n\n------------ Enter Your Banking Operations From Following Menu----------------------\n\n");
							System.out.println("1.) Deposit Money ");
							System.out.println("2.) Withdraw Money ");
							System.out.println("3.) Show Account Details  ");
							System.out.println("4.) Check Balance ");
							System.out.println("5.) Change/Update Password ");
							System.out.println("6.) Exit ");
							System.out.print("Enter Your Choice Here : ");
							choice2=sc.nextInt();
							
							switch(choice2) {//start of inner switch case
								case 1:
									System.out.print("Enter Amount : ");
									amt=sc.nextFloat();
									
									try {
										//Call to the Deposit function which returns Account balance
										System.out.println("Balance After Deposition Is : "+services.deposite(accNo, amt));
									} 
									catch (InvalidAccountNoException | SQLException e) {
										e.printStackTrace();
									}
									finally {
										System.out.println("####################### THANK YOU ###############################");
									}
									break;
								case 2:
									System.out.print("Enter Amount :  ");
									amt=sc.nextFloat();
									//System.out.println("Balance After Withdrawal Is : "+ThreadProviders.getAccountWithdrawalThread(services,accNo,amt));
									try {
										//Call to the withdraw function which returns account balance after withdraw
										System.out.println("Balance After Withdrawal Is : "+services.withdraw(accNo, amt));
									} 
									catch (InvalidFundException | InvalidAccountNoException | SQLException e1) {
										e1.printStackTrace();
									}
									break;
								case 3:
									try {
										//Call to the acccount details function which returns String as an output
										System.out.println("Account Details Are \n "+services.displayAccontDetails(accNo));
									} 
									catch (InvalidAccountNoException | SQLException e) {
										e.printStackTrace();
									}
									finally {
										System.out.println("####################### THANK YOU ###############################");
									}
									break;
								case 4:
									try {
										// enquiry function returns balance of an account
										System.out.println("Account Balance Is : "+services.enquiry(accNo));
									} 
									catch (InvalidAccountNoException | SQLException e) {
										e.printStackTrace();
									}
									finally {
										System.out.println("####################### THANK YOU ###############################");
									}
									break;
								case 5:
									System.out.print("Enter New Password : ");
									String newPassword=sc.next();
									System.out.print("Enter Confirm Password : ");
									String confirmPassword=sc.next();
									try {
										//update password fun updates password and update databse too
										System.out.println(user1.updatePassword(userName, newPassword, confirmPassword));
									} 
									catch (InvalidUserNameException | InvalidPasswordException | SQLException e) {
										e.printStackTrace();
									}
									finally {
										System.out.println("####################### THANK YOU ###############################");
									}
									break;
								case 6:
									break;
							}//end of inner switch case
						}while(choice2 !=6);//end of inner do-while loop
					}
					break; // end of case 2
				case 3:
					user1 = BusinessObjectProviders.getUserObject();
					System.out.print("Enter Username : ");
					userName=sc.next();
					System.out.print("Enter Security Question");
					securityQuestion=sc.next();
					System.out.print("Enter Answer Of Security Question : ");
					answer=sc.next();
					try {
						//forget password returns a new password if question and answer matches
						System.out.println(user1.forgetPassword(userName, securityQuestion, answer));
					} 
					catch (InvalidSecurityQuesException | InvalidAnswerException | SQLException
							| InvalidUserNameException e) {
						e.printStackTrace();
					}
					finally {
						System.out.println("##################### THANK YOU ###########################");
					}
					break; //end of case 3
				case 4:
					break;
			}//end of outer switch case
			
		}while(choice != 4); //end of outer do- while loop
		System.out.println("#################################### PRPGRAM ENDS #################################### ");	
	}//end of main function
}//end of class
