package com.example.pharmacy_mangement_system;

import java.util.Objects;

public class CredentialValidator {
    public static boolean validate(String username, String password)
    {
        if(Objects.equals(username, "") && Objects.equals(password, ""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
