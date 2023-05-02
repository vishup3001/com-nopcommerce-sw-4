package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite extends BaseTest {
    HomePage homePage = new HomePage();
    ComputersPage computersPage = new ComputersPage();
    BuildYourOwnComputerPage buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    LoginPage loginPage = new LoginPage();

    CheckOutPage checkOutPage = new CheckOutPage();

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        homePage.clickOnComputerTab();
        computersPage.clickOnDesktop();
        String actualOrder = computersPage.sortDesktopByPositionZtoA().toString();
        String ExceptedOrder = computersPage.getAllProductNamesAfterShorting().toString();
        Assert.assertTrue(ExceptedOrder.contains(actualOrder));

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        String expectedMessage = "Build your own computer";
        homePage.clickOnComputerTab();
        computersPage.clickOnDesktop();
        computersPage.selectValueFromShortingDropDown("Name: A to Z");
        computersPage.clickAddtoCartByProductName("Build your own computer");
        String actualMessage = buildYourOwnComputerPage.getBuildYourOwnComputerText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to click on add to card");

        buildYourOwnComputerPage.selectProcessor("2.2 GHz Intel Pentium Dual-Core E2200");
        buildYourOwnComputerPage.selectRam("8GB [+$60.00]");
        buildYourOwnComputerPage.selectHDD("400 GB [+$100.00]");
        buildYourOwnComputerPage.selectOs("Vista Premium");
        buildYourOwnComputerPage.selectMicrosoftOfficeSoftwareOption();
        buildYourOwnComputerPage.selectTotalCommanderSoftwareOption();
        expectedMessage = "$1,475.00";
        actualMessage = buildYourOwnComputerPage.getTotalPriceText();
        Assert.assertEquals(expectedMessage, actualMessage, "Wrong Configuration");

        buildYourOwnComputerPage.clickOnAddtoCart();
        expectedMessage = "The product has been added to your shopping cart";
        actualMessage = buildYourOwnComputerPage.getSuccessfullAddedToCartText();
        Assert.assertEquals(expectedMessage, actualMessage, "Not added in cart");

        buildYourOwnComputerPage.closeNotificationBar();
        buildYourOwnComputerPage.mouseHoverToShoppingCartAndClickOnCart();
        expectedMessage = "Shopping cart";
        actualMessage = shoppingCartPage.getShoppingCartText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to click on cart");

        shoppingCartPage.changeValueOfQuantity("2");
        shoppingCartPage.updateShoppingCart();
        expectedMessage = "$2,950.00";
        actualMessage = shoppingCartPage.getPriceText();
        Assert.assertEquals(expectedMessage, actualMessage, "Price not Updated");

        shoppingCartPage.selectTermsAndCondition();
        shoppingCartPage.clickOnCheckOut();
        expectedMessage = "Welcome, Please Sign In!";
        actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        loginPage.clickOnGuestCheckout();
        checkOutPage.enterFirstname("Prime");
        checkOutPage.enterLastname("Testing");
        checkOutPage.enterEmail("primetesting@gmail.com");
        checkOutPage.selectCountry("India");
        checkOutPage.enterCity("Vadodara");
        checkOutPage.enterAddress1("Dabhoi");
        checkOutPage.enterZipCode("391110");
        checkOutPage.enterPhoneNumber("9898989898");
        checkOutPage.clickOnContinue();
        checkOutPage.selectNextDayAir();
        checkOutPage.clickOnShippingContinue();
        checkOutPage.clickOnCreditCard();
        checkOutPage.clickOnPaymentContinue();
        checkOutPage.selectCreditCardType("Master card");
        checkOutPage.enterCardHolderName("Prime testing");
        checkOutPage.enterCardNumber("5573615091331588");
        checkOutPage.selectExpirationDate("05","2025");
        checkOutPage.enterCardCode("123");
        checkOutPage.clickOnPaymentInfoContinue();
        expectedMessage = "Credit Card";
        actualMessage = checkOutPage.getPaymentTypeText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        expectedMessage = "Next Day Air";
        actualMessage = checkOutPage.getShippingMethodText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        expectedMessage = "$2,950.00";
        actualMessage = checkOutPage.getTotalPriceText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        checkOutPage.clickOnConfirm();
        expectedMessage = "Thank you";
        actualMessage = homePage.getThankYouText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        expectedMessage = "Your order has been successfully processed!";
        actualMessage = homePage.getSuccessfulText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

        homePage.clickOnContinue();

        expectedMessage = "Welcome to our store";
        actualMessage = homePage.getWelcomeStoreText();
        Assert.assertEquals(expectedMessage, actualMessage, "Unable to checkout");

    }
}
