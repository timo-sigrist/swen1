package ch.zhaw.designpatterns.structural.facade_pattern;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Hallo Rechteck");
    }
}
