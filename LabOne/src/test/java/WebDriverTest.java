import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class WebDriverTest {
    private static WebDriver driver;


    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://www.google.pl");
    }

    @Test
    public void testFirstRes() {
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("origenes");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
        WebElement el = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/a/h3"));
        assertNotNull(el.getText());
    }



    @Test
    public void testThirdRes() {
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("roma locuta causa finita");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
        WebElement el = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[3]/div/div[1]/a/h3"));
        assertNotNull(el.getText());

    }

    @Test
    public void testNoClick() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("origenes");
        driver.findElement(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.FPdoLc.tfB0Bf > center > input.gNO89b")).sendKeys(Keys.RETURN);
        WebElement el = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/a/h3"));
        assertNotNull(el.getText());
    }

    @Test
    public void testCantLocate() {
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.linkText("No such element"));
        });

    }

}