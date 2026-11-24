package at.technikum.solid.isp.team.old;

public class FinanceManager implements TeamMember {

    private final String name;

    public FinanceManager(String name) {
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
        throw new UnsupportedOperationException("Finance managers do not work on development tasks");
    }

    @Override
    public void approveExpense(String description, double amount) {
        if (description == null || description.isBlank() || !Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Expense needs a description and positive finite amount");
        }
        System.out.println(name + " approved " + description + " for " + amount);
    }

    @Override
    public void deployRelease(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Release version must not be blank");
        }
        throw new UnsupportedOperationException("Finance managers cannot deploy releases");
    }
}
