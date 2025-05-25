package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.EditPage;
import pages.EditPage.Genre;
import pages.GamePage;
import pages.LoginPage;

import java.io.File;

public class EditPageTest extends BasePageTest {
    private static final String NEW_TITLE = "New title";
    private static final String NEW_DESCRIPTION = "New description";
    private static final Genre NEW_GENRE = Genre.ACTION;
    private static final String DOWNLOADS_FOLDER_PATH = "/Users/Marci/Downloads/";

    private EditPage editPage;
    private GamePage gamePage;

    @Test
    void testChangingTitle() {
        editPage.changeTitle(NEW_TITLE);
        editPage.save();

        gamePage.visit();
        Assertions.assertEquals(NEW_TITLE, gamePage.getTitleLabel().getText());

        editPage.visit();
        editPage.changeTitle(EditPage.DEFAULT_NAME);
        editPage.save();

        gamePage.visit();
        Assertions.assertEquals(EditPage.DEFAULT_NAME, gamePage.getTitleLabel().getText());
    }

    @Test
    void testChangingDescription() {
        editPage.changeDescription(NEW_DESCRIPTION);
        editPage.save();

        gamePage.visit();
        Assertions.assertEquals(NEW_DESCRIPTION, gamePage.getDescriptionLabel().getText());

        editPage.visit();
        editPage.changeDescription(EditPage.DEFAULT_DESCRIPTION);
        editPage.save();

        gamePage.visit();
        Assertions.assertEquals(EditPage.DEFAULT_DESCRIPTION, gamePage.getDescriptionLabel().getText());
    }

    @Test
    void testChangingGenre() {
        editPage.changeGenre(NEW_GENRE);
        editPage.save();

        gamePage.visit();
        gamePage.getInformationDropdown().click();
        Assertions.assertEquals(NEW_GENRE.getName(), gamePage.getGenreLabel().getText());

        editPage.visit();
        editPage.changeGenre(EditPage.DEFAULT_GENRE);
        editPage.save();

        gamePage.visit();
        gamePage.getInformationDropdown().click();
        Assertions.assertEquals(EditPage.DEFAULT_GENRE.getName(), gamePage.getGenreLabel().getText());
    }

    @Test
    void testDownloadingFiles() {
        editPage.downloadForWindows();

        editPage.downloadForMacOS();

        File windowsFile = new File(DOWNLOADS_FOLDER_PATH + EditPage.WINDOWS_FILENAME);
        File macOSFile = new File(DOWNLOADS_FOLDER_PATH + EditPage.MACOS_FILENAME);

        Assertions.assertTrue(windowsFile.exists());
        Assertions.assertTrue(macOSFile.exists());

        Assertions.assertTrue(windowsFile.delete());
        Assertions.assertTrue(macOSFile.delete());
    }

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        LoginPage loginPage = new LoginPage();
        loginPage.visit();
        loginPage.login();

        gamePage = new GamePage();

        editPage = new EditPage();
        editPage.visit();
    }
}