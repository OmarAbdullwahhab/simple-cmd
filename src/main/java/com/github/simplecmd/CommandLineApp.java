package com.github.simplecmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class CommandLineApp {

    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private List<CommandLineOption> options;
    private CommandLineOptionListener listener;

    public CommandLineApp(CommandLineOptionListener listener, List<CommandLineOption> options) {
        this.options = options;
        this.listener = listener;
        if(this.options!=null){
            this.options.sort(new Comparator<CommandLineOption>() {
                @Override
                public int compare(CommandLineOption o1, CommandLineOption o2) {
                    return o1.getNumber() - o2.getNumber();
                }
            });
        }
    }

    /**
     * Used to print the options in the system output window (cmd in windows , or terminal in *nix systems).
     */
    private void printOptions() {
        if (this.options == null || this.options.size() <= 0) {
            throw new IllegalArgumentException("Command line options are null or empty");
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < options.size(); i++) {

            sb.append(options.get(i).getNumber()).append(" - ").append(options.get(i).getText());
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    /**
     * reads the option entered by the user
     * @return
     * @throws IOException
     */
    private int readOption() throws IOException {

        int option = Integer.parseInt(console.readLine());
        return option;


    }

    /**
     * used to start looping through the options provided by developer as List<CommandLineOption>
     */
    public void start()  {
        try {
        this.printOptions();
        final int opt = this.readOption();
        var option = this.options.stream().filter(x->x.getNumber() == opt).findFirst().get();
        while (!option.isEndOption()) {
            this.listener.optionSelected(new CommandLineOptionEvent(option));
            this.printOptions();
            final int opt2 = this.readOption();
            option  = this.options.stream().filter(x->x.getNumber() == opt2).findFirst().get();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
