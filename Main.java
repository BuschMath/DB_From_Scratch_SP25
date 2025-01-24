public class Main {
    public static void main(String[] args) {
        Page page = new Page(1, new byte[100]);

        System.out.println("Page ID: " + page.getPageId());

        // Example of writing and reading an integer
        page.writeInt(0, 42);
        page.writeInt(4, 84);

        System.out.println("Should be 84: " + page.readInt(4)); // Should return 84
        System.out.println("Should be 42: " + page.readInt(0)); // Should return 42
    }
}