package net.tilialacus.adventofcode2022;

public class Day8 {
    public static void main(String[] args) {
        Forrest treeMap = Forrest.parseTreeMap("day8/tree_map.txt");

        System.out.println("Visible trees: " + treeMap.visibleTrees());

        System.out.println("Best scenic score: " + treeMap.bestScenic());
    }
}
