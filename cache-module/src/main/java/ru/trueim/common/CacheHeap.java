package ru.trueim.common;


import java.io.IOException;

public interface CacheHeap {


    void showCacheObjects();

    void showAmountObjectCache();

    void put(Object key, Object object) throws IOException;

    void configuration();

}
