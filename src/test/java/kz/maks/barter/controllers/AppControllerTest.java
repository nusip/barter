package kz.maks.barter.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void register() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/register" )
                .param("phoneNumber", "+77071231212"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("1")));
    }

}
