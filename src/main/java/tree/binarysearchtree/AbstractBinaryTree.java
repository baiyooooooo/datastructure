package tree.binarysearchtree;

import tree.redblacktree.RedBlackBinaryTreeNode;

/**
 * @author ybbai
 */
public abstract class AbstractBinaryTree {

    /**
     * insert node
     * @param parent parent
     * @param node node
     * @throws BinarySearchTreeException exception
     */
    public abstract void insert(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException;

    /**
     * Delete node
     * @param parent parent
     * @param node node
     * @throws BinarySearchTreeException exception
     */
    public abstract void delete(BinaryTreeNode parent, BinaryTreeNode node) throws BinarySearchTreeException;

    /**************************
     * Depth first traversal  *
     **************************/

    public void preOrderTraversalRecursive(BinaryTreeNode root){
        if(root != null){

            System.out.print(root.value);

            if(root.left != null){
                preOrderTraversalRecursive(root.left);
            }
            if(root.right != null){
                preOrderTraversalRecursive(root.right);
            }
        }
    }

    public void inOrderTraversalRecursive(BinaryTreeNode root){
        if(root != null){
            if(root.left != null){
                inOrderTraversalRecursive(root.left);
            }

            System.out.print(root.value);

            if(root.right != null){
                inOrderTraversalRecursive(root.right);
            }
        }
    }

    public void postOrderTraversalRecursive(BinaryTreeNode root){
        if(root != null){
            if(root.left != null){
                postOrderTraversalRecursive(root.left);
            }
            if(root.right != null){
                postOrderTraversalRecursive(root.right);
            }

            System.out.print(root.value);
        }
    }

    /**************************
     * Width first traversal  *
     **************************/

    public void layerTraversal(BinaryTreeNode root){

    }
}
