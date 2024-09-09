package ch.zhaw.designpatterns.structural.decorator_pattern;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
