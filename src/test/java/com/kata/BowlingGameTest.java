package com.kata;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BowlingGameTest
{
    private Roll firstRoll;
    private Roll secondRoll;
    private Roll nextFrameFirstRoll;
    private Roll nextFrameSecondRoll;
    private Frame frame;
    private BowlingGame bowlingGame;


    @Before
    public void init() {
        firstRoll = new Roll();
        secondRoll = new Roll();
        nextFrameFirstRoll = new Roll();
        nextFrameSecondRoll = new Roll();
        frame = new Frame();
        bowlingGame = new BowlingGame();
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
        nextFrameFirstRoll.setPinsKnockedDown(4);
        assertEquals(frame.calculateFrameScoreForSpare(nextFrameFirstRoll), 14);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInFirstRoll() {
        firstRoll.setPinsKnockedDown(10);
        nextFrameFirstRoll.setPinsKnockedDown(4);
        nextFrameSecondRoll.setPinsKnockedDown(4);
        frame.setRolls(listOfRolls(nextFrameFirstRoll, nextFrameSecondRoll));
        assertEquals(frame.calculateFrameScoreForStrike(), 18);
    }


    @Test
    public void shouldReturnGameScoreForFrameOneWhenZeroPinsKnockedDown() {
        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(0,0)));
        assertEquals( bowlingGame.calculateGameScore(1), 0);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneSpareAndOtherFewPinsKnockedDown() {
        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(5,5), setNewFrameWithRolls(3,4)));
        assertEquals( bowlingGame.calculateGameScore(2), 20);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneStrikeAndOtherFewPinsKnockedDown() {
        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(10,0), setNewFrameWithRolls(3,4)));
        assertEquals( bowlingGame.calculateGameScore(2), 24);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithoutAnyStrikeOrSpare() {
        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(3,3),
                setNewFrameWithRolls(2,1),setNewFrameWithRolls(1,3),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(4,3)));
        assertEquals( bowlingGame.calculateGameScore(10), 57);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithStrikeAndSpare() {

        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(4,3)));
        assertEquals( bowlingGame.calculateGameScore(10), 100);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithSpareOnTenthFrame() {

        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(5,5,4)));
        assertEquals( bowlingGame.calculateGameScore(10), 107);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithStrikeOnTenthFrame() {

        bowlingGame.setFrames(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(10,5,4)));
        assertEquals( bowlingGame.calculateGameScore(10), 112);
    }

    private List<Roll> listOfRolls(Roll ... rolls) {
        List<Roll> rollList = new ArrayList<>();
        for (Roll roll : rolls) {
            rollList.add(roll);
        }
        return rollList;
    }

    private List<Frame> listOfFrames(Frame ... frames) {
        List<Frame> frameList = new ArrayList<>();
        for (Frame frame : frames) {
            frameList.add(frame);
        }
        return frameList;
    }

    private Frame setNewFrameWithRolls(int ... rollsInFrame){
        Roll frameFirstRoll = new Roll();
        frameFirstRoll.setPinsKnockedDown(rollsInFrame[0]);
        Roll frameSecondRoll = new Roll();
        frameSecondRoll.setPinsKnockedDown(rollsInFrame[1]);
        Roll frameThirdRoll = new Roll();
        if (rollsInFrame.length > 2)
        {
            frameThirdRoll.setPinsKnockedDown(rollsInFrame[2]);
        }
        Frame frame = new Frame();
        frame.setRolls(listOfRolls(frameFirstRoll,frameSecondRoll,frameThirdRoll));
        return frame;
    }

}
