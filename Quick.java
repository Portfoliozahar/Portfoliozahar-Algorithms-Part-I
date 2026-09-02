public class Quick {
    public static void main(String[] args) {

        int n = 10;

        // В худшем случае одна компонента содержит n - 1 элементов
        int maxChanges = n - 1;

        System.out.println("Maximum changes: " + maxChanges);
    }
}