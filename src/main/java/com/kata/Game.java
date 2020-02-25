package com.kata;

import java.util.List;

public class Game {

    private final static int FIRST_ROLL = 0;
    private final static int SECOND_ROLL = 1;
    private final static int THIRD_ROLL = 2;
    private final static int LAST_FRAME = 9;
    private final static int ALL_PINS_KNOCKED_DOWN = 10;
    private List<Frame> frames;
    private int pinsKnockedInFirstRoll = 0;
    private int pinsKnockedInSecondRoll = 0;
    private int frameScore = 0;
    private int nextFrame = 0;


    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public int calculateGameScore(int maxNumberOfFrame) {

        int gameScore = 0;
        for (int frameCount = 0; frameCount < maxNumberOfFrame; frameCount++) {
            frameScore = calculateFrameScoreForAllCase(frameCount);
            gameScore = gameScore + frameScore;
        }
        return gameScore;
    }


    public int calculateFrameScoreForAllCase(int frameCount){
        nextFrame = frameCount+1;
        pinsKnockedInFirstRoll = frames.get(frameCount).getRolls().get(FIRST_ROLL).getPinsKnockedDown();
        if (isStrike(pinsKnockedInFirstRoll)) {
            frameScore = calculateScoreForStrike(nextFrame);
        }
        else {
            pinsKnockedInSecondRoll= frames.get(frameCount).getRolls().get(SECOND_ROLL).getPinsKnockedDown();
            frameScore = pinsKnockedInFirstRoll + pinsKnockedInSecondRoll;
            if (isSpare(frameScore)) {
                frameScore = calculateScoreForSpare(frameCount,nextFrame);
            } else {
                frameScore = frames.get(frameCount).calculateFrameScore();
            }
        }
        return frameScore;
    }

    public int calculateScoreForStrike(int nextFrame){
        if(nextFrame == LAST_FRAME + 1)
            return frames.get(LAST_FRAME).calculateFrameScoreForStrikeOnTenthFrame();
        else
            return frames.get(nextFrame).calculateFrameScoreForStrike();
    }

    public int calculateScoreForSpare(int frameCount,int nextFrame){
        if(frameCount == LAST_FRAME)
            return frames.get(frameCount).calculateFrameScoreForSpare(frames.get(LAST_FRAME).getRolls().get(THIRD_ROLL));
        else
            return frames.get(frameCount).calculateFrameScoreForSpare(frames.get(nextFrame).getRolls().get(FIRST_ROLL));
    }

    public boolean isStrike(int pinsKnockedInFirstRoll){

        if(pinsKnockedInFirstRoll == ALL_PINS_KNOCKED_DOWN)
            return true;
        else
            return false;
    }

    public boolean isSpare(int frameScore){
        if(frameScore == ALL_PINS_KNOCKED_DOWN)
            return true;
        else
            return false;
    }

}