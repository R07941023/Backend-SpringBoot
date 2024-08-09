package com.example.test;

import com.dev.template.MicroserviceDemoApplication;
import com.dev.template.controller.TemplateFunctionController;
import com.dev.template.dto.PersonRequest;
import com.dev.constant.CommConstant;
import com.dev.constant.URIConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest(classes = MicroserviceDemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemplateFunctionControllerTest {

    @Autowired
    private TemplateFunctionController templateFunctionController;

    private MockMvc mockMvc;
	private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(templateFunctionController)  // Replace with actual controller if needed
                .build();
		objectMapper = new ObjectMapper(); // inital ObjectMapper
    }

    @Test
    public void testTemplateGetPersonRequest() throws Exception {
		
		// header setting
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer token");
		
		// testing
		mockMvc.perform(MockMvcRequestBuilders.get(URIConstant.URI_GET_TEMPLATE_PERSONREQUEST)
			.headers(httpHeaders).accept(MediaType.APPLICATION_JSON))
			// .andDo(MockMvcResultHandlers.print())
			// .andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.retCode").value(CommConstant.RET_CD_SUCCESS))
			.andExpect(jsonPath("$.retDetail").value(CommConstant.SUCCESS))
			.andExpect(jsonPath("$.retObject[0]").exists());
    }

	@Test
    public void testTemplateCaulcuteBMI() throws Exception {
        
		// input setting
        PersonRequest personRequest = new PersonRequest();
        personRequest.setWeight(70);
        personRequest.setHeight(175);
		// data to json
        String personRequestJson = objectMapper.writeValueAsString(personRequest); 

        // header setting
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer token");

        // testing
        mockMvc.perform(MockMvcRequestBuilders.post(URIConstant.URI_POST_TEMPLATE_CALCULATEBMI)
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRequestJson))
				// .andDo(MockMvcResultHandlers.print())
				// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(CommConstant.RET_CD_SUCCESS))
                .andExpect(jsonPath("$.retDetail").value(CommConstant.SUCCESS))
                .andExpect(jsonPath("$.retObject[0].bmi").value(22.857142857142858));
    }

	@Test
    public void testTemplateUploads() throws Exception {
        // input
        MockMultipartFile file1 = new MockMultipartFile("files", "test1.txt", "text/plain", "sample file content".getBytes());
        String description = "Sample description";

        // testing
        mockMvc.perform(MockMvcRequestBuilders.multipart(URIConstant.URI_POST_TEMPLATE_UPLOADS)
                .file(file1)
                .param("description", description)
                .contentType(MediaType.MULTIPART_FORM_DATA))
				// .andDo(MockMvcResultHandlers.print())
				// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(CommConstant.RET_CD_SUCCESS))
                .andExpect(jsonPath("$.retDetail").value(CommConstant.SUCCESS))
                .andExpect(jsonPath("$.retObject[0]").value("test1.txt"));
    }

	@Test
    public void testTemplatePutData() throws Exception {
        
		// input setting
        PersonRequest personRequest = new PersonRequest();
        personRequest.setWeight(70);
        personRequest.setHeight(175);
		// data to json
        String personRequestJson = objectMapper.writeValueAsString(personRequest); 

        // header setting
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer token");

        // testing
        mockMvc.perform(MockMvcRequestBuilders.put(URIConstant.URI_PUT_TEMPLATE_DATA)
			.headers(httpHeaders)
			.contentType(MediaType.APPLICATION_JSON)
			.content(personRequestJson))
			// .andDo(MockMvcResultHandlers.print())
			// .andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.retCode").value(CommConstant.RET_CD_SUCCESS))
			.andExpect(jsonPath("$.retDetail").value(CommConstant.SUCCESS));
    }

	@Test
    public void testTemplateDeleteData() throws Exception {
        
		// input setting
        PersonRequest personRequest = new PersonRequest();
        personRequest.setWeight(70);
        personRequest.setHeight(175);
		// data to json
        String personRequestJson = objectMapper.writeValueAsString(personRequest); 

        // header setting
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer token");

        // testing
        mockMvc.perform(MockMvcRequestBuilders.delete(URIConstant.URI_DELETE_TEMPLATE_DATA)
			.headers(httpHeaders)
			.contentType(MediaType.APPLICATION_JSON)
			.content(personRequestJson))
			// .andDo(MockMvcResultHandlers.print())
			// .andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.retCode").value(CommConstant.RET_CD_SUCCESS))
			.andExpect(jsonPath("$.retDetail").value(CommConstant.SUCCESS));
    }
}
