package com.kata;

import java.util.List;

import static com.kata.Constants.*;

public class Frame {

    private List<Roll> rolls;

    public List<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }

    public int calculateFrameScore() {
        return getPinsKnockedDownInRoll(FIRST_ROLL)
                + getPinsKnockedDownInRoll(SECOND_ROLL);
    }

    public int calculateFrameScoreForSpare(Roll pinsKnockedInNextRoll) {
        return pinsKnockedInNextRoll.getPinsKnockedDown()+SPARE_BONUS;
    }

    public int calculateFrameScoreForStrike() {
        return calculateFrameScore()+ STRIKE_BONUS;
    }

    public int calculateFrameScoreForStrikeOnTenthFrame() {
        return getPinsKnockedDownInRoll(FIRST_ROLL)
                + getPinsKnockedDownInRoll(SECOND_ROLL)
                + getPinsKnockedDownInRoll(THIRD_ROLL);
    }
    public int  getPinsKnockedDownInRoll(int rollIndex){
        return  rolls.get( rollIndex ).getPinsKnockedDown();
    }
}
