package com.kata;

import java.util.List;

public class Frame {


    public int calculateFrameScore(List<Roll> rolls) {

        int frameScore = rolls.get(0).getPinsKnockedDown() + rolls.get(1).getPinsKnockedDown();
        return frameScore;
    }

    public int calculateFrameScoreForSpare(Roll pinsKnockedInNextRoll) {
        return pinsKnockedInNextRoll.getPinsKnockedDown()+10;
    }
}
