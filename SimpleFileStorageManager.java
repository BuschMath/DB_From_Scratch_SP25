import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A naive implementation of the StorageManager that uses RandomAccessFile
 * for fixed-size page read and write.
 */
public class SimpleFileStorageManager implements StorageManager {

    // For this example, let's fix page size to 4KB:
    public static final int PAGE_SIZE = 4096;

    @Override
    public Page readPage(String fileName, int pageId) throws IOException {
        File file = new File(fileName);

        // Try-with-resources ensures we close the file automatically
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long offset = computeOffset(pageId);
            
            // Check if offset is within file bounds:
            if (offset >= raf.length()) {
                throw new IOException("Page " + pageId + " is out of file bounds.");
            }

            // Seek to the correct position
            raf.seek(offset);

            // Read PAGE_SIZE bytes
            byte[] data = new byte[PAGE_SIZE];
            int bytesRead = raf.read(data);
            if (bytesRead < PAGE_SIZE) {
                throw new IOException("Unable to read full page from file.");
            }

            // Return a new Page object
            return new Page(pageId, data);
        }
    }

    @Override
    public void writePage(String fileName, Page page) throws IOException {
        File file = new File(fileName);

        // Open file in "rw" mode (read/write).
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long offset = computeOffset(page.getPageId());
            raf.seek(offset);

            // Write the page data to the file
            raf.write(page.getData());

            // Optionally, you might force the data to disk:
            // raf.getChannel().force(true);
        }
    }

    @Override
    public void createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            throw new IOException("File already exists: " + fileName);
        }

        // Simply create an empty file
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            // Could write a file header here if desired
        }
    }

    @Override
    public int getNumPages(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + fileName);
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long fileSize = raf.length();
            // Round down: integer division
            return (int) (fileSize / PAGE_SIZE);
        }
    }

    /**
     * Computes the byte offset in the file where the page should reside.
     *
     * @param pageId ID of the page to compute offset for.
     * @return Offset in bytes.
     */
    private long computeOffset(int pageId) {
        return (long) pageId * PAGE_SIZE;
    }
}

