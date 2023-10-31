import interfaces.AbstractBinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<>();
        bst.insert(12);
        bst.insert(21);
        bst.insert(5);
        bst.insert(1);
        bst.insert(8);
        bst.insert(18);
        bst.insert(23);
    }

//        12
//     5       21
//  1   8    18  23


    @Test
    public void testLeftSideBST() {
        AbstractBinarySearchTree.Node<Integer> root = bst.getRoot();

        assertEquals(Integer.valueOf(12), root.value);

        AbstractBinarySearchTree.Node<Integer> left = root.leftChild;

        assertEquals(Integer.valueOf(5), left.value);

        AbstractBinarySearchTree.Node<Integer> left_left = left.leftChild;
        AbstractBinarySearchTree.Node<Integer> left_right = left.rightChild;

        assertEquals(Integer.valueOf(1), left_left.value);
        assertEquals(Integer.valueOf(8), left_right.value);
    }

    @Test
    public void testRightSideBST() {
        AbstractBinarySearchTree.Node<Integer> root = bst.getRoot();

        assertEquals(Integer.valueOf(12), root.value);

        AbstractBinarySearchTree.Node<Integer> right = root.rightChild;

        assertEquals(Integer.valueOf(21), right.value);

        AbstractBinarySearchTree.Node<Integer> right_left = right.leftChild;
        AbstractBinarySearchTree.Node<Integer> right_right = right.rightChild;

        assertEquals(Integer.valueOf(18), right_left.value);
        assertEquals(Integer.valueOf(23), right_right.value);
    }

    @Test
    public void testSearchCheckReturnedTreeStructure() {
        AbstractBinarySearchTree<Integer> searched = bst.search(5);

        AbstractBinarySearchTree.Node<Integer> root = searched.getRoot();
        assertEquals(Integer.valueOf(5), root.value);

        AbstractBinarySearchTree.Node<Integer> left = root.leftChild;
        AbstractBinarySearchTree.Node<Integer> right = root.rightChild;

        assertEquals(Integer.valueOf(1), left.value);
        assertEquals(Integer.valueOf(8), right.value);
    }

    @Test
    public void testContains() {
        assertTrue(bst.contains(18));
        assertFalse(bst.contains(1000));
    }

    @Test
    public void testSearch() {
        BinarySearchTree<Integer> search = bst.search(12);
        BinarySearchTree<Integer> search2 = bst.search(21);
        assertEquals(Integer.valueOf(5), search.getLeft().value);
        assertEquals(Integer.valueOf(23), search2.getRight().value);
    }

    @Test
    public void testEachInOrder() {
        List<Integer> expected = List.of(1, 5, 8, 12, 18, 21, 23);
        List<Integer> actual = new ArrayList<>();
        bst.eachInOrder(actual::add);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void testRange(){
        List<Integer> expectedEmptyList = new ArrayList<>();
        List<Integer> expectedListOneElement = List.of(5);
        List<Integer> expectedList5Elements = List.of(5, 8, 12, 18,21);
        List<Integer> actualEmptyList = bst.range(8, 5);
        List<Integer> actualOneElement = bst.range(5, 5);
        List<Integer> actualList5Elements = bst.range(5, 21);
        assertEquals(expectedEmptyList, actualEmptyList);
        assertEquals(expectedListOneElement, actualOneElement);
        assertEquals(expectedList5Elements, actualList5Elements);
    }


    @Test
    public void deleteMin(){
        assertTrue(bst.contains(1));
        bst.deleteMin();
        assertFalse(bst.contains(1));
        bst.deleteMin();
        assertFalse(bst.contains(5));
        assertTrue(bst.contains(8));
        bst.deleteMin();
        assertFalse(bst.contains(8));
        bst.deleteMin();
        assertEquals(bst.getRoot().value, Integer.valueOf(21));
        assertEquals(3, bst.getSize());
    }


    @Test
    public void deleteMax(){
        assertTrue(bst.contains(23));
        bst.deleteMax();
        assertFalse(bst.contains(23));
        bst.deleteMax();
        assertFalse(bst.contains(21));
        assertTrue(bst.contains(18));
        bst.deleteMax();
        assertFalse(bst.contains(18));
        bst.deleteMax();
        assertEquals(Integer.valueOf(5), bst.getRoot().value);
        assertEquals(3, bst.getSize());
    }

    @Test
    public void testRank(){
        int rank3 = bst.rank(12);
        int rank4 = bst.rank(18);
        int rank0 = bst.rank(1);
        int rank8 = bst.rank(8);
        assertEquals(3, rank3);
        assertEquals(4, rank4);
        assertEquals(0, rank0);
        assertEquals(2, rank8);
    }

    @Test
    public void testFloor(){
        Integer floor5 = bst.floor(8);
        Integer floor8 = bst.floor(12);
        Integer floor21 = bst.floor(23);
        assertEquals(Integer.valueOf(5), floor5);
        assertEquals(Integer.valueOf(8), floor8);
        assertEquals(Integer.valueOf(21), floor21);
    }

//        12
//     5       21
//  1   8    18  23

    @Test
    public void testCeil(){
        Integer ceil12 = bst.ceil(8);
        Integer ceil8 = bst.ceil(5);
        Integer ceil18 = bst.ceil(12);
        assertEquals(Integer.valueOf(12), ceil12);
        assertEquals(Integer.valueOf(8), ceil8);
        assertEquals(Integer.valueOf(18), ceil18);

    }
}