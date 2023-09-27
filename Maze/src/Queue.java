public interface Queue<E>
{
  boolean isEmpty();
  
  void enqueue(E obj);
  
  E dequeue();
  
  E peek();
}
