package devday;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Index {

    public static final int SIZE = 10;
    private final byte multRecords;
    private final byte base64;
    private final short[] recordIndexes;

    public Index(final byte multRecords, final byte base64, final short... recordIndexes) {
        this.multRecords = multRecords;
        this.base64 = base64;
        this.recordIndexes = recordIndexes;
    }

    public byte[] toBytes() {
        final ByteBuffer bb = ByteBuffer.allocate(SIZE);
        bb.put(multRecords);
        bb.put(base64);
        for (final short s : recordIndexes) {
            bb.putShort(s);
        }
        return bb.array();
    }

    public short[] getRecordIndexes() {
        return recordIndexes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + base64;
        result = prime * result + multRecords;
        result = prime * result + Arrays.hashCode(recordIndexes);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Index other = (Index)obj;
        if (base64 != other.base64)
            return false;
        if (multRecords != other.multRecords)
            return false;
        if (!Arrays.equals(recordIndexes, other.recordIndexes))
            return false;
        return true;
    }

}