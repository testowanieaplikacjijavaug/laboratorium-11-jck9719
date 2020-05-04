import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.openqa.selenium.support.ui.WebDriverWait;

@ExtendWith(SeleniumExtension.class)
public class SeleniumExtensionTest {

    private static FirefoxDriver firefoxDriver;

    public SeleniumExtensionTest(FirefoxDriver firefoxDriver)
    {
        this.firefoxDriver = firefoxDriver;
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        firefoxDriver.quit();
    }

    @BeforeEach
    public void setUp() throws Exception {
        firefoxDriver.get("https://www.google.pl");
    }

    @Test
    public void testFirstRes() {
        firefoxDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("origenes");
        firefoxDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
       // WebElement el = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/a/h3"));
        assertNotNull(firefoxDriver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/a/h3")));
    }



    @Test
    public void testThirdRes() {
        firefoxDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("roma locuta causa finita");
        firefoxDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
        assertNotNull(firefoxDriver.findElement(By.xpath("//*[@id=\"rso\"]/div[3]/div/div[1]/a/h3")));

    }

    @Test
    public void testCantLocate() {
        assertThrows(NoSuchElementException.class, () -> {
            firefoxDriver.findElement(By.linkText("No such element"));
        });

    }

}