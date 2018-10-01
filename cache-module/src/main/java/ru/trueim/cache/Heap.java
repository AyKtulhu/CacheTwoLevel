package ru.trueim.cache;

import ru.trueim.common.CacheHeap;

public class Heap {

    private CacheHeap cache;
    private static Heap instance;

    private Heap() {
    }

    public static Heap getInstance() {
        return instance == null ? instance = new Heap() : instance;
    }

    public CacheHeap getCache(TypeCache typeCache) {
        switch (typeCache) {
            case ONE_LEVEL:
                cache = Memory.getInstance();
                break;
            case TWO_LEVEL:
                cache = FileSystem.getInstance();
                break;
        }
        return cache;
    }

    public void showHeapData() {
        Heap.getInstance().getCache(TypeCache.ONE_LEVEL).showCacheObjects();
        Heap.getInstance().getCache(TypeCache.ONE_LEVEL).showAmountObjectCache();
    }
    public void clearHeap(){
        System.out.println();
        FileSystem.getInstance().clearFileSystem();
    }

}
