package com.amigoscode.controller;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
