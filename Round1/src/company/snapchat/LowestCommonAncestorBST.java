package company.snapchat;

import chapter3.binaryTree.TreeNode;

// 235. This is for Binary Search Tree. As it is sequential, we can decide either go left or right
public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else { // This is the common ancestor
            return root;
        }
    }
    
    public TreeNode lowestCommonAncestorIteration(TreeNode root, TreeNode p, TreeNode q) {// Suppose p.val is smaller
    	while (root != null) {
    		if (p.val < root.val && q.val > root.val) {
    			return root;
    		}
    		if (p.val < root.val && q.val < root.val) {
    			root = root.left;
    		} else {
    			root = root.right;
    		}
    		
    	}
    	
    	return null;
    }
}
