package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.ShoppingList;
import com.wjcwleklinski.listservice.model.command.ShoppingListCreateCommand;
import com.wjcwleklinski.listservice.repository.ShoppingListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingListCreateCommandHandler implements CommandHandler<ShoppingListCreateCommand> {

    private final CommonService commonService;

    private final ShoppingListRepository shoppingListRepository;

    @Override
    public void execute(ShoppingListCreateCommand command) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCode(command.getCode());
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        commonService.save(shoppingList, shoppingListRepository);
    }
}
