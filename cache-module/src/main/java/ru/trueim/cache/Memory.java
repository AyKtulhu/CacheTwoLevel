package ru.trueim.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Cache;
import ru.trueim.common.CacheHeap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Memory implements CacheHeap {

    private static int cacheSize;
    private static int expireAfterAccess;
    private static Cache<Object, Object> cache;
    private static Memory instance;

    private Memory() {
    }

    static Memory getInstance() {
        return instance == null ? instance = new Memory() : instance;
    }

    private void createCache() {
        cache = CacheBuilder.newBuilder()
                .concurrencyLevel(2)
                .removalListener(removalNotification -> {
                    System.out.println("Object is deleted from memory: " + removalNotification.getKey()
                            + " - " + removalNotification.getValue());
                    try {
                        Heap.getInstance().getCache(TypeCache.TWO_LEVEL)
                                .put(removalNotification.getKey(), removalNotification.getValue());
                        Heap.getInstance().getCache(TypeCache.TWO_LEVEL).showCacheObjects();
                        Heap.getInstance().getCache(TypeCache.TWO_LEVEL).showAmountObjectCache();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .maximumSize(cacheSize)
                .expireAfterAccess(expireAfterAccess, TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public void showCacheObjects() {
        System.out.println("Output data cache MEMMORY:");
        cache.asMap().entrySet().forEach(entry -> System.out.println(entry.getKey()
                + " - " + entry.getValue()));
    }

    @Override
    public void showAmountObjectCache() {
        System.out.println("Amount objets in MEMMORY: " + cache.size());
    }

    @Override
    public void put(Object key, Object object) {
        cache.put(key, object);
    }

    @Override
    public void configuration() {
        Scanner in = new Scanner(System.in);
        System.out.print("How many objects are allowed to store in MEMMORY? - ");
        cacheSize = in.nextInt();
        System.out.print("How long is the lifetime after access, set to the object?(milliseconds) - ");
        expireAfterAccess = in.nextInt();
        this.createCache();
    }
}