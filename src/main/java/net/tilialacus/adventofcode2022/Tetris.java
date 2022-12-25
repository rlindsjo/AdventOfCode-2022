package net.tilialacus.adventofcode2022;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import static net.tilialacus.adventofcode2022.Tetris.Block.createBlock;

public class Tetris {

    private final String wind;

    Supplier<Block> blockSupplier = new Supplier<>() {
        private static final List<Block> BLOCKS = List.of(
                createBlock(new boolean[][]{
                        {true, true, true, true}
                }),
                createBlock(new boolean[][]{
                        {false, true, false},
                        {true, true, true},
                        {false, true, false}
                }),
                createBlock(new boolean[][]{
                        {false, false, true},
                        {false, false, true},
                        {true, true, true}
                }),
                createBlock(new boolean[][]{
                        {true},
                        {true},
                        {true},
                        {true}
                }),
                createBlock(new boolean[][]{
                        {true, true},
                        {true, true}
                })
        );

        Iterator<Block> iterator = BLOCKS.iterator();

        @Override
        public Block get() {
            if (!iterator.hasNext()) {
                iterator = BLOCKS.iterator();
            }
            return iterator.next();
        }
    };

    Supplier<Integer> windSupplier = new Supplier<>() {

        int index = -1;

        @Override
        public Integer get() {
            index = (index + 1) % wind.length();
            return switch (wind.charAt(index)) {
                case '<' -> -1;
                case '>' -> 1;
                default -> throw new IllegalStateException();
            };
        }
    };

    public Pit pit = new Pit();

    public Tetris() {
        this(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>");
    }

    public Tetris(String wind) {
        this.wind = wind;
    }

    public void drop(int drops) {
        for (int i = 0; i < drops; i++) {
            drop();
        }
    }

    public void drop() {
        Block block = blockSupplier.get();
        int row = pit.firstEmpty() + block.shape.length + 2;
        int x = 2;

        while (true) {
            int dx = windSupplier.get();
            if (pit.fits(x + dx, row, block)) {
                x += dx;
            }
            if (pit.fits(x, row - 1, block)) {
                row--;
            } else {
                pit.add(x, row, block);
                return;
            }
        }
    }

    public static class Block {
        private boolean[][] shape;

        public Block(boolean[][] shape) {
            this.shape = shape;
        }

        public static Block createBlock(boolean[][] shape) {
            return new Block(shape);
        }
    }

    public static class Pit {
        boolean[][] content = new boolean[10000][7];

        public boolean isEmpty(int row) {
            for (boolean b : content[row]) {
                if (b) {
                    return false;
                }
            }
            return true;
        }

        public int firstEmpty() {
            for (int i = 0; i < content.length; i++) {
                if (isEmpty(i)) {
                    return i;
                }
            }
            throw new IllegalStateException("Pit full");
        }

        public boolean fits(int x, int row, Block block) {
            if (x < 0 || block.shape[0].length + x - 1 >= content[0].length || row - block.shape.length + 1 < 0) {
                return false;
            }
            for (int i = block.shape.length - 1; i >= 0; i--) {
                for (int j = 0; j < block.shape[i].length; j++) {
                    if (content[row - i][x + j] && block.shape[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void add(int x, int row, Block block) {
            for (int i = block.shape.length - 1; i >= 0; i--) {
                for (int j = 0; j < block.shape[i].length; j++) {
                    content[row - i][x + j] |= block.shape[i][j];
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int row = firstEmpty(); row >= 0; row--) {
                builder.append(row).append(' ');
                for (boolean b : content[row]) {
                    builder.append(b ? "#" : ".");
                }
                builder.append('\n');
            }
            return builder.toString();
        }
    }
}
