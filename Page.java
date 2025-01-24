import java.util.Arrays;

/**
 * Represents a single page of data in memory.
 */
public class Page {
    
    private final int pageId;      // Unique identifier for this page
    private final byte[] data;     // The raw data stored in this page

    /**
     * Create a new Page with a given ID and data array.
     *
     * @param pageId The unique ID of this page.
     * @param data   The raw byte array representing page contents.
     */
    public Page(int pageId, byte[] data) {
        this.pageId = pageId;
        this.data = data;
    }

    public int getPageId() {
        return pageId;
    }

    public byte[] getData() {
        return data;
    }

    /**
     * Convenience method to get a copy of the page data.
     * This prevents direct external modification of the internal array.
     */
    public byte[] getDataCopy() {
        return Arrays.copyOf(data, data.length);
    }

    /**
     * Helper method to write an integer value at a given offset.
     * Demonstrates how you might store record metadata, etc.
     */
    public void writeInt(int offset, int value) {
        // For simplicity, assume 4 bytes for an integer, big-endian or little-endian
        data[offset]     = (byte) (value >>> 24);
        data[offset + 1] = (byte) (value >>> 16);
        data[offset + 2] = (byte) (value >>> 8);
        data[offset + 3] = (byte) (value);
    }

    /**
     * Helper method to read an integer from a given offset.
     */
    public int readInt(int offset) {
        return ((data[offset] & 0xFF) << 24) |
               ((data[offset + 1] & 0xFF) << 16) |
               ((data[offset + 2] & 0xFF) << 8)  |
               (data[offset + 3] & 0xFF);
    }
}
