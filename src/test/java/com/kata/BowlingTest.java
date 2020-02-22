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
    private Frame frame;


    @Before
    public void init() {
        firstRoll = new Roll();
        secondRoll = new Roll();
        frame = new Frame();

    }
    
    @Test
    public void shouldReturnFrameScoreZeroWhenZeroPinsAreKnockedDown() {
        firstRoll.setPinsKnockedDown(0);
        secondRoll.setPinsKnockedDown(0);
        assertEquals(frame.calculateFrameScore(listOfRolls(firstRoll,secondRoll)), 0);
    }

    public List<Roll> listOfRolls(Roll firstRoll, Roll secondRoll) {
        List<Roll> rolls = new ArrayList<>();
        rolls.add(firstRoll);
        rolls.add(secondRoll);
        return rolls;
    }

}
