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
                    patEq   = Pattern.compile("^=");

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
            } else
                throw new CannotLexException();
            }}}}}}}}}}
            position += m.end();
        }
        res.add(new LexEOS());
        return res;
    }
}