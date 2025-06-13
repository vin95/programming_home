public class BSTNode {
    private Integer data;
    public  BSTNode leftChild;
    public  BSTNode rightChild;


    public BSTNode(Integer data) {
        this.data = data;
    }

    public Integer getData() { return data; }

    public void setData(Integer data) { this.data = data; }

    public boolean isLeaf() {
        return (leftChild==null) && (rightChild == null);
    }

    public String toString() {
        return "("+  data.toString()  + ")";
    }
}


