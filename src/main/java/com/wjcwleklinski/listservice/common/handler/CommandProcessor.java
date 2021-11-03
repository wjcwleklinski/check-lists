package com.wjcwleklinski.listservice.common.handler;

import com.wjcwleklinski.listservice.error.ErrorMessage;
import com.wjcwleklinski.listservice.error.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final ListableBeanFactory beanFactory;

    @SuppressWarnings("unchecked")
    public <T> String process(T command) {
        try {
            CommandHandler<T> commandHandler = (CommandHandler<T>) Arrays.stream(beanFactory.getBeanNamesForType(
                    ResolvableType.forClassWithGenerics(CommandHandler.class, command.getClass())))
                    .map(beanFactory::getBean)
                    .findFirst()
                    .orElseThrow(() -> new InternalServerException(ErrorMessage.COMMAND_HANDLER_NOT_FOUND.getMessage(command.getClass())));
            return commandHandler.execute(command);
        } catch (ClassCastException classCastException) {
            throw new InternalServerException(ErrorMessage.COMMAND_HANDLER_NOT_FOUND.getMessage(command.getClass()));
        }
    }
}
