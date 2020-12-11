public class Main {
    public static void main(String[] args) {
        Heap heap=new Heap(8);
        heap.insert(10);
        heap.insert(1);
        heap.insert(3);
        heap.insert(5);
        heap.insert(2);
        heap.insert(8);
        heap.insert(11);
        //heap.insert(7);
        /*
        heap.displayHeap();
        heap.delete();
        heap.displayHeap();
        heap.delete();
        heap.displayHeap();*/
        heap.heapSort();
    }
}
