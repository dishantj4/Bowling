package com.kata;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BowlingGameTest
{
    private Roll firstRoll;
    private Roll secondRoll;
    private Roll nextFrameFirstRoll;
    private Roll nextFrameSecondRoll;
    private Frame frame;
    private BowlingGame bowlingGame;



    @Test
    public void shouldReturnFrameScoreZeroWhenZeroPinsAreKnockedDown() {
        frame = new Frame(listOfRolls(new Roll( 0 ), new Roll( 0 )));
        assertEquals(frame.calculateFrameScore(), 0);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenFewPinsAreKnockedDown() {
        frame = new Frame(listOfRolls(new Roll( 3 ), new Roll( 4 )));
        assertEquals(frame.calculateFrameScore(), 7);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInTwoRolls() {
        frame = new Frame(listOfRolls(new Roll( 5 ), new Roll( 5 )));
        assertEquals(frame.calculateFrameScoreForSpare(new Roll( 4 )), 14);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInFirstRoll() {
        frame = new Frame(listOfRolls(new Roll( 4 ), new Roll( 4 )));
        assertEquals(frame.calculateFrameScoreForStrike(), 18);
    }


    @Test
    public void shouldReturnGameScoreForFrameOneWhenZeroPinsKnockedDown() {
        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(0,0)));
        assertEquals( bowlingGame.calculateGameScore(1), 0);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneSpareAndOtherFewPinsKnockedDown() {
        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(5,5), setNewFrameWithRolls(3,4)));
        assertEquals( bowlingGame.calculateGameScore(2), 20);
    }

    @Test
    public void shouldReturnGameScoreForTwoFramesWithOneStrikeAndOtherFewPinsKnockedDown() {
        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(10,0), setNewFrameWithRolls(3,4)));
        assertEquals( bowlingGame.calculateGameScore(2), 24);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithoutAnyStrikeOrSpare() {
        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(3,3),
                setNewFrameWithRolls(2,1),setNewFrameWithRolls(1,3),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(4,3)));
        assertEquals( bowlingGame.calculateGameScore(10), 57);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithStrikeAndSpare() {

        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(4,3)));
        assertEquals( bowlingGame.calculateGameScore(10), 100);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithSpareOnTenthFrame() {

        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(5,5,4)));
        assertEquals( bowlingGame.calculateGameScore(10), 107);
    }

    @Test
    public void shouldReturnGameScoreForTenFramesWithStrikeOnTenthFrame() {

        bowlingGame =  new BowlingGame(listOfFrames(setNewFrameWithRolls(2,3),setNewFrameWithRolls(5,5),
                setNewFrameWithRolls(10,0),setNewFrameWithRolls(10,0),setNewFrameWithRolls(2,4),
                setNewFrameWithRolls(5,3),setNewFrameWithRolls(2,1),setNewFrameWithRolls(5,3),
                setNewFrameWithRolls(4,3),setNewFrameWithRolls(10,5,4)));
        assertEquals( bowlingGame.calculateGameScore(10), 112);
    }

    private List<Roll> listOfRolls(Roll ... rolls) {
        return new ArrayList<>(Arrays.asList( rolls ));
    }

    private List<Frame> listOfFrames(Frame ... frames) {
        return new ArrayList<>(Arrays.asList( frames ));
    }

    private Frame setNewFrameWithRolls(int ... rollsInFrame){
        Roll frameFirstRoll = new Roll(rollsInFrame[0]);
        Roll frameSecondRoll = new Roll(rollsInFrame[1]);
        if (rollsInFrame.length > 2)
        {
            Roll frameThirdRoll = new Roll(rollsInFrame[2]);
            return new Frame(listOfRolls(frameFirstRoll,frameSecondRoll,frameThirdRoll));
        }


        return new Frame(listOfRolls(frameFirstRoll,frameSecondRoll));
    }

}
