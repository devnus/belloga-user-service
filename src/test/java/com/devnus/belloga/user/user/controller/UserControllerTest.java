package com.devnus.belloga.user.user.controller;

import com.devnus.belloga.user.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void requestUserInfoTest() throws Exception {

        //given
        String accountId = "account-id";

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/users/accounts/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.userId").value("user-id"))
                .andExpect(jsonPath("$.response.userRole").value("ENTERPRISE"))
                .andDo(print())

                //docs
                .andDo(document("request-user-info",
                        pathParameters(
                                parameterWithName("accountId").description("계정 Id (AccountId)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("정상 응답 여부"),
                                fieldWithPath("response.userId").description("요청한 유저 아이디"),
                                fieldWithPath("response.userRole").description("요청한 유저 역할"),
                                fieldWithPath("error").description("error 발생 시 에러 정보")
                        )
                ))
                .andExpect(jsonPath("$.response.userId", is(notNullValue())))
                .andExpect(jsonPath("$.response.userRole", is(notNullValue())));
    }

    @Test
    void registerUserTest() throws Exception {
        //give
        Map<String, String> input = new HashMap<>();
        input.put("accountId", "test2_accountId");
        input.put("userRole", "LABELER");
        input.put("email", "test2@test.com");
        input.put("name", "test2_name");
        input.put("phoneNumber","01000001234");
        input.put("birthYear","2000");

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/user/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
                )
                //then
                .andExpect(status().isOk())
                .andDo(print())

                //docs
                .andDo(document("register-user",
                        requestFields(
                                fieldWithPath("accountId").description("회원가입 하는 사용자의 accountId"),
                                fieldWithPath("userRole").description("회원가입 하는 사용자의 userRole"),
                                fieldWithPath("email").description("회원가입 하는 사용자의 email"),
                                fieldWithPath("name").description("회원가입 하는 사용자의 name"),
                                fieldWithPath("phoneNumber").description("회원가입 하는 사용자의 phoneNumber"),
                                fieldWithPath("birthYear").description("회원가입 하는 사용자의 birthYear")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("정상 응답 여부"),
                                fieldWithPath("response.userRole").description("회원가입 성공한 사용자의 역할"),
                                fieldWithPath("response.userId").description("회원가입 성공한 사용자의 UserId"),
                                fieldWithPath("error").description("error 발생 시 에러 정보")
                        )
                ))
                .andExpect(jsonPath("$.response.userId", is(notNullValue())))
                .andExpect(jsonPath("$.response.userRole", is(notNullValue())));
    }
}