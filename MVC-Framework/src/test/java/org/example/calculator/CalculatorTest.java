package org.example.calculator;

import org.example.calculator.operator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

/**
 * 요구사항
 * 간단한 사칙연산을 할 수 있다.
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0으로 나누는 경우 IllegalArgumentException 예외를 발생시킨다.
 * MVC패턴 (Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest 
{
    @DisplayName("덧셈 연산을 정상적으로 수행한다.")
    @Test
    void additionTest()
    {
        int result = Calculator.calculate(new PositiveNumber(1), "+", new PositiveNumber(2));

        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 정상적으로 수행한다.")
    @Test
    void subtractionTest()
    {
        int result = Calculator.calculate(new PositiveNumber(1), "-", new PositiveNumber(2));

        assertThat(result).isEqualTo(-1);
    }

    @DisplayName("모든 연산을 정상적으로 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result)
    {
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult()
    {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }

//    최종 리팩토링 이후
//    해당 메소드는 validation 클래스 (PositivaNumber) 에서 이미 양수만을 걸러서 인자로 넘겨주기 때문에 더 이상 필요없는 메소드이다.
//    @DisplayName("0으로 나누는 경우 IllegalArgumentException 예외를 발생시킨다.")
//    @Test
//    void calculateExceptionTest()
//    {
//        assertThatCode(() -> Calculator.calculate(new PositiveNumber(10), "/", new PositiveNumber(0)))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("0으로는 나눌 수 없습니다.");
//    }
}
