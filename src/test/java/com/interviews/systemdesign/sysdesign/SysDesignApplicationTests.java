package com.interviews.systemdesign.sysdesign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SysDesignApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Test
    void contextLoads() {
    }

}
