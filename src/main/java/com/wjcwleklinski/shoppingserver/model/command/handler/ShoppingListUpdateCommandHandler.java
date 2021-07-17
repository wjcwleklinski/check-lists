package com.wjcwleklinski.shoppingserver.model.command.handler;

import com.wjcwleklinski.shoppingserver.common.handler.CommandHandler;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.command.ShoppingListUpdateCommand;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.service.CommonService;
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
