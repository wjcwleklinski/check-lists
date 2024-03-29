package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.common.handler.CommandProcessor;
import com.wjcwleklinski.listservice.common.resource.CommonResource;
import com.wjcwleklinski.listservice.model.command.*;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import com.wjcwleklinski.listservice.model.view.CheckListDetailsView;
import com.wjcwleklinski.listservice.service.CheckListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/check-lists")
@RequiredArgsConstructor
public class CheckListResource extends CommonResource {

    private final CheckListQueryService checkListQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<CheckListCollectionProjection> getCheckLists() {
        return checkListQueryService.getCheckLists();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> createCheckList(@RequestBody CheckListCreateCommand command,
                                             HttpServletRequest request) {
        return created(request, commandProcessor.process(command));
    }

    @GetMapping("/{listCode}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CheckListDetailsView getCheckList(@PathVariable("listCode") String listCode) {
        return checkListQueryService.getCheckListDetails(listCode);
    }

    @PutMapping("/{listCode}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> updateCheckList(@PathVariable("listCode") String listCode,
                                                @RequestBody CheckListUpdateCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listCode}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> deleteCheckList(@PathVariable("listCode") String listCode) {
        checkListQueryService.deleteCheckList(listCode);
        return ResponseEntity.ok().build();
    }
}
