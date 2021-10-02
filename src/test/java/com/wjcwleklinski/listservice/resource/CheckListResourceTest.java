package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.CommonResourceTest;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;


public class CheckListResourceTest extends CommonResourceTest {

    @Test
    public void getCheckLists_fullResponse() throws URISyntaxException {
        CheckList checkList1 = CheckList.builder()
                .name("name1")
                .description("description1")
                .image("image1")
                .build();
        checkList1.setCode("checkList1");
        checkList1.setId(1L);
        CheckList checkList2 = CheckList.builder()
                .name("name2")
                .description("description2")
                .image("image2")
                .build();
        checkList2.setCode("checkList2");
        checkList2.setId(2L);
        CheckList checkList3 = CheckList.builder()
                .name("name3")
                .description("description3")
                .image("image3")
                .build();
        checkList3.setCode("checkList3");
        checkList3.setId(3L);

        List<CheckListCollectionProjection> checkLists = Arrays.asList(
                mockCheckListCollectionProjection(checkList1),
                mockCheckListCollectionProjection(checkList2),
                mockCheckListCollectionProjection(checkList3)
        );
        when(checkListRepository.findAllShoppingListsBy()).thenReturn(checkLists);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(getUrl("/check-lists"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Map<String, Object>>>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(response.getBody().get(0).size(), 5);
        Assert.assertEquals(response.getBody().get(0).get("id"), 1);
        Assert.assertEquals(response.getBody().get(0).get("code"), "checkList1");
        Assert.assertEquals(response.getBody().get(0).get("name"), "name1");
        Assert.assertEquals(response.getBody().get(0).get("description"), "description1");
        Assert.assertEquals(response.getBody().get(0).get("image"), "image1");
        Assert.assertEquals(response.getBody().get(1).size(), 5);
        Assert.assertEquals(response.getBody().get(1).get("id"), 2);
        Assert.assertEquals(response.getBody().get(1).get("code"), "checkList2");
        Assert.assertEquals(response.getBody().get(1).get("name"), "name2");
        Assert.assertEquals(response.getBody().get(1).get("description"), "description2");
        Assert.assertEquals(response.getBody().get(1).get("image"), "image2");
        Assert.assertEquals(response.getBody().get(2).size(), 5);
        Assert.assertEquals(response.getBody().get(2).get("id"), 3);
        Assert.assertEquals(response.getBody().get(2).get("code"), "checkList3");
        Assert.assertEquals(response.getBody().get(2).get("name"), "name3");
        Assert.assertEquals(response.getBody().get(2).get("description"), "description3");
        Assert.assertEquals(response.getBody().get(2).get("image"), "image3");
    }

    private CheckListCollectionProjection mockCheckListCollectionProjection(CheckList checkList) {
        return new CheckListCollectionProjection() {
            @Override
            public Long getId() {
                return checkList.getId();
            }

            @Override
            public String getCode() {
                return checkList.getCode();
            }

            @Override
            public String getName() {
                return checkList.getName();
            }

            @Override
            public String getDescription() {
                return checkList.getDescription();
            }

            @Override
            public String getImage() {
                return checkList.getImage();
            }
        };
    }
}
