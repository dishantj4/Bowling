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
        for (int frameCount = 0; frameCount < maxNumberOfFrame; frameCount++) {
            firstRoll = frames.get(frameCount).getRolls().get(0).getPinsKnockedDown();
            if (firstRoll == 10) {
                frameScore = frames.get(frameCount+1).calculateFrameScoreForStrike();
            }
            else {
                frameScore = firstRoll + frames.get(frameCount).getRolls().get(1).getPinsKnockedDown();
                if (frameScore == 10) {
                    frameScore = frames.get(frameCount).calculateFrameScoreForSpare(frames.get(frameCount + 1).getRolls().get(0));
                } else {
                    frameScore = frames.get(frameCount).calculateFrameScore();
                }
            }
            gameScore = gameScore + frameScore;
        }
        return gameScore;
    }
}
