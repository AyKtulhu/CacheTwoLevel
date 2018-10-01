package ru.trueim.data;

public class ObjectHeap {

    private String name;

    public ObjectHeap() {
    }

    protected ObjectHeap(long id) {
        this.name = "Object №:" + String.valueOf(id);
        System.out.println("New object created: " + name);
    }

    public String toString() {
        return name;
    }

}
