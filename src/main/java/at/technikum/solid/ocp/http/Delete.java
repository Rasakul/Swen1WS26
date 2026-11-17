package at.technikum.solid.ocp.http;

public class Delete extends Http {

    private final int id;

    protected Delete(int id) {
        super("Delete");
        this.id = id;
    }
}
