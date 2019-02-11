package tree.binarysearchtree;

/**
 * @author ybbai
 */
public class BinarySearchTreeException extends Exception{
    public BinarySearchTreeException(BinarySearchTreeError binarySearchTreeError){
        super(binarySearchTreeError.getMessage());
    }
}
