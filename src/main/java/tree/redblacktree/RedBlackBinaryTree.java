package tree.redblacktree;

import lombok.Data;
import tree.binarysearchtree.BinarySearchTree;

/**
 * @author ybbai
 */
@Data
public class RedBlackBinaryTree extends BinarySearchTree {

    public RedBlackBinaryTreeNode root;

    /**
     * Adjust node when node is root node
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void adjustAsRootNode(RedBlackBinaryTreeNode node) throws RedBlackTreeException {
        if (root == null) {
            root = node;
            root.color = RedBlackColor.BLACK;
        } else {
            adjustWithParentIsBlack(node);
        }
    }

    /**
     * Adjust node when node's parent is black
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void adjustWithParentIsBlack(RedBlackBinaryTreeNode node) throws RedBlackTreeException {
        // If parent is black, do not need to adjust
        if (!node.parent.color.equals(RedBlackColor.BLACK)) {
            adjustWithParentAndUncleAreAllRed(node);
        }
    }

    /**
     * Adjust node when node's parent and its uncle are all red
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void adjustWithParentAndUncleAreAllRed(RedBlackBinaryTreeNode node) throws RedBlackTreeException {
        RedBlackBinaryTreeNode uncle = node.getUncle();
        RedBlackBinaryTreeNode grandParent = node.getGrandParent();
        if (uncle != null && uncle.color.equals(RedBlackColor.RED)) {
            node.parent.color = RedBlackColor.BLACK;
            uncle.color = RedBlackColor.BLACK;
            grandParent.color = RedBlackColor.RED;
            adjustAsRootNode(grandParent);
        } else {
            adjustWithParentIsRedAndUncleIsBlackOrNullAndNodeIsRightAndParentIsLeft(node);
        }
    }

    /**
     * Adjust node when node's parent is red and its uncle is black or null
     * and node is the right node of his parent
     * and node's parent is the left node of node's grand parent
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void adjustWithParentIsRedAndUncleIsBlackOrNullAndNodeIsRightAndParentIsLeft(RedBlackBinaryTreeNode node)
            throws RedBlackTreeException {
        RedBlackBinaryTreeNode grandParent = node.getGrandParent();
        if (node == node.parent.right && node.parent == grandParent.left) {
            rotateLeft(node.parent);
            node = node.left;
        } else {
            rotateRight(node.parent);
            node = node.right;
        }
        adjustWithParentIsRedAndUncleIsBlackOrNullAndNodeIsLeftAndParentIsLeft(node);
    }

    /**
     * Adjust node when node's parent is red and its uncle is black or null
     * and node is the left node of his parent
     * and node's parent is the left node of node's grand parent
     * @param node node
     * @throws RedBlackTreeException exception
     */
    private void adjustWithParentIsRedAndUncleIsBlackOrNullAndNodeIsLeftAndParentIsLeft(RedBlackBinaryTreeNode node)
        throws RedBlackTreeException{
        node.parent.color = RedBlackColor.BLACK;
        RedBlackBinaryTreeNode grandParent = node.getGrandParent();
        grandParent.color = RedBlackColor.RED;
        if(node == node.parent.left && node.parent == grandParent.left){
            rotateRight(grandParent);
        } else {
            rotateLeft(grandParent);
        }
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
            root = tmp;
        } else if (redBlackTreeNode.parent.right == redBlackTreeNode) {
            redBlackTreeNode.parent.right = tmp;
        } else {
            redBlackTreeNode.parent.left = tmp;
        }

        tmp.right = redBlackTreeNode;
        redBlackTreeNode.parent = tmp;
    }
}
