package tiger.semant;

import tiger.translate.Exp;
import tiger.types.Type;

public class ExpTy {

    private Exp exp;
    private Type ty;

    public ExpTy(Exp e, Type t) {
        exp = e;
        ty = t;
    }
    public boolean isType(int t){
        return getTy().isType(t);
    }

    /**
     * @return the exp
     */
    public Exp getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(Exp exp) {
        this.exp = exp;
    }

    /**
     * @return the ty
     */
    public Type getTy() {
        return ty;
    }

    /**
     * @param ty the ty to set
     */
    public void setTy(Type ty) {
        this.ty = ty;
    }
}
