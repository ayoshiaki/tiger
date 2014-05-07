package tiger.translate;

import tiger.frame.Frame;
import tiger.mips.MipsFrame;
import tiger.temp.Label;
import tiger.util.BoolList;

public class Level {
  Level parent;
  tiger.frame.Frame frame;		// not public!
  public tiger.translate.AccessList formals;
  public tiger.translate.AccessList frameFormals;
  public Level(Level parent, Label name, BoolList formals) {
    this(parent, name, formals, false);
  }
  public Level(Level parent, Label name, BoolList formals, boolean leaf) {
    this.parent = parent;
    //is.frame = parent.frame.newFrame(name, new BoolList(!leaf, formals));
	Label label;
	if (name == null)
		label = new Label();
	else if (parent.frame.name != null)
		label = new Label(parent.frame.name + "." + name);
	else
		label = name;
    this.frame=new MipsFrame(label, new BoolList(!leaf,formals));
    frameFormals = allocFormals(this.frame.formals);
    this.formals = frameFormals.tail;
  }
  private tiger.translate.AccessList allocFormals(tiger.frame.AccessList formals) {
    if (formals == null)
      return null;
    return new tiger.translate.AccessList(new tiger.translate.Access(this, formals.head),
			  allocFormals(formals.tail));
  }

  public Level(Frame f) {
    frame = f;
  }

  public Label name() {
    return frame.name;
  }

  public tiger.translate.Access allocLocal(boolean escape) {
    return new tiger.translate.Access(this, frame.allocLocal(escape));
  }
}
