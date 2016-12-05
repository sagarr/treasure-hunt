package devday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Record {

    public static final int SIZE = 64;
    String text;

    public Record(final String text) {
        this.text = text;
    }

    public List<byte[]> getBytes() {
        return splitArray(text.getBytes(), SIZE);
    }

    public List<byte[]> splitArray(final byte[] bytes, final int splitSize) {
        final List<byte[]> result = new ArrayList<>();
        if (bytes == null || bytes.length == 0) {
            return result;
        }

        int from = 0;
        int to = 0;
        int slicedItems = 0;
        while (slicedItems < bytes.length) {
            to = from + Math.min(splitSize, bytes.length - to);
            final byte[] slice = Arrays.copyOfRange(bytes, from, to);
            result.add(slice);
            slicedItems += slice.length;
            from = to;
        }
        return result;
    }
}
