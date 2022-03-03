package com.zipbom.zipbom.Auth.unit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.zipbom.zipbom.Auth.controller.AuthController;

@SpringBootTest
public abstract class AbstractControllerTest {

	protected MockMvc mockMvc;

	abstract protected Object controller();

	@BeforeEach
	private void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller())
			.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
			.alwaysDo(print())
			.build();
	}

}