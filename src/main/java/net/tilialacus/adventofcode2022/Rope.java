package net.tilialacus.adventofcode2022;

import java.util.HashSet;
import java.util.Set;

public class Rope {

    private Knot head;
    private Knot tail;

    private final Set<Pos> visited = new HashSet();

    public Rope() {
        this(2);
    }

    public Rope(int length) {
        tail = new Knot(new Pos(0,0));
        visited.add(tail.pos);
        head = new Knot(tail);
        while (--length >= 2) {
            head = new Knot(head);
        }
    }

    public Knot getHead() {
        return head;
    }

    public Knot getTail() {
        return tail;
    }

    public void move(String action) {
        String[] parts = action.split(" ");
        for (int steps = Integer.parseInt(parts[1]); steps > 0; steps--) {
            switch (parts[0]) {
                case "R" -> head.move(1, 0);
                case "L" -> head.move(-1, 0);
                case "U" -> head.move(0, -1);
                case "D" -> head.move(0, 1);
            };
            visited.add(tail.pos);
        }
    }

    public Set<Pos> getVisited() {
        return visited;
    }

    public record Pos (int x, int y) {
        public Pos move(int dx, int dy) {
            return new Pos(x + dx, y + dy);
        }

        public boolean touch(Pos other) {
            return Math.abs(x - other.x) <= 1 && Math.abs(y - other.y) <= 1;
        }

        public Pos moveTowards(Pos other) {
            return new Pos(towards(x, other.x), towards(y, other.y));
        }

        private int towards(int p1, int p2) {
            if (p1 > p2) {
                return p1 - 1;
            } else if (p1 < p2) {
                return p1 + 1;
            } else {
                return p1;
            }
        }
    }

    public static class Knot {
        private Pos pos;
        private Knot tail;

        public Knot(Pos pos) {
            this.pos = pos;
        }

        public Knot(Knot tail) {
            this.tail = tail;
            this.pos = tail.pos;
        }

        public void move(int dx, int dy) {
            pos = new Pos(pos.x + dx, pos.y + dy);
            moveTail();
        }

        private void catchup(Pos target) {
            if (!pos.touch(target)) {
                pos = pos.moveTowards(target);
                moveTail();
            }
        }
        private void moveTail() {
            if (tail != null) {
                tail.catchup(pos);
            }
        }

        public Pos getPos() {
            return pos;
        }
    }
}
