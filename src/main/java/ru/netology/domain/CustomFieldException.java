package ru.netology.domain;

import java.time.LocalDate;

import static java.util.Objects.isNull;

class CustomFieldException extends Exception {
    private String message;

    public CustomFieldException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public boolean isBirthDay(UsersService.Users user, LocalDate date) throws CustomFieldException {
        if (isNull(user) || isNull(user.dateOfBirth)) {
            throw new CustomFieldException("User or date of birth is null");
        }
        if (isNull(date)) {
            throw new CustomFieldException("Compare date must not be null");
        }
        return date.getDayOfMonth() == user.dateOfBirth.getDayOfMonth() && date.getMonth().equals(user.dateOfBirth.getMonth());
    }

    private void validateUser(String name, LocalDate dateOfBirth) throws Exception {
        if (isNull(name) || name.isBlank()) {
            throw new CustomFieldException("Name could not be empty or null");
        }
        if (isNull(dateOfBirth)) {
            throw new CustomFieldException("Date of birth could not be null");
        }
    }

}