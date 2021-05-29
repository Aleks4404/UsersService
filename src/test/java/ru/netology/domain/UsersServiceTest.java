package ru.netology.domain;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UsersServiceTest {
    UsersService usersService = new UsersService("John");
    UsersService usersService1 = new UsersService("Alice");
    UsersService usersService2 = new UsersService("Melinda");

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
        System.out.println("Code executes only once");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
        Users user1 = new Users("John", LocalDate.of(1994, 3, 17));
        Users user2 = new Users("Alice", LocalDate.of(1970, 4, 17));
        Users user3 = new Users("Melinda", LocalDate.of(1997, 6, 23));
        List<Users> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersService = new UsersService(usersList);
    }

    @Test
    public void whenCreateNewUserThenReturnListWithNewUser() throws Exception {
        assertThat(usersService.getUsers().size(), is(3));
        usersService.createNewUser("New User", LocalDate.of(1990, 2, 1));
        assertThat(usersService.getUsers().size(), is(4));
    }

    @Test
    public void whenRemoveUserWhenRemoveUserByName(){
        usersService.removeUser("Melinda");
        List<UsersService.Users> usersList = usersService.getUsers();
        assertThat(usersList.size(), is(2));
    }

    @Test
    public void whenCreateNewUserWithoutNameThenThrowCustomFieldException(ExpectedException assertThrown) throws Exception {
        assertThrown.expect(CustomFieldException.class);
        assertThrown.expectMessage("Name could not be empty or null");
        usersService.createNewUser(null, LocalDate.of(1990, 2, 1));
    }

    @Test
    public void whenCreateNewUserWithoutDateOfBirthThenThrowCustomFieldException() throws Exception {
        ExpectedException assertThrown = null;
        assertThrown.expect(CustomFieldException.class);
        assertThrown.expectMessage("Date of birth could not be null");
        usersService.createNewUser("Dave", null);
    }

    @Test
    public void whenIsBirthDayWhenBirthdayThenReturnTrue() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(usersService.getUsers().get(0), LocalDate.of(1990, 2, 1));
        assertFalse(isBirthday);
    }

    @Test
    public void whenIsBirthDayWhenNotBirthdayThenReturnFalse() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(usersService.getUsers().get(0), LocalDate.of(1990, 3, 17));
        assertTrue(isBirthday);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

    @After
    public void afterMethod() {
        System.out.println("Code executes after each test method");
    }

    private class Users {
        public Users(Object john, LocalDate of) {

        }
    }
}