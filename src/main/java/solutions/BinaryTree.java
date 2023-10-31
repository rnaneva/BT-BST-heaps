package solutions;

import interfaces.AbstractBinaryTree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E value;
    private final BinaryTree<E> leftChild;
    private final BinaryTree<E> rightChild;
    private BinaryTree<E> parent;


    public BinaryTree(E value, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        if (this.leftChild != null) {
            this.leftChild.setParent(this);
        }
        this.rightChild = rightChild;
        if (this.rightChild != null) {
            this.rightChild.setParent(this);
        }
    }

    public E findLowestCommonAncestor(E first, E second) {
        BinaryTree<E> treeFirst = getTreeByValue(first);
        BinaryTree<E> treeSecond = getTreeByValue(second);
        List<E> parentsFirst = new ArrayList<>();
        List<E> parentsSecond = new ArrayList<>();


        while (treeFirst.parent != null) {
            parentsFirst.add(treeFirst.getKey());
            treeFirst = treeFirst.parent;
        }
        while (treeSecond.parent != null) {
            parentsSecond.add(treeSecond.getKey());
            treeSecond = treeSecond.parent;
        }

        for (E el1 : parentsFirst) {
            for (E el2 : parentsSecond) {
                if (el1.equals(el2)) {
                    return el1;
                }
            }
        }

        return null;
    }




    public BinaryTree<E> getParent() {
        return this.parent;
    }

    public BinaryTree<E> setParent(BinaryTree<E> parent) {
        this.parent = parent;
        return this;
    }


    @Override
    public E getKey() {
        return this.value;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightChild;
    }

    @Override
    public void setKey(E key) {
        this.value = key;
    }


    @Override
    public String asIndentedPreOrder(int indent) {

        return indentPreorder(0, new StringBuilder());
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        return getListPreOrder(new ArrayList<>());

    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        return getListPostOrder(new ArrayList<>());
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        return getListInOrder(new ArrayList<>());
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (this.leftChild != null) {
            this.getLeft().forEachInOrder(consumer);
        }
        consumer.accept(this.value);
        if (this.getRight() != null) {
            this.getRight().forEachInOrder(consumer);
        }

    }

    public Set<BinaryTree<E>> dfs(BinaryTree<E> tree, LinkedHashSet<BinaryTree<E>> trees) {
        trees.add(tree);
        if (tree.leftChild != null) {
            dfs(tree.leftChild, trees);
        }
        if (tree.rightChild != null) {
            dfs(tree.rightChild, trees);
        }
        return trees;
    }

    public BinaryTree<E> getTreeByValue(E element) {
        Set<BinaryTree<E>> treesSet = dfs(this, new LinkedHashSet<>());
        for (BinaryTree<E> tree : treesSet) {
            if (tree.getKey().equals(element)) {
                return tree;
            }
        }
        return null;
    }

    private String indentPreorder(int indent, StringBuilder sb) {
        sb.append(" ".repeat(indent))
                .append(this.value)
                .append(System.lineSeparator());
        if (this.leftChild != null) {
            this.leftChild.indentPreorder(indent + 2, sb);
        }
        if (this.rightChild != null) {
            this.rightChild.indentPreorder(indent + 2, sb);
        }

        return sb.toString().trim();

    }

    public List<E> topView() {
        return getOuterElements(new ArrayList<>(), this);

    }

    private List<E>  getOuterElements(List<E> elements, BinaryTree<E> tree) {

        while(tree.leftChild != null){
            elements.add(tree.getKey());
            tree = tree.leftChild;
        }
        elements.add(tree.getKey());
        tree = this;
        while(tree.rightChild != null){
            tree = tree.rightChild;
            elements.add(tree.getKey());
        }

        return elements;
    }



    private List<AbstractBinaryTree<E>> getListPreOrder(ArrayList<AbstractBinaryTree<E>> list) {

        list.add(this);
        if (this.leftChild != null) {
            this.leftChild.getListPreOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.getListPreOrder(list);
        }

        return list;
    }



    private List<AbstractBinaryTree<E>> getListInOrder(ArrayList<AbstractBinaryTree<E>> list) {
        if (this.leftChild != null) {
            this.leftChild.getListInOrder(list);
        }
        list.add(this);
        if (this.rightChild != null) {
            this.rightChild.getListInOrder(list);
        }

        return list;
    }



    private List<AbstractBinaryTree<E>> getListPostOrder(ArrayList<AbstractBinaryTree<E>> list) {

        if (this.leftChild != null) {
            this.leftChild.getListPostOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.getListPostOrder(list);
        }
        list.add(this);
        return list;
    }








}
