package net.tilialacus.adventofcode2022;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

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
