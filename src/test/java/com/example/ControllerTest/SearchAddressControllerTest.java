package com.example.ControllerTest;

import com.example.Controllers.SearchAddressController;
import com.example.DTO.SearchByPostCodeRequest;
import com.example.DTO.SearchByPostCodeResponse;
import com.example.DTO.SearchByPrefectureCodeRequest;
import com.example.DTO.SearchByPrefectureCodeResponse;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

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
    private static final String POST_PATH = "/post/{postCode}";
    private static final String PREFECTURES_PATH = "/prefectures/{prefectureCode}";

    /**
     * Setup
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    /**
     * Test search by post code success
     * Input:
     * Request with postCode that exists in DB
     * Output:
     * Response with mapped data and OK status
     *
     * @throws Exception
     */
    @Test
    public void searchByPostCodeTest1() throws Exception {
        // setup
        String postCode = "0370101";
        SearchByPostCodeRequest searchByPostCodeRequest = new SearchByPostCodeRequest();
        searchByPostCodeRequest.setPostCode(postCode);
        SearchByPostCodeResponse searchByPostCodeResponse = new SearchByPostCodeResponse();
        searchByPostCodeResponse.setPostCode(postCode);
        Mockito.when(searchAddressService.searchByPostCode(searchByPostCodeRequest)).thenReturn(Arrays.asList(searchByPostCodeResponse));

        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + POST_PATH, searchByPostCodeRequest.getPostCode()))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test search by post code fail, case request param is empty
     * Input:
     * Request param is empty
     * Output:
     * Response with error code 400 Bad Request Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPostCodeTest2() throws Exception {
        // setup
        String postCode = "";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + POST_PATH, postCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test search by post code fail, case request param is not a number
     * Input:
     * Request param is not a number
     * Output:
     * Response with error code 400 Bad Request Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPostCodeTest3() throws Exception {
        // setup
        String postCode = "postCode";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + POST_PATH, postCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test search by post code when there is no record be found
     * Input:
     * Request with postCode that not exists in DB
     * Output:
     * Response with code 404 Not Found Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPostCodeTest4() throws Exception {
        // setup
        String postCode = "0000";
        SearchByPostCodeRequest searchByPostCodeRequest = new SearchByPostCodeRequest();
        searchByPostCodeRequest.setPostCode(postCode);
        Mockito.when(searchAddressService.searchByPostCode(searchByPostCodeRequest)).thenReturn(null);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + POST_PATH, postCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test search by Prefecture Code success
     * Input:
     * Request with prefectureCode that exists in DB
     * Output:
     * Response with mapped data and OK status
     *
     * @throws Exception
     */
    @Test
    public void searchByPrefectureCodeTest1() throws Exception {
        // setup
        String prefectureCode = "10";
        SearchByPrefectureCodeRequest searchByPrefectureCodeRequest = new SearchByPrefectureCodeRequest();
        searchByPrefectureCodeRequest.setPrefectureCode(prefectureCode);
        SearchByPrefectureCodeResponse searchByPrefectureCodeResponse = new SearchByPrefectureCodeResponse();
        searchByPrefectureCodeResponse.setPrefectureCode(prefectureCode);
        Mockito.when(searchAddressService.searchByPrefectureCode(searchByPrefectureCodeRequest)).thenReturn(Arrays.asList(searchByPrefectureCodeResponse));
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + PREFECTURES_PATH, searchByPrefectureCodeRequest.getPrefectureCode()))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    /**
     * Test search by Prefecture Code fail, case request param is empty
     * Input:
     * Request param is empty
     * Output:
     * Response with error code 400 Bad Request Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPrefectureCodeTest2() throws Exception {
        // setup
        String prefectureCode = "";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + PREFECTURES_PATH, prefectureCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test search by prefecture Code fail, case request param is not a number
     * Input:
     * Request param is not a number
     * Output:
     * Response with error code 400 Bad Request Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPrefectureCodeTest3() throws Exception {
        // setup
        String prefectureCode = "prefectureCode";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + PREFECTURES_PATH, prefectureCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test search by prefecture Code when there is no record be found
     * Input:
     * Request with prefecture code that not exists in DB
     * Output:
     * Response with code 404 Not Found Status
     *
     * @throws Exception
     */
    @Test
    public void searchByPrefectureCodeTest4() throws Exception {
        // setup
        String prefectureCode = "99";
        SearchByPrefectureCodeRequest searchByPrefectureCodeRequest = new SearchByPrefectureCodeRequest();
        searchByPrefectureCodeRequest.setPrefectureCode(prefectureCode);
        Mockito.when(searchAddressService.searchByPrefectureCode(searchByPrefectureCodeRequest)).thenReturn(null);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(POST_OFFICE_PATH + PREFECTURES_PATH, prefectureCode))
                // verify
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}


