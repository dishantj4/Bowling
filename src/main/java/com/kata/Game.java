package com.kata;

import java.util.List;

public class Game {

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
        int firstRoll = 0;
        int secondRoll = 0;
        int nextFrame = 0;
        for (int frameCount = 0; frameCount < maxNumberOfFrame; frameCount++) {
            nextFrame = frameCount+1;
            firstRoll = frames.get(frameCount).getRolls().get(0).getPinsKnockedDown();
            if (isStrike(firstRoll)) {
                frameScore = frames.get(nextFrame).calculateFrameScoreForStrike();
            }
            else {
                secondRoll= frames.get(frameCount).getRolls().get(1).getPinsKnockedDown();
                frameScore = firstRoll + secondRoll;
                if (isSpare(frameScore)) {
                    frameScore = frames.get(frameCount).calculateFrameScoreForSpare(frames.get(nextFrame).getRolls().get(0));
                } else {
                    frameScore = frames.get(frameCount).calculateFrameScore();
                }
            }
            gameScore = gameScore + frameScore;
        }
        return gameScore;
    }

    public boolean isStrike(int firstRoll){

        if(firstRoll == 10)
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