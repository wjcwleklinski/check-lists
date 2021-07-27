package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.CheckList;
import com.wjcwleklinski.listservice.model.command.CheckListUpdateCommand;
import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckListUpdateCommandHandler implements CommandHandler<CheckListUpdateCommand> {

    private final CommonService commonService;

    private final CheckListRepository checkListRepository;

    @Override
    public void execute(CheckListUpdateCommand command) {
        CheckList checkList = (CheckList) commonService.getByCode(command.getListCode(), checkListRepository);
        checkList.setName(command.getName());
        checkList.setDescription(command.getDescription());
        checkList.setImage(command.getImage());
        commonService.save(checkList, checkListRepository);
    }
}
