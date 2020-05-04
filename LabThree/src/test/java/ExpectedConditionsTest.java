import static org.junit.jupiter.api.Assertions.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;


public class ExpectedConditionsTest {
    private static WebDriver firefoxDriver;
    private static WebDriverWait wait;

    @BeforeEach
    public void setDefaultPage() {
        firefoxDriver.get("https://digi.vatlib.it/");
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        firefoxDriver = new FirefoxDriver();
        wait = new WebDriverWait(firefoxDriver,10);


    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        firefoxDriver.quit();
    }

    @Test
    public void testTitlePage() {
        assertEquals("DigiVatLib", firefoxDriver.getTitle());
    }

    @Test
    public void expectedTitleContainsTest() {
        wait.until(ExpectedConditions.titleContains("DigiVatLib"));
        assertTrue(true);
    }

    @Test
    public void searchForTheOrigenManuscriptTest() {
        wait.until(ExpectedConditions.titleContains("DigiVatLib"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("k_v"))).sendKeys("Origen");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-search"))).click();
      wait.until(ExpectedConditions.textToBePresentInElement(firefoxDriver.findElement
                        (By.xpath("/html/body/div/div/div/div/div/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/ul/li/a/div/div[2]")),
                "Fee, G. D. Origen's Text of the New Testament and the Text of Egypt, In New Testament Studies 1982"));
//        System.out.println(firefoxDriver.getCurrentUrl());
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("k_v"), "Origen"));
        assertEquals("https://digi.vatlib.it/search?k_f=0&k_v=Origen", firefoxDriver.getCurrentUrl());

    }

}
