package com.example.Service;

import com.example.DTO.SearchByPostCodeRequest;
import com.example.DTO.SearchByPostCodeResponse;
import com.example.DTO.SearchByPrefectureCodeRequest;
import com.example.DTO.SearchByPrefectureCodeResponse;
import com.example.TrainingSpringbootApplication;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Search Address Service
 */
@Service
public class SearchAddressService {

    /**
     * search address By Post Code
     *
     * @param searchByPostCodeRequest post code to search
     * @return list address
     */
    public List<SearchByPostCodeResponse> searchByPostCode(SearchByPostCodeRequest searchByPostCodeRequest) {
        List<SearchByPostCodeResponse> responsesList = new ArrayList<>();
        try {
            // search address from DB by post code
            PreparedStatement stmt = TrainingSpringbootApplication.con.prepareStatement(searchByPostCodeSQL);
            stmt.setString(1, searchByPostCodeRequest.getPostCode());
            ResultSet rs = stmt.executeQuery();

            SearchByPostCodeResponse searchByPostCodeResponse;
            if (rs.next() == false) return null;
            while (rs.next()) {
                searchByPostCodeResponse = copyPostCodeResponse(rs);
                responsesList.add(searchByPostCodeResponse);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return responsesList;
    }

    /**
     * search address By Prefecture Code
     *
     * @param searchByPrefectureCodeRequest request with prefecture code
     * @return list address
     */
    public List<SearchByPrefectureCodeResponse> searchByPrefectureCode(SearchByPrefectureCodeRequest
                                                                               searchByPrefectureCodeRequest) {
        List<SearchByPrefectureCodeResponse> responsesList = new ArrayList<>();
        try {
            PreparedStatement stmt = TrainingSpringbootApplication.con.prepareStatement(searchByPrefectureCodeSQL);
            stmt.setString(1, searchByPrefectureCodeRequest.getPrefectureCode());
            ResultSet rs = stmt.executeQuery();

            SearchByPrefectureCodeResponse searchByPrefectureCodeResponse;
            if (rs.next() == false) return null;
            while (rs.next()) {
                searchByPrefectureCodeResponse = copyPrefectureCodeResponse(rs);
                responsesList.add(searchByPrefectureCodeResponse);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return responsesList;
    }

    /**
     * COPY props from resultSet to SearchByPostCodeResponse object
     *
     * @param rs resultSet
     * @return SearchByPostCodeResponse object with props
     * @throws SQLException
     */
    private SearchByPostCodeResponse copyPostCodeResponse(ResultSet rs) throws SQLException {
        SearchByPostCodeResponse searchByPostCodeResponse = new SearchByPostCodeResponse();
        searchByPostCodeResponse.setCode(rs.getString("code"));
        searchByPostCodeResponse.setCity(rs.getString("city"));
        searchByPostCodeResponse.setCityKana(rs.getString("city_kana"));
        searchByPostCodeResponse.setPrefecture(rs.getString("prefecture"));
        searchByPostCodeResponse.setPrefectureKana(rs.getString("prefecture_kana"));
        searchByPostCodeResponse.setPrefectureCode(rs.getString("prefecture_code"));
        searchByPostCodeResponse.setArea(rs.getString("area"));
        searchByPostCodeResponse.setAreaKana(rs.getString("area_kana"));
        searchByPostCodeResponse.setMultiPostArea(rs.getString("multi_post_area"));
        searchByPostCodeResponse.setKoazaArea(rs.getString("koaza_area"));
        searchByPostCodeResponse.setChomeArea(rs.getString("chome_area"));
        searchByPostCodeResponse.setOldPostCode(rs.getString("old_post_code"));
        searchByPostCodeResponse.setPostCode(rs.getString("post_code"));
        searchByPostCodeResponse.setMultiArea(rs.getString("multi_area"));
        searchByPostCodeResponse.setUpdateShow(rs.getString("update_show"));
        searchByPostCodeResponse.setChangeReason(rs.getString("change_reason"));
        return searchByPostCodeResponse;
    }

    /**
     * COPY props from resultSet to SearchByPrefectureCodeResponse object
     *
     * @param rs resultSet
     * @return SearchByPrefectureCodeResponse object with props
     * @throws SQLException
     */
    private SearchByPrefectureCodeResponse copyPrefectureCodeResponse(ResultSet rs) throws SQLException {
        SearchByPrefectureCodeResponse searchByPrefectureCodeResponse = new SearchByPrefectureCodeResponse();
        searchByPrefectureCodeResponse.setPrefectureCode(rs.getString("prefecture_code"));
        searchByPrefectureCodeResponse.setCity(rs.getString("city"));
        searchByPrefectureCodeResponse.setCityKana(rs.getString("city_kana"));
        searchByPrefectureCodeResponse.setPrefecture(rs.getString("prefecture"));
        searchByPrefectureCodeResponse.setPrefectureKana(rs.getString("prefecture_kana"));
        searchByPrefectureCodeResponse.setCode(rs.getString("code"));
        return searchByPrefectureCodeResponse;
    }

    // search By Post Code Query
    private String searchByPostCodeSQL = "SELECT " +
            " city.code," +
            " city.city," +
            " city.city_kana," +
            " pre.prefecture," +
            " pre.prefecture_kana," +
            " pre.prefecture_code," +
            " area.area," +
            " area.area_kana," +
            " area.multi_post_area," +
            " area.koaza_area," +
            " area.chome_area," +
            " old.old_post_code," +
            " post.post_code," +
            " post.multi_area," +
            " post.update_show," +
            " post.change_reason" +
            " FROM tbl_post AS post" +
            " LEFT JOIN tbl_area AS area ON post.post_id = area.post_id" +
            " LEFT JOIN tbl_city AS city ON area.city_id = city.city_id" +
            " LEFT JOIN tbl_prefecture AS pre ON city.prefecture_id = pre.prefecture_id" +
            " LEFT JOIN tbl_old_post as old ON area.old_post_id = old.old_post_id" +
            " WHERE post.post_code = ?";
    // search ByPrefecture Code Query
    private String searchByPrefectureCodeSQL = "SELECT " +
            " city.code," +
            " city.city," +
            " city.city_kana," +
            " pre.prefecture," +
            " pre.prefecture_kana," +
            " pre.prefecture_code" +
            " FROM tbl_prefecture AS pre" +
            " LEFT JOIN tbl_city AS city ON city.prefecture_id = pre.prefecture_id" +
            " WHERE pre.prefecture_code = ?";
}
