package unionFind;

/**
 * Union Find interface.
 */
public interface UF {
    /**
     * Return the number of components.
     * @return the number of components
     */
    int count();

    /**
     * Check if the two p and q is connected.
     * @param p first element
     * @param q second element
     * @return true for connected and false for not
     */
    boolean connected(int p, int q);

    /**
     * Give component identifier for p (0 to N-1)
     * @param p element
     * @return component identifier
     */
    int find(int p);

    /**
     * Add connection between p and q.
     * @param p element p
     * @param q element q
     */
    void union(int p, int q);
}
