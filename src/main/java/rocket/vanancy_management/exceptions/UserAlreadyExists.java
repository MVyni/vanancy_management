package rocket.vanancy_management.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists.");
    }
}
