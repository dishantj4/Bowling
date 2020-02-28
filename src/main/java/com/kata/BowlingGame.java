package com.kata;

import java.util.List;

import static com.kata.Constants.*;

public class BowlingGame {

    public BowlingGame(List<Frame> frames) {
        this.frames = frames;
    }

    private List<Frame> frames;


    public int calculateGameScore(int maxNumberOfFrame) {

        int gameScore = 0;
        int frameScore;
        for (int frameCount = 0; frameCount < maxNumberOfFrame; frameCount++) {
            frameScore = calculateFrameScoreForAllCase( frameCount);
            gameScore += frameScore;
        }
        return gameScore;
    }


    private int calculateFrameScoreForAllCase(int frameCount){
        int nextFrame = frameCount+1;
        int frameScore;
        int pinsKnockedInFirstRoll = frames.get(frameCount).getPinsKnockedDownInRoll(FIRST_ROLL);
        if (isStrike(pinsKnockedInFirstRoll)) {
            frameScore = calculateScoreForStrike(nextFrame);
        }
        else {
            int pinsKnockedInSecondRoll= frames.get(frameCount).getPinsKnockedDownInRoll(SECOND_ROLL);
            if (isSpare(pinsKnockedInFirstRoll,pinsKnockedInSecondRoll)) {
                frameScore = calculateScoreForSpare(frameCount,nextFrame);
            } else {
                frameScore = frames.get(frameCount).calculateFrameScore();
            }
        }
        return frameScore;
    }

    private int calculateScoreForStrike(int nextFrame){
        if(nextFrame == LAST_FRAME + 1)
            return frames.get(LAST_FRAME).calculateFrameScoreForStrikeOnTenthFrame();
        else
            return frames.get(nextFrame).calculateFrameScoreForStrike();
    }

    private int calculateScoreForSpare(int frameCount,int nextFrame){
        if(frameCount == LAST_FRAME)
            return frames.get(frameCount).calculateFrameScoreForSpare(frames.get(LAST_FRAME).getRolls().get(THIRD_ROLL));
        else
            return frames.get(frameCount).calculateFrameScoreForSpare(frames.get(nextFrame).getRolls().get(FIRST_ROLL));
    }

    private boolean isStrike(int pinsKnockedInFirstRoll){
        return pinsKnockedInFirstRoll == ALL_PINS_KNOCKED_DOWN;
    }

    private boolean isSpare(int pinsKnockedInFirstRoll, int pinsKnockedInSecondRoll){
        return (pinsKnockedInFirstRoll+pinsKnockedInSecondRoll) == ALL_PINS_KNOCKED_DOWN;
    }

}