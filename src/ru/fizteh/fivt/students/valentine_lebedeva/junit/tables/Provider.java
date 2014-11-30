package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.storage.strings.TableProvider;

public class Provider implements TableProvider {
    private String root;
    private String workTable;
    private static final int MAX_NUMBER_OF_TABLES = 16;
    private Map<String, MultiFileTable> tables;

    public Provider(String path) throws IOException {
        tables = new HashMap<>();
        File directory = new File(path);
        if (!directory.exists()) {
            throw new FileNotFoundException(directory.getName() + " " + "not exists");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getName() + " " + "is not directory");
        }

        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                throw new NotDirectoryException("Table should be directory");
            }
            MultiFileTable table = new MultiFileTable(directory.getAbsolutePath(), file.getName());
            tables.put(file.getName(), table);
        }
        root = path;
    }

    @Override
    public Table getTable(String name) {
        return tables.get(name);
    }

    public boolean containsKey(String name) {
        return tables.containsKey(name);
    }

    @Override
    public Table createTable(String name) {
        try {
            if (!tables.isEmpty() && tables.size() == MAX_NUMBER_OF_TABLES) {
                throw new IllegalArgumentException("Too many tables");
            }
            if (Paths.get(name).toString().contains(File.separator)) {
                throw new IllegalArgumentException("Table name contains separators");
            }
            if (!containsKey(name)) {
                File directory = new File(root, name);
                if (!directory.mkdir()) {
                    throw new IOException("Creation is failed");
                }
                MultiFileTable table = new MultiFileTable(root, name);
                tables.put(name, table);
                return table;
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void removeTable(String name) {
        try {
            if (!tables.containsKey(name)) {
                throw new IllegalStateException(name + " is not exists");
            }
            tables.get(name).delete();
            tables.remove(name);
            if (name.equals(workTable)) {
                workTable = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public boolean checkWorkTable() {
        if (workTable == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setWorkTable(String name) {
        workTable = name;
    }

    public MultiFileTable getWorkTable() {
        if (checkWorkTable()) {
            return tables.get(workTable);
        } else {
            return null;
        }
    }

    public int size() {
        if (!tables.isEmpty()) {
            return tables.size();
        } else {
            return 0;
        }
    }

    public Map<String, Integer> showTables() {
        Map<String, Integer> res = new HashMap<>();
        for (String key : tables.keySet()) {
            res.put(key, tables.get(key).size());
        }
        return res;
    }

    public void close() throws IOException {
        for (MultiFileTable table : tables.values()) {
            if (table.getChanges() != 0) {
                throw new IllegalStateException(table.getChanges() + " unsaved changes");
            }
            table.close();
        }
    }

    public int getMaxNumberOfTables() {
        return MAX_NUMBER_OF_TABLES;
    }
}
