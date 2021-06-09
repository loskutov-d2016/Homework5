package Homework5;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

 class Test1 {
     private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
     private final String STUDENT_LOGIN = "user";
     private final String STUDENT_PASSWORD = "1234";
     private WebDriver driver;
     private final String expensesMenu = "//ul[contains(@class,'nav-multilevel')]/li[contains(.,'Контрагенты')]";
     private final String expensesSubmenu = "//li[@data-route='crm_contact_index']/a";
     private final String createButton = "//a[@title='Создать контактное лицо']";
     private static final String name = "Лоскутов Дмитрий Сергеевич";
     private static final String organization = "1234";

     @BeforeAll
     public static void setupWebDriverManager() {
         WebDriverManager.chromedriver().setup();
     }
     @BeforeEach
     public void beforeTest() {
         setUpDriverSession;
         login();
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

     @AfterEach
     public void tearDown() {
         if (driver != null) {
             driver.quit();
         }
     }


     @Test
     public void create_a_project{
        WebElement project = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/a/span"));
        project.click();

        WebElement Myproject = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/ul/li[4]/a/span"));
        Myproject.click();
        WebElement crateproject = driver.findElement(By.xpath("//a[@title='Создать проект']"));
        crateproject.click();
        WebElement nameInput = driver.findElement(By.xpath(".//input[@name='crm_project[name]']"));
        nameInput.sendKeys(name);

        WebElement organizationInput = driver.findElement(By.xpath(".//*[@id=\"s2id_autogen1\"]"));
        organizationInput.sendKeys(organization);
        WebElement OrganizationEnter = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li[1]/div/span"));
        OrganizationEnter.click();

        WebElement contactperson = driver.findElement(By.xpath("//div[@class=\"select2-container select2\"]"));
        contactperson.click();
        WebElement contactperson1 = driver.findElement(By.xpath("//li[@class=\"select2-results-dept-0 select2-result select2-result-selectable select2-highlighted\"]"));
        contactperson1.click();

        Select division = new Select(driver.findElement(By.name("crm.project[businessUnit]")));
        division.selectByValue("1");

        Select fieldBProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        fieldBProjectCurator.selectByVisibleText("Лоскутов Дмитрий");

        Select fieldBProjectDirector = new Select(driver.findElement(By.name("crm_project[rp]")));
        fieldBProjectDirector.selectByVisibleText("Авласёнок Денис");

        Select fieldBProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        fieldBProjectManager.selectByVisibleText("Амелин Владимир");

        WebElement saveButton = driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]"));
        saveButton.click();
     }

     @Test
         public void createNewExpenseTest() {
         By btnLocator = By.xpath(".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a/[@class='unlickable']/span[text()='Расходы']"));
         Actions actions = new Actions(driver);
         WebElement menuBtn = driver.findElement(btnLocator);
         actions.moveToElement(menuBtn);

         WebElement subMenu = driver.findElement(By.xpath(".//spn[@class='title' and text()='Заявки на расходы'}"));
         actions.moveToElement(subMenu);
         actions.click().build().perform();

         WebDriverWait waitFiveSeconds = new WebDriverWait(driver,5);
         waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
                 "div[class='pull-left btn-group icons-holder']"
         ))));
         driver.findElement(By.cssSelector("div[class='pull-left btn-group icons-holder'")).click();
         new WebDriverWait(driver,5).until(ExpectedConditions.urlContains("/create"));
         driver.findElement(By.xpath(".//textarea")).sendKeys("test");

         Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_expense_request[businessUnit]")));
         businessUnitDropDown.selectByValue(1);

         Select expenditureDropDown = new Select((driver.findElement(By.name("crm_expense_request[expenditure]")));
         expenditureDropDown.selectByValue(87);

         driver.findElement(By.name("crm_expense_request[sumPlan]")).clear();
         driver.findElement(By.name("crm_expense_request[sumPlan]")).sendKeys("1488");

         WebElement notifyDateHasChangedCheckbox = driver.findElement(By.name("crm_expense_request[dataChangeNotify]"));
         notifyDateHasChangedCheckbox.click();

         driver.findElement(By.xpath(
                 ".//div[preceding-sibling::div[child::label[@class='required']]]//input[@class='datepicker-input hasDatepicker']")).click();
         driver.findElement(By.xpath(".//a[text()='20']")).click();

         driver.findElement(By.cssSelector("button[class='btn btn-success action-button']")).click();

         String message = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                 "div[class='message']"))).getText();
         Assertions.assertTrue(message.contains("Заявки на расход сохранена"));

              }

     private void login() {
         driver.get(LOGIN_PAGE_URL);

         WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
         loginTextInput.sendKeys(STUDENT_LOGIN);

         WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[@name='_password']"));
         passwordTextInput.sendKeys(STUDENT_PASSWORD);

         WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
         loginButton.click();
     }
     private void  setUpDriverSession() {
         driver = new ChromeDriver();
         ChromeOptions options = new ChromeOptions();
         options.setPageLoadStrategy(PageLoadStrategy.EAGER);
         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
     }
}
        }


