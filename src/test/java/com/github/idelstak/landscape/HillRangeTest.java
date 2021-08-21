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

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class HillRangeTest {

    public HillRangeTest() {
    }

    @Test
    public void shouldCountHills() {
        int[] landscape = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        HillRange range = new HillRange(landscape);

        assertEquals(9, range.numberOfHills());
    }

    @Test
    public void shouldCalculateTheLowest() {
        int[] landscape = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        HillRange range = new HillRange(landscape);

        assertEquals(1, range.shortest());
    }

    @Test
    public void shouldCalculateTheHighest() {
        int[] landscape = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        HillRange range = new HillRange(landscape);

        assertEquals(9, range.tallest());
    }

}
