public class Stack {
  private Element _top;

  // theoretically not needed, as size can be determined by traversing the linked list.
  private int _size = 0;

  public void push(Element e) {
    if (_top != null) {
      e.setNext(_top);
    }
    _top = e;
    _size++;
  }

  public Element top() {
    return _top;
  }

  public boolean pop() {
    if (isEmpty()) {
      return false;
    }
    _top = _top.getNext();
    _size--;
    return true;
  }
  public void print() {
    System.out.print("print - ");
    if (isEmpty()) {
      System.out.print("Stack is empty\n");
      return;
    }

    System.out.print("Stack contains: ");
    
    Element current = _top;
    while (current != null) {
      System.out.print(current.getValue() + ", ");
      current = current.getNext();
    }
    System.out.print("top Element = " + _top.getValue() + "\n");
  }

  public boolean isEmpty() {
    return _size == 0;
  }
  public int size() {
    return _size;
  }
}
