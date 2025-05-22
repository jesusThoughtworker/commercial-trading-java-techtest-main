package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

class IsAnagramTest {

    @ParameterizedTest
    @MethodSource("testCases")
    public void it_should_not_return_none(String firstWord, String secondWord, Boolean expectedOutput){
        assertThat(isAnagram.apply(firstWord, secondWord))
                .as("It should return the expected output")
                .isEqualTo(expectedOutput);
    }

    private static Stream<Arguments> testCases(){
        return Stream.of(
                Arguments.of("hello", "ohlle", TRUE),
                Arguments.of("hello", "ohllE", TRUE),
                Arguments.of("helloe", "ohllEE", TRUE),
                Arguments.of("hello", "oh", FALSE)
        );
    }

    private static final IsAnagram isAnagram = new IsAnagram();
}