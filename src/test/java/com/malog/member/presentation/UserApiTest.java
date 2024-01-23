package com.malog.member.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malog.member.presentation.request.UserLoginReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@DisplayName("User API")
@ExtendWith(MockitoExtension.class)
class UserApiTest {

    @InjectMocks
    private UserApi userApi;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
    }

    @Nested
    @DisplayName("로그인 API 테스트")
    class 로그인 {

        String LOGIN_URI = "/api/v1/auth/login";

        @ParameterizedTest
        @ValueSource(strings = {"randomtest", "@@#$^", "@."})
        @DisplayName("이메일 형식이 올바르지 않은 경우 400 에러 반환")
        void 이메일_형식이_틀린_경우(String arg) throws Exception {
            var request = new UserLoginReq(arg, "password");
            var actions = mockMvc.perform(post(LOGIN_URI).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)));

            actions.andExpect(status().isBadRequest());
        }
    }
}
