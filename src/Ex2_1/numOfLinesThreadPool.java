package Ex2_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class numOfLinesThreadPool implements Callable<Integer> {
    private String fileName;

    /**
     * Constructor
     * 
     * @param fileName
     */
    public numOfLinesThreadPool(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() throws Exception {
        int numOfLines = 0;
        String fileName = this.fileName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                numOfLines++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return numOfLines;
    }
}
