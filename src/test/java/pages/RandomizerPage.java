package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RandomizerPage extends BasePage {
    public static final String URL = "https://itch.io/randomizer";
    public static final String TITLE_TEXT = "Randomizer - Find random games - itch.io";
    public static final String HEADER_TEXT = "Game randomizer";

    private static final By HEADER_LABEL_LOCATOR = By.xpath("//div[contains(@class, 'transition_wrapper')]//section[contains(@class, 'randomizer_intro')]//h1");
    private static final By START_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'button')]//button[contains(@class, 'start_btn')]");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public WebElement getHeaderLabel() {
        return waitAndFindElement(HEADER_LABEL_LOCATOR);
    }

    public WebElement getStartButton() {
        return waitAndFindElement(START_BUTTON_LOCATOR);
    }

    @Override
    public boolean isLoaded() {
        return TITLE_TEXT.equals(getWebDriver().getTitle()) &&
                HEADER_TEXT.equals(getHeaderLabel().getText()) &&
                getStartButton() != null;
    }
}