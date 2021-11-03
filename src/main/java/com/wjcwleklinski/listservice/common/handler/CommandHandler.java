package com.wjcwleklinski.listservice.common.handler;

public interface CommandHandler<T> {

    String execute(T command);

}
