package Zadatak1;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Zadatak {

    private WebDriver driver;
    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Bootcamp\\chromedriver.exe");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://vue-demo.daniel-avellaneda.com/login");
    }

    @Test
    public void test1() {
        String expectedResult = "https://vue-demo.daniel-avellaneda.com/login";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        String expectedEmailType = "email";
        WebElement email = driver.findElement(By.id("email"));
        String actualResult1 = email.getAttribute("type");
        Assert.assertEquals(actualResult1, expectedEmailType);

        String expectedPasswordType = "password";
        WebElement password = driver.findElement(By.id("password"));
        String actualResult2 = password.getAttribute("type");
        Assert.assertEquals(actualResult2, expectedPasswordType);
    }

    @Test
    public void test2() {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(faker.internet().emailAddress());
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(faker.internet().password());
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        loginButton.click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        String expectedMessage = "User does not exists";
        WebElement wrongMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualMessage = wrongMessage.getText();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void test3() {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("admin@admin.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(faker.internet().password());
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        loginButton.click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        String expectedMessage = "Wrong password";
        WebElement wrongMessage1 = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualMessage1 = wrongMessage1.getText();

        Assert.assertEquals(actualMessage1, expectedMessage);
    }


    @AfterClass
    public void AfterClass() {
        driver.quit();
    }
}


