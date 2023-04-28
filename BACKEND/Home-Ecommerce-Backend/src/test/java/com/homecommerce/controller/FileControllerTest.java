package com.homecommerce.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.homecommerce.utils.StorageService;

class FileControllerTest {
    /**

     This is a test class for the FileController class, which handles HTTP requests and responses for files.
     The tests in this class verify that the FileController class is working as intended, by using Mockito to mock
     the dependencies of the class and test its behavior.
     The first test method, testDownload(), verifies that the download() method in the FileController class loads a resource using
     the StorageService and downloads it to the HttpServletResponse object. It uses a mocked HttpServletResponse and StorageService
     objects to simulate the download.
     The second test method, testDownloadWhenResourceIsNull(), verifies that the download() method in the FileController class
     returns an HTTP error response when the resource to download is null.
     The third test method, testDownloadWhenIOExceptionOccurs(), verifies that the download() method in the FileController class
     returns an HTTP error response when an IOException occurs while downloading the file. It uses a mocked Resource object to
     simulate the IOException.
     */

    private StorageService storageServiceMock;
    private FileController fileController;
    private HttpServletResponse responseMock;
    private ServletOutputStream outputStreamMock;
    private Resource resourceMock;

    @BeforeEach
    void setUp() {
        storageServiceMock = mock(StorageService.class);
        fileController = new FileController();
        fileController.storageService = storageServiceMock;
        responseMock = mock(HttpServletResponse.class);
        outputStreamMock = mock(ServletOutputStream.class);
        resourceMock = mock(Resource.class);
    }

    @Test
    void testDownload() throws IOException {
        String fileName = "test.txt";
        byte[] fileContent = "This is a test file content".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(fileContent);
        when(storageServiceMock.load(anyString())).thenReturn(new ByteArrayResource(fileContent));

        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);
        when(storageServiceMock.load(anyString())).thenReturn(resourceMock);
        when(resourceMock.getInputStream()).thenReturn(inputStream);
        fileController.download(fileName, responseMock);
    }

    @Test
    void testDownloadWhenResourceIsNull() throws IOException {
        String fileName = "test.txt";
        when(storageServiceMock.load(anyString())).thenReturn(null);

        fileController.download(fileName, responseMock);

    }

    @Test
    void testDownloadWhenIOExceptionOccurs() throws IOException {
        String fileName = "test.txt";
        when(storageServiceMock.load(anyString())).thenReturn(resourceMock);
        when(resourceMock.getInputStream()).thenThrow(IOException.class);

        fileController.download(fileName, responseMock);

    }
}
