/*
 * Copyright (C) 2021 Hiram K. <https://github.com/IdelsTak>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.idelstak.landscape;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class RegionTest {

    public RegionTest() {
    }

    @Test
    @SuppressWarnings({"ThrowableResultIgnored", "ResultOfObjectAllocationIgnored"})
    public void shouldHaveMin1Row() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //Landscape array with no element
                    int[] landscape = new int[]{};
                    new Region(landscape);
                });
    }

    @Test
    @SuppressWarnings({"ThrowableResultIgnored", "ResultOfObjectAllocationIgnored"})
    public void shouldHaveMax32000Rows() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //Landscape array meant to hold 32,010 hills
                    int positions = 32_010;
                    int[] landscape = new int[positions];
                    //Randomize the heights of the hills
                    Random random = ThreadLocalRandom.current();

                    for (int i = 0; i < positions; i++) {
                        //Height of a hill randomized to between 0 and 32,000
                        landscape[i] = random.nextInt(32_000);
                    }

                    new Region(landscape);
                });
    }

    @Test
    @SuppressWarnings({"ThrowableResultIgnored", "ResultOfObjectAllocationIgnored"})
    public void shouldHaveMin0ColumnHeight() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //Hill with negative height
                    int[] landscape = new int[]{-1};
                    new Region(landscape);
                });
    }

    @Test
    @SuppressWarnings({"ThrowableResultIgnored", "ResultOfObjectAllocationIgnored"})
    public void shouldHaveMax32000ColumnHeight() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //Hill with negative height
                    int[] landscape = new int[]{32_008};
                    new Region(landscape);
                });
    }

    @Test
    public void shouldCreateStringMatrixBeforeRaining() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};
        String[][] expectedMatrix = new String[][]{
            {"X ", "  ", "  ", "  ", "X ", "  ", "  ", "  ", "  "},
            {"X ", "  ", "  ", "X ", "X ", "X ", "  ", "  ", "  "},
            {"X ", "  ", "  ", "X ", "X ", "X ", "  ", "X ", "  "},
            {"X ", "  ", "X ", "X ", "X ", "X ", "  ", "X ", "  "},
            {"X ", "X ", "X ", "X ", "X ", "X ", "  ", "X ", "X "}
        };

        Region region = new Region(landscape);
        String[][] actualMatrix = region.asMatrixBeforeRaining();

        for (int i = 0; i < actualMatrix.length; i++) {
            assertTrue(Arrays.equals(expectedMatrix[i], actualMatrix[i]));
        }

    }

    @Test
    public void shouldCreateStringMatrixAfterRaining() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};
        String[][] expectedMatrix = new String[][]{
            {"X ", "O ", "O ", "O ", "X ", "  ", "  ", "  ", "  "},
            {"X ", "O ", "O ", "X ", "X ", "X ", "  ", "  ", "  "},
            {"X ", "O ", "O ", "X ", "X ", "X ", "O ", "X ", "  "},
            {"X ", "O ", "X ", "X ", "X ", "X ", "O ", "X ", "  "},
            {"X ", "X ", "X ", "X ", "X ", "X ", "O ", "X ", "X "}
        };

        Region region = new Region(landscape);
        String[][] actualMatrix = region.asMatrixAfterRaining();

        for (int i = 0; i < actualMatrix.length; i++) {
            assertTrue(Arrays.equals(expectedMatrix[i], actualMatrix[i]));
        }
    }

    @Test
    public void shouldCreateStringMatrixAfterEvaporation() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};
        String[][] expectedMatrix = new String[][]{
            {"X ", "  ", "  ", "  ", "X ", "  ", "  ", "  ", "  "},
            {"X ", "  ", "  ", "X ", "X ", "X ", "  ", "  ", "  "},
            {"X ", "O ", "O ", "X ", "X ", "X ", "  ", "X ", "  "},
            {"X ", "O ", "X ", "X ", "X ", "X ", "  ", "X ", "  "},
            {"X ", "X ", "X ", "X ", "X ", "X ", "O ", "X ", "X "}
        };

        Region region = new Region(landscape);
        String[][] actualMatrix = region.asMatrixAfterEvaporation();

        for (int i = 0; i < actualMatrix.length; i++) {
            assertTrue(Arrays.equals(expectedMatrix[i], actualMatrix[i]));
        }
    }

    @Test
    public void shouldCalculateWaterCollectedAfterRaining() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};
        long expectedAmount = 11L;
        Region region = new Region(landscape);
        long actualAmount = region.waterAmountAfterRaining();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void shouldCaclculateWaterCollectedAfterEvaporation() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};
        long expectedAmount = 4L;

        Region region = new Region(landscape);
        long actualAmount = region.waterAmountAfterEvaporation();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void toStringShouldReturnAllInfos() {
        int[] landscape = new int[]{5, 1, 2, 4, 5, 4, 0, 3, 1};

        String expected = """
                           Before rain:
                           
                           X       X        \s
                           X     X X X      \s
                           X     X X X   X  \s
                           X   X X X X   X  \s
                           X X X X X X   X X\s
                           
                           After rain:
                           
                           X O O O X        \s
                           X O O X X X      \s
                           X O O X X X O X  \s
                           X O X X X X O X  \s
                           X X X X X X O X X\s
                           
                           After evaporation:
                           
                           X       X        \s
                           X     X X X      \s
                           X O O X X X   X  \s
                           X O X X X X   X  \s
                           X X X X X X O X X\s
                           
                           Water collected after raining: 11 units
                           Water remaining after evaporation: 4 units
                           """;

        Region region = new Region(landscape);
        String actual = region.toString();

        assertEquals(expected, actual);
    }
}
