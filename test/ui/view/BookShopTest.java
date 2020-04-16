package ui.view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookShopTest {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\paeps\\Documents\\19-20\\Semester2\\Webontwikkeling2\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/web_war_exploded/");
    }

    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void test_navigate_to_form(){
        assertEquals("Book Info", driver.getTitle());
    }

    @Test
    public void test_submitting_all_fields_blank_return_error() {
        clearForm();
        driver.findElement(By.id("calculate")).click();
        assertEquals("Book Info", driver.getTitle());
        assertEquals("Vul alle velden in.", driver.findElement(By.tagName("p")).getText());
    }

    @Test
    public void test_submitting_title_allesKomtGoed_price_10_numberOfBooks_7_end_result() {
        clearForm();
        fillOutForm("Alles Komt Goed", 10, 7);

        driver.findElement(By.id("calculate")).click();
        assertEquals("Book", driver.getTitle());
        assertEquals("Alles Komt Goed", driver.findElement(By.id("title")).getText());
        assertEquals(70, Integer.parseInt(driver.findElement(By.id("amount")).getText()));
    }

    private void clearForm() {
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("number")).clear();
    }

    private void fillOutForm(String title, int price, int number){
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("price")).sendKeys(price + "");
        driver.findElement(By.id("number")).sendKeys(String.valueOf(number));
    }

}
