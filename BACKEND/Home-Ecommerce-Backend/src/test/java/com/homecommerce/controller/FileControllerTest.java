package com.homecommerce.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;

import com.homecommerce.utils.StorageService;

public class FileControllerTest {

    @Test
    public void testDownload() throws Exception {
        String fileName = "test.txt";
        String fileContent = "Hello, world!";

        StorageService storageService = mock(StorageService.class);
        Resource resource = new ByteArrayResource(fileContent.getBytes());
        when(storageService.load(fileName)).thenReturn(resource);

        FileController controller = new FileController();
        controller.storageService = storageService;

        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletOutputStream outputStream = response.getOutputStream();

        InputStream inputStream = resource.getInputStream();
        FileCopyUtils.copy(inputStream, outputStream);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/" + fileName)).andExpect(status().isOk());
    }
}
