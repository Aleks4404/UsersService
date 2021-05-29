package ru.netology.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UsersService {
    private List<Users> users;

    public UsersService(List<Users> users) {
        this.users = users;
    }

    public UsersService(Object usersList) {

    }

    public List<Users> getUsers() {
        return users;
    }

    public List<Users> createNewUser(String name, LocalDate dateOfBirth) throws Exception {
        validateUser(name, dateOfBirth);
        Users user = new Users(name, dateOfBirth);
        users.add(user);
        return users;
    }

    private void validateUser(Object name, LocalDate dateOfBirth) {

    }

    public void removeUser(String name) {
        users = users.stream().filter(it -> !it.name.equals(name)).collect(Collectors.toList());
    }

    public boolean isBirthDay(Users users, LocalDate date) {
        return false;
    }

    class Users {
        public String name;
        public LocalDate dateOfBirth;

        public Users(String name, LocalDate dateOfBirth) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
        }
    }
}

