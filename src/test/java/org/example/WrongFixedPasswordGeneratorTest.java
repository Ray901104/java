package org.example;

public class WrongFixedPasswordGeneratorTest implements PasswordGenerator
{
    @Override
    public String generatePassword()
    {
        return "aa";
    }
}
