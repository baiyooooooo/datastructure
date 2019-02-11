package tree.binarysearchtree;

/**
 * @author ybbai
 */
public enum BinarySearchTreeError {


    /**
     * Node value already exists
     */
    NODE_ALREADY_EXISTS("Node value already exists"),

    /**
     * Node is not exists
     */
    NODE_NOT_EXISTS("Node is not exists");

    private String message;

    BinarySearchTreeError(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
