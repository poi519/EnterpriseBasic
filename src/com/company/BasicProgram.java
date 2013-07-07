package com.company;

import java.util.ArrayList;

class ProgramLine {
    public Integer number;
    public String text;
    public BasicExpr expr;
}

class BasicBinding {
    public String variable;
    public BasicVal value;
}

public class BasicProgram {
    public ArrayList<ProgramLine> lines;
    public Integer currentLine;
    public ArrayList<BasicBinding> bindings;

    public void goToNextLine() {
    //assume the line list is sorted

    }
}
