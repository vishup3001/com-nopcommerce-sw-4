package com.nopcommerce.demo.testbase;

import com.nopcommerce.demo.propertyreader.PropertyManager;
import com.nopcommerce.demo.utilitites.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends Utility {
    String browser = PropertyManager.getInstance().getBrowser();

    @BeforeMethod
    public void setUp() {
        selectBrowser(browser);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }


}
