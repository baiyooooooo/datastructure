import tree.binarysearchtree.BinarySearchTreeException;
import tree.redblacktree.RedBlackBinaryTree;
import tree.redblacktree.RedBlackBinaryTreeNode;
import tree.redblacktree.RedBlackColor;

/**
 * @author ybbai
 */
public class Main {
    public static void main(String[] args) throws BinarySearchTreeException {
        RedBlackBinaryTree redBlackBinaryTree = new RedBlackBinaryTree();
        redBlackBinaryTree.insert(null, new RedBlackBinaryTreeNode(80, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(100, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(90, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(45, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(6, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(130, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(25, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(81, RedBlackColor.RED));
        redBlackBinaryTree.insert(redBlackBinaryTree.root, new RedBlackBinaryTreeNode(12, RedBlackColor.RED));
        redBlackBinaryTree.inOrderTraversalRecursive(redBlackBinaryTree.root);

        redBlackBinaryTree.delete(redBlackBinaryTree.root, 45);

        redBlackBinaryTree.inOrderTraversalRecursive(redBlackBinaryTree.root);

    }
}
