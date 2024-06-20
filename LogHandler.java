import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogHandler {
    private static final String LOG_FILE_PATH = "log.txt";

    public static void writeLog(String log) {
        try (FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}