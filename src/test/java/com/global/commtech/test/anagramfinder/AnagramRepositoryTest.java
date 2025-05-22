package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AnagramRepositoryTest {

    @Test
    public void it_should_not_return_null_listing_words(){
        assertThat(anagramRepository.listAnagrams())
                .as("It should not return null")
                .isNotNull();
    }

    @Test
    public void it_should_save_an_anagram_when_it_is_not_saved(){
        anagramRepository.saveWord(FIRST_ANAGRAM);
        assertThat(anagramRepository.listAnagrams())
                .as("It should return the expected anagrams list")
                .isEqualTo(List.of(List.of(FIRST_ANAGRAM)));
    }

    @Test
    public void it_should_save_an_anagram_in_the_same_anagram_list(){
        anagramRepository.saveWord(FIRST_ANAGRAM);
        anagramRepository.saveWord(SECOND_ANAGRAM);
        assertThat(anagramRepository.listAnagrams().toArray()[0])
                .as("It should return the expected content in the anagram list")
                .isEqualTo(List.of(FIRST_ANAGRAM, SECOND_ANAGRAM));
    }

    @Test
    public void it_should_save_another_anagram_in_other_anagram_list(){
        anagramRepository.saveWord(FIRST_ANAGRAM);
        anagramRepository.saveWord(SECOND_ANAGRAM);
        anagramRepository.saveWord(NO_ANAGRAM);

        assertThat(anagramRepository.listAnagrams().size())
                .as("It should return two anagrams list")
                .isEqualTo(2);
    }

    private static final String FIRST_ANAGRAM = "Hello";
    private static final String SECOND_ANAGRAM = "hoLLE";

    private static final String NO_ANAGRAM = "cat";

    private static final Collection<String> FIRST_ANAGRAM_LIST = List.of(SECOND_ANAGRAM, FIRST_ANAGRAM);
    private static final Collection<String> SECOND_ANAGRAM_LIST = List.of(NO_ANAGRAM);

    @BeforeEach
    public void setupIsAnagram(){
        Mockito.lenient().when(isAnagram.apply(SECOND_ANAGRAM, FIRST_ANAGRAM)).thenReturn(TRUE);
        Mockito.lenient().when(isAnagram.apply(NO_ANAGRAM, FIRST_ANAGRAM)).thenReturn(FALSE);
    }

    @AfterEach
    public void resetRepository(){
        anagramRepository.reset();
    }

    @Mock
    private IsAnagram isAnagram;

    @InjectMocks
    private AnagramRepository anagramRepository;
}