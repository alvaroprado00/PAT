package com.icai.examen.examen2021;

import com.icai.examen.examen2021.controller.SubscribeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.icai.examen.examen2021.Utils.asJsonString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionMockMvcE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void given_controller_when_call_endpoint_then_Ok() throws Exception {

        //Given
        SubscribeRequest request = new SubscribeRequest("demo@icai.es");

        //When
        //Then
        this.mockMvc
                .perform(post("/api/subscribe")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.message").isString());
    }
    
    @Test
    public void given_controller_when_call_endpoint_with_empty_content_then_ko() throws Exception {

        //Given
    	//Me construyo una peticion empty y compruebo que me da un status de bad request
        SubscribeRequest request = new SubscribeRequest("");

        //When
        //Then
        this.mockMvc
                .perform(post("/api/subscribe")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
    
    @Test
    public void given_controller_when_call_endpoint_with_null_content_then_ko() throws Exception {

        //Given
    	//Me construyo una peticion a null y compruebo que me da un status de bad request
        SubscribeRequest request = new SubscribeRequest(null);

        //When
        //Then
        this.mockMvc
                .perform(post("/api/subscribe")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
    
    @Test
    public void given_controller_when_call_endpoint_with_notValidRegex1_content_then_ko() throws Exception {

        //Given
    	//Me construyo una peticion con mail sin @ y compruebo que me da un status de bad request
        SubscribeRequest request = new SubscribeRequest("hola");

        //When
        //Then
        this.mockMvc
                .perform(post("/api/subscribe")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
    
    
    @Test
    public void given_controller_when_call_endpoint_with_notValidRegex2_content_then_ko() throws Exception {

        //Given
    	//Me construyo una peticion con mail sin .com y compruebo que me da un status de bad request
        SubscribeRequest request = new SubscribeRequest("hola@gmail");

        //When
        //Then
        this.mockMvc
                .perform(post("/api/subscribe")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}