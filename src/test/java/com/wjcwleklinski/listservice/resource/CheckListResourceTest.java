package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.CommonResourceTest;
import com.wjcwleklinski.listservice.model.command.CheckListCreateCommand;
import com.wjcwleklinski.listservice.model.command.CheckListUpdateCommand;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import com.wjcwleklinski.listservice.model.view.CheckListDetailsView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


public class CheckListResourceTest extends CommonResourceTest {

    @Test
    public void getCheckListsFullResponse_200() throws URISyntaxException {
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

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(getUrl("/check-lists"), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Map<String, Object>>>(){});
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().get(0).size(), 5);
        Assertions.assertEquals(response.getBody().get(0).get("id"), 1);
        Assertions.assertEquals(response.getBody().get(0).get("code"), "checkList1");
        Assertions.assertEquals(response.getBody().get(0).get("name"), "name1");
        Assertions.assertEquals(response.getBody().get(0).get("description"), "description1");
        Assertions.assertEquals(response.getBody().get(0).get("image"), "image1");
        Assertions.assertEquals(response.getBody().get(1).size(), 5);
        Assertions.assertEquals(response.getBody().get(1).get("id"), 2);
        Assertions.assertEquals(response.getBody().get(1).get("code"), "checkList2");
        Assertions.assertEquals(response.getBody().get(1).get("name"), "name2");
        Assertions.assertEquals(response.getBody().get(1).get("description"), "description2");
        Assertions.assertEquals(response.getBody().get(1).get("image"), "image2");
        Assertions.assertEquals(response.getBody().get(2).size(), 5);
        Assertions.assertEquals(response.getBody().get(2).get("id"), 3);
        Assertions.assertEquals(response.getBody().get(2).get("code"), "checkList3");
        Assertions.assertEquals(response.getBody().get(2).get("name"), "name3");
        Assertions.assertEquals(response.getBody().get(2).get("description"), "description3");
        Assertions.assertEquals(response.getBody().get(2).get("image"), "image3");
    }

    @Test
    public void getChecklistByCodeFullResponse_200() throws URISyntaxException {
        CheckList checkList1 = CheckList.builder()
                .name("name1")
                .description("description1")
                .image("image1")
                .build();
        checkList1.setCode("checkList1");
        checkList1.setId(1L);
        Entry entry1 = Entry.builder()
                .name("name1")
                .description("description1")
                .image("image1")
                .priority(Entry.Priority.MEDIUM.toString())
                .checkList(checkList1)
                .build();
        entry1.setId(1L);
        entry1.setCode("entry1");
        Entry entry2 = Entry.builder()
                .name("name2")
                .description("description2")
                .image("image2")
                .priority(Entry.Priority.MEDIUM.toString())
                .checkList(checkList1)
                .build();
        entry2.setId(2L);
        entry2.setCode("entry2");
        checkList1.setEntries(Arrays.asList(entry1, entry2));

        when(checkListRepository.getByCode("checkList1")).thenReturn(Optional.of(checkList1));
        ResponseEntity<CheckListDetailsView> response = restTemplate.exchange(getUrl("/check-lists/checkList1"), HttpMethod.GET,
                null, CheckListDetailsView.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        CheckListDetailsView responseBody = response.getBody();
        Assertions.assertEquals(checkList1.getId(), responseBody.getId());
        Assertions.assertEquals(checkList1.getCode(), responseBody.getCode());
        Assertions.assertEquals(checkList1.getName(), responseBody.getName());
        Assertions.assertEquals(checkList1.getDescription(), responseBody.getDescription());
        Assertions.assertEquals(checkList1.getImage(), responseBody.getImage());
        Assertions.assertEquals(2, responseBody.getCodesOfEntries().size());
        Assertions.assertEquals(2, responseBody.getNamesOfEntries().size());
        Assertions.assertEquals(Arrays.asList(entry1.getCode(), entry2.getCode()), responseBody.getCodesOfEntries());
        Assertions.assertEquals(Arrays.asList(entry1.getName(), entry2.getName()), responseBody.getNamesOfEntries());
    }

    @Test
    public void addCheckList_200() throws URISyntaxException {
        CheckList checkList = CheckList.builder()
                .name("name")
                .description("description1")
                .image("image")
                .build();
        checkList.setCode("checkList");
        checkList.setId(1L);
        CheckListCreateCommand command = new CheckListCreateCommand();
        command.setCode(checkList.getCode());
        command.setName(checkList.getName());
        command.setDescription(checkList.getDescription());
        command.setImage(checkList.getImage());

        when(checkListRepository.save(any(CheckList.class))).thenReturn(checkList);
        HttpEntity<CheckListCreateCommand> request = new HttpEntity<>(command, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl("/check-lists"), HttpMethod.POST,
                request, String.class);
        Assertions.assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void updateCheckList_200() throws URISyntaxException {
        CheckList checkList1 = CheckList.builder()
                .name("name1")
                .description("description1")
                .image("image1")
                .build();
        checkList1.setCode("checkList1");
        checkList1.setId(1L);
        CheckListUpdateCommand command = new CheckListUpdateCommand();
        command.setName("name1 updated name");
        command.setDescription("description1 updated description");
        command.setImage("image1 updated image");

        when(checkListRepository.getByCode(checkList1.getCode())).thenReturn(Optional.of(checkList1));
        checkList1.setName(command.getName());
        checkList1.setDescription(command.getDescription());
        checkList1.setImage(command.getImage());
        when(checkListRepository.save(checkList1)).thenReturn(checkList1);
        HttpEntity<CheckListUpdateCommand> request = new HttpEntity<>(command, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl("/check-lists/checkList1"), HttpMethod.PUT,
                request, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void deleteCheckList_200() throws URISyntaxException {
        CheckList checkList1 = CheckList.builder()
                .name("name1")
                .description("description1")
                .image("image1")
                .build();
        checkList1.setCode("checkList1");
        checkList1.setId(1L);
        when(checkListRepository.getByCode(checkList1.getCode())).thenReturn(Optional.of(checkList1));
        ResponseEntity<CheckListDetailsView> response = restTemplate.exchange(getUrl("/check-lists/checkList1"), HttpMethod.DELETE,
                null, CheckListDetailsView.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
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
