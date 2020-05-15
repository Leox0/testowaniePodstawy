package aj.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTest {


    @Test
    void shouldCreateUser() {
        //given
        String name = "Adam";
        String surname = "Adamowski";
        Integer age = 20;
        Sex sex = Sex.MALE;

        //when
        User user = new User(name, surname, age, sex);

        //then
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(age, user.getAge());
        assertEquals(sex, user.getSex());
    }

}