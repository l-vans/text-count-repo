package com.lukeweb.textcount.textcountapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.net.http.HttpHeaders;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
@WebMvcTest(value = TextCountController.class)
class TextCountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TextCountService textCountService;	
	
	URL fileUrl = getClass().getResource("/data.txt");
	MediaType data1 =  File(fileUrl.getFile());

	URL fileUrl2 = getClass().getResource("/data.txt");
	MediaType data2 =  File(fileUrl.getFile());		
	
	@Test
	void testFiltertext() throws Exception {
			
			// studentService.addCourse to respond back with mockCourse
//			Mockito.when(
//					studentService.addCourse(Mockito.anyString(),
//							Mockito.any(Course.class))).thenReturn(mockCourse);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/counts")
					.accept(MediaType.MULTIPART_FORM_DATA_VALUE).contentType(data1)
					.contentType(MediaType.MULTIPART_FORM_DATA_VALUE);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			assertEquals(HttpStatus.CREATED.value(), response.getStatus());

//			assertEquals("http://localhost/students/Student1/courses/1",
//					response.getHeader(HttpHeaders.LOCATION));

	}

	private MediaType File(String file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAverageLength() {
		fail("Not yet implemented");
	}

	@Test
	void testNumberOfIndyWords() {
		fail("Not yet implemented");
	}

}
