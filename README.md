# MarketPlace
NOTE:
You need to have the PasswordsAndKeys.java-file to get access to the database. It must be places in the dataAccessLayer package for the application to run. You also have to make sure that the host of the database (Eric) has added you IP-address to the database server.
Make sure that you have downloaded the jar-file from this website:
https://mvnrepository.com/artifact/com.microsoft.sqlserver/sqljdbc4/4.0 

Open File-Project structure - Modules - Dependencies and click on the small plus sign and add the downloaded jar-file.

![image](https://user-images.githubusercontent.com/76004088/168252130-1e7373c1-fa5e-4028-a09e-31e250d977e5.png)

Make sure that you have the correct SDK. Please see the picture!
![image](https://user-images.githubusercontent.com/76004088/168252813-7bd21c9d-f3e3-4365-9e5c-76fb0118db60.png)

1. To start the system, please run the StartServer-class.
2. To start the client, please run the StartClient-class

To start SOA:
1. Run the OrderService-class
2. Run the SoaClient-class
3. Look in the console for the result or open Postman and typ in this URL:
   http://localhost:9998/marketPlace/orderSearch/?username=Eric
   Enter Annie as the value of username and see the orders change to a new user.
   If you don't have Postman you can change the name manually in SoaClient-class on line 30
   to http://localhost:9998/marketPlace/orderSearch/?username=Alban and run the SOA-instructions
   1 and 2 again.
