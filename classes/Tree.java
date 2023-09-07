package classes;

import interfaces.ITree;

public class Tree<T> implements ITree<T> {
    
    TreeNode<T> root = null;
    TreePrinter<T> printer = new TreePrinter<>();

    public void add(T element) { 

        if (root == null) {
            root = new TreeNode<T>(element);
            return;
        }
        
        add(root, element);
    }

    private void add(TreeNode<T> node, T element) {
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

    public T find(T element) {    
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    public T remove() {    
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    public void printTree() throws Exception {
        printer.print(root);        
    }


    public int getDeepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int profundidadeEsquerda = getDeepth(root.leftNode);
            int profundidadeDireita = getDeepth(root.rightNode);

            return Math.max(profundidadeEsquerda, profundidadeDireita) + 1;
        }
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        } else {
            int alturaEsquerda = getHeight(node.leftNode);
            int alturaDireita = getHeight(node.rightNode);

            return Math.max(alturaEsquerda, alturaDireita) + 1;
        }
    }

    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();
        tree.add(10);
        tree.add(5);
        tree.add(11);
        tree.add(5);
        tree.add(11);
        

        try {
			tree.printTree();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
}

class TreePrinter<T>
{
    /** Node that can be printed */

    /**
     * Print a tree
     *
     * @param root
     *            tree root node
     * @throws Exception
     */
    public void print(TreeNode<T> root) throws Exception
    {
        List<List<String>> lines = new List<List<String>>();
        List<TreeNode<T>> level = new List<TreeNode<T>>();
        List<TreeNode<T>> next = new List<TreeNode<T>>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new List<String>();

            nn = 0;

            for (TreeNode<T> n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.data.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.leftNode);
                    next.add(n.rightNode);

                    if (n.leftNode != null) nn++;
                    if (n.rightNode != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<TreeNode<T>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
}
