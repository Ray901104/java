package org.example;

public class CorrectFixedPasswordGeneratorTest implements PasswordGenerator
{
    @Override
    public String generatePassword()
    {
        return "abcdefgh"; // 8자
    }
}
