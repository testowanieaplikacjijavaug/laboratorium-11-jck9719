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
public class IsElementPresentTest {

    private static FirefoxDriver firefoxDriver;

    public IsElementPresentTest(FirefoxDriver firefoxDriver)
    {
        this.firefoxDriver = firefoxDriver;
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        firefoxDriver.quit();
    }

    @BeforeEach
    public void setUp() throws Exception {
        firefoxDriver.get("https://pl.wikipedia.org/wiki/Orygenes");
    }

    @Test
    public void isTitlePresentTest() {
        assertTrue(isElementPresent(By.id("firstHeading")));
    }

    @Test
    public void isTableOfContentPresentTest() {
        assertTrue(isElementPresent(By.id("toc")));
    }

    @Test
    public void isContentPresentTest() {
        assertTrue(isElementPresent(By.id("mw-content-text")));
    }

    @Test
    public void nonRealIdNegativeTest() {
        assertFalse(isElementPresent(By.id("Well, this id definitely does not exists;")));
    }

    public boolean isElementPresent(By by) {
        try { firefoxDriver.findElement(by); return true; }
        catch (Exception ex) { return false; }
    }


}