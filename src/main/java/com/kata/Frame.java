package com.kata;

import java.util.List;

public class Frame {

    private final static int FIRST_ROLL = 0;
    private final static int SECOND_ROLL = 1;
    private final static int THIRD_ROLL = 2;

    private List<Roll> rolls;

    public List<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }

    public int calculateFrameScore() {

        int frameScore = rolls.get(FIRST_ROLL).getPinsKnockedDown() + rolls.get(SECOND_ROLL).getPinsKnockedDown();
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

    public int calculateFrameScoreForStrikeOnTenthFrame() {
        int frameScore = rolls.get(FIRST_ROLL).getPinsKnockedDown()
                + rolls.get(SECOND_ROLL).getPinsKnockedDown() + rolls.get(THIRD_ROLL).getPinsKnockedDown();
        return frameScore;
    }

}
