import classes.Tree;

public class Structures {
    
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
