public class PriorityQueue <E extends Comparable <E>> implements Queue<E>{
    private Maxheap <E> maxheap = new Maxheap<>();
    @Override
    public int getSize(){
        return maxheap.getSize();
    }
    @Override
    public boolean isEmpty(){
        return maxheap.isEmpty();
    }

    @Override
    public void enqueue(E e){
        maxheap.add(e);
    }
    @Override
    public E dequeue(){
        return maxheap.extractMax();
    }
    @Override
    public E getFront(){
        return maxheap.findMax();
    }
}
