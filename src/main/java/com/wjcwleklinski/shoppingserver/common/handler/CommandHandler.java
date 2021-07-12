package com.wjcwleklinski.shoppingserver.common.handler;

public interface CommandHandler<T> {

    void execute(T command);

}
