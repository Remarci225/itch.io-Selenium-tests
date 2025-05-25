package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GamePage extends BasePage {
    public static final String URL = "https://selenium-tests.itch.io/my-testing-game";

    private static final By TITLE_LABEL_LOCATOR = By.xpath("//div[contains(@class, 'header')]//h1[contains(@class, 'game_title')]");
    private static final By DESCRIPTION_LABEL_LOCATOR = By.xpath("//div[contains(@class, 'left_col')]//div[contains(@class, 'formatted_description')]//p");
    private static final By INFORMATION_DROPDOWN_LOCATOR = By.xpath("//div[contains(@class, 'toggle_row')]//a[contains(@class, 'toggle_info_btn')]");
    private static final By GENRE_LABEL_LOCATOR = By.xpath("//div[contains(@class, 'game_info_panel_widget')]//td[text()='Genre']/following-sibling::node()/a");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public WebElement getTitleLabel() {
        return waitAndFindElement(TITLE_LABEL_LOCATOR);
    }

    public WebElement getDescriptionLabel() {
        return waitAndFindElement(DESCRIPTION_LABEL_LOCATOR);
    }

    public WebElement getInformationDropdown() {
        return waitAndFindElement(INFORMATION_DROPDOWN_LOCATOR);
    }

    public WebElement getGenreLabel() {
        return waitAndFindElement(GENRE_LABEL_LOCATOR);
    }
}