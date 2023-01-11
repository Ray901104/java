package org.example;

public class User
{
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator)
    {
        // as-is : 내부에서 생성하는 경우에는 결합도가 강하다.
//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

        // to-be : 상위 인터페이스를 만들어 주입받으면 결합도가 약해진다.
        // 랜덤으로 생성되는 패스워드(컨트롤 할 수 없는 부분)를 상황에 따라 PasswordGenerator를 구현한 클래스로부터
        // 주입받기 때문에 컨트롤할 수 있게 된다.
        String randomPassword = passwordGenerator.generatePassword();

        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         */
        if (randomPassword.length() >= 8 && randomPassword.length() <= 12)
        {
            this.password = randomPassword;
        }
    }

    public String getPassword()
    {
        return password;
    }
}
