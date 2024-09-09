package ch.zhaw.designpatterns.structural.facade_pattern;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Kreis");
    }
}
