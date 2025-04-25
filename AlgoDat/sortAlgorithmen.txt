import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class SortUtils {
    private static Random random = null;
    
    /* Fisher-Yates Shuffle */
    public static void shuffle(int[] a) {
        if(random==null) {
            random = new Random();
        }
        for(int i=a.length-1; i>0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }   
    /**********************************************************/
    
    public static void swap(int[] a, int i, int j) {
	    int tmp =  a[i];
	    a[i] = a[j];
	    a[j] = tmp;
    }

    /**********************************************************/
    
    public static void bubbleSort(int[] a){ 
        int m = a.length - 1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < m - i; j++){
                if (a[j] > a[j+1]){
                    swap(a, j, j+1);
                }
            }
        }
    }
    // public static void insertionSort(int[] a){ /* TODO */}
    
    public  static void mergeSort(int[] a){
        
        int[] al = new int[a.length/2];
        int[] ar = new int[a.length - al.length];
        
        if (a.length > 1){
            for(int i = 0; i < a.length / 2; i++){
                al[i] = a[i];
            }
            for(int i = a.length/2, j = 0; i < a.length; i++, j++){
                ar[j] = a[i];
            }
            mergeSort(al);
            mergeSort(ar);
            merge(a, al, ar);
        }

    }

    private static void merge(int a[], int al[], int ar[]) {
        int x = 0;
        int y = 0;
        int z = 0;

        while(x < al.length && y < ar.length){
            if (al[x] <= ar[y]){
                a[z] = al[x];
                z++;
                x++;
            } else {
                a[z] = ar[y];
                z++;
                y++;
            }
        }

        while(x < al.length){
            a[z] = al[x];
            z++;
            x++;
        }

        while(y < ar.length){
            a[z] = ar[y];
            z++;
            y++;
        }
    }
    
    public  static void heapSort(int[] a){
        buildHeap(a);
        for (int i = a.length - 1; i > 0; i--){
            swap(a, 0, i);
            heapify(a, 0, i - 1);
        }
    }

    private static void buildHeap(int[] a) {
        for (int i = a.length - 1; i >= 0; i--){
            heapify(a, i ,a.length - 1);
        }
    }

    private static void heapify(int[] a, int i, int j) { 
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left <= j && a[left] > a[max]){
            max = left;
        }
        if (right <= j && a[right] > a[max]){
            max = right;
        }
        if (max != i){
            swap(a, i, max);
            heapify(a, max, j);
        }
    }   

    public static void main(String[] args ){
        int[] numbers = {10, 3, 5, 4, 8, 2, 9, 1, 6, 7};
        PrintWriter out = new PrintWriter(System.out);
        out.println(Arrays.toString(numbers));
        heapSort(numbers);
        out.println(Arrays.toString(numbers));
        out.flush();
    }
}    
