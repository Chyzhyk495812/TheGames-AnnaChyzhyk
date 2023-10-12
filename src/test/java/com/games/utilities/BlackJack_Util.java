package com.games.utilities;

import java.util.List;

public class BlackJack_Util {
    public static int countValues(List<String> allCards){
        int sum = 0;
        boolean aceIsPresent = false;
        for (String eachCard : allCards) {
            if(eachCard.equals("JACK")||eachCard.equals("QUEEN")||eachCard.equals("KING")){
                sum+=10;
            }else if(eachCard.equals("ACE")){
                aceIsPresent = true;
            } else {
                sum+=Integer.parseInt(eachCard);
            }
        }
        if(aceIsPresent){
            if((sum + 11) > 21){
                sum+=1;
            }else{
                sum+=11;
            }
        }
        return sum;
    }
}
