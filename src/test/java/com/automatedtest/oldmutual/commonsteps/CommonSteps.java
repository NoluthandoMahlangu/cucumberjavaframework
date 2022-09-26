package com.automatedtest.oldmutual.commonsteps;

import com.automatedtest.oldmutual.basepage.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;
import java.io.File;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class CommonSteps extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.oldmutualfinance.co.za/";
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement btnAcceptCookies;

    @FindBy(css = "h1 > strong")
    private WebElement title;

    @FindBy(css = "li:nth-child(2) .om-button-text:nth-child(1)")
    private WebElement personalLoanLink;

    @FindBy(css = "div > h1")
    private WebElement loanPagetitle;

    @FindBy(css = ".hydrated:nth-child(4) > .theme-default > .om-button-text")
    private WebElement btnCalculateLoan;

    @FindBy(xpath = "//*[@id='loanAmount']")
    private WebElement ddlAmount;

    @FindBy(css = ".secondary-large > .om-button-text")
    private WebElement btnNext;

    @FindBy(css = "#repaymentDuration .selected-value-container")
    private WebElement ddlDuration;

    @FindBy(css = ".primary-large > .om-button-text")
    private WebElement btnCal;

    @FindBy(css = ".no-bottom-margin:nth-child(2) > strong")
    private WebElement amountSummary;


    CommonSteps() {
        PageFactory.initElements(driver, this);
    }

    void goToHomePage() {
        driver.get(HOME_PAGE_URL);
        wait.forLoading(5);
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
    void checkTitleDisplayed() throws Throwable {
        wait.forElementToBeDisplayed(5, this.title, "title");
        assertEquals(driver.getTitle(), "Bank and Borrow Solutions | Old Mutual");
        this.takeSnapShot(driver, "\\screenShots\\err.jpeg");

    }

    void goToPersonalLoans() throws Throwable {
        WebElement element = personalLoanLink;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        try {
            personalLoanLink.click();
        } catch (Exception e) {
            e.getMessage();
        }
        this.takeSnapShot(driver, "\\screenShots\\err.jpeg");
    }

    void calculateLinkonPersonalLoan() throws Throwable {
        wait.forLoading(50);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gatsby-focus-wrapper']/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/om-button[2]/a/span")));
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hydrated:nth-child(4) > .theme-default > .om-button-text")));
        Thread.sleep(1000);
        element.click();
    }

    void selectAmount(final String amount) throws Throwable {

            String parentHandle = driver.getWindowHandle();
            for (final String handle : driver.getWindowHandles()) {
                if (!parentHandle.equals(handle)) {
                    driver.switchTo().window(handle);
                }
            }

        wait.forLoading(10);
        WebElement element = ddlAmount;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        element.click();
        Select loanAmount = new Select((ddlAmount));
        loanAmount.selectByVisibleText(amount);
        wait.forElementToBeDisplayed(5, btnNext, "btnNext");
        btnNext.click();
    }

    void selectDuration(final String duration) throws Throwable {
        WebElement element = ddlDuration;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        element.click();
        Select loanAmount = new Select((ddlDuration));
        loanAmount.selectByVisibleText(duration);
    }

    void calculateAndValidate() throws Throwable {
        wait.forElementToBeDisplayed(5, btnCal, "btnCal");
        btnCal.click();
        String summaryAmountI = amountSummary.getText().substring(0, 10);
        String summaryAmountII = amountSummary.getText().substring(12, 21);

        if (summaryAmountI == "R1 656.43" && summaryAmountII == "R1 810.05") {
            assertEquals(summaryAmountI + summaryAmountII, amountSummary);
        } else {
            Assert.fail();
            this.takeSnapShot(driver, "\\screenShots\\err.jpeg");
        }
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

}
