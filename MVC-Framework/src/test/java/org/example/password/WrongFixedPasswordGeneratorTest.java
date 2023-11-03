package org.example.password;

import org.example.password.PasswordGenerator;

public class WrongFixedPasswordGeneratorTest implements PasswordGenerator
{
    @Override
    public String generatePassword()
    {
        return "aa";
    }
}
