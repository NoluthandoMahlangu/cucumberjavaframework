package co.za.inspired.commonsteps;

import co.za.inspired.basepage.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;


import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class CommonSteps extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.saucedemo.com/";
    private Map<String, Object> vars;
    JavascriptExecutor js;

/*
    Objects
 */
    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement txtUsername;

    @FindBy(xpath="//*[@id='password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement btnLogin;

    @FindBy(css = "*[data-test='product_sort_container']")
    private WebElement drpsort;



    /*
       Common Steps
     */
    CommonSteps(){
        PageFactory.initElements(driver, this);
    }
    void goToHomePage(){
        driver.get(HOME_PAGE_URL);
        wait.forLoading(100);
    }

    void login(final String username, final String password) {
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        txtUsername.click();
        txtUsername.sendKeys(username);

        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        txtPassword.click();
        txtPassword.sendKeys(password);

        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        btnLogin.click();

    }

    void sortAscedingOrder() throws Throwable{

        List<WebElement>beforeFilterPrice = driver.findElements(By.className("inventory_container"));

        List<Double> beforeFilterPriceList = new ArrayList<>();

        for(WebElement p : beforeFilterPrice){
            beforeFilterPriceList.add( Double.valueOf(p.getText().replace("$", "")));
        }

        Select drpDown = new Select(driver.findElement(By.className("product_sort_container")));
        drpDown.selectByVisibleText("Price (low to high)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_container"));
        List<Double>afterFilterPriceList = new ArrayList<>();

        for(WebElement p : afterFilterPrice){
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        Collections.sort(afterFilterPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        Thread.sleep(2000);

    }

}

