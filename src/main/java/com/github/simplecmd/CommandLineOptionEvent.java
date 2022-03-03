package com.github.simplecmd;

public class CommandLineOptionEvent {

    private CommandLineOption option;

    public CommandLineOptionEvent(CommandLineOption option) {
        this.option = option;
    }

    public CommandLineOption getOption() {
        return option;
    }


}
