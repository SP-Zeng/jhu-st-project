import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Selenide.$$;

public class GuiTest {

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





}
