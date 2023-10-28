import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

public class FileAgeChecker {

    public static void main(String[] arguments) {
        if (arguments.length != 1) {
            System.out.println("Usage: java FileAgeChecker <file_path>");
            System.exit(1);
        }

        String filePath = arguments[0];
        File targetFile = new File(filePath);

        if (targetFile.exists()) {
            Path targetPath = Paths.get(filePath);

            try {
                BasicFileAttributes fileAttributes = java.nio.file.Files.readAttributes(targetPath,
                        BasicFileAttributes.class);
                FileTime lastModifiedTime = fileAttributes.lastModifiedTime();
                long ageInMillis = System.currentTimeMillis() - lastModifiedTime.toMillis();
                long ageInDays = ageInMillis / (24 * 60 * 60 * 1000); // Convert to days

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date lastModifiedDate = new Date(lastModifiedTime.toMillis());

                System.out.println("The file was last modified on: " + dateFormat.format(lastModifiedDate));
                System.out.println("File age in days: " + ageInDays);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }
}
