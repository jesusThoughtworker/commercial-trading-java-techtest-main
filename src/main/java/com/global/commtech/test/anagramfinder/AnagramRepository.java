package com.global.commtech.test.anagramfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class AnagramRepository {

    public void saveWord(String word){
        var anagramKey = ANAGRAMS_MEMORY.keySet().stream()
                .filter(anagram -> isAnagram.apply(word, anagram))
                .findFirst();

        var entryList = new ArrayList<String>();

        if (anagramKey.isPresent()){
            entryList = (ArrayList<String>) ANAGRAMS_MEMORY.get(anagramKey.get());
            entryList.add(word);
            ANAGRAMS_MEMORY.put(anagramKey.get(), entryList);
            return;
        }

        entryList.add(word);
        ANAGRAMS_MEMORY.put(word, entryList);
    }

    public Collection<Collection<String>> listAnagrams(){
        return ANAGRAMS_MEMORY.values().stream().toList();
    }

    public void reset() {
        ANAGRAMS_MEMORY = new HashMap<>();
    }

    private Collection<String> getAnagramsList(String anagramKey){
        return ANAGRAMS_MEMORY.get(anagramKey);
    }

    @Autowired
    private IsAnagram isAnagram;

    private static HashMap<String, Collection<String>> ANAGRAMS_MEMORY = new HashMap<>();

}
