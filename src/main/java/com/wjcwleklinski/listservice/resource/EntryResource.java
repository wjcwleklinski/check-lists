package com.wjcwleklinski.listservice.resource;

import com.wjcwleklinski.listservice.common.handler.CommandProcessor;
import com.wjcwleklinski.listservice.model.command.EntryCreateCommand;
import com.wjcwleklinski.listservice.model.command.EntryUpdateCommand;
import com.wjcwleklinski.listservice.model.command.EntryDeleteCommand;
import com.wjcwleklinski.listservice.model.view.EntryCollectionView;
import com.wjcwleklinski.listservice.model.view.EntryDetailsView;
import com.wjcwleklinski.listservice.service.EntryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping-lists/{listCode}/products")
@RequiredArgsConstructor
public class EntryResource {

    private final EntryQueryService entryQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<EntryCollectionView> getEntries(@PathVariable("listCode") String listCode) {
        return entryQueryService.getProductsInList(listCode);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@PathVariable("listCode") String listCode,
                                           @Valid @RequestBody EntryCreateCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
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
