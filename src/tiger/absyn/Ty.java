package tiger.absyn;

abstract public class Ty extends Absyn {
    @Override
    public abstract void accept(Visitor v);
}
