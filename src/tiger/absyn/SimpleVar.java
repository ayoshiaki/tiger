package tiger.absyn;

import tiger.semant.Entry;
import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.semant.VarEntry;
import tiger.symbol.Symbol;
import tiger.types.INT;

public class SimpleVar extends Var {

    public Symbol name;

    public SimpleVar(int p, Symbol n) {
        pos = p;
        name = n;
    }

    @Override
    public ExpTy transVar() {   
        Entry x = (Entry) Env.getEnv().getVenv().get(name);
        if(x != null && x.getEntryType() == Entry.VARENTRY){
            VarEntry ent = (VarEntry)x;
            return new ExpTy(null, ent.getTy());
        } else {
            System.err.println("Erro posicao " + pos + " variavel " +name +" nao definida !");
        }
        return new ExpTy(null, new INT());
    }
}
