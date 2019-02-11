package tree.binarysearchtree;

/**
 * @author ybbai
 */
public class BinarySearchTree extends AbstractBinaryTree {

    @Override
    public void insert(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException {
        if (parent == null) {
            parent = node;
        }

        if (parent.value < node.value) {
            insert(parent.right, node);
        } else if (parent.value > node.value) {
            insert(parent.left, node);
        } else {
            throw new BinarySearchTreeException(BinarySearchTreeError.NODE_ALREADY_EXISTS);
        }
    }

    @Override
    public void delete(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException {
        if (node == null) {
            throw new BinarySearchTreeException(BinarySearchTreeError.NODE_NOT_EXISTS);
        }
        if (parent.value == node.value) {
            // TODO: delete node
        } else if (parent.value < node.value) {
            delete(parent.right, node);
        } else {
            delete(parent.left, node);
        }
    }

    private void deleteNode(BinaryTreeNode node, BinaryTreeNode child) {
        if(node.parent.left == node){
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
    }
}
