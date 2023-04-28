## CS422 Software Testing & Debugging Final Project

**Team members: Johnny Saldana, Shaopeng Zeng, Yujian He**

## How to Run The Program

Start the backend server before the frontend client.

**Backend**

  1. Install [MYSQL](https://www.mysql.org/download/)
  2. Configure MYSQL database credentials in `BACKEND\Home-Ecommerce-Backend\src\main\resources\application.properties`. Spring Boot will import mock data into database by executing `import.sql` automatically.
  3. Put your mySQL username and password as spring.datasource.username and spring.datasource.password in `\BACKEND\Home-Ecommerce-Backend\src\main\resources\application.properties`
  4. Put your google login username and password as spring.mail.username and spring.mail.password in
  `\BACKEND\SpringBoot-Backend\src\main\resources\application.properties`
  5. `cd BACKEND`
  6. Run `mvn install`.
  7. Run `mvn spring-boot:run`.
  8. `cd Home-Ecommerce-Backend`
  9. Run `mvn spring-boot:run`.
  10. `cd SpringBoot-Backend`
  11. Run `mvn spring-boot:run`.
  12. The backend server is running on [localhost:8080]().

**Frontend**
  1. Install [Node.js and npm](https://www.npmjs.com/get-npm)
  2. `cd frontend`.
  3. Run `npm install`.
  4. Run `ng serve`
  5. The frontend client is running on [localhost:4200]().

**Note Before Testing**
  For the GUI testing and API tesing, we have some pre existing data stored in our local database. This is because the author's code has bug to login using duplicate registered login username. 
   * After you have both frontend and backend deployed, please run `jhu-st-project\ApiTesting\src\test\java\StFinalSetup.java` in the API testing environment to initialize the databases for testing.

**Tests**
  1. JUnit for Whitebox unit on the Backend
  and Integration testing on the Backend in `\BACKEND\Home-Ecommerce-Backend\src\test\java\com\homecommerce\*` (It's a Gradle Project, in Intellij, import project folder as \BACKEND\Home-Ecommerce-Backend)
  2. JUnit for Blackbox unit on the Backend Services (DTOs and Models are all an Entity classes to model the databases so it's not tested and Controllers are endpoints tested in the API testing) in `\BACKEND\Home-Ecommerce-Backend\src\test\java\blackbox_testing\services\` (It's a Gradle Project, in Intellij, import project folder as \BACKEND\Home-Ecommerce-Backend)
  3. API Testing on the Backend in `\GUItesting\src\test\java`. Use Intellj, open the test file end with ".java" then press "ctrl + shift + F10" or press "Run" to go over tests. (It's a Gradle Project, in Intellij, import project folder as \GUItesting)
  4. GUI Testing on the Backend in `\ApiTesting\src\test\java`. Use Intellj, open the test file end with ".java" then press "ctrl + shift + F10" or press "Run" to go over tests. (It's a Gradle Project, in Intellij, import project folder as \ApiTesting)
  5. Load testing on the Backend in `\LoadTesting\st-final-project-load-testing.jmx` This load testing is based on [Jemeter GUI mode](https://jmeter.apache.org/), download the Jemeter, and move st-final-project-load-testing.jmx into `apache-jmeter-5.5\bin` and open the .jmx file in the Jemeter GUI. 
  6. Angular unit testing in `\FRONTEND\src\app` (all files end with ".spec.ts"). In \Frontend, run `ng test`.


**Faults after testing**
* Load Testing Bugs
  * Orders
    *  load testing starts to randomly send orders to /api/orders, the backend accepts it. Random orders including random numbers, random order dates, random customer information that does not even exist in the database. Author did not check for the validity of orders in any aspects.
  * Products
    * Using the category APIs, I can insert multiple same category, say cars, and when I want to create a product with car, it will return a SQL error because there are too many cars as category in the DB.
    * Also, same as the orders, you can input random names/values in the fields of product API, and they do not check for any validity. If a category is invalid, it will just set category to null.
    * The api/products/cats is wrong. The endpoint code itself is buggy,
  * WishList
    * In wishlist, the backend only checks what the product’s id, and does not verify for any other information.
  * General: The program does not check for any validity/completeness of the Json / form-data body, you can basically include anything or miss anything in the Json body and the program will just set the missing parameter to null, which can be extremely insecure.

* BlackBox Testing bugs
* Address Service
  * In address service: the author does not check about null object being as an address and accept it.
* OrderDetailService
  * In testFindById_InvalidId(), when an invalid id has been entered, the author will just throw an NoSuchElementException, which does not make any sense to terminate the program. A correct way would be to return null.
* OrderService
  * Similiar to failure in  testFindById_InvalidId(), in testConfirm_InvalidId(), when we try to confirm an invalid id number, the author will just throw NoSuchElementException.
  * In testFindById_InvalidId(), when an invalid id has been entered, the author will just throw an NoSuchElementException, which does not make any sense to terminate the program. A correct way would be to return null.
* Payment Service
  * In testFindPaymentById_InvalidId(), when we try to find payment with an invalid id,  the author will just throw an NoSuchElementException.
* Product Service
  * In testFindProductById_InvalidId(), when we try to find product with an invalid id,  the author will just throw an NoSuchElementException.

* Whitebox testing bugs
  * Admin
    * Security vulnerability: The password of the admin user is being transmitted in plain text in the request body for the validateUser() method, which could potentially be intercepted by an attacker
    * Incomplete updateAdmin() method: The updateAdmin() method of AdminService does not handle the case where the Admin object passed to it is null, which could cause a NullPointerException to be thrown if the method is called with a null argument.
    * Unused method: The saveAdmin() method of AdminService is not used anywhere
    * The AdminService constructor takes an AdminRepository argument but it is not being used to initialize the field dao, which could result in a NullPointerException if the AdminService is used before the dao field is initialized.
  * Wishlist
    * findByuserid broken there isn't a way to attach a wishlist to a customer
    * wishlistController.listall broken because it also can’t properly get customers by id. Had to use mock to hardcode a response to get this working
  * Customer
    * In the updateProfile method if the password is empty or null with cust.getPwd().equals("") || cust.getPwd()==null. This can throw a NullPointerException if cust.getPwd() is null

* API testing
  * For the Wishlist Api, it is not implement property return status code <500> after performing GET and POST
  * All Api will return status code <405> for PUT and DELETE
  * The Admin Api will return status code <405> due to the Authorization Issue
* GUI testing
  * User
    * For user registration, the format of email, phone number, city name and the password length has no restrictions, should add some check statements to improve security and prevent abuse.
    * For user login, If you use the same email to register twice, the email stops working due to the conflict of database. Should solve this conflict when user register
    * For user, the product can be added without the price, which should not happen.
    * For user, if the product is already existed in the cart, you can not add that product again but is able to adjust the item amount in the cart, which do not make sense. The item amount should automatically update when user add repeated item.
    * The error message “item already existed” only pops up when user enter the cart page. Should just show on the home page.
    * For user, payment is actually fake. User do not need the card number, CVV, expiration day to place the order.
    * For user, payment is actually fake. User do not need the card number, CVV, expiration day to place the order.
    * For user, they can place order when the cart is empty, which should not happen.
  * Admin
    * The the dashboard in the main page, the column “company” is a typo. The column represents the price of the order.
    * The dashboard can click “view” and view the order. However, it directs to the product page after clicking “view”. I think the order page function does not exist.
    * For admin, duplicates categories name can add. Should check if the name is already existed before adding.
    * For admin, the added product can added without price, product name and description. As long as it includes images, it is able to upload and does not make sense.
    * For admin, upload bulk image function is not working. It does not have the button of submit after adding the image
    * For admin, the send email function is not working.










# Online Shop Application

#### Problem Statement
 <p> This project is an attempt to provide the advantages of online shopping to customers of a real shop. It helps buying the products in the shop anywhere through internet. Thus the customer will get the service of online shopping and home delivery from his favorite shop. This system can be implemented to any shop in the locality or to multinational branded shops having retail outlet chains. If shops are providing an online portal where their customers can enjoy easy shopping from anywhere, the shops won’t be losing any more customers to the trending online shops such as flipcart or amazon. <p>

   ---------

#### A full-stack Online Shop web application using Spring Boot and Angular 8.
This is a Single Page Appliaction with client-side rendering. It includes [backend](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/tree/master/BACKEND) and [frontend](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/tree/master/FRONTEND) two seperate projects on different branches.
E-Commerce application developed for performing Admin and Customer user role operations with respective user interfaces. Application is implemented in two parts:
1. RESTfull web services: API's build using spring boot are used for handling all the back end operations
2. Front End: User interfaces designed and developed using Angular utilising web services for handling appropriate user actions.I also use [angular material](https://material.angular.io/) UI component library for creating frontend component like navbar, buttons
The frontend client makes API calls to the backend server when it is running.

  ---------

#### Features available based on the user role
* Admin
  * Adding products
  * Adding catagories
  * Updating products
  * Deleting products
  * Manage Orders
  * View user
  * Can send email
  * Can upload bulk data in csv format
* Customer
  * Registering into System
  * Login into Website
  * Add product in wishlist
  * Adding product to Cart
  * Updating/ Deleting the Product in cart as well as in wishlist
  * Placing the order

* Technologies:
  * Angular8 (https://material.angular.io/)
  * Typescript
  * Spring Boot
  * Hibernate with JPA
  * MySQL

  ---------

 #### Application screenshots

* <b><u>Login</u></b>
    ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/login.jpeg)
* <b>Register</b>
    ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/register.jpeg)

    <h3>USER FUNCTIONALITY</h3>

 * Customer
    * <b>Home</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/user_dashboard.jpeg)
        <br>
    * <b>User profile</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/user_profile.jpeg)
        <br>
    * <b>Customer cart functionality</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/user_cart_functionality.jpeg)
        <br>
    * <b>Customer Wishlist functionality</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/user_wishlist_functionality.jpeg)
        <br>
    * <b>User order history</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/user_order_history.jpeg)


    <h3>ADMIN FUNCTIONALITY</h3>

* Admin
    * <b>Home</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20dashboard.jpeg)
        <br>
    * <b>Add new catagory</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20add%20catatogies.jpeg)
        <br>
    * <b>Add new produc</b>t
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20add%20products.jpeg)
        <br>
    * <b>Bulk implementation</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20bulk.jpeg)
        <br>
    * <b>Email functionality</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20email.jpeg)
        <br>
    * <b>View user</b>
        ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/admin%20can%20view%20user.jpeg)
---------

#### Coverage Reports
* See the COVERAGE folder for the Jacoco reports.
* Note, we explicitly achieved 100% LC just for models, controllers, services, DTOs, and repos. The remaining files were not relevant to test the Backend and were purposefully excluded.

#### Running Whitebox Tests
1. Open the Home-Ecommerce-Backend as its own project
2. Execute `mvn clean install -Dmaven.test.failure.ignore=true` expect tests to fail revealing faults
3. Run Jacoco report

 #### Eureka Server for Microservice
 Eureka Server is service discovery for your microservices, where all client applications can register by themselves and other microservices look up the Eureka Server to get independent microservices to get the job complete.
Eureka Server is also known as Discovery Server and it contains all the information about client microservices running on which IP address and port.
To achieve this you need to create a Eureka Server application and add the below dependency in POM.xml.
<h6>
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency></h6>

 * spring.application.name is a unique name for your application.
 * server.port in which your application will be bound and wewill use default port 8761 for eureka server.
 * eureka.client.fetch-registry doesn't register itself in eureka server.
 * eureka.client.register-with-eureka is determines if service register itself as a client in eureka server.

 * <b>EUREKA SERVER</b>
     ![Image of screenshot](https://github.com/singhanshika311/wipro_capstone_project_c7_b2/blob/master/PROJECT%20IMAGES/eureka%20server.jpeg)
      <br>
 ---------

