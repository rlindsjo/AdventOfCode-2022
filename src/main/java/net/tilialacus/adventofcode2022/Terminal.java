package net.tilialacus.adventofcode2022;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Terminal {

    Folder root = new Folder(null, "/");
    Folder current = null;
    public static Terminal parseTerminal(String resource) {
        Terminal terminal = new Terminal();
        FileUtil.resourceLinesAsList(resource).forEach(terminal::parse);

        return terminal;
    }

    private void parse(String log) {
        if (log.startsWith("$")) {
            if (log.startsWith("$ cd ..")) {
                current = current.parent;
            } else if (log.startsWith("$ cd /")) {
                current = root;
            } else if (log.startsWith("$ cd ")) {
                current = current.subfolder(log.substring(5));
            }
        } else {
            String[] parts = log.split(" ");
            if (parts[0].equals("dir")) {
                current.subfolder(parts[1]);
            } else {
                current.content(Integer.parseInt(parts[0]), parts[1]);
            }
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public void visit(Consumer<Folder> visitor) {
        root.visit(visitor);
    }

    public static class Collector {
        final List<Folder> match = new ArrayList<>();
        private Predicate<Folder> predicate;

        public Collector(Predicate<Folder> predicate) {
            this.predicate = predicate;
        }

        public void collect(Terminal.Folder folder) {
            if (predicate.test(folder)) {
                match.add(folder);
            }
        }
    }

    public static class Folder {
        private Folder parent;
        private String name;
        private List<Folder> folder = new ArrayList<>();

        private List<Content> content = new ArrayList<>();
        public Folder(Folder parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        public Folder subfolder(String name) {
            for (Folder folder : folder) {
                if (folder.name.equals(name)) {
                    return folder;
                }
            }
            Folder created = new Folder(this, name);
            folder.add(created);
            return created;
        }

        public void content(int size, String name) {
            content.add(new Content(name, size));
        }

        public int size() {
            return (Integer) folder.stream().mapToInt(Folder::size).sum()
                   + (Integer) content.stream().mapToInt(Content::size).sum();
        }

        public void visit(Consumer<Folder> visitor) {
            visitor.accept(this);
            folder.forEach(it -> it.visit(visitor));
        }

        public String getName() {
            return name;
        }
    }

    private static class Content {
        private final String name;
        private final int size;

        public Content(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public int size() {
            return size;
        }
    }
}
