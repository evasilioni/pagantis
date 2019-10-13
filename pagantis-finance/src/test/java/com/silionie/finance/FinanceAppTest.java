package com.silionie.finance;

import com.silionie.FinanceApp;
import com.silionie.model.Transfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		FinanceApp.class,
		FinanceAppTest.ITConfiguration.class
		},
		webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FinanceAppTest {

	@LocalServerPort
	protected Integer randomServerPort;

	@Autowired
	protected RestTemplate restTemplate;

	@Configuration
	public static class ITConfiguration {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}

	}
}
