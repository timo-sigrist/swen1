package ch.zhaw.designpatterns.behavioral.visitor_pattern;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
