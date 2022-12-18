package net.tilialacus.adventofcode2022;

import java.util.HashSet;
import java.util.Set;

public class Rope {

    private Pos head = new Pos(0,0);
    private Pos tail = head;

    private final Set<Pos> visited = new HashSet();

    public Rope() {
        visited.add(tail);
    }

    public Pos getHead() {
        return head;
    }

    public Pos getTail() {
        return tail;
    }

    public void move(String action) {
        String[] parts = action.split(" ");
        for (int steps = Integer.parseInt(parts[1]); steps > 0; steps--) {
            head = switch (parts[0]) {
                case "R" -> head.move(1, 0);
                case "L" -> head.move(-1, 0);
                case "U" -> head.move(0, -1);
                case "D" -> head.move(0, 1);
                default -> head;
            };
            if (!tail.touch(head)) {
                tail = tail.moveTowards(head);
                visited.add(tail);
            }
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
}
