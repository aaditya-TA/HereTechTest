package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.nio.file.WatchEvent;
import java.util.List;

public class MapsPage extends Base {

    @FindBy(css = "[aria-label='Search Google Maps']")
    public WebElement searchBox;

    @FindBy(css = "button[guidedhelpid='searchbutton']")
    public WebElement searchButton;

    @FindBy (css = "button[aria-label='Photo of Wankhede Stadium Mumbai']")
    public WebElement photoWankhede;

    @FindBy(css = "button[jsaction='pane.rating.category']")
    public WebElement stadiumText;

    @FindBy (css = "h1[jstcache]>span:nth-of-type(1)")
    public WebElement stadiumFrameTitle;


    @FindBy (css = "div[jsaction='pane.rating.moreReviews']>span>span")
    public WebElement ratingNumber;

    @FindBy(css = "button[aria-label*='reviews'][vet]")
    public WebElement numberOfreview;

    @FindBy(css = "button[aria-label*='Website: mumbaicricket.com']")
    public WebElement mumbaiCricketLink;

    @FindBy(css = "button[aria-label*='Address']")
    public WebElement address;

    @FindBy(css="div[aria-label*='Phone']")
    public WebElement phoneNumberSection;

    @FindBy(xpath = "//*[@id='pane']//div//div[1]//div//div//div[2]//div//div")
    public WebElement addressText;

    @FindBy(css = "button[aria-label*='Directions to']")
    public WebElement directionsIcon;

    @FindBy(css="[id*='directions-searchbox'] div>input")
    public WebElement directionFromSearchBox;

    @FindBy(xpath="//*[contains(@class,'delay-')]//following-sibling::div")
    public List<WebElement> firstOptionDistance;

    @FindBy(css="[class*='delay-']>span:nth-of-type(1)")
    public List <WebElement> firstOptionTime;




    public MapsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
