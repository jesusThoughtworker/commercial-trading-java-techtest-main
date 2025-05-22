package com.global.commtech.test.anagramfinder;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.io.BufferedReader;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        var fileReader = new FileReader(args[0]);
        var bufferedReader = new BufferedReader(fileReader);
        var line = "";
        while ((line = bufferedReader.readLine()) != null) {
            anagramRepository.saveWord(line);
        }

        anagramRepository.listAnagrams().forEach(
                anagramList -> {
                    System.out.println(String.join(",", anagramList));
                }
        );
    }

    @Autowired
    private AnagramRepository anagramRepository;
}
