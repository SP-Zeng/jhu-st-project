import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Selenide.$$;


public class GuiTestUser {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:4200/");
    }

    public static void login_1_account() {
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a")).click();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/div[1]/input")).click();
        actions().sendKeys("1").perform();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/div[2]/input")).click();
        actions().sendKeys("1").perform();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/input")).click();
    }

    /*
    Test to see if the user can register successfully. However, the format of email, phone number, city name and the
    password length has no restrictions, should add some check statements to improve security and prevent abuse
     */
    @Test
    public void testRegister(){
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]/a")).click();

        //customerName
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[1]/input")).click();
        actions().sendKeys("Ken").perform();

        //city
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[2]/input")).click();
        actions().sendKeys("Baltimore").perform();

        //gender
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[2]/input")).click();
        Select se = new Select($(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[3]/select")));
        se.selectByIndex(2);

        //email
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[4]/input")).click();
        actions().sendKeys("ken@gmail.com").perform();

        //mobile
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[5]/input")).click();
        actions().sendKeys("123456789").perform();

        //password
        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/div[6]/input")).click();
        actions().sendKeys("123456789").perform();


        $(By.xpath("/html/body/app-root/app-register/div/div/div/div[2]/form/input")).click();

        webdriver().shouldHave(url("http://localhost:4200/login"));
    }

    /*
    using what we the user to just register to log in. If you use the same email to register twice, this email login stops
    working due to the conflict of database,
     */
    @Test
    public void testLogin() {
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a")).click();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/div[1]/input")).click();
        actions().sendKeys("1").perform();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/div[2]/input")).click();
        actions().sendKeys("1").perform();
        $(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div[2]/form/input")).click();

        assertEquals("1", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[1]/th[2]")).getOwnText());
    }


    /*
    The logout function works properly
     */
    @Test
    public void testLogout() {
        login_1_account();
        $(By.xpath(" //*[@id=\"navbarNav\"]/ul/li[7]/button")).click();
        assertEquals("Login", $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a")).getOwnText());
    }

    /*
    Test the user can see the product at homepage after login
     */
    @Test
    public void testHome() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")).click();

        assertEquals("Products", $(By.xpath("/html/body/app-root/app-home/div/h4")).getOwnText());
        $$("/html/body/app-root/app-home/div/div").shouldHave(sizeGreaterThan(1));
    }

    /*
    Test the user can see the product at homepage after login
     */
    @Test
    public void testCategories() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarDropdown\"]")).click();

        assertEquals("vechicle", $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/div/a[1]")).getOwnText());
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/div/a[1]")).click();

        assertEquals("Products", $(By.xpath("/html/body/app-root/app-home/div/h4")).getOwnText());
    }

    /*
    Test the user Profile page after login
     */
    @Test
    public void testProfile() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a")).click();

        //name
        assertEquals("1", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[1]/th[2]")).getOwnText());

        //address
        assertEquals("1", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[2]/th[2]")).getOwnText());

        //gender
        assertEquals("Male", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[3]/th[2]")).getOwnText());

        //email
        assertEquals("1", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[4]/th[2]")).getOwnText());

        //Phone
        assertEquals("1", $(By.xpath("/html/body/app-root/app-profile/div/table/tr[5]/th[2]")).getOwnText());

    }

    /*
    Test the user add product into their wish list
     */
    @Test
    public void testWishListAndAddFunction() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")).click();

        //add first item into the wishlist
        $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[2]/button[2]")).click();
        String product_name = $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[1]/h6")).getOwnText();

        //get into wishlist page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[5]/a")).click();

        assertTrue($(By.xpath("/html/body/app-root/app-wishlist/div/div/div/table/tbody/tr/td[1]")).getOwnText().contains(product_name));

        //remove the item
        $(By.xpath("/html/body/app-root/app-wishlist/div/div/div/table/tbody/tr/td[5]/button")).click();
        assertEquals(2, $$("tr").size());
    }

    /*
    Test the user add product into their cart
     */
    @Test
    public void testCart() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")).click();

        //add first item into the cart
        $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[2]/button[1]")).click();
        String product_name = $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[1]/h6")).getOwnText();

        //get into cart page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[6]/a")).click();

        assertTrue($(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[1]")).getOwnText().contains(product_name));

        //remove the item
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[5]/button")).click();

    }

    /*
    Test the user add product into their cart
     */
    @Test
    public void testCartAddDeleteRemoveItem() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")).click();

        //add first item into the cart
        $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[2]/button[1]")).click();
        String product_name = $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[1]/h6")).getOwnText();

        //get into cart page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[6]/a")).click();

        //add 1 more item
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[3]/button[2]")).click();
        assertTrue($(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[3]")).getOwnText().contains("2"));

        //delete 1 more item
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[3]/button[1]")).click();

        //remove the item
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tbody/tr/td[5]/button")).click();
        assertTrue($(By.xpath("/html/body/app-root/app-viewcart/div/div/div[1]/table/tfoot/tr/th[2]")).getOwnText().contains("0"));

    }

    /*
    Test the user add product into their cart and place order
     */
    @Test
    public void testOrder() {
        login_1_account();
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")).click();

        //add first item into the cart
        $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[2]/button[1]")).click();
        String product_name = $(By.xpath("/html/body/app-root/app-home/div/div/div[1]/div/div[1]/h6")).getOwnText();

        //get into cart page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[6]/a")).click();

        //enter random card number
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[2]/div/div/form/div[1]/input")).click();
        actions().sendKeys("1234567891234567").perform();

        //enter random name
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[2]/div/div/form/div[2]/input")).click();
        actions().sendKeys("1").perform();

        //enter expiration date
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[2]/div/div/form/div[3]/div[1]/input")).click();
        actions().sendKeys("July 2023").perform();

        //CVV
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[2]/div/div/form/div[3]/div[2]/input")).click();
        actions().sendKeys("123").perform();

        //place order
        $(By.xpath("/html/body/app-root/app-viewcart/div/div/div[2]/div/div/form/button")).click();

        //get into order history page
        $(By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]/a")).click();


        assertTrue($(By.xpath("//html/body/app-root/app-orderhistory/div/div/div/table/tbody/tr[1]/td[3]/table/tbody/tr/td[1]")).getOwnText().contains(product_name));
    }





}
