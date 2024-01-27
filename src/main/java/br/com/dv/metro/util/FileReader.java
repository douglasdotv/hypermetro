package br.com.dv.metro.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileReader {

    private static final String EMPTY_FILE_CONTENT_MESSAGE = "File content is empty!";
    private static final String FILE_NOT_FOUND_MESSAGE = "Error! Such a file doesn't exist!";
    private static final String READ_FILE_ERROR_TEMPLATE = "Error while reading file %s: %s";

    private FileReader() {
    }

    public static String readFileAsString(String filePath) {
        Path pathObj = Paths.get(filePath);
        if (Files.exists(pathObj)) {
            try {
                String fileContent = Files.readString(pathObj);
                if (fileContent.isEmpty()) {
                    throw new FileReadException(EMPTY_FILE_CONTENT_MESSAGE);
                }
                return fileContent;
            } catch (IOException ex) {
                throw new FileReadException(String.format(READ_FILE_ERROR_TEMPLATE, filePath, ex.getMessage()), ex);
            }
        } else {
            throw new FileReadException(FILE_NOT_FOUND_MESSAGE);
        }
    }

}
