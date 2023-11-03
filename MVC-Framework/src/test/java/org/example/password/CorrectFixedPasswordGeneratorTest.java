package org.example.password;

import org.example.password.PasswordGenerator;

public class CorrectFixedPasswordGeneratorTest implements PasswordGenerator
{
    @Override
    public String generatePassword()
    {
        return "abcdefgh"; // 8자
    }
}
