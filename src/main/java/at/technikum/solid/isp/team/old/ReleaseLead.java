package at.technikum.solid.isp.team.old;

public class ReleaseLead implements TeamMember {

    private final String name;

    public ReleaseLead(String name) {
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
        System.out.println(name + " is coordinating " + task);
    }

    @Override
    public void approveExpense(String description, double amount) {
        if (description == null || description.isBlank() || !Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Expense needs a description and positive finite amount");
        }
        throw new UnsupportedOperationException("Release leads cannot approve expenses");
    }

    @Override
    public void deployRelease(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Release version must not be blank");
        }
        System.out.println(name + " deployed release " + version);
    }
}
