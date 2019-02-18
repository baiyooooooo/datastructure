package tree.redblacktree;

import lombok.Data;
import tree.binarysearchtree.BinarySearchTreeError;
import tree.binarysearchtree.BinarySearchTreeException;

/**
 * @author ybbai
 */
@Data
public class RedBlackBinaryTree {

    public RedBlackBinaryTreeNode root;

    public RedBlackBinaryTree(){
        this.root = null;
    }

    public RedBlackBinaryTree(int value){
        this.root = new RedBlackBinaryTreeNode(value, RedBlackColor.BLACK);
    }

    public void insert(RedBlackBinaryTreeNode parent, RedBlackBinaryTreeNode node) throws BinarySearchTreeException {
        insertNode(parent, node);
        fixAfterInsertion(node);
    }

    public void insertNode(RedBlackBinaryTreeNode parent, RedBlackBinaryTreeNode node) throws BinarySearchTreeException {
        if (node == null) {
            throw new BinarySearchTreeException(BinarySearchTreeError.NODE_NOT_EXISTS);
        } else if(parent == null){
            this.root = node;
            this.root.color = RedBlackColor.BLACK;
            return;
        }
        if (parent.value < node.value) {
            if(parent.right == null){
                parent.right = node;
                node.parent = parent;
            } else {
                insertNode(parent.right, node);
            }
        } else if (parent.value > node.value) {
            if(parent.left == null){
                parent.left = node;
                node.parent = parent;
            } else {
                insertNode(parent.left, node);
            }
        } else {
            throw new BinarySearchTreeException(BinarySearchTreeError.NODE_ALREADY_EXISTS);
        }
    }

    public void delete(RedBlackBinaryTreeNode node, int value) throws BinarySearchTreeException {
        deleteNode(node, value);
        fixAfterDeletion(node);
    }

    private void deleteNode(RedBlackBinaryTreeNode node, int value) throws BinarySearchTreeException {
        if (node.value == value) {
            deleteNode(node);
        } else if (node.value < value) {
            deleteNode(node.right, value);
        } else {
            deleteNode(node.left, value);
        }
    }

    /**
     * Fix After insert a node
     *
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void fixAfterInsertion(RedBlackBinaryTreeNode node) throws RedBlackTreeException {
        node.color = RedBlackColor.RED;
        if (this.root == null || this.root == node) {
            this.root.color = RedBlackColor.BLACK;
        } else {
            while (node != null && node != root && node.parent.color == RedBlackColor.RED) {
                if (node.parent == node.parent.parent.left) {
                    RedBlackBinaryTreeNode uncle = node.parent.parent.right;
                    if (uncle != null && uncle.color == RedBlackColor.RED) {
                        node.parent.color = RedBlackColor.BLACK;
                        uncle.color = RedBlackColor.BLACK;
                        node.parent.parent.color = RedBlackColor.RED;
                        node = node.parent.parent;
                    } else {
                        if (node == node.parent.right) {
                            node = node.parent;
                            rotateLeft(node);
                        }
                        node.parent.color = RedBlackColor.BLACK;
                        node.parent.parent.color = RedBlackColor.RED;
                        rotateRight(node.parent.parent);
                    }
                } else {
                    RedBlackBinaryTreeNode uncle = node.parent.parent.left;
                    if (uncle != null && uncle.color == RedBlackColor.RED) {
                        node.parent.color = RedBlackColor.BLACK;
                        uncle.color = RedBlackColor.BLACK;
                        node.parent.parent.color = RedBlackColor.RED;
                        node = node.parent.parent;
                    } else {
                        if (node == node.parent.left) {
                            node = node.parent;
                            rotateRight(node);
                        }
                        node.parent.color = RedBlackColor.BLACK;
                        node.parent.parent.color = RedBlackColor.RED;
                        rotateLeft(node.parent.parent);
                    }
                }
            }
            this.root.color = RedBlackColor.BLACK;
        }
    }

    /**
     * Fix after delete a node
     * @param node node
     * @throws BinarySearchTreeException Exception
     */
    private void fixAfterDeletion(RedBlackBinaryTreeNode node) throws BinarySearchTreeException {
        while (node != this.root && node.color.equals(RedBlackColor.BLACK)) {
            if (node == node.parent.left) {
                RedBlackBinaryTreeNode brother = node.parent.right;

                if (brother.color.equals(RedBlackColor.RED)) {
                    brother.color = RedBlackColor.BLACK;
                    node.parent.color = RedBlackColor.RED;
                    rotateLeft(node.parent);
                    brother = node.parent.right;
                }
                if(brother.left.color.equals(RedBlackColor.BLACK) && brother.right.color.equals(RedBlackColor.BLACK)){
                    brother.color = RedBlackColor.RED;
                    node = node.parent;
                } else {
                    if (brother.right.color.equals(RedBlackColor.BLACK)) {
                        brother.left.color = RedBlackColor.BLACK;
                        brother.color = RedBlackColor.RED;
                        rotateRight(brother);
                        brother = node.parent.right;
                    }
                    brother.color = node.parent.color;
                    node.parent.color = RedBlackColor.BLACK;
                    brother.right.color = RedBlackColor.BLACK;
                    rotateLeft(node.parent);
                    node = this.root;
                }
            } else {
                RedBlackBinaryTreeNode brother = node.parent.left;
                if (brother.color.equals(RedBlackColor.RED)) {
                    brother.color = RedBlackColor.BLACK;
                    node.parent.color = RedBlackColor.RED;
                    rotateRight(node.parent);
                    brother = node.parent.left;
                }
                if(brother.right.color.equals(RedBlackColor.BLACK) && brother.left.color.equals(RedBlackColor.BLACK)){
                    brother.color = RedBlackColor.RED;
                    node = node.parent;
                } else {
                    if (brother.left.color.equals(RedBlackColor.BLACK)) {
                        brother.right.color = RedBlackColor.BLACK;
                        brother.color = RedBlackColor.RED;
                        rotateLeft(brother);
                        brother = node.parent.left;
                    }
                    brother.color = node.parent.color;
                    node.parent.color = RedBlackColor.BLACK;
                    brother.left.color = RedBlackColor.BLACK;
                    rotateRight(node.parent);
                    node = this.root;
                }
            }
        }

        node.color = RedBlackColor.BLACK;
    }

    private void rotateLeft(RedBlackBinaryTreeNode redBlackTreeNode) throws RedBlackTreeException {
        if (redBlackTreeNode.right == null) {
            throw new RedBlackTreeException(RedBlackTreeError.NO_RIGHT_NODE);
        }
        RedBlackBinaryTreeNode tmp = redBlackTreeNode.right;

        redBlackTreeNode.right = tmp.left;
        if (redBlackTreeNode.right != null) {
            redBlackTreeNode.right.parent = redBlackTreeNode;
        }

        tmp.parent = redBlackTreeNode.parent;
        if (redBlackTreeNode.parent == null) {
            this.root = tmp;
        } else if (redBlackTreeNode.parent.left == redBlackTreeNode) {
            redBlackTreeNode.parent.left = tmp;
        } else {
            redBlackTreeNode.parent.right = tmp;
        }

        tmp.left = redBlackTreeNode;
        redBlackTreeNode.parent = tmp;
    }

    private void rotateRight(RedBlackBinaryTreeNode redBlackTreeNode) throws RedBlackTreeException {
        if (redBlackTreeNode.left == null) {
            throw new RedBlackTreeException(RedBlackTreeError.NO_LEFT_NODE);
        }
        RedBlackBinaryTreeNode tmp = redBlackTreeNode.left;
        redBlackTreeNode.left = tmp.right;
        if (redBlackTreeNode.left != null) {
            redBlackTreeNode.left.parent = redBlackTreeNode;
        }

        tmp.parent = redBlackTreeNode.parent;
        if (redBlackTreeNode.parent == null) {
            this.root = tmp;
        } else if (redBlackTreeNode.parent.right == redBlackTreeNode) {
            redBlackTreeNode.parent.right = tmp;
        } else {
            redBlackTreeNode.parent.left = tmp;
        }

        tmp.right = redBlackTreeNode;
        redBlackTreeNode.parent = tmp;
    }

    public void preOrderTraversalRecursive(RedBlackBinaryTreeNode node){
        if(node != null){

            System.out.println(node);

            if(node.left != null){
                preOrderTraversalRecursive(node.left);
            }
            if(node.right != null){
                preOrderTraversalRecursive(node.right);
            }
        }
    }

    public void inOrderTraversalRecursive(RedBlackBinaryTreeNode node){
        if(node != null){
            if(node.left != null){
                inOrderTraversalRecursive(node.left);
            }

            System.out.println(node);

            if(node.right != null){
                inOrderTraversalRecursive(node.right);
            }
        }
    }

    public void postOrderTraversalRecursive(RedBlackBinaryTreeNode node){
        if(node != null){
            if(node.left != null){
                postOrderTraversalRecursive(node.left);
            }
            if(node.right != null){
                postOrderTraversalRecursive(node.right);
            }

            System.out.println(node);
        }
    }
    public RedBlackBinaryTreeNode findMaxNode(final RedBlackBinaryTreeNode node) {
        RedBlackBinaryTreeNode tmp = node;
        if (tmp == null) {
            return null;
        }
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    public RedBlackBinaryTreeNode findMinNode(final RedBlackBinaryTreeNode node) {
        RedBlackBinaryTreeNode tmp = node;
        if (tmp == null) {
            return null;
        }
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private void deleteNode(RedBlackBinaryTreeNode node) {
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
            RedBlackBinaryTreeNode deletedNode = findMaxNode(node.left);

            // Way2: Find the min node in node's right
//            BinaryTreeNode deletedNode = findMinNode(node.right);
            node.value = deletedNode.value;
            deleteNode(deletedNode);
        }
    }

    private void deleteLeafNode(RedBlackBinaryTreeNode node) {
        if (node.parent.left == node) {
            node.parent.left = null;
        } else {
            node.parent.right = null;
        }
        node.parent = null;
    }

    private void deleteNoLeafNode(RedBlackBinaryTreeNode node, RedBlackBinaryTreeNode child) {
        if (node.parent.left == node) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        child.parent = node.parent;
    }

}
