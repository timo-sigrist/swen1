package ch.zhaw.designpatterns.creational.builder_pattern;

public interface Item {
    public String name();
    public Packing packing();
    public float price();
}