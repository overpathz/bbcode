package com.overpathz.bbhomework.domain;

import lombok.Data;

import java.time.LocalDate;


@Data
public final class User {
    private final String username;
    private final Integer age;
    private final String password;
    private final LocalDate birthDate;
}
