package com.automatedtest.vodacom.commonsteps;

import com.automatedtest.vodacom.basepage.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.Map;

import static java.awt.SystemColor.window;
import static org.junit.Assert.assertEquals;


public class CommonSteps extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.vodacom.co.za/";
    private Map<String, Object> vars;
    JavascriptExecutor js;


    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement btnAcceptCookies;
    @FindBy(xpath = "//*[@id='hotspot-portlet-card-content-cta---3293947340-1']")
    private WebElement btnBuyNow;

    @FindBy(xpath = "//p[@class='head']")
    private WebElement lblOrderHead;

    @FindBy(xpath = "//div[@class='DependencyHelper__CustomContainer-ws8fko-0 gaukmq DealProduct_title__2axoh']")
    private WebElement productValidation;

    @FindBy(xpath = "//div[@class='DealDetailFooterTop_details-value__1rON9 col-md-8 col-6']")
    private WebElement lblPrice;

    @FindBy(xpath = "//*[@id='product-fbt-tabpane-DESC']/div/div[2]/div[2]")
    private WebElement lblDuration;

    @FindBy(xpath = "//*[@id='product-fbt-tabpane-DESC']/div/div[3]/div[2]")
    private WebElement lblAvailability;

    @FindBy(xpath = "//button[text()='Get this deal']")
    private WebElement btnGetDeal;

    @FindBy(xpath = "//div[@class='middle_section_body']//p")
    private WebElement lblOrderSummary;

    @FindBy(xpath = "////div[@class='details_cell']")
    private WebElement lblDevice;

    @FindBy(xpath = "//p[@ng-repeat='plan in dealdetails.plans.details']")
    private WebElement lblPlan;

    @FindBy(xpath = "//p[text()='Contract cover has been added ']")
    private WebElement lblContractCover;

    CommonSteps() {
        PageFactory.initElements(driver, this);
    }

    void goToHomePage() {
        driver.get(HOME_PAGE_URL);
        wait.forLoading(10);

    }

    void acceptCookie(final String action){
        wait.forElementToBeDisplayed(5, this.btnAcceptCookies, "btnAcceptCookies");
        if(btnAcceptCookies.isDisplayed()) {
            btnAcceptCookies.click();
        }else {
            System.out.println("No cookie window pop up");
        }

        Alert alert = driver.switchTo().alert();
        if (action.equalsIgnoreCase("dismiss")) {
            alert.dismiss();

        } else {
            alert.accept();
        }
    }

    void onlineOnlyDeals() throws Throwable {
        WebElement element = btnBuyNow;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        try {
            btnBuyNow.click();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    void productValidation() {
        wait.forElementToBeDisplayed(5, this.lblOrderHead, "lblOrderHead");
        assertEquals(driver.getTitle(), "2x Samsung Galaxy A32 LTE");
    }


    void dealDetailsValidations() {
        WebElement element = lblPrice;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String actualString = lblPrice.getText();
        Assert.assertTrue(actualString.contains("R299 PM"));

        String actualStringII = lblDuration.getText();
        Assert.assertTrue(actualStringII.contains("36 months"));

        String actualStringIII = lblAvailability.getText();
        Assert.assertTrue(actualStringIII.contains("Yes"));
    }

    void clickGetDeal(){

        JavascriptExecutor jsExecuter = (JavascriptExecutor)driver;
        jsExecuter.executeScript("window.scrollBy(0,-250)");

        wait.forElementToBeDisplayed(5,btnGetDeal , "btnGetDeal");
        btnGetDeal.click();
    }

    void oderPageValidation() {
        String actualString = lblOrderHead.getText();
        Assert.assertTrue(actualString.contains("Order summary"));
    }

    void oderSummaryValidation() {

        String actualString = lblDevice.getText();
        Assert.assertTrue(actualString.contains("R299 PM"));

        String actualStringII = lblPlan.getText();
        Assert.assertTrue(actualStringII.contains("36 months"));

        String actualStringIII = lblContractCover.getText();
        Assert.assertTrue(actualStringIII.contains("Yes"));
    }

    void dealDetailsAssertions() {
        WebElement element = lblPrice;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String actualString = lblPrice.getText();
        Assert.assertTrue(actualString.contains("R500 PM"));

        String actualStringII = lblDuration.getText();
        Assert.assertTrue(actualStringII.contains("36 months"));

        String actualStringIII = lblAvailability.getText();
        Assert.assertTrue(actualStringIII.contains("Yes"));
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
