package com.company;

abstract class EvalTimeException extends Exception {
    public EvalTimeException() { super(); }
    public EvalTimeException(String s) { super(s); }
}

interface BasicExpr {
    public BasicVal eval(BasicProgram program) throws EvalTimeException;
}

class LabelledLine implements BasicExpr {
    public Integer number;
    public BasicExpr expression;

    public LabelledLine(Integer n, BasicExpr e) {
        number = n;
        expression = e;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        return  expression.eval(program);
    }
}

abstract class BinaryExpr implements BasicExpr {
    public BasicExpr left, right;

    public BinaryExpr(BasicExpr l, BasicExpr r) {
        left = l;
        right = r;
    }
}

class GTExpr extends BinaryExpr {
    public GTExpr(BasicExpr l, BasicExpr r) {
        super(l,r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        Integer x = (((IntValue) left.eval(program)).value >
                     ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class LTExpr extends BinaryExpr {
    public LTExpr(BasicExpr l, BasicExpr r) {
        super(l,r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        Integer x = (((IntValue) left.eval(program)).value <
                ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class EqExpr extends BinaryExpr {
    public EqExpr(BasicExpr l, BasicExpr r) {
        super(l,r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        Integer x = (((IntValue) left.eval(program)).value ==
                ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class SumExpr extends BinaryExpr {
    public SumExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        return new IntValue(((IntValue) left.eval(program)).value +
                            ((IntValue) right.eval(program)).value);
    }
}

class DiffExpr extends BinaryExpr {
    public DiffExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        return new IntValue(((IntValue) left.eval(program)).value -
                ((IntValue) right.eval(program)).value);
    }
}

class ProductExpr extends BinaryExpr {
    public ProductExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        return new IntValue(((IntValue) left.eval(program)).value *
                ((IntValue) right.eval(program)).value);
    }
}

class VarExpr implements BasicExpr {
    private String name;

    public VarExpr(String n) {
        name = n;
    }

    public String getName() {
       return name;
    }

    public BasicVal eval(BasicProgram program)
            throws VariableNotBoundException {
        return program.getVar(name);
    }
}

class LetExpr implements BasicExpr {
    private VarExpr variable;
    private BasicExpr expression;

    public LetExpr(VarExpr var, BasicExpr expr) {
        variable = var;
        expression = expr;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        program.setVar(variable.getName(), expression.eval(program));
        return new NoValue();
    }
}

class PrintExpr implements BasicExpr {
    private BasicExpr expression;

    public PrintExpr(BasicExpr expr) {
        expression = expr;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        System.out.println(expression.eval(program).toString());
        return new NoValue();
    }
}

class InputExpr implements BasicExpr {
    private VarExpr variable;

    public InputExpr(VarExpr v) {
        variable = v;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        System.out.print("?");
        program.setVar(variable.getName(),
                new IntValue(Integer.parseInt(program.scanner.nextLine())));
        return new NoValue();
    }
}

class ListExpr implements BasicExpr {
    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        System.out.println(program.toString());
        return new NoValue();
    }
}

class RunExpr implements BasicExpr {
    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        program.run();
        return new NoValue();
    }
}

class GoToExpr implements BasicExpr {
    private Integer lineNumber;

    public GoToExpr(Integer n) {
        lineNumber = n;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        program.goTo(lineNumber);
        return new NoValue();
    }
}

class IfExpr implements BasicExpr {
    private BasicExpr condition;
    private Integer lineNumber;

    public IfExpr(BasicExpr c, Integer n) {
        condition = c;
        lineNumber = n;
    }

    public BasicVal eval(BasicProgram program) throws EvalTimeException {
        if(condition.eval(program).toString().equals("-1"))
            program.goTo(lineNumber);
        return new NoValue();
    }
}
