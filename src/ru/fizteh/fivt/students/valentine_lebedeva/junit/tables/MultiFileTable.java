package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.utils.Rm;

public final class MultiFileTable extends ATable {
    private static final int MAX_NUMBER_OF_DIRECTORIES = 16;
    private static final int MAX_NUMBER_OF_FILES = 16;
    private File directory;

    public MultiFileTable(final String path, final String name) throws IOException {
        super(name);
        directory = new File(path, name);
        if (directory.list().length != 0) {
            oldBase = this.read();
        }
        workBase = new HashMap<>(oldBase);
    }

    @Override
    public Map<String, String> read() throws IOException {
        Map<String, String> base = new HashMap<>();
        if (!base.isEmpty()) {
            base.clear();
        }
        if (directory.list().length != 0) {
            for (final File dir : directory.listFiles()) {
                for (final File file : dir.listFiles()) {
                    FileMapTable tmp = new FileMapTable(file.getAbsolutePath());
                    base.putAll(tmp.read());
                    tmp.closeFile();
                }
            }
        }
        return base;
    }

    public File getDirectory() {
        return directory;
    }

    @Override
    public int commit() {
        try {
            int changes = getChanges();
            boolean curDirCreated = false;
            ArrayList<Map<String, String>> keys = new ArrayList<>();
            for (int i = 0; i < MAX_NUMBER_OF_DIRECTORIES; i++) {
                curDirCreated = false;
                String directName = String.format("%d.dir", i);
                File curDir = new File(directory.getAbsolutePath(), directName);
                keys.clear();
                for (int j = 0; j < MAX_NUMBER_OF_FILES; j++) {
                    keys.add(new HashMap<>());
                }
                for (String key : workBase.keySet()) {
                    if (getNumberOfDirectory(key) == i) {
                        if (!curDirCreated) {
                            curDir.mkdir();
                        }
                        curDirCreated = true;
                        keys.get(getNumberOfFile(key)).put(key, workBase.get(key));
                    }
                }
                if (curDirCreated) {
                    for (int j = 0; j < MAX_NUMBER_OF_FILES; j++) {
                        if (!keys.get(j).isEmpty()) {
                            String fileName = String.format("%d.dat", j);
                            File curFile = new File(curDir.getAbsolutePath(), fileName);
                            FileMapTable tmp = new FileMapTable(curFile.getAbsolutePath());
                            tmp.setBase(keys.get(j));
                            tmp.close();
                        }
                    }
                }
                oldBase = new HashMap<>(workBase);
            }
            return changes;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public int getNumberOfDirectory(final String key) {
        int hashcode = key.hashCode();
        return hashcode % MAX_NUMBER_OF_DIRECTORIES;
    }

    public int getNumberOfFile(final String key) {
        int hashcode = key.hashCode();
        return hashcode / MAX_NUMBER_OF_DIRECTORIES % MAX_NUMBER_OF_FILES;
    }

    @Override
    public void close() throws IOException {
        this.delete();
        if (!directory.mkdir()) {
            throw new IOException("Creation is failed");
        }
        this.commit();
    }

    @Override
    public void delete() throws IOException {
        if (directory.list().length == 0) {
            Rm.rmNorm(directory.getAbsolutePath());
        } else {
            Rm.rmRec(directory.getAbsolutePath());
        }
    }
}
