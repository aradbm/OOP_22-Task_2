package Ex2_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class numOfLinesThreads extends Thread {
    private final String fileName;
    private int numOfLines;

    /********************************* Constructor ********************************* /
     /**
     * Constructor.
     * @param fileName - the file's name.
     */
    public numOfLinesThreads(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    /********************************* Run Method ********************************* /
     /**
     * Run method that will run upon start(). Calls calcNumOfLines() method to calculate numOfLines.
     */
    @Override
    public void run() {
        calcNumOfLines();
    }

    /********************************* Calculate numOfLines Method  ********************************* /
     /**
     * Calculates number of lines of the file "fileName".
     */
    public void calcNumOfLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            while (reader.readLine() != null) {
                this.numOfLines++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /********************************* Return "numOfLines" Method ********************************* /
     /**
     *
     * @return numOfLines - number of lines for "fileName".
     */
    public int getNumOfLines() {
        return numOfLines;
    }
}
