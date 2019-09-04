package com.example.ServiceTest;

import com.example.Entity.OldPost;
import com.example.Repository.OldPostRepository;
import com.example.Service.CRUDOldPostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link CRUDOldPostService}
 */
@RunWith(MockitoJUnitRunner.class)
public class CRUDOldPostServiceTest {

    @InjectMocks
    CRUDOldPostService crudOldPostService;
    @Mock
    OldPostRepository oldPostRepository;

    /**
     * Test save OldPost success
     * Input:
     * A OldPost that not exist in DB
     * Output:
     * Saved OldPost what same with input
     */
    @Test
    public void saveOldPostTest1() {
        // setup
        OldPost OldPost = new OldPost();
        OldPost.setOldPostId("9000");
        OldPost.setOldPostCode("9000");
        when(oldPostRepository.save(OldPost)).thenReturn(OldPost);
        // exercise
        OldPost savedOldPost = crudOldPostService.saveOldPost(OldPost);
        // verify
        Assert.assertNotNull(savedOldPost);
        Assert.assertEquals(OldPost.getOldPostId(), savedOldPost.getOldPostId());
        Assert.assertEquals(OldPost.getOldPostCode(), savedOldPost.getOldPostCode());

    }

    /**
     * Test save OldPost fail, input OldPost is null
     * Input:
     * null
     * Output:
     * null
     */
    @Test
    public void saveOldPostTest2() {
        // setup
        OldPost OldPost = null;
        when(oldPostRepository.save(OldPost)).thenReturn(OldPost);
        // exercise
        OldPost savedOldPost = crudOldPostService.saveOldPost(OldPost);
        // verify
        Assert.assertNull(savedOldPost);
    }

    /**
     * Test find OldPost success
     * Input:
     * OldPostId that exist in DB
     * Output:
     * OldPost mapping with input OldPostId
     */
    @Test
    public void findByOldPostIdTest1() {
        // setup
        String OldPostId = "9000";
        OldPost OldPost = new OldPost();
        OldPost.setOldPostId(OldPostId);
        OldPost.setOldPostCode("9000");
        when(oldPostRepository.findOne(OldPostId)).thenReturn(OldPost);
        // exercise
        OldPost foundOldPost = crudOldPostService.find(OldPostId);
        // verify
        Assert.assertNotNull(foundOldPost);
        Assert.assertEquals(OldPost.getOldPostId(), foundOldPost.getOldPostId());
        Assert.assertEquals(OldPost.getOldPostCode(), foundOldPost.getOldPostCode());
    }

    /**
     * Test find OldPost when no data mapping with OldPostId
     * Input:
     * OldPostId that not exist in DB
     * Output:
     * null
     */
    @Test
    public void findByOldPostIdTest2() {
        // setup
        String OldPostId = "9999";
        when(oldPostRepository.findOne(OldPostId)).thenReturn(null);
        // exercise
        OldPost foundOldPost = crudOldPostService.find(OldPostId);
        // verify
        Assert.assertNull(foundOldPost);
    }

    /**
     * Test find OldPost when input OldPostId is null
     * Input:
     * OldPostId that not exist in DB
     * Output:
     * null
     */
    @Test
    public void findByOldPostIdTest3() {
        // setup
        when(oldPostRepository.findOne(null)).thenReturn(null);
        // exercise
        OldPost foundOldPost = crudOldPostService.find(null);
        // verify
        Assert.assertNull(foundOldPost);
    }

    /**
     * Test update OldPost success
     * Input:
     * A OldPost has OldPostId that exist in DB, with some different info
     * Output:
     * Updated OldPost with new info
     */
    @Test
    public void updateOldPostTest1() {
        // setup
        String OldPostId = "9000";
        OldPost OldPost = new OldPost();
        OldPost.setOldPostId(OldPostId);
        OldPost.setOldPostCode("9595");
        when(oldPostRepository.findOne(OldPostId)).thenReturn(OldPost);
        when(oldPostRepository.save(OldPost)).thenReturn(OldPost);
        // exercise
        OldPost updatedOldPost = crudOldPostService.updateOldPost(OldPost);
        // verify
        Assert.assertNotNull(updatedOldPost);
        Assert.assertEquals(OldPost.getOldPostId(), updatedOldPost.getOldPostId());
        Assert.assertEquals(OldPost.getOldPostCode(), updatedOldPost.getOldPostCode());
    }

    /**
     * Test update OldPost failed, input OldPost is null
     * Input:
     * null
     * Output:
     * null
     */
    @Test
    public void updateOldPostTest2() {
        // setup
        OldPost OldPost = null;
        when(oldPostRepository.findOne(anyString())).thenReturn(OldPost);
        when(oldPostRepository.save(OldPost)).thenReturn(OldPost);
        // exercise
        OldPost updatedOldPost = crudOldPostService.updateOldPost(OldPost);
        // verify
        Assert.assertNull(updatedOldPost);
    }

    /**
     * function delete trả về void nên chưa biết test ntn
     */
    @Test
    public void deleteOldPostTest() {
        String OldPostId = "200";
        crudOldPostService.deleteOldPost(OldPostId);
    }
}
