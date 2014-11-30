package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.utils.Rm;

public final class FileMapTable extends ATable {
    private RandomAccessFile dbFile;
    private static final Charset CODING = StandardCharsets.UTF_8;

    public FileMapTable(final String fileName) throws IOException {
        super(fileName);
        dbFile = new RandomAccessFile(fileName, "rw");
        oldBase = this.read();
        workBase = new HashMap<>(oldBase);
    }

    @Override
    public Map<String, String> read() throws IOException {
        dbFile.seek(0);
        Map<String, String> base = new HashMap<>();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte symbol;
        int offset;
        String[] pair = new String[2];
        int j = 0;
        while (dbFile.length() - dbFile.getFilePointer() > 4) {
            offset = dbFile.readInt();
            dbFile.seek(dbFile.getFilePointer() + 2);
            for (int i = 0; i < offset; i++) {
                symbol = dbFile.readByte();
                buffer.write(symbol);
            }
            pair[j % 2] = buffer.toString("UTF-8");
            if (j % 2 == 1) {
                base.put(pair[0], pair[1]);
            }
            if (dbFile.length() - dbFile.getFilePointer() > 2) {
                dbFile.seek(dbFile.getFilePointer() + 2);
            }
            j++;
            buffer.reset();
        }
        return base;
    }

    @Override
    public int commit() {
        try {
            int changes = getChanges();
            dbFile.setLength(0);
            for (Entry<String, String> item : workBase.entrySet()) {
                writeItem(item.getKey());
                writeItem(item.getValue());
            }
            return changes;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void writeItem(final String arg) throws IOException {
        dbFile.writeInt(arg.getBytes(CODING).length);
        dbFile.writeChar(' ');
        dbFile.write(arg.getBytes(CODING));
        dbFile.writeChar(' ');
    }

    @Override
    public void close() throws IOException {
        this.commit();
        dbFile.close();
    }

    public void closeFile() throws IOException {
        dbFile.close();
    }

    @Override
    public void delete() throws IOException {
        Rm.rmNorm(name);
    }
}
