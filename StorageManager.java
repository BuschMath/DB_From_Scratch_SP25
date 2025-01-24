import java.io.IOException;

/**
 * StorageManager interface defines the basic read/write operations for pages.
 */
public interface StorageManager {

    /**
     * Reads a page from the given file and returns a Page object.
     *
     * @param fileName Name (or path) of the file to read from.
     * @param pageId   The ID of the page to read (also used to compute offset).
     * @return The Page object containing the data read from disk.
     * @throws IOException if file access fails.
     */
    Page readPage(String fileName, int pageId) throws IOException;

    /**
     * Writes a Page object to the given file.
     *
     * @param fileName Name (or path) of the file to write to.
     * @param page     The Page object to be written.
     * @throws IOException if file access fails.
     */
    void writePage(String fileName, Page page) throws IOException;

    /**
     * (Optional) Creates a new empty file or initializes file metadata.
     *
     * @param fileName Name of the file to create.
     * @throws IOException if file creation fails.
     */
    void createFile(String fileName) throws IOException;

    /**
     * (Optional) Returns the size (in pages) of the file.
     *
     * @param fileName The file to check.
     * @return The number of pages in the file.
     * @throws IOException if file access fails.
     */
    int getNumPages(String fileName) throws IOException;
}

