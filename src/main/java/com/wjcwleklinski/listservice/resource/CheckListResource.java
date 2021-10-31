package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.common.handler.CommandProcessor;
import com.wjcwleklinski.listservice.model.command.*;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import com.wjcwleklinski.listservice.model.view.CheckListDetailsView;
import com.wjcwleklinski.listservice.service.CheckListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check-lists")
@RequiredArgsConstructor
public class CheckListResource {

    private final CheckListQueryService checkListQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<CheckListCollectionProjection> getCheckLists() {
        return checkListQueryService.getCheckLists();
    }

    @PostMapping
    public ResponseEntity<?> createCheckList(@RequestBody CheckListCreateCommand command) {
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listCode}")
    public CheckListDetailsView getCheckList(@PathVariable("listCode") String listCode) {
        return checkListQueryService.getCheckListDetails(listCode);
    }

    @PutMapping("/{listCode}")
    public ResponseEntity<?> updateCheckList(@PathVariable("listCode") String listCode,
                                                @RequestBody CheckListUpdateCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listCode}")
    public ResponseEntity<?> deleteCheckList(@PathVariable("listCode") String listCode) {
        checkListQueryService.deleteCheckList(listCode);
        return ResponseEntity.ok().build();
    }
}
