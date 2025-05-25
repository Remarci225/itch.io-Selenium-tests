package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public static final String URL = "https://itch.io/login";

    private static final By USERNAME_INPUT_LOCATOR = By.xpath("//div[contains(@class, 'input_row')]//input[@name='username']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//div[contains(@class, 'input_row')]//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'buttons')]//button[contains(@class, 'button') and text()='Log in']");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public WebElement getUsernameInput() {
        return waitAndFindElement(USERNAME_INPUT_LOCATOR);
    }

    public WebElement getPasswordInput() {
        return waitAndFindElement(PASSWORD_INPUT_LOCATOR);
    }

    public WebElement getLoginButton() {
        return waitAndFindElement(LOGIN_BUTTON_LOCATOR);
    }

    public void login() {
        getUsernameInput().sendKeys(USERNAME);
        getPasswordInput().sendKeys(PASSWORD);
        getLoginButton().click();
    }
}