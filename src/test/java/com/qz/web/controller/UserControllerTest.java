package com.qz.web.controller;


import static org.mockito.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc =MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenQuerySuccess() throws Exception{
		String result  = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.param("username", "yb")
				.param("age","18")
				.param("ageTo","60")
				.param("xxx","yyy")
//				.param("size", "12")
//				.param("page","3")
//				.param("sort", "age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenGenInfoSuccess() throws Exception{
		String result  =mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("yb"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
		
	}
	
	
	
	
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void whereCreateSuccess() throws  Exception {
		Date date=new Date();
		System.out.println(date.getTime());
		String content= "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenCreateFail() throws Exception {
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(reuslt);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content="{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";;
		String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
		
	}
	
	@Test
	public void whereDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
