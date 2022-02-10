package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

public class allMoviePage extends Base {

    @FindBy(css = "input[type='search']")
    public WebElement searchBox;

    @FindBy(css = "input[type='submit']")
    public WebElement searchButton;

    @FindBy(css = "div[class='results']>h1")
    public WebElement searchResults;

    @FindBy(xpath="//div/a[text()='The Godfather']")
    public WebElement movieNameResult;

    @FindBy(css="span[class='header-movie-genres']>a")
    public WebElement genreName ;

    @FindBy(xpath="//span[contains(text(),'MPAA')]/span")
    public WebElement mpAARating ;

    @FindBy(css="[title='Cast & Crew']")
    public WebElement crewTab;

    @FindBy(css="[class='director_container'] dt")
    public WebElement directorName;

    @FindBy(xpath="//a[text()='Al Pacino']//parent::div//following-sibling::div[@class='cast_role']")
    public WebElement alPacinoCharacterName;

    public allMoviePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }







}
