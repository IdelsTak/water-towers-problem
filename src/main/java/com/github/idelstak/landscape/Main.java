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
 * <pre><b>Problem Statement</b></pre><br>
 * There is a landscape with hills and pits which has similar square shapes. Max
 * number of positions is {@code 32000}. Height is between {@code 0} and
 * {@code 32000}.
 * <br>
 * <br>
 * <b>For example</b>: The first position has height 2, the second position has
 * height 5. The landscape can be represented as a collection of heights:
 * <br>
 * <br>
 * {@code [2, 5, 6, 8, 1, 4, 6, 2, 7, 2, 8, 4, 2, 6, 8, 4, 2, 5, 6, 2, 3, 5, 0, 2, 4, 1, 5, 1, 4, 4, 6, 2, 5, 2, 5, 2, 4, 2, 5, 7, 2, 2, 4, 6, 9, 6, 4, 6, 3, 6, 3, 6, 7, 5, 3, 2]}
 * <br>
 * <pre>
 *                                                                                        X
 *      X             X       X                                                           X
 *      X         X   X       X                                                 X         X               X
 *    X X     X   X   X     X X       X                       X                 X       X X X   X   X   X X
 *  X X X     X   X   X     X X     X X     X         X       X   X   X       X X       X X X   X   X   X X X
 *  X X X   X X   X   X X   X X X   X X     X     X   X   X X X   X   X   X   X X     X X X X X X   X   X X X
 *  X X X   X X   X   X X   X X X   X X   X X     X   X   X X X   X   X   X   X X     X X X X X X X X X X X X X
 *X X X X   X X X X X X X X X X X X X X X X X   X X   X   X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 *X X X X X X X X X X X X X X X X X X X X X X   X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 * </pre>
 * <br>
 * When it rains, the landscape is filled with water. Water is collected inside
 * pits only between hills.
 * <br>
 * <br>
 * <b>For example</b>: collected 183 squares of water
 * <br>
 * <pre>
 *      X O O O O O O X O O O X O O O O O O O O O O O O O O O O O O O O O O O O O O O O O X
 *      X O O O O X O X O O O X O O O O O O O O O O O O O O O O O O O O O O O O X O O O O X O O O O O O O X
 *    X X O O X O X O X O O X X O O O X O O O O O O O O O O O X O O O O O O O O X O O O X X X O X O X O X X
 *  X X X O O X O X O X O O X X O O X X O O X O O O O X O O O X O X O X O O O X X O O O X X X O X O X O X X X
 *  X X X O X X O X O X X O X X X O X X O O X O O X O X O X X X O X O X O X O X X O O X X X X X X O X O X X X
 *  X X X O X X O X O X X O X X X O X X O X X O O X O X O X X X O X O X O X O X X O O X X X X X X X X X X X X X
 *X X X X O X X X X X X X X X X X X X X X X X O X X O X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 *X X X X X X X X X X X X X X X X X X X X X X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 * </pre>
 * <br>
 * Then the sun comes out from behind the clouds, it partly evaporates the water
 * from the top of the pits.
 * <br>
 * The sun can evaporate up to 2 squares.
 * <br>
 * <br>
 * <b>For example</b>: some amount of water is evaporated. As a result, finally
 * only 99 squares of water are collected.
 * <br>
 * <pre>
 *      X             X       X                                                           X
 *      X         X   X       X                                                 X         X               X
 *    X X O O X O X O X O O X X O O O X O O O O O O O O O O O X O O O O O O O O X O O O X X X   X   X   X X
 *  X X X O O X O X O X O O X X O O X X O O X O O O O X O O O X O X O X O O O X X O O O X X X O X O X O X X X
 *  X X X O X X O X O X X O X X X O X X O O X O O X O X O X X X O X O X O X O X X O O X X X X X X O X O X X X
 *  X X X O X X O X O X X O X X X O X X O X X O O X O X O X X X O X O X O X O X X O O X X X X X X X X X X X X X
 *X X X X O X X X X X X X X X X X X X X X X X O X X O X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 *X X X X X X X X X X X X X X X X X X X X X X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X
 * </pre>
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class Main {

    /**
     *
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] landscape = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            try {
                landscape[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException nfe) {
                System.out.println("Some provided arguments are in an incorrect format: [" + nfe.getMessage() + ']');
            }
        }

        System.out.printf("Creating landscape: %s...\n", Arrays.toString(landscape));

        String description = new Region(landscape).toString();

        System.out.println(description);

        System.exit(0);

    }

}
