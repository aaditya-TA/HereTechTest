package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.util.List;

public class RedBusPage extends Base {

    @FindBy(css = "[id='src']")
    public WebElement fromSearchbox;

    @FindBy(css = "input[id='dest']")
    public WebElement toSearchBox;

    @FindBy (css = "input[id='onward_cal']")
    public WebElement dateBox;

    @FindBy(css = "td[class='current day']")
    public WebElement currentDay;

    @FindBy(css="button[id='search_btn']")
    public WebElement searchBtn;

    @FindBy(css="ul[class='autoFill homeSearch']>li")
    public WebElement autoSuggestResultFrom;

    @FindBy(css = "div[class*='star-rating-container']>span")
    public List<WebElement> ratings;

    @FindBy(css="div[class*='travels']:nth-child(1)")
    public List<WebElement> nameToursAndTravels;

    @FindBy(css = "[class='clearfix row-one']>div:nth-of-type(2)>div:nth-of-type(1)")
    public List<WebElement> startTime;

    @FindBy(css = "[class='clearfix row-one']>div:nth-of-type(4)>div:nth-of-type(1)")
    public List<WebElement> endTime;

    @FindBy(css = "[class='clearfix row-one']>div:nth-of-type(6) div[class='fare d-block']>span")
    public List<WebElement> moneyForTravel;

    @FindBy(css="[class='clearfix row-one']>div:nth-of-type(2)>div:nth-of-type(2)")
    public List<WebElement> fromLocation;

    @FindBy(css="[class='clearfix row-one']>div:nth-of-type(4)>div[class*='loc']")
    public List<WebElement> toLocation;

    public RedBusPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
