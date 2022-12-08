package net.tilialacus.adventofcode2022;

import java.util.List;

public class Forrest {
    private int[][] trees;

    public Forrest(int[][] trees) {
        this.trees = trees;
    }

    public static Forrest parseTreeMap(String resource) {
        List<String> input = FileUtil.resourceLinesAsList(resource);
        int[][] heights = new int[input.size()][input.get(0).length()];
        for (int row = 0; row < input.size() ; row++) {
            String line = input.get(row);
            for (int col = 0; col < line.length(); col++) {
                heights[row][col] = line.charAt(col) - '0';
            }
        }
        return new Forrest(heights);
    }

    public int visibleTrees() {
        boolean[][] visible = new boolean[trees.length][trees[0].length];
        // rows
        for (int row = 0; row < trees.length; row++) {
            int minHeight = -1;
            // Left
            for (int col = 0; col < trees[row].length && minHeight < 9; col++) {
                if (trees[row][col] > minHeight) {
                    visible[row][col] = true;
                    minHeight = trees[row][col];
                }
            }
            // Right
            minHeight = -1;
            for (int col = trees[row].length - 1; col >=0 && minHeight < 9; col--) {
                if (trees[row][col] > minHeight) {
                    visible[row][col] = true;
                    minHeight = trees[row][col];
                }
            }
        }
        for (int col = 0; col < trees[0].length; col++) {
            int minHeight = -1;
            // Top
            for (int row = 0; row < trees.length && minHeight < 9; row++) {
                if (trees[row][col] > minHeight) {
                    visible[row][col] = true;
                    minHeight = trees[row][col];
                }
            }
            // Bottom
            minHeight = -1;
            for (int row = trees.length - 1; row >=0 && minHeight < 9; row--) {
                if (trees[row][col] > minHeight) {
                    visible[row][col] = true;
                    minHeight = trees[row][col];
                }
            }
        }
        int count = 0;
        for (boolean[] row : visible) {
            for (boolean seen : row) {
                if (seen) {
                    count++;
                }

            }
        }
        return count;
    }

    public int scenic(int row, int col) {
        int treeHeight = trees[row][col];
        int score = 1;
        int seen = 0;
        for (int i = col - 1; i >= 0; i--) {
            seen ++;
            if (trees[row][i] >= treeHeight) {
                break;
            }
        }
        score *= seen;
        seen = 0;
        for (int i = col + 1; i < trees[0].length ; i++) {
            seen ++;
            if (trees[row][i] >= treeHeight) {
                break;
            }
        }

        score *= seen;
        seen = 0;
        for (int i = row - 1; i >= 0; i--) {
            seen ++;
            if (trees[i][col] >= treeHeight) {
                break;
            }
        }
        score *= seen;
        seen = 0;
        for (int i = row + 1; i < trees.length ; i++) {
            seen ++;
            if (trees[i][col] >= treeHeight) {
                break;
            }
        }
        return score * seen;
    }

    public int bestScenic() {
        int best = 0;
        for (int row = 0; row < trees.length; row++) {
            for (int col = 0; col < trees[row].length; col++) {
                int score = scenic(row, col);
                if (score > best) {
                    best = score;
                }
            }
        }
        return best;
    }
}
