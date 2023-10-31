package solutions;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LowestCommonAncestorTest {

    private BinaryTree<Integer> binaryTree;

    @Before
    public void setUp() {
        binaryTree =
                new BinaryTree<Integer>(7,
                        new BinaryTree<Integer>(21, null, null),
                        new BinaryTree<Integer>(14,
                                new BinaryTree<Integer>(23, null, null),
                                new BinaryTree<Integer>(6, null,
                                        new BinaryTree<Integer>(13, null, null))));
    }

    @Test
    public void testLCA() {
        assertEquals(Integer.valueOf(14), binaryTree.findLowestCommonAncestor(23, 13));
    }

    @Test
    public void testGetTree() {
        BinaryTree<Integer> tree14 = binaryTree.getTreeByValue(14);
        BinaryTree<Integer> tree6 = binaryTree.getTreeByValue(6);
        BinaryTree<Integer> tree13 = binaryTree.getTreeByValue(13);
        assertNull(tree13.getRight());
        assertEquals(Integer.valueOf(13), tree6.getRight().getKey());
        assertEquals(Integer.valueOf(6), tree14.getRight().getKey());

    }

    @Test
    public void testParent() {
        BinaryTree<Integer> tree23 = binaryTree.getTreeByValue(23);
        assertEquals(Integer.valueOf(14), tree23.getParent().getKey());
    }

    @Test
    public void testDFS() {
        Set<Integer> expected = Set.of(7, 21, 14, 23, 6, 13);
        Set<BinaryTree<Integer>> dfs = binaryTree.dfs(binaryTree, new LinkedHashSet<>());
        Set<Integer> actual = new HashSet<>();
        dfs.forEach( tree -> actual.add(tree.getKey()));
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }



}