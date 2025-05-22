package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@Component
public class AnagramRepository {

    private static HashMap<String, Collection<String>> ANAGRAMS_MEMORY = new HashMap<>();


    public void saveWord(String word){
        var anagramKey = getAnagramKey(word);

        if (ANAGRAMS_MEMORY.containsKey(anagramKey)){
            var entryList = (ArrayList<String>) ANAGRAMS_MEMORY.get(anagramKey);
            entryList.add(word);
            ANAGRAMS_MEMORY.put(anagramKey, entryList);
            return;
        }

        var entryList = new ArrayList<String>();
        entryList.add(word);
        ANAGRAMS_MEMORY.put(anagramKey, entryList);
    }

    private static String getAnagramKey(String word) {
        var anagramTokens = word.toLowerCase().toCharArray();
        Arrays.sort(anagramTokens);
        return new String(anagramTokens);
    }

    public Collection<Collection<String>> listAnagrams(){
        return ANAGRAMS_MEMORY.values().stream().toList();
    }

    public void reset() {
        ANAGRAMS_MEMORY = new HashMap<>();
    }

}
