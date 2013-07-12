package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CannotLexException extends Exception {
    public CannotLexException() { super(); }
}

interface Rule<L extends Lexeme> {
    public Pattern pattern();
    public L lexeme(Matcher m);
}

class Lexer {
    private String text;
    private Integer position;
    static Rule<?>[] rules = {
            new Rule<LexInt>() {
                public Pattern pattern() {return Pattern.compile("^\\d+");}
                public LexInt lexeme(Matcher m) {return new LexInt(m.group());}
            },
            new Rule<LexBool>() {
                public Pattern pattern() {return Pattern.compile("^(false|true)");}
                public LexBool lexeme(Matcher m) {return new LexBool(m.group());}
            },
            new Rule<LexPlus> (){
                public Pattern pattern() {return Pattern.compile("^\\+");}
                public LexPlus lexeme(Matcher m) {return new LexPlus();}
            },
            new Rule<LexMinus> (){
                public Pattern pattern (){return Pattern.compile("^\\-");}
                public LexMinus lexeme (Matcher m){return new LexMinus();}
            },
            new Rule<LexTimes> (){
                public Pattern pattern (){return Pattern.compile("^\\*");}
                public LexTimes lexeme (Matcher m){return new LexTimes();}
            },
            new Rule<LexLPar> (){
                public Pattern pattern(){return Pattern.compile("^\\(");}
                public LexLPar lexeme(Matcher m){return new LexLPar();}
            },
            new Rule<LexRPar> (){
                public Pattern pattern (){return Pattern.compile("^\\)");}
                public LexRPar lexeme (Matcher m){return new LexRPar();}
            },
            new Rule<LexGT> (){
                public Pattern pattern(){return Pattern.compile("^>");}
                public LexGT lexeme(Matcher m){return new LexGT();}
            },
            new Rule<LexLT> (){
                public Pattern pattern (){return Pattern.compile("^<");}
                public LexLT lexeme (Matcher m){return new LexLT();}
            },
            new Rule<LexEq> (){
                public Pattern pattern(){return Pattern.compile("^=");}
                public LexEq lexeme (Matcher m){return new LexEq();}
            },
            new Rule<LexLet> (){
                public Pattern pattern (){return Pattern.compile("^LET\\s");}
                public LexLet lexeme(Matcher m){return new LexLet();}
            },
            new Rule<LexPrint> (){
                public Pattern pattern (){return Pattern.compile("^PRINT\\s");}
                public LexPrint lexeme (Matcher m){return new LexPrint();}
            },
            new Rule<LexInput> (){
                public Pattern pattern (){return Pattern.compile("^INPUT\\s");}
                public LexInput lexeme (Matcher m){return new LexInput();}
            },
            new Rule<LexList> (){
                public Pattern pattern (){return Pattern.compile("^LIST(\\s|$)");}
                public LexList lexeme (Matcher m){return new LexList();}
            },
            new Rule<LexRun> (){
                public Pattern pattern (){return Pattern.compile("^RUN(\\s|$)");}
                public LexRun lexeme (Matcher m){return new LexRun();}
            },
            new Rule<LexGoTo> (){
                public Pattern pattern (){return Pattern.compile("^GOTO\\s+");}
                public LexGoTo lexeme (Matcher m){return new LexGoTo();}
            },
            new Rule<LexIf> (){
                public Pattern pattern (){return Pattern.compile("^IF\\s+");}
                public LexIf lexeme (Matcher m){return new LexIf();}
            },
            new Rule<LexThen> (){
                public Pattern pattern (){return Pattern.compile("^THEN\\s+");}
                public LexThen lexeme (Matcher m){return new LexThen();}
            },
            new Rule<LexRem> (){
                public Pattern pattern (){return Pattern.compile("^REM\\s(.+)");}
                public LexRem lexeme(Matcher m) {return new LexRem(m.group(1));}
            },
            new Rule<LexVarName> (){
                public Pattern pattern (){return Pattern.compile("^\\p{Alpha}\\p{Alnum}*") ;}
                public LexVarName lexeme (Matcher m){return new LexVarName(m.group());}
            }
    };

    public Lexer(String t) {
        text = t;
        position = 0;
    }

    public ArrayList<Lexeme> lex() throws CannotLexException {
        ArrayList<Lexeme> res = new ArrayList<Lexeme>();
        Matcher m;
        String rest;

        while(position < text.length()) {
            rest = text.substring(position);
            m = Pattern.compile("^\\s+").matcher(rest);
            if(m.find()) {
            //Do nothing
            } else {
                for(Rule<?> rule : rules) {
                    m = rule.pattern().matcher(rest);
                    if(m.find()) {
                        res.add(rule.lexeme(m));
                        break;
                    }
                }
            }
            position += m.end();
        }
        res.add(new LexEOS());
        return res;
    }
}