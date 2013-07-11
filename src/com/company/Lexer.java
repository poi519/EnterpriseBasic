package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CannotLexException extends Exception {
    public CannotLexException() { super(); }
}

class Lexer {
    private String text;
    private Integer position;
    static Pattern  patWSpace = Pattern.compile("^\\s+"),
                    patInt  = Pattern.compile("^\\d+"),
                    patBool = Pattern.compile("^(false|true)"),
                    patPlus = Pattern.compile("^\\+"),
                    patMinus = Pattern.compile("^\\-"),
                    patTimes = Pattern.compile("^\\*"),
                    patLPar = Pattern.compile("^\\("),
                    patRPar = Pattern.compile("^\\)"),
                    patGT   = Pattern.compile("^>"),
                    patLT   = Pattern.compile("^<"),
                    patEq   = Pattern.compile("^="),
                    patLet  = Pattern.compile("^LET\\s"),
                    patPrint= Pattern.compile("^PRINT\\s"),
                    patInput= Pattern.compile("^INPUT\\s"),
                    patList = Pattern.compile("^LIST(\\s|$)"),
                    patRun = Pattern.compile("^RUN(\\s|$)"),
                    patGoto= Pattern.compile("^GOTO\\s+"),
                    patIf  = Pattern.compile("^IF\\s+"),
                    patThen= Pattern.compile("^THEN\\s+"),
                    patRem = Pattern.compile("^REM\\s(.+)"),
                    patVarName = Pattern.compile("^\\p{Alpha}\\p{Alnum}*");

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

            m = patWSpace.matcher(rest);
            if(m.find()) {
            //Do nothing
            } else {

            m = patList.matcher(rest);
            if(m.find()) {
                res.add(new LexList());
            } else {

            m = patRem.matcher(rest);
            if(m.find()) {
                res.add(new LexRem(m.group(1)));
            } else {

            m = patIf.matcher(rest);
            if(m.find()) {
                res.add(new LexIf());
            } else {

            m = patThen.matcher(rest);
            if(m.find()) {
                res.add(new LexThen());
            } else {

            m = patGoto.matcher(rest);
            if(m.find()) {
                res.add(new LexGoTo());
            } else {

            m = patRun.matcher(rest);
            if(m.find()) {
                res.add(new LexRun());
            } else {

            m = patLet.matcher(rest);
            if(m.find()) {
                res.add(new LexLet());
            } else {

            m = patPrint.matcher(rest);
            if(m.find()) {
                res.add(new LexPrint());
            } else {

            m = patInput.matcher(rest);
            if(m.find()) {
                res.add(new LexInput());
            }else {

            m = patInt.matcher(rest);
            if(m.find()) {
                res.add(new LexInt(m.group()));
            } else {

            m = patBool.matcher(rest);
            if(m.find()) {
                res.add(new LexBool(m.group()));
            } else {

            m = patPlus.matcher(rest);
            if(m.find()) {
                res.add(new LexPlus());
            } else {

            m = patMinus.matcher(rest);
            if(m.find()) {
                res.add(new LexMinus());
            } else {

            m = patTimes.matcher(rest);
            if(m.find()) {
                res.add(new LexTimes());
            } else {

            m = patLPar.matcher(rest);
            if(m.find()) {
                res.add(new LexLPar());
            } else {

            m = patRPar.matcher(rest);
            if(m.find()) {
                res.add(new LexRPar());
            } else {

            m = patGT.matcher(rest);
            if(m.find()) {
                res.add(new LexGT());
            } else {

            m = patLT.matcher(rest);
            if(m.find()) {
                res.add(new LexLT());
            } else {

            m = patEq.matcher(rest);
            if(m.find()) {
                res.add(new LexEq());
            } else {

            m = patVarName.matcher(rest);
            if(m.find()) {
                res.add(new LexVarName(m.group()));
            } else
                throw new CannotLexException();
            }}}}}}}}}}}}}}}}}}}}
            position += m.end();
        }
        res.add(new LexEOS());
        return res;
    }
}