package tiger.translate;

public class AccessList {
  public Access head;
  public AccessList tail;
  AccessList(Access h, AccessList t) {
    head = h;
    tail = t;
  }
}
