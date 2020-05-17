package aj.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static aj.model.Group.*;
import static org.junit.jupiter.api.Assertions.*;

class GroupTest {


    @Test
    public void shouldAddUserCorrectly() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        int initialGroupSize = group.getGroupSize();
        int expectedGroupSize = 1;

        //when
        group.addUser(user);

        //then
        assertEquals(initialGroupSize + 1, group.getGroupSize());
        assertEquals(expectedGroupSize, group.getGroupSize());
        User firstUser = group.getUsers().get(0);
        assertEquals(user, firstUser);

    }

    @Test
    public void shouldNotAllowToYoungUserToJoinTheGroup() {
        //given
        User user = new User("Adam", "Adamowicz", 5, Sex.MALE);
        Group group = new Group("Adamowe", true);

        int exprectedGroupSize = 0;
        int initialGroupSize = group.getGroupSize();

        //when
        Executable executable = () -> group.addUser(user);

        //then
        GroupOperationException groupOperationException = assertThrows(GroupOperationException.class, executable);
        assertEquals(USER_NEEDS_TO_BE_OF_LEGAL_AGE, groupOperationException.getMessage());

        //then additional
        assertEquals(initialGroupSize, group.getGroupSize());
        assertEquals(exprectedGroupSize, group.getGroupSize());


    }

/*    @Test
    public void shouldNotAllowToYoungUserToJoinTheGroup(){
        //given
        User user = new User("Adam", "Adamowicz", 5, Sex.MALE);
        Group group = new Group("Adamowe", true);

        //when
        try {
            group.addUser(user);
        } catch (GroupOperationException e) {

            //then
            assertEquals(USER_NEEDS_TO_BE_OF_LEGAL_AGE,e.getMessage());
        }


    }*/

    @Test
    public void shouldNotAllowToRemoveAdmin() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        group.addUser(user);

        int expectedGroupSize = 1;
        int initialGroupSize = group.getGroupSize();

        //when
        Executable executableRemove = () -> group.removeUser(user);

        //then
        GroupOperationException groupOperationException = assertThrows(GroupOperationException.class, executableRemove);
        assertEquals(CANNOT_REMOVE_ADMIN, groupOperationException.getMessage());
        assertEquals(initialGroupSize, group.getGroupSize());
        assertEquals(expectedGroupSize, group.getGroupSize());


    }

    @Test
    @Disabled // wyłączamy, bo poniżej jest jupiter
    public void shouldRemoveUserCorrectly() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        User user2 = new User("Karol", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        group.addUser(user);
        group.addUser(user2);

        int expectedGroupSize = 1;
        int userNotPresent = -1;

        //when
        group.removeUser(user2);

        //then
        assertEquals(expectedGroupSize, group.getGroupSize());
        assertEquals(user, group.getUsers().get(0));
        assertEquals(userNotPresent, group.getUsers().indexOf(user2));
    }

    @Test
    public void shouldRemoveUserCorrectlyJupiter() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        User user2 = new User("Karol", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        group.addUser(user);
        group.addUser(user2);

        int expectedGroupSize = 1;
        int userNotPresent = -1;

        //when
        group.removeUser(user2);

        //then
        assertEquals(expectedGroupSize, group.getGroupSize());
        assertEquals(user, group.getUsers().get(0));
        assertEquals(userNotPresent, group.getUsers().indexOf(user2));
    }

    @Test
    public void firstAddedUserShouldBeAnAdmin() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        //when
        group.addUser(user);

        //then
        assertEquals(user, group.getAdmin());
    }

    @Test
    public void shouldChangeAdminCorrectly() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        User user2 = new User("Karol", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        group.addUser(user);
        group.addUser(user2);
        User initialAdmin = group.getAdmin();

        //when
        group.changeGroupAdmin(user2);

        //then
        assertEquals(user2, group.getAdmin());
        assertEquals(initialAdmin, user);

    }
    @Test
    public void shouldNotAllowToChangeAdmin() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        User user2 = new User("Karol", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        group.addUser(user);
        group.addUser(user2);
        User initialAdmin = group.getAdmin();

        //when
        Executable executableChangeGroup = () -> group.changeGroupAdmin(user);

        //then
        GroupOperationException groupOperationException = assertThrows(GroupOperationException.class, executableChangeGroup);
        assertEquals(NEW_ADMIN_MUST_BE_DIFFERENT_USER, groupOperationException.getMessage());

        assertEquals(user, group.getAdmin());
        assertEquals(initialAdmin, user);

    }


}
