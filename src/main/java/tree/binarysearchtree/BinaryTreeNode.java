package tree.binarysearchtree;

/**
 * @author ybbai
 */
public class BinaryTreeNode {

    public BinaryTreeNode parent;

    public BinaryTreeNode left;

    public BinaryTreeNode right;

    public int value;

    public BinaryTreeNode getGrandParent(){
        return this.parent.parent;
    }

    public BinaryTreeNode getUncle(){
        // Exact same object
        if(this.parent == this.getGrandParent().left){
            return this.right;
        } else {
            return this.left;
        }
    }
}
