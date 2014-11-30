package ru.fizteh.fivt.students.valentine_lebedeva.junit.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public final class Rm {
    public static void rmRec(final String arg) throws IOException {
        File f = new File(arg);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                rmRec(files[i].getPath());
            } else {
                rmNorm(files[i].getPath());
            }
        }
        rmNorm(f.getPath());
    }

    public static void rmNorm(final String arg) throws IOException {
        File f = new File(arg);
        if (!f.exists()) {
            throw new NoSuchFileException("No such file or directory");
        }
        if (!f.delete()) {
            throw new IOException("Incorrect delete");
        }
    }

    private Rm() {
    }
}
