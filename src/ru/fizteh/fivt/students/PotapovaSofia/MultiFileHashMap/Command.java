package ru.fizteh.fivt.students.PotapovaSofia.MultiFileHashMap;

import java.util.Vector;

public interface Command {
    void execute(Vector<String> args, DataBase db);
}
