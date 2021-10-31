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
        CheckList checkList = CheckList.builder()
                .name(command.getName())
                .description(command.getDescription())
                .image(command.getImage())
                .build();
        checkList.setCode(command.getCode());
        commonService.save(checkList, checkListRepository);
    }
}
