package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.fizteh.fivt.storage.strings.Table;

public abstract class ATable implements Table {
    protected Map<String, String> workBase;
    protected Map<String, String> oldBase;
    protected String name;

    public ATable(String tableName) throws IOException {
        workBase = new HashMap<>();
        oldBase = new HashMap<>();
        name = tableName;
    }

    public abstract Map<String, String> read() throws IOException;

    public abstract void delete() throws IOException;

    @Override
    public abstract int commit();

    @Override
    public int rollback() {
        int changes = getChanges();
        workBase = new HashMap<>(oldBase);
        return changes;
    }

    public abstract void close() throws IOException;

    public Map<String, String> getWorkBase() {
        return workBase;
    }

    @Override
    public String put(final String key, final String value) {
        String res = workBase.get(key);
        workBase.put(key, value);
        return res;
    }

    @Override
    public String remove(final String key) {
        String res = get(key);
        workBase.remove(key);
        return res;
    }

    @Override
    public String get(final String key) {
        return workBase.get(key);
    }

    @Override
    public int size() {
        return workBase.size();
    }

    @Override
    public List<String> list() {
        List<String> res = new ArrayList<>();
        for (String key : workBase.keySet()) {
            res.add(key);
        }
        return res;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getChanges() {
        if (workBase == null || workBase.equals(oldBase)) {
            return 0;
        }
        int changes = 0;
        for (String key : workBase.keySet()) {
            if (!oldBase.containsKey(key) || !oldBase.get(key).equals(workBase.get(key))) {
                changes++;
            }
        }
        for (String key : oldBase.keySet()) {
            if (!workBase.containsKey(key)) {
                changes++;
            }
        }
        return changes;
    }

    public void setBase(Map<String, String> newBase) {
        workBase = new HashMap<String, String>(newBase);
    }

    public boolean containsKey(String key) {
        return workBase.containsKey(key);
    }
}
