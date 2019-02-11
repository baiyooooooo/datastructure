package tree.redblacktree;

import lombok.Data;
import tree.binarysearchtree.BinaryTreeNode;

/**
 * @author ybbai
 */

@Data
public class RedBlackBinaryTreeNode extends BinaryTreeNode {

    public RedBlackColor color;

    public RedBlackBinaryTreeNode(final int value, final RedBlackColor color){
        this.value = value;
        this.color = color;
    }

}
