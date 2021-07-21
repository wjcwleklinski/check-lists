package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.Entry;
import com.wjcwleklinski.listservice.model.CheckList;
import com.wjcwleklinski.listservice.model.command.EntryCreateCommand;
import com.wjcwleklinski.listservice.repository.EntryRepository;
import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class EntryCreateCommandHandler implements CommandHandler<EntryCreateCommand> {

    private final CommonService commonService;

    private final CheckListRepository checkListRepository;

    private final EntryRepository entryRepository;

    @Override
    public void execute(EntryCreateCommand command) {
        CheckList checkList = (CheckList) commonService.getByCode(command.getListCode(), checkListRepository);
        Entry entry = new Entry();
        entry.setCode(command.getCode());
        entry.setName(command.getName());
        entry.setDescription(command.getDescription());
        entry.setPriority(command.getPriority());
        entry.setImage(command.getImage());
        entry.setCheckList(checkList);
        commonService.save(entry, entryRepository);
        if (checkList.getEntries() == null) {
            checkList.setEntries(new ArrayList<>());
        }
        checkList.getEntries().add(entry);
    }
}
