package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.BiFunction;

@Component
public class IsAnagram implements BiFunction<String, String, Boolean> {

    @Override
    public Boolean apply(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            return Boolean.FALSE;
        }
        var tokenListFirstWord = firstWord.toLowerCase().toCharArray();
        var tokenListSecondWord = secondWord.toLowerCase().toCharArray();

        Arrays.sort(tokenListFirstWord);
        Arrays.sort(tokenListSecondWord);

        return Arrays.equals(tokenListFirstWord, tokenListSecondWord);
    }
}
