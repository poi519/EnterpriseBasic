package com.company;

interface BasicExpr {
    public BasicVal eval(BasicProgram program);
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

    public BasicVal eval(BasicProgram program) {
        Integer x = (((IntValue) left.eval(program)).value >
                     ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class LTExpr extends BinaryExpr {
    public LTExpr(BasicExpr l, BasicExpr r) {
        super(l,r);
    }

    public BasicVal eval(BasicProgram program) {
        Integer x = (((IntValue) left.eval(program)).value <
                ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class EqExpr extends BinaryExpr {
    public EqExpr(BasicExpr l, BasicExpr r) {
        super(l,r);
    }

    public BasicVal eval(BasicProgram program) {
        Integer x = (((IntValue) left.eval(program)).value ==
                ((IntValue) right.eval(program)).value) ? -1 : 0;
        return new IntValue(x);
    }
}

class SumExpr extends BinaryExpr {
    public SumExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value +
                            ((IntValue) right.eval(program)).value);
    }
}

class DiffExpr extends BinaryExpr {
    public DiffExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value -
                ((IntValue) right.eval(program)).value);
    }
}

class ProductExpr extends BinaryExpr {
    public ProductExpr(BasicExpr l, BasicExpr r) {
        super(l, r);
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value *
                ((IntValue) right.eval(program)).value);
    }
}