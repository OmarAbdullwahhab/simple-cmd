package com.github.simplecmd;

import java.util.ArrayList;
import java.util.List;

public class SimpleApplication {


    public static void main(String[] args){
        List<CommandLineOption> optionsList = new ArrayList<>();
        optionsList.add(new CommandLineOption(1,"Add Value", false));
        optionsList.add(new CommandLineOption(2,"Edit Value", false));
        optionsList.add(new CommandLineOption(7,"Delete Value", false));
        optionsList.add(new CommandLineOption(4,"Display Value", false));
        optionsList.add(new CommandLineOption(5,"Sub application", false));
        optionsList.add(new CommandLineOption(100,"Exit", true));
        var listener = new CommandLineOptionListener(){
            @Override
            public void optionSelected(CommandLineOptionEvent event) {
                var option  = event.getOption();
                switch (option.getNumber()){
                    case 1 -> System.out.println("Value is added");
                    case 2 -> System.out.println("Value is edited");
                    case 7 -> System.out.println("Value is deleted");
                    case 4 -> System.out.println("Value is displayed");
                    case 5 -> {
                        var subOptions = new ArrayList<CommandLineOption>();
                        subOptions.add(new CommandLineOption(1,"Sub app test", false));
                        subOptions.add(new CommandLineOption(2,"Sub app exit", true));
                        var subListener = new CommandLineOptionListener(){

                            @Override
                            public void optionSelected(CommandLineOptionEvent event) {
                                var subOption = event.getOption();
                                switch (subOption.getNumber()){
                                    case 1 -> System.out.println("Sub app test is selected");
                                }
                            }
                        };
                        CommandLineApp subApp = new CommandLineApp(subListener,subOptions);
                        subApp.start();
                    }
                }
            }
        };
        CommandLineApp app = new CommandLineApp(listener,optionsList);
        app.start();
    }
}
