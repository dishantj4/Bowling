package com.kata;

import java.util.List;

public class Frame {

    private final static int firstRoll = 0;
    private final static int  secondRoll = 1;;

    private List<Roll> rolls;

    public List<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }

    public int calculateFrameScore() {

        int frameScore = rolls.get(firstRoll).getPinsKnockedDown() + rolls.get(secondRoll).getPinsKnockedDown();
        return frameScore;
    }

    public int calculateFrameScoreForSpare(Roll pinsKnockedInNextRoll) {
        int bonus = 10;
        return pinsKnockedInNextRoll.getPinsKnockedDown()+bonus;
    }

    public int calculateFrameScoreForStrike() {
        int bonus = 10;
        return calculateFrameScore()+ bonus;
    }
}
