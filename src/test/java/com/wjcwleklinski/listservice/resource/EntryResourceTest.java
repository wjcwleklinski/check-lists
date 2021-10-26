package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.CommonResourceTest;
import com.wjcwleklinski.listservice.model.command.EntryCreateCommand;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.view.EntryDetailsView;
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

import static org.mockito.Mockito.when;


public class EntryResourceTest extends CommonResourceTest {

    @Test
    public void getEntriesFullResponse_200() throws URISyntaxException {
        CheckList checkList = new CheckList();
        checkList.setCode("checkListCode");
        checkList.setId(1L);
        Entry entry1 = Entry.builder()
                .priority(Entry.Priority.MEDIUM.name())
                .image("image1")
                .description("description1")
                .name("name1")
                .checkList(checkList)
                .build();
        entry1.setCode("entryCode1");
        entry1.setId(1L);
        Entry entry2 = Entry.builder()
                .priority(Entry.Priority.HIGH.name())
                .image("image2")
                .description("description2")
                .name("name2")
                .checkList(checkList)
                .build();
        entry2.setCode("entryCode2");
        entry2.setId(2L);

        when(entryRepository.findEntriesByCheckListCode(checkList.getCode())).thenReturn(Arrays.asList(entry1, entry2));
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(getUrl("/check-lists/checkListCode/entries"), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Map<String, Object>>>(){});
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().size(), 2);
        Assertions.assertEquals(response.getBody().get(0).size(), 4);
        Assertions.assertEquals(response.getBody().get(0).get("code"), "entryCode1");
        Assertions.assertEquals(response.getBody().get(0).get("name"), "name1");
        Assertions.assertEquals(response.getBody().get(0).get("priority"), Entry.Priority.MEDIUM.name());
        Assertions.assertEquals(response.getBody().get(0).get("image"), "image1");
        Assertions.assertEquals(response.getBody().get(1).size(), 4);
        Assertions.assertEquals(response.getBody().get(1).get("code"), "entryCode2");
        Assertions.assertEquals(response.getBody().get(1).get("name"), "name2");
        Assertions.assertEquals(response.getBody().get(1).get("priority"), Entry.Priority.HIGH.name());
        Assertions.assertEquals(response.getBody().get(1).get("image"), "image2");
    }

    @Test
    public void getEntryByCodeFullResponse_200() throws URISyntaxException {
        CheckList checkList = new CheckList();
        checkList.setCode("checkListCode");
        checkList.setName("checkListName");
        checkList.setId(1L);
        Entry entry = Entry.builder()
                .priority(Entry.Priority.MEDIUM.name())
                .image("image")
                .description("description")
                .name("name")
                .checkList(checkList)
                .build();
        entry.setCode("entryCode");
        entry.setId(1L);

        when(entryRepository.getByCode(entry.getCode())).thenReturn(Optional.of(entry));
        ResponseEntity<EntryDetailsView> response = restTemplate.exchange(getUrl("/check-lists/checkListCode/entries/entryCode"), HttpMethod.GET,
                null, EntryDetailsView.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        EntryDetailsView responseBody = response.getBody();
        Assertions.assertEquals(entry.getCode(), responseBody.getCode());
        Assertions.assertEquals(entry.getName(), responseBody.getName());
        Assertions.assertEquals(entry.getImage(), responseBody.getImage());
        Assertions.assertEquals(entry.getDescription(), responseBody.getDescription());
        Assertions.assertEquals(entry.getPriority(), responseBody.getPriority());
        Assertions.assertEquals(entry.getCheckList().getCode(), responseBody.getCheckListCode());
        Assertions.assertEquals(entry.getCheckList().getName(), responseBody.getCheckListName());
    }

    @Test
    public void addEntry_200() throws URISyntaxException {
        CheckList checkList = new CheckList();
        checkList.setCode("checkListCode");
        checkList.setId(1L);
        Entry entry = Entry.builder()
                .priority(Entry.Priority.MEDIUM.name())
                .image("image")
                .description("description")
                .name("name")
                .checkList(checkList)
                .build();
        entry.setCode("entryCode");
        entry.setId(1L);

        EntryCreateCommand command = new EntryCreateCommand();
        command.setCode(entry.getCode());
        command.setPriority(Entry.Priority.LOW.name());
        command.setName(entry.getName());
        command.setDescription(entry.getDescription());
        command.setImage(entry.getImage());

        when(checkListRepository.getByCode(checkList.getCode())).thenReturn(Optional.of(checkList));
        when(entryRepository.save(entry)).thenReturn(entry);
        HttpEntity<EntryCreateCommand> request = new HttpEntity<>(command, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl("/check-lists/checkListCode/entries"), HttpMethod.POST,
                request, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void updateEntry_200() throws URISyntaxException {

    }

    @Test
    public void deleteEntry_200() throws URISyntaxException {

    }

}
