package hereTech;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import resources.Base;
import resources.ReadExcelFile;

import java.io.*;
import java.time.Duration;

public class hereTechTests extends Base {


    Logger logger = LoggerFactory.getLogger(hereTechTests.class);
    SoftAssert assertion ;

    allMoviePage moviePageObj;
    MapsPage mapsPageObj;
    PepperFryPage pepperFryPageObj;
    DataTablePage dataTablePageObj;
    RedBusPage redBusPageObj;

    @Parameters("browser")
    @BeforeTest
    public void setupConfiguration(String browser) throws IOException {
        driver = initializeDriver(browser);
        mapsPageObj = new MapsPage(driver);
        moviePageObj = new allMoviePage(driver);
        pepperFryPageObj = new PepperFryPage(driver);
        dataTablePageObj = new DataTablePage(driver);
        redBusPageObj = new RedBusPage(driver);

    }





    @Parameters({"browser"})
    @Test(priority = 01, description = "TC-01 : Verify Google maps functionality")
    public void test1_verifyGoogleMaps(String browser) throws IOException {
        try{
            assertion = new SoftAssert();
        if(browser.equalsIgnoreCase("Chrome")){

        //navigating to the google maps page
            String placeName = "Wankhede Stadium";
            String frameTitleName = "Wankhede Stadium - Google Maps";
            String phoneNumber = "022 2279 5500";
            driver.get("https://www.google.com/maps/");

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
            //waiting till search box appear
            wait.until(ExpectedConditions.visibilityOf(mapsPageObj.searchBox));
            mapsPageObj.searchBox.sendKeys(placeName);
            mapsPageObj.searchButton.click();

            //waiting till photo of wankhede appears
            wait.until(ExpectedConditions.visibilityOf(mapsPageObj.photoWankhede));
            takeScreenShot("TC-01_googleMaps_1");

            //Verifying the text Stadium
            assertion.assertTrue(mapsPageObj.stadiumText.getText().equalsIgnoreCase("Stadium"),"Stadium text is present");

            //Verifying the title
            System.out.println(mapsPageObj.stadiumFrameTitle.getText());
            assertion.assertTrue(mapsPageObj.stadiumFrameTitle.getText().equalsIgnoreCase(frameTitleName),"The title should be " +frameTitleName +"but is " +mapsPageObj.stadiumFrameTitle.getText());


            //Verifying and printing the rating and printing the number of reviews
            assertTrue(mapsPageObj.ratingNumber.isDisplayed(),"Ratings is present");
            assertTrue(mapsPageObj.numberOfreview.isDisplayed(),"Ratings is present");
            logger.info("The rating of Google maps is "+mapsPageObj.ratingNumber.getText()+" and the number of reviewers are : "+mapsPageObj.numberOfreview.getText());


            //Verifying the mumbai cricket link
            assertion.assertTrue(mapsPageObj.mumbaiCricketLink.isDisplayed(),"Mumbai cricket link is available");

            //Verifying and printing the address
            String addressText = mapsPageObj.address.getText();
            logger.info("The address present above the link is : "+addressText);

            //Verifying that the phone number is present
            System.out.println(mapsPageObj.phoneNumberSection.getText());
            assertion.assertTrue(mapsPageObj.phoneNumberSection.getText().equalsIgnoreCase(phoneNumber),"The phone shouled be "+phoneNumber+"but is "+mapsPageObj.phoneNumberSection.getText());

            //Taking a screensnhot again
            takeScreenShot("TC-01_googleMaps_2");

            assertion.assertAll();
        }
    else

    {
        logger.info("Shouldn't be tested on another browser");
    }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        }





    @Parameters({"browser"})
    @Test(priority = 01, description = "TC-02 : Verify all movie website")
    public void test2_verifyallMoviePage(String browser) throws IOException {
        try {
            assertion = new SoftAssert();
            if (browser.equalsIgnoreCase("firefox")) {
                //navigating to the webpage
                driver.get("http://www.allmovie.com/");
                String movieName = "The Godfather";
                String ratingMpa = "A";
                String genreName = "Crime";
                String directorNameText = "Francis Ford Coppola";
                String alPacinoCharacterName = "Michael Corleone";

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                //waiting till search box appear
                wait.until(ExpectedConditions.visibilityOf(moviePageObj.searchButton));


                //Searching for the movie and clicking on the search button
                assertTrue(moviePageObj.searchBox.isDisplayed(), "Search box is displayed");
                moviePageObj.searchBox.sendKeys(movieName);
                moviePageObj.searchButton.click();

                wait.until(ExpectedConditions.visibilityOf(moviePageObj.searchResults));
                String searchResultsText = moviePageObj.searchResults.getText();
                //Printing the number of results found
                logger.info("The number of results are : " + searchResultsText);

                //Clicking the godfather movie of 1972
                assertTrue(moviePageObj.movieNameResult.isDisplayed(), "The first godfather movie info is present");
                moviePageObj.movieNameResult.click();

                //Verifying that the genre is crime
                assertTrue(moviePageObj.genreName.getText().equalsIgnoreCase(genreName), "The genre is crime");

                //Verifying that it's mpa rating is "A"
                assertion.assertTrue(moviePageObj.mpAARating.getText().equalsIgnoreCase(ratingMpa), "The rating is 'A' but found " + moviePageObj.mpAARating.getText() + "");

                //Moving to the case & crew tab
                moviePageObj.crewTab.click();

                wait.until(ExpectedConditions.visibilityOf(moviePageObj.directorName));

                //Verifying the director name
                assertion.assertTrue(moviePageObj.directorName.isDisplayed(), "Director name is displayed");
                assertion.assertTrue(moviePageObj.directorName.getText().equalsIgnoreCase(directorNameText), "The director name is Francis ford coppola");

                //verifying that al pacino's character is michael corleone by parent and sibling relationship
                assertion.assertTrue(moviePageObj.alPacinoCharacterName.getText().equalsIgnoreCase(alPacinoCharacterName), "Al pacino's character name is michael corleone");
                assertion.assertAll();


            } else {
                logger.info("Shouldn't be tested on another browser");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Parameters({"browser"})
    @Test(priority = 02, description = "TC-03 : Verify Pepper fry website by entering Bedsheets")
    public void test3_verifyPepperfry_01(String browser) throws IOException, InterruptedException {
        try {
            assertion = new SoftAssert();
            //navigating to the webpage
            driver.get("https://www.pepperfry.com/");
            ReadExcelFile objExcelFile = new ReadExcelFile();


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            //waiting till search box appear
            wait.until(ExpectedConditions.visibilityOf(pepperFryPageObj.searchButton));

            pepperFryPageObj.searchBox.sendKeys(objExcelFile.readExcel(1));

            pepperFryPageObj.searchButton.click();

            Thread.sleep(5000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pepperFryPageObj.lowestPriceRadio);

//verifying that the radio is selected
            assertion.assertTrue(pepperFryPageObj.lowestPriceRadio.isSelected());

            assertion.assertAll();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    @Parameters({"browser"})
    @Test(priority = 03, description = "TC-04 : Verify Pepper fry website by entering Clocks")
    public void test3_verifyPepperfry_02(String browser) throws IOException, InterruptedException {
        try {
            assertion = new SoftAssert();
            //navigating to the webpage
            driver.get("https://www.pepperfry.com/");
            ReadExcelFile objExcelFile = new ReadExcelFile();


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            //waiting till search box appear
            wait.until(ExpectedConditions.visibilityOf(pepperFryPageObj.searchButton));

            //retrieving the value from the excel and searching by it
            pepperFryPageObj.searchBox.sendKeys(objExcelFile.readExcel(2));

            pepperFryPageObj.searchButton.click();


            wait.until(ExpectedConditions.visibilityOf(pepperFryPageObj.sortBySection));
            //checking if the results are displayed
            assertTrue(pepperFryPageObj.sortBySection.isDisplayed(), "Results are displayed");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pepperFryPageObj.lowestPriceRadio);

            //verifying that the radio is selected
            assertion.assertTrue(pepperFryPageObj.lowestPriceRadio.isSelected(), "is selected");

            assertion.assertAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @Parameters({"browser"})
    @Test(priority = 04, description = "TC-05 : Verify Pepper fry website by entering Padlocks")
    public void test3_verifyPepperfry_03(String browser) throws IOException, InterruptedException {
        try {
            assertion = new SoftAssert();
            //navigating to the webpage
            driver.get("https://www.pepperfry.com/");
            ReadExcelFile objExcelFile = new ReadExcelFile();


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            //waiting till search box appear
            wait.until(ExpectedConditions.visibilityOf(pepperFryPageObj.searchButton));

            //retrieving the value from the excel and searching by it
            pepperFryPageObj.searchBox.sendKeys(objExcelFile.readExcel(3));

            pepperFryPageObj.searchButton.click();

            assertTrue(pepperFryPageObj.sortBySection.isDisplayed(),"Results are found");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pepperFryPageObj.lowestPriceRadio);

            //verifying that the radio is selected
            assertion.assertTrue(pepperFryPageObj.lowestPriceRadio.isSelected(), "lowest price is selected");



            for(int i = 0; i<pepperFryPageObj.nameOfMaterial.size();i++){
                String text = pepperFryPageObj.nameOfMaterial.get(i).getText();
                System.out.println("The name of the padlock is " +text);
                String priceText = pepperFryPageObj.priceOfMaterial.get(i).getText();
                System.out.println("The name of the padlock is " +priceText);
                System.out.println("****************************************");
            }

            assertion.assertAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Parameters({"browser"})
    @Test(priority = 05, description = "TC-06 : Verify Google maps")
    public void test4_verifygoogleMaps(String browser) throws IOException, InterruptedException {
        if(browser.equalsIgnoreCase("Chrome")){
        //navigating to the webpage
        driver.get("http://maps.google.com");
        String address = "Borivali Railway Station";
        String fromSearchBox = "Kashigaon,Mira road";

        mapsPageObj.searchBox.sendKeys(address);
        mapsPageObj.searchBox.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(mapsPageObj.addressText));

        String addressText = mapsPageObj.addressText.getText();
        System.out.println(addressText);

        takeScreenShot("TC-06_maps_01");


        //Direction icon is visible
        assertTrue(mapsPageObj.directionsIcon.isDisplayed(),"Directions icon is visible");
        wait.until(ExpectedConditions.elementToBeClickable(mapsPageObj.directionsIcon));
        Thread.sleep(2000);
        mapsPageObj.directionsIcon.click();


        Thread.sleep(2000);
        mapsPageObj.directionFromSearchBox.sendKeys(fromSearchBox);
        mapsPageObj.directionFromSearchBox.sendKeys(Keys.ENTER);

        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(mapsPageObj.firstOptionDistance.get(0)));

        String firstOptionDistance = mapsPageObj.firstOptionDistance.get(0).getText();
        String firstOptionTime = mapsPageObj.firstOptionTime.get(0).getText();

        System.out.println("The first option's distance is : "+firstOptionDistance);
        System.out.println("The first option's time is : "+firstOptionTime);


        }
        else{
            logger.info("Not supposed to be tested on firefox and IE");
        }
    }

    @Parameters({"browser"})
    @Test(priority = 06, description = "TC-07 : Verify Data table website")
    public void test4_verifyDataTable(String browser) throws IOException, InterruptedException {
        if(browser.equalsIgnoreCase("Chrome")){
        //navigating to the webpage
        driver.get("https://datatables.net/");

        Select sel = new Select(dataTablePageObj.dropdownEntry);
        sel.selectByValue("25");
        Thread.sleep(3000);

        //click to sort by ascending age
        dataTablePageObj.ageColumn.click();


//Getting the entries and entering name , location and office
        for(int i =0;i<dataTablePageObj.ageValue.size();i++){
            String st = dataTablePageObj.ageValue.get(i).getText();
            int ageNumber = Integer.parseInt(st);

            if(ageNumber<30){
                System.out.println("The age is : " +st);
                System.out.println("The name is : "+dataTablePageObj.nameValue.get(i).getText());
                System.out.println("The position is : "+dataTablePageObj.positionValue.get(i).getText());
                System.out.println("The office is : "+dataTablePageObj.officeValue.get(i).getText());
                System.out.println("*************************************************");
                System.out.println();
            }
        }}
        else{
            logger.info("Shouldn't be tested on another browser");
        }

    }


    @Parameters({"browser"})
    @Test(priority = 06, description = "TC-07 : Verify Red bus website")
    public void test6_redBus(String browser) throws IOException, InterruptedException {
        if(browser.equalsIgnoreCase("Chrome")) {
            //navigating to the webpage
            driver.get("https://redbus.in/");

            String fromSearchText = "Mumbai";
            String destinationText = "Airoli";

            //Entering in from searchbox
            redBusPageObj.fromSearchbox.sendKeys(fromSearchText);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(redBusPageObj.autoSuggestResultFrom));
            redBusPageObj.autoSuggestResultFrom.click();

            //Entering in to searchbox
            redBusPageObj.toSearchBox.sendKeys(destinationText);
            wait.until(ExpectedConditions.visibilityOf(redBusPageObj.autoSuggestResultFrom));
            redBusPageObj.autoSuggestResultFrom.click();

            redBusPageObj.dateBox.click();

            redBusPageObj.currentDay.click();
            Thread.sleep(1000);

            redBusPageObj.searchBtn.click();
            Thread.sleep(5000);

            //Calling the write to excel method
           writeToExcel();
        }
        else {
            logger.info("Shouldn't be tested on another browser");
        }


        }



        public void takeScreenShot(String pathName) throws IOException {
            File src =( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,new File("Screenshots/"+pathName+".png"));
        }

        public void writeToExcel() throws IOException {
    FileInputStream fis = new FileInputStream("xlFIles\\redBusData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    for (int i = 0; i < redBusPageObj.ratings.size(); i++) {
        String ratingText = redBusPageObj.ratings.get(i).getText();
        double ratingNum = Double.parseDouble(ratingText);
        if (ratingNum < 4.0) {
            System.out.println(ratingNum);


            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(i + 1);
            Cell nameCell = row.createCell(0);
            Cell startTimeCell = row.createCell(1);
            Cell endTimeCell = row.createCell(2);
            Cell ratingCell = row.createCell(3);
            Cell moneyCell = row.createCell(4);
            Cell fromLocationCell = row.createCell(5);
            Cell toLocationCell = row.createCell(6);

            nameCell.setCellValue(redBusPageObj.nameToursAndTravels.get(i).getText());
            startTimeCell.setCellValue(redBusPageObj.startTime.get(i).getText());
            endTimeCell.setCellValue(redBusPageObj.endTime.get(i).getText());
            ratingCell.setCellValue(redBusPageObj.ratings.get(i).getText());
            moneyCell.setCellValue(redBusPageObj.moneyForTravel.get(i).getText());
            fromLocationCell.setCellValue(redBusPageObj.fromLocation.get(i).getText());
            toLocationCell.setCellValue(redBusPageObj.toLocation.get(i).getText());


        }


    }
    File f = new File("xlFiles\\redBusData.xlsx");
    FileOutputStream fos = new FileOutputStream(f);
    workbook.write(fos);
    fos.close();
    workbook.close();
  }


    @AfterTest()
       public void teardown(){
        driver.quit();
        }



}
