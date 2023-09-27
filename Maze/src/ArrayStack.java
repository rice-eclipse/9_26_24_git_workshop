import java.util.ArrayList;

public class ArrayStack<E> implements Stack<E>
{
  private ArrayList<E> data;
  
  //INSERT CODE BELOW
  
  public ArrayStack(ArrayList<E> list){
	  data = list;
  }
  
  public boolean isEmpty() {
	  return data.isEmpty();
  }
  
  public void push(E obj) {
	  data.add(obj);
  }
  
  public E pop() {
	  E temp = data.get(data.size()-1);
	  data.remove(temp);
	  return temp;
  }
  
  public E peek() {
	  return data.get(data.size()-1);
  }
}