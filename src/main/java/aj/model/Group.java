package aj.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Group {

    public static final String USER_NEEDS_TO_BE_OF_LEGAL_AGE = "Użytkownik musi być pełnoletni żeby dołączyć do tej grupy";
    public static final String CANNOT_REMOVE_ADMIN = "Administrator nie może być usunięty z grupy, należy najpierw zmienić administratora";
    public static final String NEW_ADMIN_MUST_BE_DIFFERENT_USER = "Nowy administrator musi być innym urzytkownikiem";
    public static final int LEGAL_AGE = 18;

    private String name;
    private List<User> users;
    private boolean majorityRestriction;
    private User admin;
    private UUID id;

    public Group(String name) {
        this.name = name;
        users = new ArrayList<>();
        id = UUID.randomUUID();
    }

    public Group(String name, boolean majorityRestriction) {
        this(name);
        this.majorityRestriction = majorityRestriction;
    }

    public void addUser(User user) throws GroupOperationException {
        if (majorityRestriction && user.getAge() < LEGAL_AGE) {
            throw new GroupOperationException(USER_NEEDS_TO_BE_OF_LEGAL_AGE);
        }
        users.add(user);
        if (getGroupSize() == 0) {
            admin = user;
        }
    }

    public void removeUser(User user) throws GroupOperationException {
        if (user.equals(admin)) {
            throw new GroupOperationException(CANNOT_REMOVE_ADMIN);
        }
        users.remove(user);
    }

    public void changeGroupAdmin(User newAdmin) throws GroupOperationException {
        if (newAdmin.equals(admin)) {
            throw new GroupOperationException(NEW_ADMIN_MUST_BE_DIFFERENT_USER);
        }
        admin = newAdmin;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getGroupSize() {
        return users.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
