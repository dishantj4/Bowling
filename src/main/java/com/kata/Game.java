package com.kata;

import java.util.List;

public class Game {

    private final static int firstRoll = 0;
    private final static int  secondRoll = 1;
    private List<Frame> frames;

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public int calculateGameScore(int maxNumberOfFrame) {

        int gameScore = 0;
        int frameScore = 0;
        int pinsKnockedInFirstRoll = 0;
        int pinsKnockedInSecondRoll = 0;
        int nextFrame = 0;
        for (int frameCount = 0; frameCount < maxNumberOfFrame; frameCount++) {
            nextFrame = frameCount+1;
            pinsKnockedInFirstRoll = frames.get(frameCount).getRolls().get(firstRoll).getPinsKnockedDown();
            if (isStrike(pinsKnockedInFirstRoll)) {
                frameScore = calculateScoreForStrike(nextFrame);
            }
            else {
                pinsKnockedInSecondRoll= frames.get(frameCount).getRolls().get(secondRoll).getPinsKnockedDown();
                frameScore = pinsKnockedInFirstRoll + pinsKnockedInSecondRoll;
                if (isSpare(frameScore)) {
                    frameScore = calculateScoreForSpare(frameCount,nextFrame);
                } else {
                    frameScore = frames.get(frameCount).calculateFrameScore();
                }
            }
            gameScore = gameScore + frameScore;
        }
        return gameScore;
    }

    public int calculateScoreForStrike(int nextFrame){
        return frames.get(nextFrame).calculateFrameScoreForStrike();

    }

    public int calculateScoreForSpare(int frameCount,int nextFrame){
        return frames.get(frameCount).calculateFrameScoreForSpare(frames.get(nextFrame).getRolls().get(firstRoll));

    }
    public boolean isStrike(int pinsKnockedInFirstRoll){

        if(pinsKnockedInFirstRoll == 10)
            return true;
        else
            return false;
    }

    public boolean isSpare(int frameScore){
        if(frameScore == 10)
            return true;
        else
            return false;
    }
}