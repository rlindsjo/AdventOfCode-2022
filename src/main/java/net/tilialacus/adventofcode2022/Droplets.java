package net.tilialacus.adventofcode2022;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class Droplets {

    Set<Droplet> droplets = new HashSet<>();

    public void parse(String input) {
        String[] parts = input.split(",");
        droplets.add(new Droplet(
                parseInt(parts[0]),
                parseInt(parts[1]),
                parseInt(parts[2]))
        );
    }

    public int surfaceArea() {
        return droplets.stream().mapToInt(this::surfaceArea).sum();
    }

    private int surfaceArea(Droplet droplet) {
        return surface(droplet.up()) +
                surface(droplet.down()) +
                surface(droplet.left()) +
                surface(droplet.right()) +
                surface(droplet.in()) +
                surface(droplet.out());
    }

    private int surface(Droplet neighbour) {
        return droplets.contains(neighbour) ? 0 : 1;
    }

    public int outsideSurfaceArea() {
        Box box = new Box();
        droplets.forEach(box::expand);
        box.grow();
        return box.fill(0, 0, 0);
    }

    private class Box {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        Set<Droplet> filled = new HashSet<>();
        public void expand(Droplet droplet) {
            minX = min(droplet.x(), minX);
            minY = min(droplet.y(), minY);
            minZ = min(droplet.z(), minZ);
            maxX = max(droplet.x(), maxX);
            maxY = max(droplet.y(), maxY);
            maxZ = max(droplet.z(), maxZ);
        }

        public void grow() {
            minX--;
            minY--;
            minZ--;
            maxX++;
            maxY++;
            maxZ++;
        }

        public int fill(int x, int y, int z) {
            int surface = 0;
            Queue<Droplet> explore = new LinkedList<>();
            Consumer<Droplet> possible = (d) -> {
                if (inBox(d) && !filled.contains(d)) {
                    explore.add(d);
                }
            };

            explore.add(new Droplet(x, y, z));
            while (!explore.isEmpty()) {
                Droplet current = explore.remove();
                if (!inBox(current)) {
                    continue;
                }
                if (droplets.contains(current)) {
                    surface++;
                } else if (filled.add(current)) {
                    possible.accept(current.up());
                    possible.accept(current.down());
                    possible.accept(current.left());
                    possible.accept(current.right());
                    possible.accept(current.in());
                    possible.accept(current.out());
                }
            }
            return surface;
        }

        private boolean inBox(Droplet current) {
            return minX <= current.x && maxX >= current.x &&
                    minY <= current.y && maxY >= current.y &&
                    minZ <= current.z && maxZ >= current.z;
        }
    }

    record Droplet(int x, int y, int z) {
        public Droplet up() {
            return new Droplet(x, y, z - 1);
        }
        public Droplet down() {
            return new Droplet(x, y, z + 1);
        }
        public Droplet left() {
            return new Droplet(x-1, y, z );
        }
        public Droplet right() {
            return new Droplet(x+1, y, z );
        }
        public Droplet in() {
            return new Droplet(x, y + 1, z );
        }
        public Droplet out() {
            return new Droplet(x, y - 1, z );
        }
    }
}
