package ru.trueim.data;

import ru.trueim.cache.Heap;
import ru.trueim.cache.TypeCache;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ParentDataHeap {

    private static ParentDataHeap instance;
    private static Long key = 1l;
    private Random random = new Random();

    private ParentDataHeap() {
    }

    public static ParentDataHeap getInstance() {
        return instance == null ? instance = new ParentDataHeap() : instance;
    }

    public void createObjacts(int amountCreateObjects) throws IOException {
        this.fillDataHeap(amountCreateObjects);
    }

    public void fillDataHeap(int objectAmount) throws IOException {
        for (int x = 0; x <= objectAmount; x++) {
            Heap.getInstance().getCache(TypeCache.ONE_LEVEL).put(this.nextKey(), this.nextObject());
            Heap.getInstance().showHeapData();
        }
    }

    public ObjectHeap nextObject() {
        return new ObjectHeap(random.nextLong());
    }

    public Long nextKey() {
        return key++;
    }

}
