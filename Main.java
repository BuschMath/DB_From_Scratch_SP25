public class Main {
    public static void main(String[] args) {
        Page page = new Page(1, new byte[100]);

        System.out.println("Page ID: " + page.getPageId());
    }
}