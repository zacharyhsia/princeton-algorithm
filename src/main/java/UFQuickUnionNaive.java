/**
 * Lazy approach.
 */
public class UFQuickUnionNaive implements UF {
    private int N;
    private int[] ids;
    private int count;

    /**
     * Initilize N sites;
     * @param N number of element.
     */
    public UFQuickUnionNaive(int N) {
        this.N = N;
        this.count = N;
        ids = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }


    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        return findRoot(p);
    }

    @Override
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (rootP == rootQ) {
            return;
        }

        ids[rootP] = rootQ;
        count--;
    }

    private int findRoot(int p) {
        int root;
        while ((root = ids[p]) != p) {
            p = root;
        }
        return root;
    }
}
