package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.utils.RemoveFile;

public class ProviderTest {
    private final File testDir = new File(System.getProperty("test.dir"), "providerTest");
    private final File testProviderDir = new File(testDir.getPath(), "test");

    @Before
    public void beforeTest() {
        testDir.mkdir();
    }

    @Test(expected = FileNotFoundException.class)
    public void testProviderDirectoryNotExists() throws IOException {
        new Provider(testProviderDir.getPath());
    }

    @Test
    public void testProviderDirectoryExists() throws IOException {
        testProviderDir.mkdir();
        new Provider(testProviderDir.getPath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProviderIsNotDirectory() throws IOException {
        File file = new File(testDir.getAbsolutePath(), "provider.dat");
        file.createNewFile();
        new Provider(file.getAbsolutePath());
    }

    @Test(expected = NotDirectoryException.class)
    public void testProviderIsNotSubdirectory() throws IOException {
        testProviderDir.mkdir();
        File file = new File(testProviderDir.getAbsolutePath(), "provider.dat");
        file.createNewFile();
        new Provider(testProviderDir.getAbsolutePath());
    }

    @Test
    public void testCreateTableNotExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("table"));
    }

    public void testCreateTableExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("table"));
        Assert.assertEquals(null, provider.createTable("table"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithMaxNumberOfTables() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        for (int i = 0; i <= provider.getMaxNumberOfTables(); i++) {
            provider.createTable("table" + i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithSeparator() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        provider.createTable("tab/le");
    }

    @Test
    public void testRemoveTableExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("table"));
        provider.removeTable("table");
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveTableNotExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        provider.removeTable("table");
    }

    @Test
    public void testCheckWorkTableNull() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertEquals(false, provider.checkWorkTable());
    }

    @Test
    public void testGetWorkTableName() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        provider.setWorkTable("name");
        Assert.assertNotEquals(null, provider.createTable("name"));
        Assert.assertNotEquals(null, provider.getWorkTable());
    }

    @Test
    public void testGetWorkTableNull() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertEquals(null, provider.getWorkTable());
    }

    @Test
    public void testCheckWorkTableName() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        provider.setWorkTable("name");
        Assert.assertEquals(true, provider.checkWorkTable());
    }

    @Test
    public void testGetTableExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("table"));
        Assert.assertNotEquals(null, provider.getTable("table"));
    }

    @Test
    public void testGetTableNotExists() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertEquals(null, provider.getTable("table"));
    }

    @Test
    public void testSizeTableIsEmpty() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertEquals(0, provider.size());
    }

    @Test
    public void testSizeTableIsNotEmpty() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        for (int i = 0; i < provider.getMaxNumberOfTables(); i++) {
            provider.createTable("table" + i);
        }
        Assert.assertEquals(provider.getMaxNumberOfTables(), provider.size());
    }

    @Test
    public void testClose() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("name"));
        provider.getTable("name").put("key", "value");
        provider.getTable("name").commit();
        provider.close();
    }

    @Test(expected = IllegalStateException.class)
    public void testCloseWithUnsavedChanges() throws IOException {
        testProviderDir.mkdir();
        Provider provider = new Provider(testProviderDir.getPath());
        Assert.assertNotEquals(null, provider.createTable("name"));
        provider.getTable("name").put("key", "value");
        provider.close();
    }

    @After
    public void afterTest() throws IOException {
        RemoveFile.rmRec(testDir.getAbsolutePath());
        testDir.mkdir();
    }

}
