package net.tilialacus.adventofcode2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

public class Monkeys {

    private static final Pattern PATTERN = Pattern.compile("(....): (?:(\\d+)|(....) (.) (....))");

    private Map<String, Monkey> monkeys = new HashMap<>();
    private List<Monkey> listens;

    public void parse(String input) {
        Matcher matcher = PATTERN.matcher(input);
        matcher.find();
        String name = matcher.group(1);
        Monkey monkey = getMonkey(name);
        if (matcher.group(2) != null) {
            monkey.setOperation(matcher.group(2));
        } else {
            monkey.setOperation(matcher.group((4)));
            monkey.setListenTo(List.of(
                    getMonkey(matcher.group(3)),
                    getMonkey(matcher.group(5))
            ));
        }
    }

    public Monkey getMonkey(String name) {
        return monkeys.computeIfAbsent(name, Monkey::new);
    }

    public long getHumanValue() {
        Monkey root = getMonkey("root");
        root.setOperation("=");
        Set<Monkey> humanPath = new HashSet<>();
        root.toHuman(humanPath);
        long result = 0;
        Monkey current = root;
        while (!current.name.equals("humn")) {
            long term = current.listens.stream()
                    .filter(it -> !humanPath.contains(it))
                    .findFirst()
                    .orElseThrow()
                    .getValue();

            Monkey next = current.listens.stream()
                    .filter(it -> humanPath.contains(it))
                    .findFirst()
                    .orElseThrow();

            result = switch (current.operation) {
                case "+" -> result - term;
                // a = b - c -> b = a+c, c = b - a
                case "-" -> current.listens.get(0) == next ? result + term : term - result;
                case "*" -> result / term;
                // a = b / c -> b = a * c, c = b / a
                case "/" -> current.listens.get(0) == next ? result * term : term / result;
                case "=" -> term;
                default -> throw new IllegalStateException();
            };

            current = next;
        }
        return result;
    }
    public static class Monkey {
        private final String name;
        public List<Monkey> listens;
        private String operation;
        private String group;

        public Monkey(String name) {
            this.name = name;
        }

        public boolean toHuman(Set<Monkey> humanPath) {
            if (name.equals("humn")) {
                humanPath.add(this);
                return true;
            } else if (listens == null) {
                return false;
            } else if (listens.get(0).toHuman(humanPath) || listens.get(1).toHuman(humanPath)) {
                humanPath.add(this);
                return true;
            }
            return false;
        }
        public long getValue() {
            return switch (operation) {
                case "+" -> listens.get(0).getValue() + listens.get(1).getValue();
                case "-" -> listens.get(0).getValue() - listens.get(1).getValue();
                case "*" -> listens.get(0).getValue() * listens.get(1).getValue();
                case "/" -> listens.get(0).getValue() / listens.get(1).getValue();
                default -> parseLong(operation);
                };
            }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public void setListenTo(List<Monkey> listens) {
            this.listens = listens;
        }
    }
}
