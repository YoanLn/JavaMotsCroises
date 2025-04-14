package pobj.csp;

import java.util.List;

public interface ICSP {
    List<IVariable> getVars();
    boolean isConsistent();
    ICSP assign(IVariable vi, String val);
}