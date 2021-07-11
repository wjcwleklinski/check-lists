package com.wjcwleklinski.shoppingserver.common.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final List<CommandHandler<?>> handlers;

    private final ListableBeanFactory beanFactory;

    public <T> void process(T command) {
        Optional<CommandHandler<?>> commandHandler = handlers.stream()
                .filter(handler -> handler.getCommandType().equals(command.getClass()))
                .findFirst();
        commandHandler.ifPresent(handler -> ((CommandHandler<T>) handler).execute(command));
    }




}
