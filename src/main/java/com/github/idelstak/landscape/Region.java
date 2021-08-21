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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class Region {

    private static final String HILL = "X\s";
    private static final String PUDDLE = "O\s";
    private static final String GAP = "\s\s";
    private final HillRange range;

    public Region(int[] landscape) {
        this(new HillRange(landscape));
    }

    private Region(HillRange range) {
        if (range.numberOfHills() < 1
                || range.numberOfHills() > 32_000
                || range.shortest() < 0
                || range.tallest() > 32_000) {
            throw new IllegalArgumentException();
        }
        this.range = range;
    }
    
    public String[][] asMatrixBeforeRaining() {
        int[] landscape = range.landscape();
        String[][] matrix = new String[range.tallest()][landscape.length];

        for (int i = 0; i < range.tallest(); i++) {
            for (int j = 0; j < landscape.length; j++) {
                int height = landscape[j];
                matrix[i][j] = height >= (range.tallest() - i) ? HILL : GAP;
            }
        }

        return matrix;
    }
    
    public String[][] asMatrixAfterRaining() {
        String[][] matrix = asMatrixBeforeRaining();

        for (String[] row : matrix) {
            List<Integer> hillsPosList = new ArrayList<>();

            for (int i = 0; i < row.length; i++) {
                if (row[i].equals(HILL)) {
                    hillsPosList.add(i);
                }
            }

            if (!hillsPosList.isEmpty()) {
                List<Integer> puddlesPosList = new ArrayList<>();
                Integer leadingHillPos = hillsPosList.get(0);
                Integer[] hillsPosArr = hillsPosList.toArray(Integer[]::new);

                for (Integer trailingHillPos : hillsPosArr) {
                    int distanceBetweenHills = trailingHillPos - leadingHillPos;

                    if (distanceBetweenHills > 1) {
                        for (int j = 1; j < distanceBetweenHills; j++) {
                            puddlesPosList.add(leadingHillPos + j);
                        }
                    }

                    leadingHillPos = trailingHillPos;
                }

                for (int k = 0; k < row.length; k++) {
                    row[k] = puddlesPosList.contains(k) ? PUDDLE : row[k];
                }
            }
        }

        return matrix;
    }

    public String[][] asMatrixAfterEvaporation() {
        String[][] matrix = asMatrixAfterRaining();

        for (int i = 0; i < matrix[0].length; i++) {
            String[] column = new String[matrix.length];

            for (int j = 0; j < matrix.length; j++) {
                column[j] = matrix[j][i];
            }

            List<Integer> puddlesPosList = new ArrayList<>();

            for (int k = 0; k < column.length; k++) {
                if ((column[k]).equals(PUDDLE)) {
                    puddlesPosList.add(k);
                }
            }

            if (!puddlesPosList.isEmpty()) {
                if (puddlesPosList.size() >= 2) {
                    for (int l = 0; l < 2; l++) {
                        matrix[puddlesPosList.get(l)][i] = GAP;
                    }
                } else {
                    matrix[puddlesPosList.get(0)][i] = GAP;
                }
            }
        }

        return matrix;
    }

    public long waterAmountAfterRaining() {
        return waterAmount(asMatrixAfterRaining());
    }

    public long waterAmountAfterEvaporation() {
        return waterAmount(asMatrixAfterEvaporation());
    }

    private long waterAmount(String[][] matrix) {
        long count = 0L;
        for (String[] rows : matrix) {
            for (String feature : rows) {
                if (Objects.equals(PUDDLE, feature)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return """
               Before rain:
               
               %s               
               After rain:
               
               %s               
               After evaporation:
               
               %s               
               Water collected after raining: %d units
               Water remaining after evaporation: %d units
               """
                .formatted(
                        asString(asMatrixBeforeRaining()),
                        asString(asMatrixAfterRaining()),
                        asString(asMatrixAfterEvaporation()),
                        waterAmountAfterRaining(),
                        waterAmountAfterEvaporation()
                );
    }

    private String asString(String[][] landscapeMatrix) {
        StringBuilder sb = new StringBuilder();
        for (String[] rows : landscapeMatrix) {
            StringBuilder s = new StringBuilder();
            for (String features : rows) {
                s.append(features);
            }
            s.append("\n");
            sb.append(s);
        }
        return sb.toString();
    }

}
