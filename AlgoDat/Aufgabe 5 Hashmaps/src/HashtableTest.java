import java.util.Hashtable;

public class HashtableTest {
    private Hashtable<Integer, Integer> ht;
    private long delta;
    
    public HashtableTest() {
        ht = new  Hashtable<Integer, Integer>();
        setDelta();
    }
    
    public HashtableTest(int capacity) {
        ht = new  Hashtable<Integer, Integer>(capacity);
        setDelta();
    }
    
    private void setDelta() {
        long startTime = System.nanoTime();
        long endTime   = System.nanoTime();
        delta = (endTime-startTime);
    }
    
    public long getInsertTime(Integer x) {
        Integer s = x*x;
        long startTime = System.nanoTime();
        ht.put(s,x);
        long endTime = System.nanoTime();
        return (endTime - startTime - delta)/1000;
    }
                             
    
    public static void main(String[] args) {
        HashtableTest htt = new HashtableTest(999);

        for(int i=1; i<10000; i++) {
            long l = htt.getInsertTime(i);
            if (l>20) System.out.println(""+i +": " + l);
        }
    }
}
