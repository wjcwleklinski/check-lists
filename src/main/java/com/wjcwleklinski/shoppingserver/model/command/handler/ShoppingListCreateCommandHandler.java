package com.wjcwleklinski.shoppingserver.model.command.handler;

import com.wjcwleklinski.shoppingserver.common.handler.CommandHandler;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.command.ShoppingListCreateCommand;
import com.wjcwleklinski.shoppingserver.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingListCreateCommandHandler implements CommandHandler<ShoppingListCreateCommand> {

    private final ShoppingListService service;

    @Override
    public void execute(ShoppingListCreateCommand command) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCode(command.getCode());
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        service.createShoppingList(shoppingList);
    }
}
