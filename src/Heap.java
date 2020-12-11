public class Heap {
    private int capacity;
    private Node[] heapArray;
    private int size;

    public Heap(int capacity){
        this.capacity=capacity;
        heapArray=new Node[capacity];
        size=0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean isFull(){
        return size==capacity;
    }

    public Node delete(){
        if(isEmpty()){
            return null;
        }
        Node temp=new Node(heapArray[0].getData());
        heapArray[0]=new Node(heapArray[size-1].getData());
        heapArray[size-1]=null;
        size--;
        trickleDown(0);
        return temp;
    }

    public void trickleDown(int index){
        int currentIndex=index;

        while(true){
            int leftChild= currentIndex*2+1;
            int rightChild= currentIndex*2+2;

            if(leftChild>=size && rightChild>=size){//leaf
                break;
            }

            if(rightChild>=size && leftChild<size){ //only left child
                if(heapArray[currentIndex].getData()>heapArray[leftChild].getData()){
                    swap(currentIndex,leftChild);
                    currentIndex=leftChild;
                }else{
                    break;
                }
            }else{ //have two children
                if(heapArray[currentIndex].getData()<heapArray[leftChild].getData()
                && heapArray[currentIndex].getData()<heapArray[rightChild].getData()){
                    break;
                }
                //if left child < right child
                if(heapArray[leftChild].getData()<heapArray[rightChild].getData()){
                    swap(currentIndex,leftChild);
                    currentIndex=leftChild;
                }else{
                    swap(currentIndex,rightChild);
                    currentIndex=rightChild;
                }
            }
        }
    }

    public boolean insert(int data){
        Node newNode=new Node(data);
        if(size>=capacity){
            System.out.println("The heap is full!");
            return false;
        }
        heapArray[size]=newNode;
        size++;
        //if the new node is smaller than its parent, we need to trickle down
        if(newNode.getData()<heapArray[(size-2)/2].getData()){
            trickleUp(size-1);
        }
        return true;
    }

    public void trickleUp(int index){
        int currentIndex=index;

        while(true){
            int parentIndex=(currentIndex-1)/2;

            if(currentIndex==0){ //reach the root
                break;
            }

            //if the node is less than its parent, swap them
            if(heapArray[currentIndex].getData()<heapArray[parentIndex].getData()){
                swap(currentIndex,parentIndex);
                currentIndex=parentIndex;
            }else{ //if larger, finished
                break;
            }
        }
    }

    public boolean change(int index, int newValue){
        if(index<0 || index>=size){
            System.out.println("Please input a valid index!");
            return false;
        }

        int oldValue=heapArray[index].getData();
        heapArray[index].setData(newValue);

        if(newValue>oldValue){
            trickleDown(index);
        }else{
            trickleUp(index);
        }
        return true;
    }

    public void swap(int index1, int index2){
        Node temp=new Node(heapArray[index1].getData());
        heapArray[index1]=new Node(heapArray[index2].getData());
        heapArray[index2]=temp;
    }
    public void displayHeap()
    {
        System.out.print("heapArray: ");    // array format
        for(int i=0; i<size; i++)
            if(heapArray[i] != null)
                System.out.print( heapArray[i].getData() + " ");
            else
                System.out.print( "-- ");
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        String dots = "...............................";
        System.out.println(dots+dots);      // dotted top line

        while(size > 0)              // for each heap item
        {
            if(column == 0)                  // first item in row?
                for(int k=0; k<nBlanks; k++)  // preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(heapArray[j].getData());

            if(++j == size)           // done?
                break;

            if(++column==itemsPerRow)        // end of row?
            {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            }
            else                             // next item on row
                for(int k=0; k<nBlanks*2-2; k++)
                    System.out.print(' ');     // interim blanks
        }  // end for
        System.out.println("\n"+dots+dots); // dotted bottom line
    }  // end displayHeap()

    public void heapSort(){
        Heap heap=new Heap(this.capacity);
        heap.size=size;
        //clone a new heap
        for(int i=0; i<size; i++){
            heap.heapArray[i]=new Node(this.heapArray[i].getData());
        }
        while(!heap.isEmpty()){
            System.out.print(heap.delete().getData()+" ");
        }
        System.out.println();
    }

}
