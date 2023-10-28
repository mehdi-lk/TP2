import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {
    public static void main(String[] arguments) {
        if (arguments.length != 1) {
            System.out.println("Usage: java LineCounter <file_path>");
            System.exit(1);
        }

        String filePath = arguments[0];
        try {
            int linesOfCode = countNonEmptyLines(filePath);
            System.out.println(linesOfCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countNonEmptyLines(String filePath) throws IOException {
        int count = 0;
        boolean inCommentBlock = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Ignore empty lines
                }

                if (line.startsWith("/*")) {
                    inCommentBlock = true;
                }

                if (!inCommentBlock && !line.startsWith("//")) {
                    count++;
                }

                if (line.contains("*/")) {
                    inCommentBlock = false;
                    line = line.substring(line.indexOf("*/") + 2).trim();
                }
            }
        }

        return count;
    }
}
