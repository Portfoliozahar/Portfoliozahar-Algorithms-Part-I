public class UnionFind {

    private int[] id;

    static void union(int[] id, int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }


    public static void main(String[] args) {

        int n = 10;
        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
        }

        union(id, 1, 2);
        union(id, 3, 4);
        union(id, 5, 6);
        union(id, 7, 8);
        union(id, 7, 9);
        union(id, 2, 8);
        union(id, 0, 5);
        union(id, 1, 9);

        int components = 0;

        for (int i = 0; i < n; i++) {
            boolean root = true;

            for (int j = 0; j < i; j++) {
                if (id[i] == id[j]) {
                    root = false;
                    break;
                }
            }

            if (root) {
                components++;
            }
        }

        System.out.println("Components: " + components);
    }
}