package at.technikum.solid.lsp.shapes.refactored;

public abstract class Shape {

    protected int width;
    protected int height;

    protected Shape(int width, int height) {
        validateDimension(width);
        validateDimension(height);
        this.width = width;
        this.height = height;
    }

    public long getArea() {
        return (long) width * height;
    }

    protected static void validateDimension(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void setHeight(int height);

    public abstract void setWidth(int width);
}
