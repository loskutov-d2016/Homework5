package Homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Test2 {
    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "user";
    private final String STUDENT_PASSWORD = "1234";
    private WebDriver driver;
        private final String orgChosen = "//span[@class='select2-arrow']";
    private final String orgInput = "//input[contains (@class,'select2-input')]";
    private final String orhResult = "//div[@class='select2-result-label']";
    private final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    private final String message =  "//div[contains (@Class, 'alert-success')]";


    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void beforeTest() {
        setUpDriverSession;
        login();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void creating_a_contact_person() {

        WebElement fieldLastName = driver.findElement(By.name("crm_contact[lastName])"));
        fieldLastName.sendKeys("Лоскутов");
        System.out.println("Поле Фамилия заполнено:" + !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        //поле имя
        WebElement fieldFirstName = driver.findElement(By.name("crm_contact[company]"));
        fieldFirstName.sendKeys("Дмитрий");
        System.out.println("Поле Имя заполнено:" + !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        // поле организация
        WebElement fieldOrganisation = driver.findElement(By.name("crm_contact[company]"));

        driver.findElement(By.xpath(orgChosen)).click();
        driver.findElement(By.xpath(orgInput)).sendKeys("104");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orhResult))));
        driver.findElement(By.xpath(orgInput)).sendKeys(Keys.ENTER);

        WebElement fieldjobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        fieldjobTitle.sendKeys("Менеджер");

        driver.findElement(By.xpath(saveButton)).click();

        WebElement messageSuccess = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(messageSuccess));

    }
    private void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
    private void  setUpDriverSession() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}



