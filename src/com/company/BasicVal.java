package com.company;

abstract class BasicVal implements BasicExpr {
    public abstract String toString();

    public BasicVal eval(BasicProgram program) {
        return this;
    }
}

class NoValue extends BasicVal {
    public String toString() {
        return "No value";
    }
}

class IntValue extends BasicVal {
    public Integer value;

    public IntValue(Integer v) {
        value = v;
    }

    public String toString() {
        return value.toString();
    }
}
