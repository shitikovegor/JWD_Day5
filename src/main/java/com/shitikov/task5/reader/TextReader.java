package com.shitikov.task5.reader;

import com.shitikov.task5.exception.ProjectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {
    public String readFile(String fileName) {
        String text = "";
        Path path = Paths.get(fileName);

        if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
            try (Stream<String> dataStream = Files.lines(path)) {
                text = dataStream.collect(Collectors.joining());
            } catch (IOException | UncheckedIOException e) {
                throw new RuntimeException("Program error.", e);
            }
        }
        return text;
    }

    public String readConsole() throws ProjectException {
        StringJoiner text = new StringJoiner("\n");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null && line.length() != 0) {
                text.add(line);
            }
        } catch (IOException e) {
            throw new ProjectException("Console error");
        }
        return text.toString();
    }
}
