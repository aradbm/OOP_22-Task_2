package Ex2_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class numOfLinesThreadPool implements Callable<Integer> {
    private final String fileName;

    /**
     * Constructor
     * 
     * @param fileName- the file's name.
     */
    public numOfLinesThreadPool(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Call method that will run upon submit(). Calculates and returns number of lines for Future object.
     *
     * @return numOfLines - number of lines for "fileName".
     */
    @Override
    public Integer call() {
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
