package com.games.pages;

import com.games.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkers {

    public Checkers(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="message")
    public static WebElement message;

    @FindBy(id ="board")
    public static WebElement board;

    @FindBy(xpath = "//a[.='Restart...']")
    public static WebElement restartButton;
    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

    public static void makeMoveAndTakePiece(int rowNumber, int cellNumber, int targetRowNumber, int targetCellNumber) throws InterruptedException {
        clickOnCell(rowNumber, cellNumber);
        waitForMakeMoveMessage();

        WebElement targetCellElement = getCellAsWebElement(targetRowNumber, targetCellNumber);

        // Wait for the target cell to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(targetCellElement));

        // Now, click on the target cell
        targetCellElement.click();

        Thread.sleep(20);
    }






    public static boolean isEmptyCell(WebElement cell){
        boolean isValid = false;
        if(cell.getAttribute("src").contains("gray")){
            isValid = true;
        }
        return isValid;
    }

    public static void clickOnCell(int rowNumber, int cellNumber){
        String locator = "//img[@name='space"+ cellNumber + rowNumber +"']";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }



    //method that can help us to pass any row number and cell number and get that cell as web element
    public static WebElement getCellAsWebElement(int rowNumber, int cellNumber){
        if(rowNumber<0||rowNumber>7) {
            throw new RuntimeException("Invalid row number provided :" + rowNumber);
        }
        String locator = "//img[@name='space"+ cellNumber + rowNumber +"']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    public static void waitForMakeMoveMessage(){
       WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        if (messageElement.getText().equals("make a move")) {
            // The "make a move" message is displayed, proceed with the move
        }
    }
    }




