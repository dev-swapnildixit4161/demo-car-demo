package com.elasticsearch.elasticsearch.eventlistener;

public interface CloudConsumer<T> {

    void consumeEvent(T event);

}
