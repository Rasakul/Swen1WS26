package at.technikum.solid.isp.team.old;

public class JuniorDeveloper implements TeamMember {

    private final String name;

    public JuniorDeveloper(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        this.name = name;
    }

    @Override
    public void workOn(String task) {
        if (task == null || task.isBlank()) {
            throw new IllegalArgumentException("Task must not be blank");
        }
        System.out.println(name + " is working on " + task);
    }

    @Override
    public void approveExpense(String description, double amount) {
        if (description == null || description.isBlank() || !Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Expense needs a description and positive finite amount");
        }
        throw new UnsupportedOperationException("Junior developers cannot approve expenses");
    }

    @Override
    public void deployRelease(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Release version must not be blank");
        }
        throw new UnsupportedOperationException("Junior developers cannot deploy releases");
    }
}
