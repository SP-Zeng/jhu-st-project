package com.homecommerce.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;
import java.io.ByteArrayInputStream;


class DiskStorageServiceImplTest {

    private static DiskStorageServiceImpl storageService;
    private static final String TEST_FILE_NAME = "test-file.txt";

    @BeforeAll
    static void setUp() throws Exception {
        storageService = new DiskStorageServiceImpl();
    }

    @AfterAll
    static void tearDown() throws Exception {
        FileSystemUtils.deleteRecursively(new File("./src/server/uploads"));
    }

    @Test
    void testLoadAll() {
        List<String> files = storageService.loadAll();
        assertNotNull(files);
        assertEquals(0, files.size());
    }

    @Test
    void testStore() throws IOException {
        String content = "Test file content";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(TEST_FILE_NAME, inputStream);

        String fileName = storageService.store(multipartFile);

        assertNotNull(fileName);
        assertEquals(32 + TEST_FILE_NAME.length(), fileName.length());

        File file = new File("./src/server/uploads" + "/" + fileName);
        assertTrue(file.exists());
        assertTrue(file.isFile());

        FileInputStream fileInputStream = new FileInputStream(file);
        String storedContent = StreamUtils.copyToString(fileInputStream, null);

        assertEquals(content, storedContent);

        fileInputStream.close();
        file.delete();
    }

    @Test
    void testLoad() throws IOException {
        String content = "Test file content";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());


        MockMultipartFile multipartFile = new MockMultipartFile(TEST_FILE_NAME, inputStream);

        String fileName = storageService.store(multipartFile);

        Resource resource = storageService.load(fileName);

        assertNotNull(resource);
        assertTrue(resource.exists());
        assertTrue(resource.isReadable());

        InputStream resourceInputStream = resource.getInputStream();
        String storedContent = StreamUtils.copyToString(resourceInputStream, null);

        assertEquals(content, storedContent);

        resourceInputStream.close();
        //file.delete();
    }

    @Test
    void testDelete() throws IOException {
        String content = "Test file content";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(TEST_FILE_NAME, inputStream);

        String fileName = storageService.store(multipartFile);

        storageService.delete(fileName);

        File file = new File("./src/server/uploads" + "/" + fileName);
        assertTrue(!file.exists());
    }

}
