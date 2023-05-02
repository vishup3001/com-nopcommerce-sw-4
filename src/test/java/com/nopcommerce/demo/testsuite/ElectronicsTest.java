package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ElectronicsTest extends BaseTest {
    String email;
    HomePage homePage = new HomePage();
    CellPhonePage cellPhonePage = new CellPhonePage();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    LoginPage loginPage = new LoginPage();

    RegisterPage registerPage = new RegisterPage();

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        homePage.mouseHoverToElectronicTab();
        homePage.mouseHoverToCellPhonesAndClick();
        String expectedMessage = "Cell phones";
        String actualMessage = cellPhonePage.getCellPhoneText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        String name = UUID.randomUUID().toString();
        email = name + "@gmail.com";
        String option = "Register";

        homePage.mouseHoverToElectronicTab();
        homePage.mouseHoverToCellPhonesAndClick();
        String expectedMessage = "Cell phones";
        String actualMessage = cellPhonePage.getCellPhoneText();
        Assert.assertEquals(expectedMessage, actualMessage);

        cellPhonePage.clickOnListViewIcon();
        cellPhonePage.selectPhoneFromList("Nokia Lumia 1020");

        expectedMessage = "Nokia Lumia 1020";
        actualMessage = cellPhonePage.getProductNameText();
        Assert.assertEquals(expectedMessage, actualMessage);

        expectedMessage = "$349.00";
        actualMessage = cellPhonePage.getPriceText();
        Assert.assertEquals(expectedMessage, actualMessage);

        cellPhonePage.updateQuantity("2");
        cellPhonePage.clickOnAddToCart();

        expectedMessage = "The product has been added to your shopping cart";
        actualMessage = cellPhonePage.getSuccessfullAddedToCartText();
        Assert.assertEquals(actualMessage, expectedMessage);

        cellPhonePage.closeNotificationBar();
        shoppingCartPage.mouseHoverToShoppingCard();
        shoppingCartPage.mouseHoverToGoToCardAndClick();

        expectedMessage = "Shopping cart";
        actualMessage = shoppingCartPage.getShoppingCartText();
        Assert.assertEquals(actualMessage, expectedMessage);

        expectedMessage = "2";
        actualMessage = shoppingCartPage.getNumberOfQuantityValue();
        Assert.assertEquals(actualMessage, expectedMessage);

        expectedMessage = "$698.00";
        actualMessage = shoppingCartPage.getPriceText();
        Assert.assertEquals(actualMessage, expectedMessage);

        shoppingCartPage.selectTermsAndCondition();
        shoppingCartPage.clickOnCheckOut();

        expectedMessage = "Welcome, Please Sign In!";
        actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(expectedMessage, actualMessage);

        homePage.clickOnRegisterTab();
        registerPage.enterFirstname("Prime");
        registerPage.enterLastname("Testing");
        registerPage.enterEmail(email);
        registerPage.enterPassword("Admin@123");
        registerPage.enterConfirmPassword("Admin@123");
        registerPage.clickOnRegisterButton();

        expectedMessage = "Your registration completed";
        actualMessage = registerPage.getRegistrationCompletedText();
        Assert.assertEquals(expectedMessage, actualMessage);

        System.out.println("-----------------Fail because of bug--------------------------");
        System.out.println("=================================================================");
        System.out.println("-----------------Shopping cart is getting empty--------------------------");

    }
}
