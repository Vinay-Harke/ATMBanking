# Bank-Management-System
Bank Management System is a mini-project application coded in java programming language.The Databse used for this project is Oracle 19C. This project is CUI(Console User Interface) based. This application able to do following tasks 
1.) SignUp
2.) SignIn
  i.) Deposit Money 
  ii.) Withdraw Money 
  iii.) Show Account Details  
  iv.) Check Balance 
  v.) Change/Update Password 
  vi.) Exit 
3.) ForgetPassword
4.) Exit

In this application as earlier said that Oracle db is used. For this application two tables are created which are follows : 
  1.) UserDetails
  2.) AccountDetails
 
 UserDetails(username,password,securityQuestion,answer,accNO);
 AccountDetails(accNo,accType,accBal);

**now lets see subfolders / structure of application**
The application mainly contains three main folders :
  1.) src (contains .java files)
  2.) resources (contains .properties files)
  3.) bin (contains .class files)
  
 1.) The "src" folder contains a subfolder "com" this com subfolder contains 9 subfolders which are as follows:
        1)bean : bean folder contains the .java files which are mainly creates an Object of AccountDetails and UserDetails.
                  i)  Account.java
                  ii) User.java
        2)client : this folder containd the main function where client can interact with application.
        3)dao : (Data Access Object)
        4)daoImpl
        5)exceptions
        6)providers
        7)Services
        8)serviceImpl
        9)threads
