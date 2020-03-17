package com.xuanwugate.authentication.jwt;

/**
 * Token
 */
public class Token {
    public String name;
    public String description;

    public Token() {
    }

    public Token(String name, String description) {
        this.name = name;
        this.description = description;
    }
}