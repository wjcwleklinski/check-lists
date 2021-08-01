package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.command.CheckListCreateCommand;
import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckListCreateCommandHandler implements CommandHandler<CheckListCreateCommand> {

    private final CommonService commonService;

    private final CheckListRepository checkListRepository;

    @Override
    public void execute(CheckListCreateCommand command) {
        CheckList checkList = new CheckList();
        checkList.setCode(command.getCode());
        checkList.setName(command.getName());
        checkList.setDescription(command.getDescription());
        checkList.setImage(command.getImage());
        commonService.save(checkList, checkListRepository);
    }
}
