package tiger.semant;

abstract public class Entry {

    public static int FUNENTRY = 1, VARENTRY = 2;
    private tiger.translate.Access access;

    public abstract int getEntryType();

    /**
     * @return the access
     */
    public tiger.translate.Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(tiger.translate.Access access) {
        this.access = access;
    }
}
