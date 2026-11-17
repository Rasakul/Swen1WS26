package at.technikum.solid.lsp.shapes.refactored;

public class Rectangle extends Shape {

    public Rectangle(int width, int height) {
        super(width,height);
    }

    // Contract: changes the width without changing the height.
    @Override
    public void setWidth(int width) {
        validateDimension(width);
        this.width = width;
    }

    // Contract: changes the height without changing the width.
    @Override
    public void setHeight(int height) {
        validateDimension(height);
        this.height = height;
    }

}
