package com.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BowlingTest
{


    @Test
    public void shouldReturnFrameScoreZeroWhenZeroPinsAreKnockedDown() {

        Roll roll1 = new Roll();
        roll1.setPinsKnockedDown(0);
        Roll roll2 = new Roll();
        roll2.setPinsKnockedDown(0);
        List<Roll> rolls = new ArrayList<>();
        rolls.add(roll1);
        rolls.add(roll2);
        Frame frame = new Frame();
        assertEquals(frame.calculateFrameScore(rolls), 0);
    }


}
