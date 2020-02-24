package com.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BowlingTest
{
    private Roll firstRoll;
    private Roll secondRoll;
    private Roll nextFrameFirstRoll;
    private Roll nextFrameSecondRoll;
    private Frame frame;
    private Game game;


    @Before
    public void init() {
        firstRoll = new Roll();
        secondRoll = new Roll();
        nextFrameFirstRoll = new Roll();
        nextFrameSecondRoll = new Roll();
        frame = new Frame();
        game = new Game();
    }

    @Test
    public void shouldReturnFrameScoreZeroWhenZeroPinsAreKnockedDown() {
        firstRoll.setPinsKnockedDown(0);
        secondRoll.setPinsKnockedDown(0);
        frame.setRolls(listOfRolls(firstRoll,secondRoll));
        assertEquals(frame.calculateFrameScore(), 0);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenFewPinsAreKnockedDown() {
        firstRoll.setPinsKnockedDown(3);
        secondRoll.setPinsKnockedDown(4);
        frame.setRolls(listOfRolls(firstRoll,secondRoll));
        assertEquals(frame.calculateFrameScore(), 7);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInTwoRolls() {
        firstRoll.setPinsKnockedDown(3);
        secondRoll.setPinsKnockedDown(7);
        frame.setRolls(listOfRolls(firstRoll,secondRoll));
        if(frame.calculateFrameScore() ==10){
            nextFrameFirstRoll.setPinsKnockedDown(4);
        }
        assertEquals(frame.calculateFrameScoreForSpare(nextFrameFirstRoll), 14);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInFirstRoll() {
        firstRoll.setPinsKnockedDown(10);
        if(firstRoll.getPinsKnockedDown() ==10){
            nextFrameFirstRoll.setPinsKnockedDown(4);
            nextFrameSecondRoll.setPinsKnockedDown(4);
        }
        frame.setRolls(listOfRolls(nextFrameFirstRoll, nextFrameSecondRoll));
        assertEquals(frame.calculateFrameScoreForStrike(), 18);
    }


    @Test
    public void shouldReturnGameScoreForFrameOneWhenZeroPinsKnockedDown() {
        firstRoll.setPinsKnockedDown(0);
        secondRoll.setPinsKnockedDown(0);
        frame.setRolls(listOfRolls(firstRoll, secondRoll));
        game.setFrames(listOfFrames(frame));
        assertEquals(game.calculateGameScore(1), 0);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneSpareAndOtherFewPinsKnockedDown() {
        firstRoll.setPinsKnockedDown(5);
        secondRoll.setPinsKnockedDown(5);
        frame.setRolls(listOfRolls(firstRoll, secondRoll));
        Frame nextFrame = new Frame();
        nextFrameFirstRoll.setPinsKnockedDown(3);
        nextFrameSecondRoll.setPinsKnockedDown(4);
        nextFrame.setRolls(listOfRolls(nextFrameFirstRoll, nextFrameSecondRoll));
        game.setFrames(listOfFrames(frame, nextFrame));
        assertEquals(game.calculateGameScore(2), 20);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneStrikeAndOtherFewPinsKnockedDown() {
        firstRoll.setPinsKnockedDown(10);
        frame.setRolls(listOfRolls(firstRoll));
        Frame nextFrame = new Frame();
        nextFrameFirstRoll.setPinsKnockedDown(3);
        nextFrameSecondRoll.setPinsKnockedDown(4);
        nextFrame.setRolls(listOfRolls(nextFrameFirstRoll, nextFrameSecondRoll));
        game.setFrames(listOfFrames(frame, nextFrame));
        assertEquals(game.calculateGameScore(2), 24);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithoutAnyStrikeOrSpare() {

        game.setFrames(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(3,3),
                setNewFrameWithRolls(2,1),setNewFrameWithRolls(1,3),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(4,3)));
        assertEquals(game.calculateGameScore(10), 57);
    }

    public List<Roll> listOfRolls(Roll ... rolls) {
        List<Roll> rollList = new ArrayList<>();
        for (Roll roll : rolls) {
            rollList.add(roll);
        }
        return rollList;
    }

    public List<Frame> listOfFrames(Frame ... frames) {
        List<Frame> frameList = new ArrayList<>();
        for (Frame frame : frames) {
            frameList.add(frame);
        }
        return frameList;
    }

    public Frame setNewFrameWithRolls(int firstRollInFrame, int secondRollInFrame){
        Roll frame3FirstRoll = new Roll();
        frame3FirstRoll.setPinsKnockedDown(firstRollInFrame);
        Roll frame3SecondRoll = new Roll();
        frame3SecondRoll.setPinsKnockedDown(secondRollInFrame);
        Frame frame3 = new Frame();
        frame3.setRolls(listOfRolls(frame3FirstRoll,frame3SecondRoll));
        return frame3;
    }

}
