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
    void requestEnterpriseUserInfoTest() throws Exception {
        //given
        String accountId = "enterprise-account-id";

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/enterprise/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.phoneNumber").value("01000000001"))
                .andExpect(jsonPath("$.response.email").value("devnus1@devnus.com"))
                .andExpect(jsonPath("$.response.name").value("devnus1_name"))
                .andExpect(jsonPath("$.response.organization").value("devnus_organization"))
                .andDo(print())

                //docs
                .andDo(document("request-enterprise-user-info",
                        pathParameters(
                                parameterWithName("accountId").description("?????? Id (AccountId)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("?????? ?????? ??????"),
                                fieldWithPath("response.phoneNumber").description("????????? ?????? ??????"),
                                fieldWithPath("response.email").description("????????? ?????? ?????????"),
                                fieldWithPath("response.name").description("????????? ?????? ??????"),
                                fieldWithPath("response.organization").description("????????? ?????? ?????? ??????"),
                                fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                        )
                ))
                .andExpect(jsonPath("$.response.phoneNumber", is(notNullValue())))
                .andExpect(jsonPath("$.response.email", is(notNullValue())))
                .andExpect(jsonPath("$.response.name", is(notNullValue())))
                .andExpect(jsonPath("$.response.organization", is(notNullValue())));
    }

    @Test
    void requestLabelerUserInfoTest() throws Exception {
        //given
        String accountId = "labeler-account-id";

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/labelers/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.email").value("devnus2@devnus.com"))
                .andExpect(jsonPath("$.response.name").value("devnus2_name"))
                .andExpect(jsonPath("$.response.birthYear").value("2000"))
                .andDo(print())

                //docs
                .andDo(document("request-labeler-user-info",
                        pathParameters(
                                parameterWithName("accountId").description("?????? Id (AccountId)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("?????? ?????? ??????"),
                                fieldWithPath("response.email").description("????????? ?????? ?????????"),
                                fieldWithPath("response.name").description("????????? ?????? ??????"),
                                fieldWithPath("response.birthYear").description("????????? ?????? ????????????"),
                                fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                        )
                ))
                .andExpect(jsonPath("$.response.email", is(notNullValue())))
                .andExpect(jsonPath("$.response.name", is(notNullValue())))
                .andExpect(jsonPath("$.response.birthYear", is(notNullValue())));
    }

    @Test
    void getEnterpriseUserInfoTest() throws Exception {
        //given
        String accountId = "enterprise-account-id";

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/enterprise")
                .contentType(MediaType.APPLICATION_JSON)
                .header("enterprise-id",accountId)
        )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.phoneNumber").value("01000000001"))
                .andExpect(jsonPath("$.response.email").value("devnus1@devnus.com"))
                .andExpect(jsonPath("$.response.name").value("devnus1_name"))
                .andExpect(jsonPath("$.response.organization").value("devnus_organization"))
                .andDo(print())

                //docs
                .andDo(document("get-enterprise-user-info",
                        responseFields(
                                fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("?????? ?????? ??????"),
                                fieldWithPath("response.phoneNumber").description("????????? ?????? ??????"),
                                fieldWithPath("response.email").description("????????? ?????? ?????????"),
                                fieldWithPath("response.name").description("????????? ?????? ??????"),
                                fieldWithPath("response.organization").description("????????? ?????? ?????? ??????"),
                                fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                        )
                ))
                .andExpect(jsonPath("$.response.phoneNumber", is(notNullValue())))
                .andExpect(jsonPath("$.response.email", is(notNullValue())))
                .andExpect(jsonPath("$.response.name", is(notNullValue())))
                .andExpect(jsonPath("$.response.organization", is(notNullValue())));
    }

    @Test
    void getLabelerUserInfoTest() throws Exception {
        //given
        String accountId = "labeler-account-id";

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/user/v1/labeler")
                .contentType(MediaType.APPLICATION_JSON)
                .header("labeler-id",accountId)
        )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.email").value("devnus2@devnus.com"))
                .andExpect(jsonPath("$.response.name").value("devnus2_name"))
                .andExpect(jsonPath("$.response.birthYear").value("2000"))
                .andDo(print())

                //docs
                .andDo(document("get-labeler-user-info",
                        responseFields(
                                fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("?????? ?????? ??????"),
                                fieldWithPath("response.email").description("????????? ?????? ?????????"),
                                fieldWithPath("response.name").description("????????? ?????? ??????"),
                                fieldWithPath("response.birthYear").description("????????? ?????? ????????????"),
                                fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                        )
                ))
                .andExpect(jsonPath("$.response.email", is(notNullValue())))
                .andExpect(jsonPath("$.response.name", is(notNullValue())))
                .andExpect(jsonPath("$.response.birthYear", is(notNullValue())));
    }
}