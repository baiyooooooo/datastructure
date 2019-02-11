package tree.redblacktree;

/**
 * @author ybbai
 */
public enum RedBlackTreeError {

    /**
     * Node is null
     */
    NODE_IS_NULL("Node is null"),

    /**
     * No left node
     */
    NO_LEFT_NODE("No left node"),

    /**
     * No right node
     */
    NO_RIGHT_NODE("No right node");

    private String message;

    RedBlackTreeError(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
