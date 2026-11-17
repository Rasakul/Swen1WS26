package at.technikum.solid.lsp.shapes.old;

public class RectangleOLD {

    private int width;
    private int height;

    public RectangleOLD(int width, int height) {
        validateDimension(width);
        validateDimension(height);
        this.width = width;
        this.height = height;
    }

    // Contract: changes the width without changing the height.
    public void setWidth(int width) {
        validateDimension(width);
        this.width = width;
    }

    // Contract: changes the height without changing the width.
    public void setHeight(int height) {
        validateDimension(height);
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getArea() {
        return (long) width * height;
    }

    private static void validateDimension(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
    }
}
