public class HashTable<E extends Comparable<E>>{
  //di esemplare
  private final int CAPACITY = 199;
  private Object[] a;
  private int size;
  //classe listnode interna
  private class ListNode{
    private E element;
    private ListNode next;
    public ListNode(E e, ListNode n){
      setElement(e);
      setNext(n);
    }
    public ListNode(){
      this(null,null);
    }
    public void setElement(E e){element = e;}
    public void setNext(ListNode n){next = n;}
    public E getElement(){return element;}
    public ListNode getNext(){return next;}
  }
  //costruttore vuoto
  public HashTable(){
    makeEmpty();
  }
  //makeEmpty
  private void makeEmpty(){
    a = new Object[CAPACITY];
    for(int i = 0; i < a.length; i++)
      a[i] = new ListNode();
    size = 0;
  }
  //isEmpty
  public boolean isEmpty(){
    return size <= 0;
  }
  //size
  public int size(){
    return size;
  }
  //Hash
  private int hash(E e){
    int in = e.hashCode() & a.length;
    if(in < 0)
      return -in;
    return in;
  }
  //search
  private ListNode search(E e){
    return (ListNode)a[hash(e)];
  }
  //add se non è già contenuto e se non è null
  public void add(E e){
    if(e == null || contains(e))
      throw new IllegalArgumentException();
    ListNode n = search(e);
    n.setNext(new ListNode(e, n.getNext()));
    size++;
  }
  //contains
  public boolean contains(E e){
    ListNode n = search(e).getNext();
    while(n != null){
      if(n.getElement().compareTo(e) == 0)
        return true;
      n = n.getNext();
    }
    return false;
  }
  //remove
  public void remove(E e){
    if(e == null || !contains(e) || isEmpty())
      throw new IllegalArgumentException();
    ListNode n = search(e);
    while(n.getNext() != null){
      if(n.getNext().getElement().compareTo(e) == 0){
        n.setNext(n.getNext().getNext());
        size--;
      }
      n = n.getNext();
    }
  }
  //elementSet ordinato
  public Comparable[] elementSet(){
    if(isEmpty())
      return new Comparable[0];
    Comparable[] ele = new Comparable[size];
    int j = 0;
    for(int i = 0; i < a.length; i++){
      ListNode n = (ListNode)a[i];
      while(n.getNext() != null){
        ele[j++] = n.getNext().getElement();
        n = n.getNext();
      }
    }
    sort(ele);
    return ele;
  }
  //sort by merge
  private static void sort(Comparable[] x){
    if(x.length < 2)
      return;
    int mid = x.length / 2;
    Comparable[] left = new Comparable[mid];
    Comparable[] right = new Comparable[x.length-mid];
    System.arraycopy(x, 0, left, 0, left.length);
    System.arraycopy(x, mid, right, 0, right.length);
    sort(left);
    sort(right);
    merge(x,left,right);
  }
  private static void merge(Comparable[] x, Comparable[] b, Comparable[] c){
    int ix = 0, ib = 0, ic = 0;
    while(ib < b.length && ic < c.length){
      if(b[ib].compareTo(c[ic]) < 0)
        x[ix++] = b[ib++];
      else
        x[ix++] = c[ic++];
    }
    while(ib < b.length)
      x[ix++] = b[ib++];
    while(ic < c.length)
      x[ix++] = c[ic++];
  }

}
