package classes;

public class TreeNode<T>  {
    
    T data;
    TreeNode<T> leftNode;
    TreeNode<T> rightNode;

    List<TreeNode<T>> nodes = new List<>();

    public TreeNode(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return "TreeNode [ data = " + data + ", leftNode = " + leftNode + ", rightNode = " + rightNode + "]";
    }

}
