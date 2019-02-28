package com.yonyou.userdemo;

import com.yonyou.userdemo.controller.ContactController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserdemoApplicationTests {

    private MockMvc mvc;



    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new ContactController()).build();
    }

    @Test
    public void index() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/contact/failed").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(content().string(equalTo("{\"msg\":\"无效的url!\",\"status\":0}")));
    }

}

