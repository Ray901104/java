package org.example.calculatorWebApplication;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest
{
    // 단 건인 경우 : operand1=11
    // 다 건인 경우 : List<QueryString>
    @Test
    void createTest()
    {
        QueryString queryString = new QueryString("operand1", "11");

        assertThat(queryString).isNotNull();
    }
}
