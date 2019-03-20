package com.codependent.mutualauth.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeRestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public String home() throws RestClientException, URISyntaxException, JsonProcessingException {
		return "success";
	}

	/*@GetMapping("/")
	public String home() throws RestClientException, URISyntaxException, JsonProcessingException {
		MobileTerminateRequest mobileTerminateRequest = new MobileTerminateRequest();
		mobileTerminateRequest.setCountryCode("+91");
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headers = getHttpHeaders();
		final String jsonRequest = mapper.writeValueAsString(mobileTerminateRequest);
		HttpEntity<String> request = getStringHttpEntity(headers, jsonRequest);
		*//*String forObject = restTemplate
				.getForObject(new URI("http://localhost:9090/notification/sms/v1/send"), String.class);*//*
		ResponseEntity<String> response = restTemplate.exchange("https://localhost:8449", HttpMethod.GET, request, String.class);
		//ResponseEntity<String> response = restTemplate.exchange("https://localhost:8444", HttpMethod.GET, request, String.class);
		//ResponseEntity<String> response = restTemplate.exchange("https://localhost:9090/notification/sms/v1/send", HttpMethod.POST, request, String.class);
		System.out.println(response.getBody().toString());
		return "success";
	}*/

	private HttpEntity<String> getStringHttpEntity(HttpHeaders headers, String jsonRequest) {
		return new HttpEntity<>(jsonRequest, headers);
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
