package com.kata;

import java.util.List;

public class Frame {


    public int calculateFrameScore(List<Roll> rolls) {

        int frameScore = 0;
        for(Roll roll : rolls)
        {
            frameScore = frameScore +roll.getPinsKnockedDown();
        }
        return frameScore;
    }
}
