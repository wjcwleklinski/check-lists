package com.wjcwleklinski.listservice.common.handler;

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
    public <T> void process(T command) {
        try {
            CommandHandler<T> commandHandler = (CommandHandler<T>) Arrays.stream(beanFactory.getBeanNamesForType(
                    ResolvableType.forClassWithGenerics(CommandHandler.class, command.getClass())))
                    .map(beanFactory::getBean)
                    .findFirst()
                    .orElseThrow(() -> new InternalServerException("Handler not found for command: " + command.getClass()));
            commandHandler.execute(command);
        } catch (ClassCastException classCastException) {
            throw new InternalServerException("Handler not found for command: " + command.getClass());
        }
    }
}
