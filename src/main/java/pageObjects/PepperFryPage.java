package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.util.List;

public class PepperFryPage extends Base {

    @FindBy(css = "[id='search']")
    public WebElement searchBox;

    @FindBy(css = "[id='searchButton']")
    public WebElement searchButton;

    @FindBy (css = "li[data-sort='price-asc']>input")
    public WebElement lowestPriceRadio;

    @FindBy(css = "[class='clip__filter-warpper']")
    public WebElement sortBySection;

    @FindBy(css = "h2[class*='clipCard__ttl']")
    public List<WebElement> nameOfMaterial;

    @FindBy(css = "[class='clipCard__price-offer']")
    public List<WebElement> priceOfMaterial;

    @FindBy(css = "span[class='clipCard__price-offer']")
    public List<WebElement> priceOfElement;

    @FindBy(css = "div[class='popup-box gb-modal reg_modal']>a[class='popup-close']")
    public WebElement closeLayer;


    public PepperFryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
