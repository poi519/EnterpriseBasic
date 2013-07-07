package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lexer {
    public String text;
    public Integer position;
    static Pattern  patWSpace = Pattern.compile("^\\s+"),
                    patInt = Pattern.compile("^\\d+"),
                    patBool = Pattern.compile("^(false|true)"),
                    patPlus = Pattern.compile("^\\+"),
                    patMinus = Pattern.compile("^\\-"),
                    patTimes = Pattern.compile("^\\*"),
                    patLPar = Pattern.compile("^\\("),
                    patRPar = Pattern.compile("^\\)");

    public Lexer(String t, Integer pos) {
        text = t;
        position = pos;
    }

    public ArrayList<Lexeme> lex() {
        ArrayList<Lexeme> res = new ArrayList<Lexeme>();
        Matcher m;
        String rest;

        while(position < text.length()) {
            rest = text.substring(position);

            m = patWSpace.matcher(rest);
            if(m.find()) {
                position += m.end();
            } else {

            m = patInt.matcher(rest);
            if(m.find()) {
                res.add(new LexInt(m.group()));
                position += m.end();
            } else {

            m = patBool.matcher(rest);
            if(m.find()) {
                res.add(new LexBool(m.group()));
                position += m.end();
            } else {

            m = patPlus.matcher(rest);
            if(m.find()) {
                res.add(new LexPlus());
                position += m.end();
            } else {

            m = patMinus.matcher(rest);
            if(m.find()) {
                res.add(new LexMinus());
                position += m.end();
            } else {

            m = patTimes.matcher(rest);
            if(m.find()) {
                res.add(new LexTimes());
                position += m.end();
            } else {

            m = patLPar.matcher(rest);
            if(m.find()) {
                res.add(new LexLPar());
                position += m.end();
            } else {

            m = patRPar.matcher(rest);
            if(m.find()) {
                res.add(new LexRPar());
                position += m.end();
            }
            }
            }
            }
            }
            }
            }
            }
            }
        res.add(new LexEOS());
        return res;
    }
}