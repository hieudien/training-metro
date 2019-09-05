package com.example.ControllerTest;

import com.example.Controllers.SearchAddressController;
import com.example.DTO.SearchByPostCodeRequest;
import com.example.DTO.SearchByPostCodeResponse;
import com.example.Service.SearchAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;

/**
 * Test for {@link com.example.Controllers.SearchAddressController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchAddressControllerTest {
    @Mock
    SearchAddressService searchAddressService;
    @InjectMocks
    SearchAddressController sut;
    private MockMvc mockMvc;

    private static final String POST_OFFICE_PATH = "/post_offices";

    /**
     * Setup
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void searchByPostCodeTest1() throws Exception {
        SearchByPostCodeRequest searchByPostCodeRequest = new SearchByPostCodeRequest();
        String postCode = "0370101";
        searchByPostCodeRequest.setPostCode(postCode);
        SearchByPostCodeResponse searchByPostCodeResponse = new SearchByPostCodeResponse();
        searchByPostCodeResponse.setCode("value");
        searchByPostCodeResponse.setCity("value");
        searchByPostCodeResponse.setCityKana("value");
        searchByPostCodeResponse.setPrefecture("value");
        searchByPostCodeResponse.setPrefectureKana("value");
        searchByPostCodeResponse.setPrefectureCode("value");
        searchByPostCodeResponse.setArea("value");
        searchByPostCodeResponse.setAreaKana("value");
        searchByPostCodeResponse.setMultiPostArea("value");
        searchByPostCodeResponse.setKoazaArea("value");
        searchByPostCodeResponse.setChomeArea("value");
        searchByPostCodeResponse.setOldPostCode("value");
        searchByPostCodeResponse.setPostCode(postCode);
        searchByPostCodeResponse.setMultiArea("value");
        searchByPostCodeResponse.setUpdateShow("value");
        searchByPostCodeResponse.setChangeReason("value");
        searchByPostCodeResponse.setPrefectureCode("value");
        Mockito.when(searchAddressService.searchByPostCode(searchByPostCodeRequest)).thenReturn(Arrays.asList(searchByPostCodeResponse));

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(searchByPostCodeRequest);
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + "/post/{postCode}", searchByPostCodeRequest.getPostCode()))
                .andExpect(MockMvcResultMatchers.status().isOk());

//        mockMvc.perform(MockMvcRequestBuilders
//                .get(POST_OFFICE_PATH + "/post/{postCode}", searchByPostCodeRequest.getPostCode())
//                .contentType("application/json")
//                .content(content))
//                .andExpect(jsonPath("post_code").value(
//                        is(searchByPostCodeResponse.getPostCode())))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


