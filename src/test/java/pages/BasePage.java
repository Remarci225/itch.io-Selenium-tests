package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public abstract class BasePage implements StaticPage {
    protected static WebDriver WEB_DRIVER;
    protected static WebDriverWait WEB_DRIVER_WAIT;
    protected static ChromeOptions CHROME_OPTIONS = (ChromeOptions) (new ChromeOptions()).setEnableDownloads(true);
    protected static final Duration TIMEOUT = Duration.ofSeconds(3);

    public static final String BASE_URL = "https://itch.io/";
    protected static String CONFIGURATION_PATH = "./src/test/resources/configuration/production.txt";
    protected static String USERNAME;
    protected static String PASSWORD;

    private static final By SEARCHBAR_INPUT_LOCATOR = By.xpath("//div[contains(@class, 'input_wrapper')]//input[contains(@class, 'search_input')]");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'user_panel_widget')]//a[contains(@class, 'panel_button') and @href='/login']");
    private static final By PROFILE_LABEL_LOCATOR = By.xpath("//a[@data-label='my_profile']//span[contains(@class, 'user_name')]");
    private static final By USER_DROPDOWN_LOCATOR = By.xpath("//div[contains(@class, 'drop_menu_wrap')]//button[@data-label='menu_tick']");
    private static final By LOGOUT_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'menu_group')]//a[@data-label='log_out']");

    public static WebDriver getWebDriver() {
        return WEB_DRIVER;
    }

    public static WebDriverWait getWebDriverWait() {
        return WEB_DRIVER_WAIT;
    }

    public static void setUp() {
        // Read username and password from file
        try (Stream<String> lines = Files.lines(Paths.get(CONFIGURATION_PATH))) {
            List<String> data = lines
                    .map(line -> line.replace(": ", ":").split(":")[1])
                    .toList();

            USERNAME = data.get(0);
            PASSWORD = data.get(1);
        } catch (IOException ioException) {
            System.out.println("Couldn't read file at " + CONFIGURATION_PATH + ", check if the file exists and contains valid data.");
        }

        // Set up the web driver
        WEB_DRIVER = new ChromeDriver(CHROME_OPTIONS);
        WEB_DRIVER.manage().window().maximize();

        // Set up the web driver wait
        WEB_DRIVER_WAIT = new WebDriverWait(WEB_DRIVER, TIMEOUT);
    }

    public static void tearDown() {
        // Quit the web driver
        if (WEB_DRIVER != null) {
            WEB_DRIVER.quit();
        }
    }

    public static WebElement waitAndFindElement(By elementLocator) {
        try {
            // Wait until the selected element becomes visible on the page
            WEB_DRIVER_WAIT.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

            // Find and return the selected element
            return WEB_DRIVER.findElement(elementLocator);
        } catch (WebDriverException webDriverException) {
            // Return null if an exception occurred and the selected element could not be found
            return null;
        }
    }

    public static List<WebElement> waitAndFindElements(By elementsLocator) {
        try {
            // Wait until all the selected elements become visible on the page
            WEB_DRIVER_WAIT.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementsLocator));

            // Find and return the selected elements
            return WEB_DRIVER.findElements(elementsLocator);
        } catch (WebDriverException webDriverException) {
            // Return an empty list if an exception occurred and the selected elements could not be found
            return List.of();
        }
    }

    public BasePage() {
        visit(BASE_URL);
    }

    public BasePage(String url) {
        visit(url);
    }

    public void visit() {
        if (WEB_DRIVER != null) {
            WEB_DRIVER.get(BASE_URL);
        }
    }

    public void visit(String url) {
        if (WEB_DRIVER != null) {
            WEB_DRIVER.get(url);
        }
    }

    public WebElement getSearchbarInput() {
        return waitAndFindElement(SEARCHBAR_INPUT_LOCATOR);
    }

    public WebElement getLoginButton() {
        return waitAndFindElement(LOGIN_BUTTON_LOCATOR);
    }

    public WebElement getProfileLabel() {
        return waitAndFindElement(PROFILE_LABEL_LOCATOR);
    }

    public WebElement getUserDropdown() {
        return waitAndFindElement(USER_DROPDOWN_LOCATOR);
    }

    public WebElement getLogoutButton() {
        return waitAndFindElement(LOGOUT_BUTTON_LOCATOR);
    }

    public void search(String query) {
        getSearchbarInput().clear();
        getSearchbarInput().sendKeys(query + '\n');
    }

    public void viewProfile() {
        getProfileLabel().click();
    }

    public void logout() {
        getUserDropdown().click();
        getLogoutButton().click();
    }

    public boolean isLoaded() {
        return waitAndFindElement(By.xpath("//body")) != null;
    }
}