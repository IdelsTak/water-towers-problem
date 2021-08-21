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

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class HillRange {

    private final int[] landscape;

    public HillRange(int[] landscape) {
        if (landscape.length < 1) {
            throw new IllegalArgumentException();
        }
        //Make a local copy of the original array
        this.landscape = Arrays.copyOf(landscape, landscape.length);
    }

    public int[] landscape() {
        return Arrays.copyOf(landscape, landscape.length);
    }

    public int numberOfHills() {
        return landscape.length;
    }

    /**
     * Sorts the array of heights to find the lowest value. The lowest value
     * would be the first value (at position {@code 0}) in the array
     *
     * @return the lowest value among the values stored in the array of heights
     */
    public int shortest() {
        int[] l = this.landscape();
        //Sort into ascending numerical order
        Arrays.sort(l);
        //Return the first/lowest value
        return l[0];
    }

    /**
     * Sorts the array to find the largest value. The highest value would be the
     * last value (at position {@code array.length - 1}) in the array
     *
     * @return the highest value among the values stored in the array of heights
     */
    public int tallest() {
        int[] l = this.landscape();
        //Sort into ascending numerical order
        Arrays.sort(l);
        //Return the last/highest value
        return l[l.length - 1];
    }
}
