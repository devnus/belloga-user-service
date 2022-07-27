package com.devnus.belloga.user.user.controller;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import com.devnus.belloga.user.user.domain.EnterpriseUser;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

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
        String accountId = "test-accountId";
        EnterpriseUser enterpriseUser = EnterpriseUser.builder()
                .accountId(accountId)
                .userRole(UserRole.ENTERPRISE)
                .organization("test-organization")
                .phoneNumber("01000000000")
                .name("test-name")
                .email("test@test.com")
                .id("test-user-id")
                .build();
        userRepository.save(enterpriseUser);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/users/accounts/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.userId").value("test-user-id"))
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
}