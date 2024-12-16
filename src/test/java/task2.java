import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.*;

public class task2 {
    private String tempFilePath = "empFile.txt";

    @BeforeEach
    void setUp() throws IOException {
        File tempFile = new File(tempFilePath);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("Пример содержимого файла");
            }
        }
    }

    @AfterEach
    void tearDown() {
        new File(tempFilePath).delete();
    }

    @Test
    void throwExceptionTest() {
        try {
            throw new RuntimeException("Это непроверяемое исключение");
        } catch (RuntimeException e) {
            Assertions.assertEquals("Это непроверяемое исключение", e.getMessage());
            System.out.println("Тест throwExceptionTest пройден");
        }
    }

    @Test
    void throwCheckedExceptionTest() {
        try {
            throwCheckedException();
        } catch (IOException e) {
            Assertions.assertEquals("Это проверяемое исключение", e.getMessage());
            System.out.println("Тест throwCheckedExceptionTest пройден");
        }
    }

    public void throwCheckedException() throws IOException {
        throw new IOException("Это проверяемое исключение");
    }

    @Test
    void finallyBlockTest() {
        boolean flag = false;

        try {
            throw new Exception("Это исключение");
        } catch (Exception e) {
            Assertions.assertEquals("Это исключение", e.getMessage());
            System.out.println("Тест finallyBlockTest пройден");
        } finally {
            flag = true;
            Assertions.assertTrue(flag);
        }
    }
}