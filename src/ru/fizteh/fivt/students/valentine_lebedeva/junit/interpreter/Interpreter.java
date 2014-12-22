package ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;

public abstract class Interpreter {
    public static final String PROMPT = "$ ";
    public static final String COMMAND_SEPARATOR = ";";
    protected Map<String, Command> commands;

    public Interpreter() {
        commands = new HashMap<>();
    }

    public final void interactive() throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            String cmd;
            String[] cmdargs;
            Object object = getObject();
            while (true) {
                System.out.print(PROMPT);
                cmd = input.nextLine();
                cmdargs = cmd.split(COMMAND_SEPARATOR);
                for (int i = 0; i < cmdargs.length; i++) {
                    interprete(cmdargs[i].trim(), false, object);
                }
            }
        }
    }

    public final void batch(final String[] args) throws Exception {
        String cmd;
        String[] cmdargs;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        builder.append(COMMAND_SEPARATOR + "exit");
        cmd = builder.toString();
        cmdargs = cmd.split(COMMAND_SEPARATOR);
        Object object = getObject();
        for (int i = 0; i < cmdargs.length; i++) {
            interprete(cmdargs[i].trim(), false, object);
        }
    }

    public final void interprete(final String cmdArgs, final Boolean isBatchMode, Object object) throws Exception {
        try {
            String[] parseArgs = cmdArgs.split(" ");
            if (commands.containsKey(parseArgs[0])) {
                commands.get(parseArgs[0]).execute(Arrays.copyOfRange(parseArgs, 1, parseArgs.length), object);
            } else {
                throw new IllegalArgumentException("Wrong command");
            }
        } catch (StopInterpretationException e) {
            throw new StopInterpretationException(e.getExitMode());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (isBatchMode) {
                throw new StopInterpretationException(1);
            }
        }
    }

    public abstract Object getObject() throws Exception;
}
