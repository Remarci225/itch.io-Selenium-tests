package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JamPage extends BasePage {
    public static final String URL = "https://itch.io/jams";

    private static final By JAM_CELL_LOCATOR = By.xpath("(//div[contains(@class, 'calendar_row')]//div[contains(@class, 'jam_cell')])[position() < 11]");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public List<WebElement> getJamCells() {
        return waitAndFindElements(JAM_CELL_LOCATOR);
    }

    @Override
    public boolean isLoaded() {
        return !getJamCells().isEmpty();
    }
}