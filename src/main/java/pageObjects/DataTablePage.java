package pageObjects;

import org.apache.poi.ss.formula.functions.WeekNum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.util.List;

public class DataTablePage extends Base {

    @FindBy(css = "[name='example_length']")
    public WebElement dropdownEntry;

    @FindBy(css = "input[type='submit']")
    public WebElement searchButton;

    @FindBy(css = "div[class='results']>h1")
    public WebElement searchResults;

    @FindBy(css="[aria-label*='Age']")
    public WebElement ageColumn;

    @FindBy(xpath = "//tr[@class]//td[4]")
    public List<WebElement> ageValue;

    @FindBy(xpath = "//tr[@class]//td[1]")
    public List<WebElement> nameValue;

    @FindBy(xpath = "//tr[@class]//td[2]")
    public List<WebElement> positionValue;


    @FindBy(xpath = "//tr[@class]//td[3]")
    public List<WebElement> officeValue;



    public DataTablePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
}
