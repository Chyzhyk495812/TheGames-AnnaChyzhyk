package com.games.step_definitions;

import com.games.utilities.BlackJack_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BlackJack_StepDefinitions {
    Response response;
    String baseUrl;
    String deckId;//deckId to use in different steps
    Map<Integer, List<String>> playersCards = new LinkedHashMap<>();//to store each player cards info
    String winningPlayer = "no winner";

    @Given("I send GET request to {string}")
    public void i_send_get_request_to(String url) {
        response = when().get(url);
        baseUrl = url;
    }
    @Given("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
    @When("I get a new deck")
    public void i_get_a_new_deck() {
        //send GET request for getting a new deck and also storing new deck id into a variable
        deckId =  when().get(baseUrl+"api/deck/new/").then().statusCode(200)
                .extract().jsonPath().getString("deck_id");
    }
    @When("I shuffle the deck")
    public void i_shuffle_the_deck() {

        given().pathParam("deckId",deckId)
                .when().get(baseUrl+"api/deck/{deckId}/shuffle/")
                .then().statusCode(200);
    }
    @When("I deal {int} cards to each of {int} players")
    public void i_deal_cards_to_each_of_players(int numberOfCards, int numberOfPlayers) {

        //dynamic loop storing each player cards information into a map

        for (int i = 1; i <= numberOfPlayers ; i++) {

            JsonPath jsonPath = given().pathParam("deckId", deckId)
                    .and().queryParam("count", numberOfCards)
                    .when().get(baseUrl + "api/deck/{deckId}/draw/").then().statusCode(200).extract().jsonPath();

            playersCards.put(i, jsonPath.getList("cards.value"));
        }
    }
    @Then("I check whether either player has blackjack")
    public void i_check_whether_either_player_has_blackjack() {
        //iterate through each player information to validate amount
        //using Util method to calculate card values

        for (Integer eachPlayer : playersCards.keySet()) {
            if(BlackJack_Util.countValues(playersCards.get(eachPlayer))==21){
                winningPlayer = "Player: "+eachPlayer;
                break;
            }
        }
        System.out.println(playersCards);
    }
    @Then("I display the winning player if any")
    public void i_display_the_winning_player_if_any() {
        System.out.println("In this game winning player is: "+winningPlayer);
    }

}
