package tree.redblacktree;

import lombok.Data;
import tree.binarysearchtree.BinaryTreeNode;

/**
 * @author ybbai
 */

@Data
public class RedBlackBinaryTreeNode extends BinaryTreeNode {

    public RedBlackColor color;

    public RedBlackBinaryTreeNode parent;

    public RedBlackBinaryTreeNode left;

    public RedBlackBinaryTreeNode right;

    public int value;

    public RedBlackBinaryTreeNode(final int value, final RedBlackColor color){
        this.value = value;
        this.color = color;
    }

    @Override
    public RedBlackBinaryTreeNode getGrandParent(){
        return this.parent.parent;
    }

    @Override
    public RedBlackBinaryTreeNode getUncle(){
        // Exact same object
        if(this.parent == this.getGrandParent().left){
            return this.right;
        } else {
            return this.left;
        }
    }

    @Override
    public RedBlackBinaryTreeNode getBrother(){
        if(this == parent.left){
            return parent.right;
        } else {
            return parent.left;
        }
    }

    public boolean isLeftChild(){
        return this == parent.left;
    }

    public boolean hasRedChild(){
        return this.left != null && this.left.color.equals(RedBlackColor.RED) &&
                this.right != null && this.right.color.equals(RedBlackColor.RED);
    }

    @Override
    public String toString(){
        return this.value + " | " + this.color;
    }
}
