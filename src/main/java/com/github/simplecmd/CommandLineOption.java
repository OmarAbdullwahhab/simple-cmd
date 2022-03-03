package com.github.simplecmd;

public class CommandLineOption {

    private int number;
    private String text;
    private boolean endOption;

    public CommandLineOption(int number, String text, boolean isEndOption){
        this.number = number;
        this.text  = text;
        this.endOption = isEndOption;
    }

    public int getNumber() {
        return number;
    }


    public String getText() {
        return text;
    }

    public boolean isEndOption() {
        return endOption;
    }
}
