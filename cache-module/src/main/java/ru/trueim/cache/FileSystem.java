package ru.trueim.cache;

import com.google.gson.Gson;
import ru.trueim.common.CacheHeap;
import ru.trueim.data.ObjectHeap;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

class FileSystem implements CacheHeap {

    private static int cacheSize;
    private static FileSystem instance;
    private static LinkedList<Long> listKey = new LinkedList<>();

    private FileSystem() {
    }

    protected static FileSystem getInstance() {
        return instance == null ? instance = new FileSystem() : instance;
    }

    @Override
    public void showCacheObjects() {
        System.out.println("Output data cache FILE SYSTEM:");
        for (int x = 0; x < listKey.size(); x++) {
            System.out.println(listKey.get(x)+" - "
                    + deserializeStringObject(readeFile
                    (listKey.get(x))));
        }

    }

    @Override
    public void showAmountObjectCache() {
        System.out.println("Amount objets in FILE SYSTEM: "+listKey.size());
    }

    @Override
    public void put(Object key, Object object) throws IOException {
        signUpKey((long) key);
        createFile(key, object);
        System.out.println("New object created in FILE SYSTEM:" + key + "-" + object.toString());
    }

    private String serializingObjectString(Object object) {
        return new Gson().toJson(object);
    }

    private ObjectHeap deserializeStringObject(String jason) {
        return new Gson().fromJson(jason, ObjectHeap.class);
    }

    private void signUpKey(Object key) {
        if (checkAmountMaxCache()) deleteFile(listKey.pollFirst());
        listKey.add((Long) key);
    }

    private boolean checkAmountMaxCache() {
        return listKey.size() >= cacheSize;

    }

    private String readeFile(long key) {
        String object = null;
        try {
            FileInputStream fout = new FileInputStream(key + ".json");
            ObjectInputStream ois = new ObjectInputStream(fout);
            object = (String) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    private void deleteFile(long firstKey) {
        if (new File(firstKey + ".json").delete())
            System.out.println("KILL file - " + firstKey);
    }

    private void createFile(Object key, Object object) {
        try {
            FileOutputStream fout = new FileOutputStream(key + ".json");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(serializingObjectString(object));
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void clearFileSystem(){
        for (int x = 0; x < listKey.size(); x++) {
            deleteFile(listKey.get(x));
        }

    }

    @Override
    public void configuration() {
        Scanner in = new Scanner(System.in);
        System.out.print("How many objects are allowed to store in FILE SYSTEM? - ");
        cacheSize = in.nextInt();
    }

}
