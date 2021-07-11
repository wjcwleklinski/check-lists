package com.wjcwleklinski.shoppingserver.common.handler;

public interface CommandHandler<T> {

    Class<T> getCommandType();

    void execute(T command);

}
