package at.technikum.solid.lsp.shapes.old;

// LSP violation: preserving equal sides breaks Rectangle's independent setter contracts.
public class SquareOLD extends RectangleOLD {

    public SquareOLD(int side) {
        super(side, side);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
