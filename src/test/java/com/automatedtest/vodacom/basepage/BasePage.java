package com.automatedtest.vodacom.basepage;

import com.automatedtest.vodacom.infrastructure.driver.Setup;
import com.automatedtest.vodacom.infrastructure.driver.Wait;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected Wait wait;
    public BasePage() {
        this.driver = Setup.driver;
        this.wait = new Wait(this.driver);
    }
}
