package com.wjcwleklinski.listservice.common.handler;

public interface CommandHandler<T> {

    void execute(T command);

}
