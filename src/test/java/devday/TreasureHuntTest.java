package devday;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author rohankar
 */
public class TreasureHuntTest {

    private static final int TOTAL_BLOCKS = 1_000;

    @Test
    public void game() throws FileNotFoundException, IOException {
        // create index
        // FIXME only supports ASC order, should be mix match indices
        final Index blankIndex = new Index((byte)0, (byte)0);
        final Index index444 = new Index((byte)0, (byte)0, (short)24);
        final Index index123 = new Index((byte)0, (byte)0, (short)534);
        final Index index2016 = new Index((byte)1, (byte)0, (short)246, (short)746);
        final Index index42 = new Index((byte)0, (byte)0, (short)35, (short)636);
        // write index to index file
        try (FileOutputStream fos = new FileOutputStream(Paths.get("index.dat").toFile())) {
            for (int i = 0; i < 2050; i++) {
                switch (i) {
                    case 444:
                        fos.write(index444.toBytes());
                        break;
                    case 123:
                        fos.write(index123.toBytes());
                        break;
                    case 2016:
                        fos.write(index2016.toBytes());
                        break;
                    case 42:
                        fos.write(index42.toBytes());
                        break;
                    default:
                        fos.write(blankIndex.toBytes()); // TODO garbage index
                }
            }
        }

        // create game plot
        final Record record1 = new Record("Your next index to read 123");
        final Record record2 = new Record("This year is your next clue");
        final Record record3 = new Record("Answer to the Ultimate Question of Life, the Universe, and Everything");
        final Record record4 = new Record(
            "Congratulations, you won!! now go to stage, connect your laptop and show _all the clues_ to everyone!");

        final Map<List<Short>, Iterator<byte[]>> map = new HashMap<>();
        addToMap(index444, record1, map);
        addToMap(index123, record2, map);
        addToMap(index2016, record3, map);
        addToMap(index42, record4, map);

        try (final FileOutputStream fileOutputStream = new FileOutputStream(Paths.get("game.dat").toFile())) {

            for (int i = 0; i < TOTAL_BLOCKS; i++) {
                final int j = i;
                // very ugly logic to check i
                if (map.keySet().stream().anyMatch(shorts -> shorts.contains((short)j))) {
                    map.keySet().stream().filter(shorts -> shorts.contains((short)j)).forEach(shorts -> {
                        final Iterator<byte[]> recordBytes = map.get(shorts);
                        try {
                            fileOutputStream.write(Arrays.copyOf(recordBytes.next(), Record.SIZE));
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    fileOutputStream.write(Arrays.copyOf("".getBytes(), Record.SIZE)); // TODO garbage record
                }
            }
        }

        System.out.println("------------");
    }

    private void addToMap(final Index index, final Record record, final Map<List<Short>, Iterator<byte[]>> map) {
        final List<Short> shorts = new ArrayList<>();
        for (final short s : index.getRecordIndexes()) {
            shorts.add(s);
        }
        map.put(shorts, record.getBytes().iterator());
    }

}
