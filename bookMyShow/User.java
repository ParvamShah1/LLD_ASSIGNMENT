package bookMyShow;

public class User {

    private final String emailId;
    private final String fullName;

    public User(String emailId, String fullName) {
        this.emailId = emailId;
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName + " (" + emailId + ")";
    }
}
