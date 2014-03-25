package tiger.semant;

import tiger.types.Type;

public class VarEntry extends Entry {
    private Type ty;
    public VarEntry(Type t) {
        ty = t;
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

    @Override
    public int getEntryType() {
        return Entry.VARENTRY;
    }
    
}
