import java.awt.*;
import java.util.Random;

public class Maxheap <E extends Comparable <E>>{
    private Array <E> data;
    public Maxheap(int capacity){
        data = new Array<>(capacity);
    }
    public Maxheap(E[] arr){
        data = new Array<>(arr);
        // heapify
        for(int i = parent(arr.length-1); i>=0;i--)
            siftDown(i);
    }
    public Maxheap(){
        data = new Array<>();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    public int getSize(){
        return data.getSize();
    }
    private int parent(int index){
        if(index==0)
            throw new IllegalArgumentException("index 0 no parent");
        return (index-1)/2;
    }
    private int leftChild(int index){
        return (index*2+1);
    }
    private int rightChild(int index){
        return (index*2+2);
    }
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }
    private void siftUp(int k){
        while (k>0 && data.get(parent(k)).compareTo(data.get(k))<0 ){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }
    public E findMax(){
        return data.get(0);
    }
    public E extractMax(){
        E res = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return res;
    }
    public E replace (E e){
        E res = findMax();
        data.set(0,e);
        siftDown(0);
        return res;
    }

    private void siftDown(int k){
        while (leftChild(k)<data.getSize()){
            int j = leftChild(k);
            if(j+1 < data.getSize() && data.get(j+1).compareTo(data.get(j))>0) // right child > left child
                j= rightChild(k);
           if(data.get(k).compareTo(data.get(j))>0)  //data[k] > data[j] > data[i]
               break;
           data.swap(k,j);
           k = j;
        }

    }
    @Override
    public String toString(){
        return data.toString();
    }
    private static double testHeap( Maxheap<Integer> dut, int opCount, boolean heapify) {
        Random random = new Random();
        Integer [] arr = new Integer[opCount];
        for (int i = 0; i < opCount; i++)
            arr[i] = random.nextInt(Integer.MAX_VALUE);

        long startTime = System.nanoTime();
        if(heapify){
            dut = new Maxheap<Integer>(arr);
//            System.out.println("verify max: "+dut.findMax());
//            System.out.println("verify heap: "+dut);
        }
        else{
            dut = new Maxheap<Integer>();
            for (int i = 0; i < arr.length; i++) {
                dut.add(arr[i]);
            }
//            System.out.println("verify max: "+dut.findMax());
//            System.out.println("verify heap: "+dut);
        }
        long endTime =System.nanoTime();
        return (endTime - startTime) /1000000000.0;
    }
    private static double debugHeap( Maxheap<Integer> dut, int opCount, boolean debug) {
        long startTime = System.nanoTime();
        Random random = new Random();
        if (debug) System.out.println("test start ->" + startTime / 1000000000.0);
        for (int i = 0; i < opCount; i++) {
            if (debug && i % opCount / 30 == 0) System.out.print(".");
            //dut.add(random.nextInt(Integer.MAX_VALUE));
            dut.add(i);
            System.out.println("data: "+dut);
        }
        System.out.println("added");
        System.out.println("data: "+dut);

        Array<Integer> arr = new Array<>(opCount);
        for (int i = 0; i < opCount; i++) {
            if (debug && i % opCount / 30 == 0) System.out.print(".");
            arr.addLast(dut.extractMax());
        }
        System.out.println("extracted");
        System.out.println("data: "+arr);

        for (int i = 1; i < opCount; i++){
            if (arr.get(i - 1) < arr.get(i)) {
                System.out.println("error");
            } else
                if (debug && i % opCount / 30 == 0) System.out.print(".");
         }
        System.out.println("checked");

        long endTime =System.nanoTime();
        if(debug) System.out.println("\ntest ended ->" +endTime/1000000000.0);

        return (endTime - startTime) /1000000000.0;
    }
    public static void main(String[] args) {
        // write your code here
        Maxheap<Integer> maxheap = new Maxheap<>();
        double time=0;
        int opCount = 10000000;
        time = testHeap(maxheap,opCount,true);
        System.out.println("Test heapify completed: "+time+ "s");
        time = testHeap(maxheap,opCount,false);
        System.out.println("Test completed: "+time+ "s");


//        time = debugHeap(maxheap,5,true);
//        System.out.println("Test completed: "+time+ "s");
    }
}
