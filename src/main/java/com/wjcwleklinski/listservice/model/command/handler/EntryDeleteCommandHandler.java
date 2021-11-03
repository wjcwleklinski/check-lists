package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.command.EntryDeleteCommand;
import com.wjcwleklinski.listservice.repository.EntryRepository;
import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntryDeleteCommandHandler implements CommandHandler<EntryDeleteCommand> {

    private final CommonService commonService;

    private final CheckListRepository checkListRepository;

    private final EntryRepository entryRepository;

    @Override
    public String execute(EntryDeleteCommand command) {
        CheckList checkList = (CheckList) commonService.getByCode(command.getListCode(), checkListRepository);
        List<Entry> entries = checkList.getEntries().stream()
                .filter(product -> command.getEntryCodes().contains(product.getCode()))
                .collect(Collectors.toList());
        entryRepository.deleteInBatch(entries);
        return checkList.getCode();
    }
}
