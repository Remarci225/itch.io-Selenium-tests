package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    public static final String URL = "https://itch.io/";

    private static final By GAME_CELL_LOCATOR = By.xpath("(//div[contains(@class, 'featured_game_grid_widget')]//div[contains(@class, 'game_cell')])[position() < 11]");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public List<WebElement> getGameCells() {
        return waitAndFindElements(GAME_CELL_LOCATOR);
    }

    @Override
    public boolean isLoaded() {
        return !getGameCells().isEmpty();
    }
}