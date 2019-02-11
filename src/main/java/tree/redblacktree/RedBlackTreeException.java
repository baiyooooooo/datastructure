package tree.redblacktree;

/**
 * @author ybbai
 */
public class RedBlackTreeException extends Exception {
    public RedBlackTreeException(RedBlackTreeError redBlackTreeError){
        super(redBlackTreeError.getMessage());
    }
}
