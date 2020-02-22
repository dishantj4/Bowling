package com.kata;

import java.util.List;

public class Frame {


    public int calculateFrameScore(List<Roll> rolls) {

        int score = 0;
        for(Roll roll : rolls)
        {
           score = score +roll.getPinsKnockedDown();
        }
        return score;
    }
}
