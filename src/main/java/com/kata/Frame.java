package com.kata;

import java.util.List;

public class Frame {

    private List<Roll> rolls;

    public List<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }

    public int calculateFrameScore() {

        int frameScore = rolls.get(0).getPinsKnockedDown() + rolls.get(1).getPinsKnockedDown();
        return frameScore;
    }

    public int calculateFrameScoreForSpare(Roll pinsKnockedInNextRoll) {
        return pinsKnockedInNextRoll.getPinsKnockedDown()+10;
    }

    public int calculateFrameScoreForStrike() {
        return calculateFrameScore()+10;
    }
}
