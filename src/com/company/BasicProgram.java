package com.company;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

class VariableNotBoundException extends EvalTimeException {
    public VariableNotBoundException(String name) { super(name); }
}

class NoSuchLineException extends EvalTimeException {
    public NoSuchLineException(String  n) { super(n); }
}
class ProgramLine {
    public String text;
    public BasicExpr expression;

    public ProgramLine(String t, BasicExpr e) {
        text = t;
        expression = e;
    }
}

class BasicBinding {
    public String name;
    public BasicVal value;
}

public class BasicProgram {
    private TreeMap<Integer, ProgramLine> lines = new TreeMap<Integer, ProgramLine>();
    private Integer currentLine;
    private HashMap<String, BasicVal> bindings = new HashMap<String, BasicVal>();
    public Scanner scanner = new Scanner(System.in);

    public void setVar(String name, BasicVal value) {
        bindings.put(name, value);
    }

    public BasicVal getVar(String name) throws VariableNotBoundException {
        BasicVal v = bindings.get(name);
        if(v == null) throw new VariableNotBoundException(name);
        return v;
    }

    public String toString() {
        String listing = "";
        if(lines.size() == 0)
            return listing;
        else {
            for(Integer k = lines.firstKey(); k != null; k = lines.higherKey(k)) {
                listing += (lines.get(k).text).concat("\n");
            }
            return listing;
        }
    }

    public void putLine(Integer n, String t, BasicExpr e) {
        lines.put(n, new ProgramLine(t, e));
    }

    public void clear() {
        lines.clear();
    }

    public void goTo(Integer n) {
        currentLine = n;
    }

    public void run() throws EvalTimeException {
        bindings = new HashMap<String, BasicVal>();
        currentLine = lines.firstKey();
        BasicExpr expr;
        do {
            if(lines.containsKey(currentLine)) {
                expr = lines.get(currentLine).expression;
            } else
                throw new NoSuchLineException(currentLine.toString());
            currentLine = lines.higherKey(currentLine);
            expr.eval(this);
        } while(currentLine != null);
    }
}
