package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EditPage extends BasePage {
    public static final String URL = "https://itch.io/game/edit/3581785";
    public static final String DEFAULT_NAME = "My Testing Game";
    public static final String DEFAULT_DESCRIPTION = "A game which is not really a game but helps out with Selenium testing.";
    public static final Genre DEFAULT_GENRE = Genre.ADVENTURE;
    public static final String WINDOWS_FILENAME = "My Testing Game.zip";
    public static final String MACOS_FILENAME = "My Testing Game.app.zip";
    private static final long SAVE_TIMEOUT = 500;
    private static final long DOWNLOAD_TIMEOUT = 15_000;

    private static final By TITLE_INPUT_LOCATOR = By.xpath("//div[contains(@class, 'input_row')]//input[@name='game[title]']");
    private static final By DESCRIPTION_INPUT_LOCATOR = By.xpath("(//div[contains(@class, 'redactor-box')]//div[contains(@class, 'redactor-layer')])[1]");
    private static final By GENRE_DROPDOWN_LOCATOR = By.xpath("//select[@name='game[genre]']/following-sibling::node()");
    private static final By GENRE_SELECTORS_LOCATOR = By.xpath("//div[contains(@class, 'selectize-dropdown-content')]//div[contains(@class, 'option')]");
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'header_nav_tools')]//button[contains(@class, 'save_btn')]");
    private static final By WINDOWS_DOWNLOAD_LABEL_LOCATOR = By.xpath("(//div[contains(@class, 'upload_tools')]//button[contains(@class, 'more_btn')])[1]");
    private static final By MACOS_DOWNLOAD_LABEL_LOCATOR = By.xpath("(//div[contains(@class, 'upload_tools')]//button[contains(@class, 'more_btn')])[2]");
    private static final By CLOSE_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'game_edit_upload_tools_lightbox_widget')]//button[contains(@class, 'close_button')]");
    private static final By DOWNLOAD_BUTTON_LOCATOR = By.xpath("//div[contains(@class, 'download_form')]//button[@value='download']");

    @Override
    public void visit() {
        super.visit(URL);
    }

    public WebElement getTitleInput() {
        return waitAndFindElement(TITLE_INPUT_LOCATOR);
    }

    public WebElement getDescriptionInput() {
        return waitAndFindElement(DESCRIPTION_INPUT_LOCATOR);
    }

    public WebElement getGenreDropdown() {
        return waitAndFindElement(GENRE_DROPDOWN_LOCATOR);
    }

    public List<WebElement> getGenreSelectors() {
        return waitAndFindElements(GENRE_SELECTORS_LOCATOR);
    }

    public WebElement getSaveButton() {
        return waitAndFindElement(SAVE_BUTTON_LOCATOR);
    }

    public void changeTitle(String title) {
        getTitleInput().clear();
        getTitleInput().sendKeys(title);
    }

    public void changeDescription(String description) {
        getDescriptionInput().clear();
        getDescriptionInput().sendKeys(description);
    }

    public void changeGenre(Genre genre) {
        getGenreDropdown().click();
        getGenreSelectors().stream()
                .filter(genreSelector -> genreSelector.getText().equals(genre.getName()))
                .findFirst().ifPresent(WebElement::click);
    }

    public void save() {
        getSaveButton().click();

        try {
            Thread.sleep(SAVE_TIMEOUT);
        } catch (InterruptedException interruptedException) {
            System.out.println("Waiting has been interrupted during saving");
        }
    }

    public WebElement getWindowsDownloadLabel() {
        return waitAndFindElement(WINDOWS_DOWNLOAD_LABEL_LOCATOR);
    }

    public WebElement getMacOSDownloadLabel() {
        return waitAndFindElement(MACOS_DOWNLOAD_LABEL_LOCATOR);
    }

    public WebElement getCloseButton() {
        return waitAndFindElement(CLOSE_BUTTON_LOCATOR);
    }

    public WebElement getDownloadButton() {
        return waitAndFindElement(DOWNLOAD_BUTTON_LOCATOR);
    }

    private void download(WebElement downloadLabel) {
        downloadLabel.click();
        getDownloadButton().click();
        getCloseButton().click();

        try {
            Thread.sleep(DOWNLOAD_TIMEOUT);
        } catch (InterruptedException interruptedException) {
            System.out.println("Waiting has been interrupted during downloading");
        }
    }

    public void downloadForWindows() {
        download(getWindowsDownloadLabel());
    }

    public void downloadForMacOS() {
        download(getMacOSDownloadLabel());
    }

    public enum Genre {
        NO_GENRE("No genre"),
        ACTION("Action"),
        ADVENTURE("Adventure"),
        CARD_GAME("Card Game"),
        EDUCATIONAL("Educational"),
        FIGHTING("Fighting"),
        INTERACTIVE_FICTION("Interactive Fiction"),
        PLATFORMER("Platformer"),
        PUZZLE("Puzzle"),
        RACING("Racing"),
        RHYTHM("Rhythm"),
        ROLE_PLAYING("Role Playing"),
        SHOOTER("Shooter"),
        SIMULATION("Simulation"),
        SPORTS("Sports"),
        STRATEGY("Strategy"),
        SURVIVAL("Survival"),
        VISUAL_NOVEL("Visual Novel"),
        OTHER("Other")
        ;

        private final String name;

        Genre(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}