package ru.fizteh.fivt.students.sautin1.shell;

import static ru.fizteh.fivt.students.sautin1.shell.CommandParser.convertArrayToString;

/**
 * Created by sautin1 on 9/30/14.
 */
public class ShellMain {

    public static void main(String[] args) {
        Shell shell = new Shell();
        if (args.length == 0) {
            // interactive mode
            shell.interactWithUser();
        } else {
            // non-interactive mode
            boolean success = shell.callCommands(convertArrayToString(args));
            if (!success) {
                System.exit(1);
            }
        }
        System.exit(0);
    }

}
