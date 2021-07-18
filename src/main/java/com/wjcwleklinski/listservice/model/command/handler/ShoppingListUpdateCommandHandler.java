package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.ShoppingList;
import com.wjcwleklinski.listservice.model.command.ShoppingListUpdateCommand;
import com.wjcwleklinski.listservice.repository.ShoppingListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingListUpdateCommandHandler implements CommandHandler<ShoppingListUpdateCommand> {

    private final CommonService commonService;

    private final ShoppingListRepository shoppingListRepository;

    @Override
    public void execute(ShoppingListUpdateCommand command) {
        ShoppingList shoppingList = (ShoppingList) commonService.getByCode(command.getListCode(), shoppingListRepository);
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        commonService.save(shoppingList, shoppingListRepository);
    }
}
