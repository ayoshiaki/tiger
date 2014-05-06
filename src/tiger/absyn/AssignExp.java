package tiger.absyn;

public class AssignExp extends Exp {
   public Var var;
   public Exp exp;
   public AssignExp(Position p, Var v, Exp e) {setPosition(p); var=v; exp=e;}

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
