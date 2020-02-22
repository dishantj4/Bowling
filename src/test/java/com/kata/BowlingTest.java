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
    private Roll nextFrameSecondtRoll;
    private Frame frame;


    @Before
    public void init() {
        firstRoll = new Roll();
        secondRoll = new Roll();
        nextFrameFirstRoll = new Roll();
        nextFrameSecondtRoll = new Roll();
        frame = new Frame();

    }

    @Test
    public void shouldReturnFrameScoreZeroWhenZeroPinsAreKnockedDown() {
        firstRoll.setPinsKnockedDown(0);
        secondRoll.setPinsKnockedDown(0);
        assertEquals(frame.calculateFrameScore(listOfRolls(firstRoll,secondRoll)), 0);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenFewPinsAreKnockedDown() {
        firstRoll.setPinsKnockedDown(3);
        secondRoll.setPinsKnockedDown(4);
        assertEquals(frame.calculateFrameScore(listOfRolls(firstRoll,secondRoll)), 7);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInTwoRolls() {
        firstRoll.setPinsKnockedDown(3);
        secondRoll.setPinsKnockedDown(7);
        if(frame.calculateFrameScore(listOfRolls(firstRoll,secondRoll)) ==10){
            nextFrameFirstRoll.setPinsKnockedDown(4);
        }
        assertEquals(frame.calculateFrameScoreForSpare(nextFrameFirstRoll), 14);
    }

    @Test
    public void shouldReturnFrameScoreValueWhenAllPinsAreKnockedDownInFirstRoll() {
        firstRoll.setPinsKnockedDown(10);
        if(firstRoll.getPinsKnockedDown() ==10){
            nextFrameFirstRoll.setPinsKnockedDown(4);
            nextFrameSecondtRoll.setPinsKnockedDown(4);
        }
        assertEquals(frame.calculateFrameScoreForStrike(listOfRolls(nextFrameFirstRoll,nextFrameSecondtRoll)), 18);
    }

    public List<Roll> listOfRolls(Roll firstRoll, Roll secondRoll) {
        List<Roll> rolls = new ArrayList<>();
        rolls.add(firstRoll);
        rolls.add(secondRoll);
        return rolls;
    }

}
