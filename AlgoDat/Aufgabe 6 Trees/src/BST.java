public class BST {
    private enum Order { PRE, IN, POST };
    
    private BSTNode root;
  
    /**
     * Returns true if this BST is empty
     * @return true if this BST is empty
     */
    public boolean isEmpty() { return root == null; }
        

    /**
     * Inserts the value data to the BST n 
     * @param n - root of the BST (subtree)
     * @param data - value to be added to the BST 
     */        
    private void insert(BSTNode n, Integer data) {
        if (data <= n.getData()) {
            if (n.leftChild == null) {
                n.leftChild = new BSTNode(data);
            } else {
                insert(n.leftChild, data);
            }
        } else {
            if (n.rightChild == null) {
                n.rightChild = new BSTNode(data);
            } else {
                insert(n.rightChild, data);
            }
        }
    }

    
    /**
     * Inserts the value data to this BST 
     * @param data - value to be added to the BST 
     */ 
    public void insert(Integer data) {
        if (isEmpty()) root =  new BSTNode(data);
        else insert(root,data);   
    }
    
    
    /**
     * Returns the smallest value of the BST with root node 
     * @param node - root node of a BST (subtree)
     * @return smallest value of the BST (subtree)
     */
    private BSTNode findMin(BSTNode node) {
        if (node.leftChild != null){
            return findMin(node.leftChild);
        } else {
            return node;
        }
    }

    
    /**
     * Returns the smallest value of this BST
     * @return the smallest value of this BST
     */
    public Integer getMin() {
        if (isEmpty()) return null;
        else           return findMin(root).getData();
    }


    /**
     * @param node - root node of a BST (subtree)
     * @param data - value to remove from this BST
     * @return the node that replaces the removed node
     */  
    private BSTNode remove(BSTNode node, Integer data) {
        if (node == null) {
            return null;
        }
        if (data < node.getData()) {
            node.leftChild = remove(node.leftChild, data);
        } else if (data > node.getData()) {
            node.rightChild = remove(node.rightChild, data);
        } else {
            node = replace(node);
        }
        return node;
    }

    private BSTNode replace(BSTNode node){
        if (node.leftChild == null && node.rightChild == null) {
            if(root == node){
                root = null;
            }    
            return null;
        }
        else if (node.leftChild == null) {
            if(root == node){
                root = node.rightChild;
            }   
            return node.rightChild;
        } else if (node.rightChild == null) {
            if(root == node){
                root = node.leftChild;
            }   
            return node.leftChild;
        }
        BSTNode successor = findMin(node.rightChild);
        node.setData(successor.getData());
        node.rightChild = remove(node.rightChild, successor.getData());
        return node;
    }
    
    /**
     * Removes the node with the value data from this BST
     * @para data - value of the node to be removed from this BST
     */     
    public void remove(Integer data) {
        remove(root,data);
    }


    /**
     * Test if the Value data is stored in the BST
     * @param data - Element 
     * @return true if this BST contains the specific value
     */
    public boolean isElement(Integer data) {
        if (isEmpty()){
            return false;
        }
        return isElement(root, data);
    }

    public boolean isElement(BSTNode node, Integer data){
        if (node == null) {
            return false;
        }
        if (node.getData().equals(data)){
            return true;
        } else if (node.getData() > data){
            return isElement(node.leftChild, data);
        } else {
            return isElement(node.rightChild, data);
        }
    }
    

        
    private static void inOrderTraversal(BSTNode n, StringBuffer sb) {
        if(n != null) {
            inOrderTraversal(n.leftChild, sb);
            sb.append(n);
            sb.append(" ");
            inOrderTraversal(n.rightChild, sb);
        }
    }

    private static void postOrderTraversal(BSTNode n, StringBuffer sb) {
        if(n != null) {
            postOrderTraversal(n.leftChild, sb);
            postOrderTraversal(n.rightChild, sb);
            sb.append(n);
            sb.append(" ");
        }
    }

    private static void preOrderTraversal(BSTNode n, StringBuffer sb) {
        if(n != null) {
            sb.append(n);
            sb.append(" ");
            preOrderTraversal(n.leftChild, sb);
            preOrderTraversal(n.rightChild, sb);
         
        }
    }
    
    private String orderTraversal(Order o) {
         StringBuffer sb = new StringBuffer("[");
         switch(o) {
         case PRE:   preOrderTraversal(root, sb); break;
         case IN:    inOrderTraversal(root, sb); break;
         case POST:  postOrderTraversal(root, sb); break;
         }
         sb.append("]");
        return sb.toString();
    }
    
    public String preOrderTraversal()  {
        return orderTraversal(Order.PRE);
    }    

    public String inOrderTraversal()   {
        return orderTraversal(Order.IN);
    }
    
    public String postOrderTraversal() {
        return orderTraversal(Order.POST);
    }    

    /**
     * Returns a string representation of this BST. The string
     * representation consists of a list of this BST elements in
     * the order, enclosed in square brackets ("[]"). Adjacent
     * elements are separated by the characters ", " (comma and
     * space). 
     * @return a string representation of this BST
     */
    public String toString() { return inOrderTraversal();  }
  
}
