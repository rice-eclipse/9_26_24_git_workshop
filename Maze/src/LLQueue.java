public class LLQueue<E> implements Queue<E>
{
  private ListNode<E> first;
  private ListNode<E> last;
  
  //INSERT CODE BELOW
  
  public boolean isEmpty() {
	  return first==null;
  }
  
  public void enqueue(E obj) {
	  if(first==null) {
		  this.first = new ListNode<E>(obj, null);
		  last = first;
	  }else {
		  last.setNext(new ListNode<E>(obj, null));
		  this.last = last.getNext();
	  }
  }
  
  public E dequeue() {
	  E firstVal = first.getValue();
	  this.first = first.getNext();
	  return firstVal;
  }
  
  public E peek() {
	  return first.getValue();
  }
  
  
  
}