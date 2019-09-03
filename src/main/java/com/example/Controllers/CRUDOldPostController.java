package com.example.Controllers;

import com.example.Common.Util;
import com.example.Entity.OldPost;
import com.example.Service.CRUDOldPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD Old Post Controller
 */
@RestController
@RequestMapping(value = "/oldPost")
public class CRUDOldPostController {

    @Autowired
    private CRUDOldPostService crudOldPostService;

    /**
     * Save new Old Post
     *
     * @param oldPost info from request
     * @return saved Old Post with HttpStatus
     */
    @PostMapping(path = {"/save"})
    public ResponseEntity<Object> saveOldPost(@RequestBody OldPost oldPost) {
        if (oldPost == null) return Util.returnBadRequestStatus();
        OldPost savedOldPost = null;
        try {
            savedOldPost = crudOldPostService.saveOldPost(oldPost);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        if (savedOldPost == null) return Util.returnServerError();
        String result = Util.convertObjToJSON(savedOldPost);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * find Old Post By Old Post Id
     *
     * @param id get from Url path
     * @return ResponseEntity with data and status
     */
    @GetMapping(path = {"/find", "/find/{id}"})
    public ResponseEntity<Object> findOldPost(@PathVariable(name = "id", required = false) String id) {
        if (Util.isInputValid(id) == false) return Util.returnBadRequestStatus();
        OldPost oldPost = crudOldPostService.find(id);
        if (oldPost == null) return Util.returnNotFoundStatus();
        String result = Util.convertObjToJSON(oldPost);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update an Old Post
     *
     * @param oldPost get from request
     * @return updated Old Post with OK Status
     */
    @PostMapping(path = {"/update"})
    public ResponseEntity<Object> updateOldPost(@RequestBody OldPost oldPost) {
        if (oldPost == null || Util.isInputValid(oldPost.getOldPostId()) == false) return Util.returnBadRequestStatus();
        OldPost updatedOldPost = null;
        try {
            updatedOldPost = crudOldPostService.updateOldPost(oldPost);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        if (updatedOldPost == null) return Util.returnServerError();
        String result = Util.convertObjToJSON(updatedOldPost);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete an Old Post
     *
     * @param id to delete
     * @return ResponseEntity with OK Status
     */
    @GetMapping(path = {"/delete", "/delete/{id}"})
    public ResponseEntity<Object> deleteOldPost(@PathVariable(name = "id", required = false) String id) {
        if (Util.isInputValid(id) == false) return Util.returnBadRequestStatus();
        try {
            crudOldPostService.deleteOldPost(id);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
