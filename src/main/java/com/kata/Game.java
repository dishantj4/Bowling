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

    public int calculateGameScore() {
        return frames.get(0).calculateFrameScore();
    }
}
