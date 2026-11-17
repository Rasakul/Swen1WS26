package at.technikum.solid.lsp.shapes.refactored;

public class Square extends Shape {

    public Square(int side) {
        super(side, side);
    }

    @Override
    public void setWidth(int width) {
        this.setSide(height);
    }

    @Override
    public void setHeight(int height) {
        this.setSide(height);
    }

    private void setSide(int side){
        this.height=side;
        this.width=side;
    }
}
