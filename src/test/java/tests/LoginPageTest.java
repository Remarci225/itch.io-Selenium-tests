package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.LoginPage;

public class LoginPageTest extends BasePageTest {
    private LoginPage loginPage;

    @Test
    void testLogin() {
        loginPage.login();
        Assertions.assertDoesNotThrow(loginPage::getProfileLabel);
    }

    @Test
    void testLogout() {
        loginPage.login();
        Assertions.assertNotNull(loginPage.getProfileLabel());

        loginPage.logout();
        Assertions.assertNull(loginPage.getProfileLabel());
    }

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        loginPage = new LoginPage();
        loginPage.visit();
    }
}