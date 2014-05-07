package tiger.tree;
abstract public class Stm {
	abstract public ExpList kids();
	abstract public Stm build(ExpList kids);
}
