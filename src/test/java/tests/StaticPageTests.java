package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.BasePage;
import pages.BrowsePage;
import pages.HomePage;
import pages.JamPage;
import pages.RandomizerPage;

public class StaticPageTests extends BasePageTest {
    @Test
    void testStaticPageLoading() {
        BasePage[] staticPages = {
                new BrowsePage(),
                new HomePage(),
                new JamPage(),
                new RandomizerPage()
        };

        for (BasePage staticPage : staticPages) {
            staticPage.visit();
            Assertions.assertTrue(staticPage.isLoaded());
        }
    }
}