package com.wjcwleklinski.shoppingserver.model.command.handler;

import com.wjcwleklinski.shoppingserver.common.handler.CommandHandler;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.command.ShoppingListCreateCommand;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.service.CommonService;
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
