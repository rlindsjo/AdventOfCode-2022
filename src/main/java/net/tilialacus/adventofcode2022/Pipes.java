package net.tilialacus.adventofcode2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.emptySet;

public class Pipes {
    private static Pattern parser = Pattern.compile("Valve (\\w+) has flow rate=(\\d+); tunnels? leads? to valves? (.*)$");
    public Map<String, Valve> valves = new HashMap<>();
    public void add(String input) {
        Matcher matcher = parser.matcher(input);
        matcher.find();
        String from = matcher.group(1);
        int rate = Integer.parseInt(matcher.group(2));
        Valve valve = getValve(from);
        valve.setRate(rate);
        String[] connections = matcher.group(3).split(", ");
        for (String connection : connections) {
            valve.addConnection(getValve(connection));
        }
    }

    public Valve getValve(String id) {
        return valves.computeIfAbsent(id, Valve::new);
    }

    public Move getBest(int minutes) {
        Queue<Move> queue = new PriorityQueue<>(Comparator.comparing(Pipes.Move::score).reversed());
        Pipes.Move best = new Pipes.Move(null, minutes, 0, getValve("AA"), emptySet());
        queue.add(best);
        while (!queue.isEmpty()) {
            Pipes.Move current = queue.remove();
            if (current.score > best.score) {
                best = current;
                System.err.println();
            }
            if (current.minute == 0) {
                continue;
            }
            if (!current.open.contains(current.valve)) {
                if (current.valve.getRate() > 0) {
                    Set<Pipes.Valve> open = new HashSet<>();
                    open.addAll(current.open);
                    open.add(current.valve);
                    queue.add(current.openValve());
                }
            }

            for (Pipes.Valve connection : current.valve.connections) {
                Pipes.Move to = current.to(connection);
                if (!to.noScoreLoop()) {
                    queue.add(to);
                }
            }
        }
        return best;
    }

    public static class Valve {
        private final String id;
        private int rate;

        public Set<Valve> connections = new HashSet<>();

        public Valve(String id) {
            this.id = id;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public void addConnection(Valve valve) {
            connections.add(valve);
        }

        public int getRate() {
            return rate;
        }
    }

    public static class Move {
        Set<Valve> open;
        private Move prev;
        int minute;
        Valve valve;
        int score;

        public Move(Move prev, int minute, int score, Valve valve, Set<Valve>open) {
            this.prev = prev;
            this.minute = minute;
            this.score = score;
            this.valve = valve;
            this.open = open;
        }

        public int score() {
            return score;
        }

        @Override
        public String toString() {
            return minute + " " + score;
        }

        public Move openValve() {
            HashSet<Valve> newOpen = new HashSet<>(open);
            newOpen.add(valve);
            return new Move(this, minute - 1, score + valve.getRate() * (minute - 1), valve, newOpen);
        }

        public Move to(Valve connection) {
            return new Move(this, minute - 1, score, connection, open);
        }

        public boolean noScoreLoop() {
            for (Move b = prev; b != null; b = b.prev) {
                if (score > b.score) {
                    return false;
                } else if (b.valve == valve) {
                    return true;
                }
            }
            return false;
        }
    }
}
