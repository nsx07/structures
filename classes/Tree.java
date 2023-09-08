package classes;

import interfaces.ITree;

import java.util.Objects;
import java.util.function.Function;

public class Tree<T> implements ITree<T> {
    
    private TreeNode<T> root = null;
    private int length = 0;

    //#region API Methods

    public void add(T element) { 
        length++;
        if (root == null) {
            root = new TreeNode<>(element);
            return;
        }


        add(root, element);
    }

    public T remove(T element) {
        T elementRemoved = null;
        // gets the match node, only gets the root if tree there is no branch
        var node = getNode(x -> x.data.equals(element), root);

        if (node != null) {
            // gets parent node, using functional method that iterate over all nodes
            var nodeParent = getNode(x ->(x.rightNode != null && x.rightNode.data.equals(element)) || (x.leftNode != null && x.leftNode.data.equals(element)), root);

            if (nodeParent != null) {
                // check position of the current node based on hash code
                boolean isRight = element.hashCode() >= nodeParent.hashCode();

                if (isRight) {
                    nodeParent.rightNode = null;
                } else {
                    nodeParent.leftNode = null;
                }

                if (node.leftNode == null && node.rightNode == null) {
                    // check if current node is a leaf
                    elementRemoved = node.data;
                } else {
                    if (node.rightNode != null) {
                        add(nodeParent, node.rightNode);
                    }

                    if (node.leftNode != null) {
                        add(nodeParent, node.leftNode);
                    }
                }
            }
            else {
                if (root.data.equals(element)) {
                    // check if element is root
                    var oldRoot = root;
                    root = oldRoot.rightNode;
                    add(root, oldRoot.leftNode);
                    elementRemoved = oldRoot.data;
                }
            }
        }

        length--;
        return elementRemoved;
    }

    public T get(T element) {
        TreeNode<T> node = getNode(element, root);
        return node != null ? node.data : null;
    }

    public T get(Function<? super T, Boolean> predicate) {
        var node = get(predicate, root);
        return node != null ? node.data : null;
    }

    public void printTree() {
        BTreePrinter.print(root, this);
    }

    public void clear() {
        root = null;
        length = 0;
    }

    //#endregion

    //#region Private methods members :: get(), getNode()², add()², getDepth(), getHeight()

    private TreeNode<T> get(Function<? super T, Boolean> predicate, TreeNode<T> node) {
        if (Objects.isNull(node) || Objects.isNull(predicate)) {
            return null;
        }

        TreeNode<T> matchNode = null;

        matchNode = get(predicate, node.leftNode);
        if (matchNode != null) {
            return matchNode;
        }

        matchNode = get(predicate, node.rightNode);
        if (matchNode != null) {
            return matchNode;
        }

        if (predicate.apply(node.data)) {
            return node;
        }

        return null;
    }

    private TreeNode<T> getNode(Function<? super TreeNode<T>, Boolean> predicate, TreeNode<T> node) {
        if (Objects.isNull(node) || Objects.isNull(predicate)) {
            return null;
        }

        TreeNode<T> matchNode = null;

        matchNode = getNode(predicate, node.leftNode);
        if (matchNode != null) {
            return matchNode;
        }

        matchNode = getNode(predicate, node.rightNode);
        if (matchNode != null) {
            return matchNode;
        }

        if (predicate.apply(node)) {
            return node;
        }

        return null;
    }

    private TreeNode<T> getNode(T element, TreeNode<T> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        if (node.data.equals(element)) {
            return node;
        }

        TreeNode<T> matchNode = null;
        if (element.hashCode() > node.hashCode()) {
            matchNode = getNode(element, node.rightNode);
        } else {
            matchNode = getNode(element, node.leftNode);
        }

        return matchNode;
    }

    private void add(TreeNode<T> parent, TreeNode<T> node) {
        if (Objects.isNull(parent) || Objects.isNull(node)) return;

        int parentHash = parent.hashCode();
        int nodeHash = node.hashCode();

        if (nodeHash >= parentHash) {
            if (parent.rightNode == null) {
                parent.rightNode = node;
            } else {
                add(parent.rightNode, node);
            }
        } else {
            if (parent.leftNode == null) {
                parent.leftNode = node;
            } else {
                add(parent.leftNode, node);
            }
        }
    }

    private void add(TreeNode<T> node, T element) {
        if (Objects.isNull(element) || Objects.isNull(node)) return;

        int nodeHash = node.hashCode();
        int elHash = element.hashCode();

        if (elHash >= nodeHash) {
            if (node.rightNode == null) {
                node.rightNode = new TreeNode<>(element);
            } else {
                add(node.rightNode, element);
            }
        } else {
            if (node.leftNode == null) {
                node.leftNode = new TreeNode<>(element);
            } else {
                add(node.leftNode, element);
            }
        }
    }

    private int getDepthRecursive(TreeNode<T> current, TreeNode<T> node, int depth) {
        if (current == null) {
            return -1;
        }
        if (current == node) {
            return depth;
        }

        int leftDepth = getDepthRecursive(current.leftNode, node, depth + 1);
        if (leftDepth != -1) {
            return leftDepth;
        }

        return getDepthRecursive(current.rightNode, node, depth + 1);
    }

    private int getDepth(TreeNode<T> node) {
        return getDepthRecursive(root, node, 0);
    }

    private int getHeight(TreeNode<T> node) {
        if (Objects.isNull(node)) {
            return -1;
        } else {
            int leftHeight = getHeight(node.leftNode);
            int rightHeight = getHeight(node.rightNode);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    //#endregion

    //#region Private classes members :: BTreePrinter, TreeNode<T>

    private static class BTreePrinter {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_BLUE = "\u001B[34m";
        private static String traversePreOrder(TreeNode root, Tree tree) {
            if (root == null) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            sb.append(ANSI_BLACK);
            sb.append(root.data);
            sb.append(" | ↓ " + tree.getHeight(root));
            sb.append(" | \uD83C\uDF33 " + tree.length);
            sb.append(ANSI_RESET);

            String pointerRight = ANSI_BLUE + "└─R─ " + ANSI_RESET;
            String pointerLeft = ANSI_GREEN + ((root.rightNode != null) ? "├─L─ " : "└─L─ ") + ANSI_RESET;

            traverseNodes(sb, "", pointerLeft, root.leftNode, root.rightNode != null, tree);
            traverseNodes(sb, "", pointerRight, root.rightNode, false, tree);

            return sb.toString();
        }
        private static void traverseNodes(StringBuilder sb, String padding, String pointer, TreeNode node, boolean hasRightSibling, Tree tree) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node != null ? node.data : "null");
            sb.append(node != null ? " | ↓ " + tree.getHeight(node) + " | ↑ " + tree.getDepth(node) : "");
            if (node != null) {
                StringBuilder paddingBuilder = new StringBuilder(padding);
                if (hasRightSibling) {
                    paddingBuilder.append("│  ");
                } else {
                    paddingBuilder.append("   ");
                }



                String paddingForBoth = paddingBuilder.toString();
                String pointerRight = ANSI_BLUE + "└─R─ " + ANSI_RESET;
                String pointerLeft = ANSI_GREEN + ((node.rightNode != null) ? "├─L─ " : "└─L─ ") + ANSI_RESET;
                traverseNodes(sb, paddingForBoth, pointerLeft, node.leftNode, true, tree);
                traverseNodes(sb, paddingForBoth, pointerRight, node.rightNode, false, tree);
            }

        }
        public static void print(TreeNode root, Tree tree) {
            System.out.println();
            System.out.print(traversePreOrder(root, tree));
            System.out.println();
        }
    }

    private static class TreeNode<T>  {

        public T data;
        public TreeNode<T> leftNode;
        public TreeNode<T> rightNode;

        public List<TreeNode<T>> nodes = new List<>();

        public TreeNode(T data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

        @Override
        public String toString() {
            return String.format("""
                TreeNode {
                    \tdata = %s,
                    \tleftNode = %s,
                    \trightNode = %s
                }
                """, data, leftNode, rightNode);
        }

    }

    //#endregion

}