package com.global.commtech.test.anagramfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@Component
public class AnagramRepository {

    private static HashMap<String, Collection<String>> ANAGRAMS_MEMORY = new HashMap<>();

    public AnagramRepository(IsAnagram isAnagram){
        this.isAnagram = isAnagram;
    }

    public void saveWord(String word){
        var anagramTokens = word.toLowerCase().toCharArray();
        Arrays.sort(anagramTokens);
        var anagramKey = new String(anagramTokens);

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

    public Collection<Collection<String>> listAnagrams(){
        return ANAGRAMS_MEMORY.values().stream().toList();
    }

    public void reset() {
        ANAGRAMS_MEMORY = new HashMap<>();
    }

    private IsAnagram isAnagram;
}
