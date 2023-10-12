package com.games.step_definitions;

import com.games.pages.Checkers;
import com.games.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Checkers_StepDefinitions {

    Checkers checkersPage = new Checkers();

    @Given("I am on the Checkers game website")
    public void i_am_on_the_checkers_game_website() {


        //title verification
        String expectedTitle = "Checkers - Games for the Brain";
        String actualTitle = Driver.getDriver().getTitle();
        assertEquals(expectedTitle,actualTitle);


        String expectedMessage = "Select an orange piece to move.";
        String actualMessage = checkersPage.message.getText();
        assertEquals(expectedMessage,actualMessage);
        assertTrue(checkersPage.message.isDisplayed());
        assertTrue(checkersPage.board.isDisplayed());

    }

    @When("I make five legal moves as orange and take a blue piece")
    public void i_make_five_legal_moves_as_orange_and_take_a_blue_piece() throws InterruptedException {
        checkersPage.makeMoveAndTakePiece(2, 6, 3, 7);

        checkersPage.makeMoveAndTakePiece(3, 7, 4, 6);

        checkersPage.makeMoveAndTakePiece(1, 3, 2, 2);

        checkersPage.makeMoveAndTakePiece(1, 5, 2, 4);

        checkersPage.makeMoveAndTakePiece(2, 2, 3, 1);
    }

    @And("I restart the game")
    public void i_restart_the_game() {
        checkersPage.restartButton.click();
    }
    @Then("I confirm that the game has been successfully restarted")
    public void i_confirm_that_the_game_has_been_successfully_restarted() {
        //verify the message to make a move is displayed and correct
        String expectedMessage = "Select an orange piece to move.";
        String actualMessage = checkersPage.message.getText();
        assertEquals(expectedMessage,actualMessage);
        assertTrue(checkersPage.message.isDisplayed());
    }


}
