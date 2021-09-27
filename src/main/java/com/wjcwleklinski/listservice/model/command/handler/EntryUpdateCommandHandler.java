package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.command.EntryUpdateCommand;
import com.wjcwleklinski.listservice.repository.EntryRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EntryUpdateCommandHandler implements CommandHandler<EntryUpdateCommand> {

    private final CommonService commonService;

    private final EntryRepository entryRepository;

    @Override
    public void execute(EntryUpdateCommand command) {
        Entry entry = (Entry) commonService.getByCode(command.getEntryCode(), entryRepository);
        entry.setName(command.getName());
        entry.setDescription(command.getDescription());
        entry.setPriority(command.getPriority());
        entry.setImage(command.getImage());
        entryRepository.save(entry);
    }
}
