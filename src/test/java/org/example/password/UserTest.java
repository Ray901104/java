package org.example.password;

import org.example.password.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest
{
    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest()
    {
        // given
        User user = new User();

        // when
//        user.initPassword(new CorrectFixedPasswordGeneratorTest());
        user.initPassword(() -> "abcdefgh"); // @FunctionalInterface 적용 후 람다 이용

        // then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합되지 않아 초기화 되지 않는다.")
    @Test
    void passwordTest2()
    {
        // given
        User user = new User();

        // when
        user.initPassword(() -> "aa"); // @FunctionalInterface 적용 후 람다 이용

        // then
        assertThat(user.getPassword()).isNull();
    }
}