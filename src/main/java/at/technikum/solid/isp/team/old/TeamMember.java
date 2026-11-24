package at.technikum.solid.isp.team.old;

public interface TeamMember {
    void workOn(String task);

    void approveExpense(String description, double amount);

    void deployRelease(String version);
}
