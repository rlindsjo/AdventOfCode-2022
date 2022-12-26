package net.tilialacus.adventofcode2022;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

public class Monkeys {

    private static final Pattern PATTERN = Pattern.compile("(....): (?:(\\d+)|(....) (.) (....))");

    private Map<String, Monkey> monkeys = new HashMap<>();

    public void parse(String input) {
        Matcher matcher = PATTERN.matcher(input);
        matcher.find();
        String name = matcher.group(1);
        Monkey monkey = getMonkey(name);
        if (matcher.group(2) != null) {
            monkey.setValue(() -> parseLong(matcher.group(2)));
        } else {
            Monkey m1 = getMonkey(matcher.group(3));
            Monkey m2 = getMonkey(matcher.group(5));
            Supplier<Long> value = switch (matcher.group(4)) {
                case "+" -> () -> m1.value.get() + m2.value.get();
                case "-" -> () -> m1.value.get() - m2.value.get();
                case "*" -> () -> m1.value.get() * m2.value.get();
                case "/" -> () -> m1.value.get() / m2.value.get();
                default -> throw new IllegalStateException();
            };
            monkey.setValue(value);
        }
    }

    public Monkey getMonkey(String name) {
        return monkeys.computeIfAbsent(name, Monkey::new);
    }

    public static class Monkey {
        private final String name;
        private Supplier<Long> value;

        public Monkey(String name) {
            this.name = name;
        }

        public void setValue(Supplier<Long> value) {
            this.value = value;
        }

        public long getValue() {
            return value.get();
        }
    }
}
