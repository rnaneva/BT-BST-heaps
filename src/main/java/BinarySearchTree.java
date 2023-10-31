
import interfaces.AbstractBinarySearchTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private AbstractBinarySearchTree.Node<E> root;
    private int size;


    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    public void eachInOrder(Consumer<E> consumer) {
        forEach(consumer, this.root);
    }

    public int getSize() {
        return this.size;
    }

    private void forEach(Consumer<E> consumer, Node<E> node) {
        if (node.leftChild != null) {
            forEach(consumer, node.leftChild);
        }
        consumer.accept(node.value);
        if (node.rightChild != null) {
            forEach(consumer, node.rightChild);
        }
    }

    public AbstractBinarySearchTree.Node<E> getRoot() {
        return this.root;
    }

    @Override
    public AbstractBinarySearchTree.Node<E> getLeft() {
        return root.leftChild;
    }

    @Override
    public AbstractBinarySearchTree.Node<E> getRight() {
        return root.rightChild;
    }

    @Override
    public E getValue() {
        return root.value;
    }

    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            this.root = newNode;
            size = 1;
        } else {
            insertChild(element, this.root);
        }


    }

    private void insertChild(E element, Node<E> node) {

        if (node.leftChild == null && element.compareTo(node.value) < 0) {
            node.leftChild = new Node<>(element);
            size++;
        } else if (node.rightChild == null && element.compareTo(node.value) > 0) {
            node.rightChild = new Node<>(element);
            size++;
        }

        if (element.compareTo(node.value) < 0) {
            insertChild(element, node.leftChild);
        }
        if (element.compareTo(node.value) > 0) {
            insertChild(element, node.rightChild);
        }

    }

    public boolean contains(E element) {
        Node<E> current = this.root;
        while (current != null) {
            if (element.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (element.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else {
                break;
            }
        }
        return current != null;
    }

    public BinarySearchTree<E> search(E element) {
        Node<E> current = this.root;
        while (current != null) {
            if (element.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (element.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else {
                return new BinarySearchTree<>(current);
            }
        }
        return null;
    }


    public List<E> range(E first, E second) {
        if (first.compareTo(second) > 0) {
            return new ArrayList<>();
        } else if (first.compareTo(second) == 0) {
            return List.of(first);
        }

        return rangeInOrder(first, second, this.root, new ArrayList<>());
    }

    private List<E> rangeInOrder(E first, E second, Node<E> node, List<E> list) {
        if (node == null) {
            return list;
        }

        if (node.leftChild != null) {
            rangeInOrder(first, second, node.leftChild, list);
        }
        if (first.compareTo(node.value) <= 0 && second.compareTo(node.value) >= 0) {
            list.add(node.value);
        }
        if (node.rightChild != null) {
            rangeInOrder(first, second, node.rightChild, list);
        }
        return list;
    }

    public void deleteMin() {
        ensureNonEmpty();
        if (root.leftChild == null) {
            if (root.rightChild != null) {
                root = root.rightChild;
            } else {
                this.root = null;
            }
            size--;
            return;
        }
        Node<E> node = traverseLeft(this.root);
        if (node.leftChild.rightChild != null) {
            node.leftChild = node.leftChild.rightChild;
        } else {
            node.leftChild = null;
        }
        size--;

    }

    private void ensureNonEmpty() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    private Node<E> traverseLeft(Node<E> node) {
        while (node.leftChild.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public void deleteMax() {
        ensureNonEmpty();
        if (root.rightChild == null) {
            if (root.leftChild != null) {
                root = root.leftChild;
            } else {
                this.root = null;
            }
            size--;
            return;
        }
        Node<E> node = traverseRight(this.root);
        if (node.rightChild.leftChild != null) {
            node.rightChild = node.rightChild.leftChild;
        } else {
            node.rightChild = null;
        }
        size--;
    }

    private Node<E> traverseRight(Node<E> node) {
        while (node.rightChild.rightChild != null) {
            node = node.rightChild;
        }
        return node;
    }

    public int count() {
        return this.size;
    }

    public int rank(E element) {

        return getSmallerElements(element, this.root, new ArrayList<>()).size();

    }


    public E ceil(E element) {

        ensureNonEmpty();
        List<E> biggerElement = getBiggerElements(element, this.root, new ArrayList<>());

        return biggerElement.get(0);

    }

    private List<E> getBiggerElements(E element, Node<E> node, List<E> values) {
        ensureNonEmpty();

        if (node.leftChild != null) {
            getBiggerElements(element, node.leftChild, values );
        }
        if (element.compareTo(node.value) < 0) {
            values.add(node.value);
        }
        if (node.rightChild != null) {
            getBiggerElements(element, node.rightChild, values );
        }

        return values;
    }

    private List<E> getSmallerElements(E element, Node<E> node, List<E> values) {
        ensureNonEmpty();

        if (node.leftChild != null) {
            getSmallerElements(element, node.leftChild, values );
        }
        if (element.compareTo(node.value) > 0) {
            values.add(node.value);
        }
        if (node.rightChild != null) {
            getSmallerElements(element, node.rightChild, values );
        }

        return values;
    }

    public E floor(E element) {

        ensureNonEmpty();
        List<E> smallerElements = getSmallerElements(element, this.root, new ArrayList<>());
        return smallerElements.get(smallerElements.size() - 1);
    }

    public Node<E> getParent(E element) {
        if (this.size == 1) {
            return null;
        }
        if (this.size == 2 || this.size == 3) {
            return this.root;
        }
        Node<E> current = this.root;
        Node<E> parent = current;
        while (current != null) {
            if (element.compareTo(current.value) < 0) {
                parent = current;
                current = current.leftChild;
            } else if (element.compareTo(current.value) > 0) {
                parent = current;
                current = current.rightChild;
            } else {
                return parent;
            }
        }
        return null;
    }
}
