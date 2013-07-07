package com.company;

abstract class BasicVal implements BasicExpr {
    public BasicVal eval(BasicProgram program) {
        return this;
    }
}

class NoValue extends BasicVal {
}

class IntValue extends BasicVal {
    public Integer value;

    public IntValue(Integer v) {
        value = v;
    }
}
