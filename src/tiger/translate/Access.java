package tiger.translate;

public class Access {
  Level home;
  tiger.frame.Access acc;
  Access(Level h, tiger.frame.Access a) {
    home = h;
    acc = a;
  }
  public String toString() {
    return "[" + home.frame.name.toString() + "," + acc.toString() + "]";
  }
}
