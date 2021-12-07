package com.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.SampleCrudDockerApp;
import com.sample.TaskerRepository;
import com.sample.model.Tasker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest({TaskerCrudController.class})
public class TaskerCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskerRepository taskerRepository;


    @Test
    public void testGetAllTaskers() throws Exception {
        List<Tasker> taskers = new ArrayList<>();
        final Tasker tasker1 = new Tasker("Profit", LocalDate.now());
        final Tasker tasker2 = new Tasker("Learn React", LocalDate.now());
        taskers.add(tasker1);
        taskers.add(tasker2);
        Mockito.when(taskerRepository.findAll()).thenReturn(taskers);
        mockMvc.perform(get("/tasker").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        Mockito.verify(taskerRepository, Mockito.times(1)).findAll();
    }

   @Test
    public void testCreateTasker() throws Exception {
        final Tasker tasker1 = new Tasker("Profit", LocalDate.of(2021, 10, 5));
        Mockito.when(taskerRepository.findById("Profit")).thenReturn(Optional.of(tasker1));
        Mockito.when(taskerRepository.save(any())).thenReturn(tasker1);
        MvcResult result = mockMvc.perform(post("/tasker").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Profit\",\"date\":\"2021-10-05T08:53:29.459Z\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn();
        final String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content, "{\"name\":\"Profit\",\"date\":\"2021-10-05\"}");
    }

    @Test
    public void testPutExample() throws Exception {
        final Tasker tasker1 = new Tasker("Profit", LocalDate.of(2021, 10, 5));
        Mockito.when(taskerRepository.findById("Profit")).thenReturn(Optional.of(tasker1));
        Mockito.when(taskerRepository.save(any())).thenReturn(tasker1);
        MvcResult result = mockMvc.perform(put("/tasker/Profit").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Profit\",\"date\":\"2021-05-05T08:53:29.459Z\"}").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn();
        final String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content, "{\"name\":\"Profit\",\"date\":\"2021-05-05\"}");
    }

    @Test
    public void testDeleteExample() throws Exception {
        mockMvc.perform(delete("/tasker/Profit"))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
    }
}
