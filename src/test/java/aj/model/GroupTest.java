package aj.model;

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
    public void shouldNotAllowRemoveAdminUser() {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");

        Executable executable = () -> group.addUser(user);

        int expectedGroupSize = 1;

        //when
        Executable executableRemove = () -> group.removeUser(user);

        //then
        GroupOperationException groupOperationException = assertThrows(GroupOperationException.class, executableRemove);
        assertEquals(CANNOT_REMOVE_ADMIN, groupOperationException.getMessage());


    }

    @Test
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
    public void firstAddedUserShouldBeAnAdmin() throws GroupOperationException {
        //given
        User user = new User("Adam", "Adamowicz", 25, Sex.MALE);
        Group group = new Group("Adamowe");
        
        //when
        group.addUser(user);

        //then
        assertEquals(user, group.getAdmin());
    }

}
