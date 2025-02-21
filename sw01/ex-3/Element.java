public class Element {
  private int _value;
  private Element _next;

  public Element(int value) {
    _value = value;
  }

  public int getValue() {
    return _value;
  }

  public Element getNext() {
    return _next;
  }

  public void setNext(Element next) {
    _next = next;
  }
}
