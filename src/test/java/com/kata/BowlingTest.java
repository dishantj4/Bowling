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
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);
        game.setFrames(frames);
        assertEquals(game.calculateGameScore(), 0);
    }

    public List<Roll> listOfRolls(Roll firstRoll, Roll secondRoll) {
        List<Roll> rolls = new ArrayList<>();
        rolls.add(firstRoll);
        rolls.add(secondRoll);
        return rolls;
    }

}
