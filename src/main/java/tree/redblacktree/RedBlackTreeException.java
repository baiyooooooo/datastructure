package tree.redblacktree;

import tree.binarysearchtree.BinarySearchTreeException;

/**
 * @author ybbai
 */
public class RedBlackTreeException extends BinarySearchTreeException {
    public RedBlackTreeException(RedBlackTreeError redBlackTreeError){
        super(redBlackTreeError.getMessage());
    }
}
