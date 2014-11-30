package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.IOException;

import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.storage.strings.TableProviderFactory;

public class ProviderFactory implements TableProviderFactory {
    @Override
    public TableProvider create(String dir) {
        try {
            TableProvider tables = new Provider(dir);
            return tables;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
