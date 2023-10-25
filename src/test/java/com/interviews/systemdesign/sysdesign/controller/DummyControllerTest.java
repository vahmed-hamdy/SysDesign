package com.interviews.systemdesign.sysdesign.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviews.systemdesign.sysdesign.service.DummyService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@TestPropertySource(
        locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class DummyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired
    private DummyService dummyService;

    @Test
    @Disabled
    @DisplayName("Some Dummy test for a dummy proj")
    void test() throws Exception {
        mockMvc.perform(get("/dummy/malk"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", equalTo(1)));
    }

}
