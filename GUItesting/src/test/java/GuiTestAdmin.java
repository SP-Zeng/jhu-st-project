import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Selenide.$$;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;


public class GuiTestAdmin {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:4200/admin");
    }

    public static void login_admin() {
        $(By.xpath("/html/body/app-root/app-adminlogin/div/div/div/div/div[2]/form/div[1]/input")).click();
        actions().sendKeys("admin").perform();
        $(By.xpath("/html/body/app-root/app-adminlogin/div/div/div/div/div[2]/form/div[2]/input")).click();
        actions().sendKeys("admin").perform();
        $(By.xpath("/html/body/app-root/app-adminlogin/div/div/div/div/div[2]/form/input")).click();
    }

    /*
    test admin login
     */
    @Test
    public void testLogin() {
        login_admin();
        assertEquals("Welcome! Administrator", $(By.xpath("/html/body/app-root/app-dashboard/app-header/div/h1[1]")).getOwnText());
    }

    /*
    test admin dashboard
     */
    @Test
    public void testDashboard() {
        login_admin();

        //dashboard title
        assertEquals("Monthly Sales SHOP-E-HOME", $(By.xpath("/html/body/app-root/app-dashboard/div/header/div/div/div/div[1]/h1")).getOwnText());

        // list of the customer, date, price, and order status
        assertTrue($$("tr").size() > 2);
    }


    /*
    test admin categories page
     */
    @Test
    public void testCategories() {
        login_admin();

        //go to the categories page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a")).click();

        //categories page title
        assertEquals("Categories", $(By.xpath("/html/body/app-root/app-category/div/h4")).getOwnText());

        //add new categories "game"
        $(By.xpath("/html/body/app-root/app-category/div/div/div[2]/form/div/input")).click();
        actions().sendKeys("game").perform();
        $(By.xpath("/html/body/app-root/app-category/div/div/div[2]/form/button")).click();

        //check if the "game" is added successfully
        boolean found = false;
        for (SelenideElement li : $$("tr")) {
            String text = li.getText();
            if (text.contains("game")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    /*
    test admin products page
     */
    @Test
    public void testProducts() {
        login_admin();

        //go to the product page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]/a")).click();

        //product page title
        assertEquals("Products", $(By.xpath("/html/body/app-root/app-products/div[1]/h4")).getOwnText());

        //add new product name
        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[1]/input")).click();
        actions().sendKeys("Iphone").perform();

        //add new category
        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[2]/select")).click();
        Select se = new Select($(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[2]/select")));
        se.selectByIndex(3);

        //add new description
        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[3]/input")).click();
        actions().sendKeys("it is ken's iphone").perform();

        //add new price
        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[4]/input")).click();
        actions().sendKeys("10000").perform();


//        String imagePath = "src/test/java/35b7338ddf9c4e4296cd4eaaf4258dd7.jpg";
//        Path imageFilePath = Paths.get(imagePath);
//        boolean imageFileExists = Files.exists(imageFilePath);
//        if (imageFileExists) {
//            System.out.println("Image file exists at path: " + imagePath);
//        }else {
//            System.out.println("Image file does not exist at path: " + imagePath);
//        }
//        File imageFile = new File("35b7338ddf9c4e4296cd4eaaf4258dd7.jpg");
//        String imagePath1 = imageFile.getAbsolutePath();
//        System.out.println("Image file path: " + imagePath1);

        //add new image
//        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/div[5]/input")).click();
//        File fileToUpload = new File("src/test/java/35b7338ddf9c4e4296cd4eaaf4258dd7.jpg");
//        String fileInputSelector = "/html/body/app-root/app-products/div[1]/div/div[2]/form/div[5]/input";
//        $(fileInputSelector).uploadFile(fileToUpload);
//        String imagePath = "src/test/java/35b7338ddf9c4e4296cd4eaaf4258dd7.jpg";
//        actions().sendKeys(imagePath).perform();

        //add button
//        $(By.xpath("/html/body/app-root/app-products/div[1]/div/div[2]/form/input")).click();
    }

    /*
    test admin users page
     */
    @Test
    public void testUserPage() {
        login_admin();

        //go to the user page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[6]/a")).click();

        //product page title
        assertEquals("Customers List", $(By.xpath("/html/body/app-root/app-users/div/h4")).getOwnText());

        //check if the "game" is added successfully
        boolean found = false;
        for (SelenideElement li : $$("tr")) {
            String text = li.getText();
            if (text.contains("Ken") && text.contains("Baltimore")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    /*
    test admin products BulkImage page. it is not working because it does not have the button of submit
    after adding the file.
     */
    @Test
    public void testProductsBulkImage() {
        login_admin();

        //go to the product page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]/a")).click();

        //go to the BulkImage page
        $(By.xpath("/html/body/app-root/app-products/div[2]/a[1]/button")).click();

//        $(By.xpath("//*[@id=\"csvFileUpload\"]")).click();
//        File fileToUpload = new File("src/test/java/35b7338ddf9c4e4296cd4eaaf4258dd7.jpg");
//        String fileInputSelector = "/html/body/app-root/app-products/div[1]/div/div[2]/form/div[5]/input";
//        $(fileInputSelector).uploadFile(fileToUpload);
//        String imagePath = "src/test/java/35b7338ddf9c4e4296cd4eaaf4258dd7.jpg";
//        actions().sendKeys(imagePath).perform();
    }

    /*
    test admin products BulkImage page. it is not working because it does not have the button of submit
    after adding the file.
     */
    @Test
    public void testProductsSendEmail() {
        login_admin();

        //go to the product page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]/a")).click();

        //go to the send email page
        $(By.xpath("/html/body/app-root/app-products/div[2]/a[2]/button")).click();

        //name
        $(By.xpath("/html/body/app-root/app-email/form/table/tr[1]/td[2]/input")).click();
        actions().sendKeys("ken").perform();

        //age
        $(By.xpath("/html/body/app-root/app-email/form/table/tr[2]/td[2]/input")).click();
        actions().sendKeys("1").perform();

        //Country
        $(By.xpath("/html/body/app-root/app-email/form/table/tr[3]/td[2]/input")).click();
        actions().sendKeys("US").perform();

        //email
        $(By.xpath("/html/body/app-root/app-email/form/table/tr[4]/td[2]/input")).click();
        actions().sendKeys("easyjiajob@gmail.com").perform();

        $(By.xpath("/html/body/app-root/app-email/form/table/tr[5]/td[2]/button")).click();
    }

    }
