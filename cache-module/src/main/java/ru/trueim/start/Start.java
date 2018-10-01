package ru.trueim.start;

import ru.trueim.cache.TypeCache;
import ru.trueim.cache.Heap;
import ru.trueim.data.ParentDataHeap;

import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello!!!");
        Heap.getInstance().getCache(TypeCache.ONE_LEVEL).configuration();
        Heap.getInstance().getCache(TypeCache.TWO_LEVEL).configuration();
        Scanner in = new Scanner(System.in);
        System.out.print("How many would you like to create objects? - ");
        ParentDataHeap.getInstance().createObjacts(in.nextInt());
        System.out.printf("%s%n%s%n%s%n"
                ,"Check the data in the file system:"
                ,"target/"
                ,"To complete, type any character and press Enter....");
        System.out.println(in.next());
        Heap.getInstance().clearHeap();
        System.out.println("Operation completed...");
    }
}


