package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import pages.BasePage;

public abstract class BasePageTest {
    @BeforeEach
    protected void setUp() {
        BasePage.setUp();
    }

    @AfterEach
    protected void tearDown() {
        BasePage.tearDown();
    }
}