package Ex2_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class numOfLinesThreads implements Runnable {
    private String fileName;
    private int numOfLines;

    /**
     * Constructor
     * 
     * @param fileName
     */
    public numOfLinesThreads(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    /**
     * Returns the number of lines in the files in fileNames array
     * 
     * @param fileName - array of file names
     * @return numOfLines - number of lines in all files
     */
    public int getNumOfLines() {
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

    /**
     * getter method for numOfLines
     * 
     * @return numOfLines
     */
    public int getNum() {
        return numOfLines;
    }

    /**
     * run method for runnable class
     */
    @Override
    public void run() {
        this.numOfLines = getNumOfLines();
    }
}
