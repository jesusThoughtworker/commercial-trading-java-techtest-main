package com.global.commtech.test.anagramfinder;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {


    @Autowired
    private AnagramCache anagramCache;

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        var fileReader = new FileReader(args[0]);
        var bufferedReader = new BufferedReader(fileReader);
        var line = "";
        var previousWord = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (previousWord.length() < line.length()) {
                printAnagrams();
            }
            anagramCache.saveWord(line);
            previousWord = line;
        }
        printAnagrams();
    }

    private void printAnagrams() {
        anagramCache.listAnagrams().forEach(
                anagramList -> {
                    System.out.println(String.join(",", anagramList));
                }
        );
        anagramCache.reset();
    }

}
