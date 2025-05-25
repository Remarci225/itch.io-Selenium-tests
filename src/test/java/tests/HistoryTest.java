package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.BasePage;
import pages.BrowsePage;
import pages.HomePage;
import pages.JamPage;
import pages.RandomizerPage;

public class HistoryTest extends BasePageTest {
    private void back() {
        BasePage.getWebDriver().navigate().back();
    }

    private String getCurrentUrl() {
        return BasePage.getWebDriver().getCurrentUrl();
    }

    @Test
    void testHistory() {
        HomePage homePage = new HomePage();
        BrowsePage browsePage = new BrowsePage();
        JamPage jamPage = new JamPage();
        RandomizerPage randomizerPage = new RandomizerPage();

        homePage.visit();
        browsePage.visit();
        jamPage.visit();
        randomizerPage.visit();

        back();
        Assertions.assertEquals(JamPage.URL, getCurrentUrl());

        back();
        Assertions.assertEquals(BrowsePage.URL, getCurrentUrl());

        back();
        Assertions.assertEquals(HomePage.URL, getCurrentUrl());
    }
}