package tree.binarysearchtree;

/**
 * @author ybbai
 */
public class BinarySearchTree extends AbstractBinaryTree {

    BinaryTreeNode root;

    @Override
    public void insert(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException {
        if (parent == null) {
            root = node;
        } else {
            if (parent.value < node.value) {
                if(parent.right == null){
                    parent.right = node;
                    node.parent = parent;
                } else {
                    insert(parent.right, node);
                }
            } else if (parent.value > node.value) {
                if(parent.left == null){
                    parent.left = node;
                    node.parent = parent;
                } else {
                    insert(parent.left, node);
                }
            } else {
                throw new BinarySearchTreeException(BinarySearchTreeError.NODE_ALREADY_EXISTS);
            }
        }
    }

    @Override
    public void delete(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException {
        if (node == null) {
            throw new BinarySearchTreeException(BinarySearchTreeError.NODE_NOT_EXISTS);
        }
        if (parent.value == node.value) {
            deleteNode(node);
        } else if (parent.value < node.value) {
            delete(parent.right, node);
        } else {
            delete(parent.left, node);
        }
    }

    public BinaryTreeNode findMaxNode(final BinaryTreeNode node) {
        BinaryTreeNode tmp = node;
        if (tmp == null) {
            return null;
        }
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    public BinaryTreeNode findMinNode(final BinaryTreeNode node) {
        BinaryTreeNode tmp = node;
        if (tmp == null) {
            return null;
        }
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private void deleteNode(BinaryTreeNode node) {
        if (node.left == null && node.right == null) {
            deleteLeafNode(node);
        } else if (node.left == null) {
            deleteNoLeafNode(node, node.right);
        } else if (node.right == null) {
            deleteNoLeafNode(node, node.left);
        } else {
            // When node has both left and right child,
            // find the min of left node or min of right node
            // replace value of node to min/max node
            // delete min/max node;

            // Way1: Find the max node in node's left
            BinaryTreeNode deletedNode = findMaxNode(node.left);

            // Way2: Find the min node in node's right
//            BinaryTreeNode deletedNode = findMinNode(node.right);
            node.value = deletedNode.value;
            deleteNode(deletedNode);
        }
    }

    private void deleteLeafNode(BinaryTreeNode node) {
        if (node.parent.left == node) {
            node.parent.left = null;
        } else {
            node.parent.right = null;
        }
        node.parent = null;
    }

    private void deleteNoLeafNode(BinaryTreeNode node, BinaryTreeNode child) {
        if (node.parent.left == node) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        child.parent = node.parent;
    }
}
