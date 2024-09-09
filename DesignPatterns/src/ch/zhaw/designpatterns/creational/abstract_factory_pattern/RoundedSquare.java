package ch.zhaw.designpatterns.creational.abstract_factory_pattern;

public class RoundedSquare implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare::draw() method.");
    }
}
