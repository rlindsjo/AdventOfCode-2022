package net.tilialacus.adventofcode2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class FileUtil {

    public static Spliterator<String> resourceLines(String resource) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource), StandardCharsets.UTF_8));
        return new LineReaderSpliterator(reader);
    }

    public static List<String> resourceLinesAsList(String resource) {
          return StreamSupport.stream(resourceLines(resource), false).toList();
    }

    public static class LineReaderSpliterator implements Spliterator<String> {
        private final BufferedReader reader;

        public LineReaderSpliterator(BufferedReader reader) {
            this.reader = reader;
        }

        public int characteristics() {
            return DISTINCT | NONNULL | IMMUTABLE | ORDERED;
        }

        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        public boolean tryAdvance(Consumer<? super String> action) {
            try {
                String line = reader.readLine();
                if ( line != null ) {
                    action.accept(line);
                    return true;
                } else return false;
            } catch(java.io.IOException ex) {
                return false;
            }
        }

        public Spliterator<String> trySplit() { return null; }
    }
}
