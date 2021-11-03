package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.common.handler.CommandProcessor;
import com.wjcwleklinski.listservice.common.resource.CommonResource;
import com.wjcwleklinski.listservice.model.command.EntryCreateCommand;
import com.wjcwleklinski.listservice.model.command.EntryUpdateCommand;
import com.wjcwleklinski.listservice.model.command.EntryDeleteCommand;
import com.wjcwleklinski.listservice.model.view.EntryCollectionView;
import com.wjcwleklinski.listservice.model.view.EntryDetailsView;
import com.wjcwleklinski.listservice.service.EntryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/check-lists/{listCode}/entries")
@RequiredArgsConstructor
public class EntryResource extends CommonResource {

    private final EntryQueryService entryQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<EntryCollectionView> getEntries(@PathVariable("listCode") String listCode) {
        return entryQueryService.getProductsInList(listCode);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@PathVariable("listCode") String listCode,
                                         @Valid @RequestBody EntryCreateCommand command,
                                         HttpServletRequest request) {
        command.setListCode(listCode);
        return created(request, commandProcessor.process(command));
    }

    @GetMapping("/{entryCode}")
    public EntryDetailsView getEntryDetails(@PathVariable("entryCode") String entryCode) {
        return entryQueryService.getProductByCode(entryCode);
    }

    @PutMapping("/{entryCode}")
    public ResponseEntity<?> updateEntry(@PathVariable("entryCode") String entryCode,
                                            @RequestBody EntryUpdateCommand command) {
        command.setEntryCode(entryCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEntries(@PathVariable("listCode") String listCode,
                                           @RequestBody EntryDeleteCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }
}
