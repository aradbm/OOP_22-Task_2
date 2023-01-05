## Getting Started

This project is divided into 2 parts.  
First, we will explain about the first part and then about the second part.

The project is about JAVA Threading, Callable && Future interfaces,  
Creating/Reading/Deleting files and Priority Based Task Scandalising.


![Logo](https://www.cronj.com/blog/wp-content/uploads/Untitled-1-1.png)

***

## Included Files
* __Ex2_1__
  * >___Ex2_1.java___ -  
  Class that creates\deletes files and can keeps track 
  of number of lines in total.
  * > ___numOfLinesThreads.java___ -  
  Class that Extends Thread (which implements
  Runnable) and calculates  
  the number of lines in a file.
  * >___numOfLinesThreadPool.java___ -  
  Class that implements Callable and calculates number of lines in a file.
  * >___Ex2_1 Diagram.uml___ -  
  An UML diagram to present all dependencies in part 1.
   * >___Part1Test.java___ -  
   Tests for part 1.

* Ex2_2

***

## About Threading in JAVA
A thread in Java is the direction or path that is taken while a program is being executed.
Generally, all the programs have at least one thread,
known as the main thread, that is provided by the JVM or
Java Virtual Machine at the starting of the program’s execution.  
At this point,
the main() method is invoked by the main thread.

A thread is an execution thread in a program.  
Multiple threads of execution can be run concurrently
by an application running on the Java Virtual Machine. 
The priority of each thread varies.  
Higher priority threads are executed before lower priority threads.

Thread is critical in the program because it enables multiple
operations to take place within a single method.
Each thread in the program often has its own program
counter, stack, and local variable.

***

## Authors

- [@ShalevBenDavid](https://github.com/ShalevBenDavid)

- [@aradbm](https://github.com/aradbm)

***

# Ex2 - Part 1
## [Ex2_1](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_1/Ex2_1.java)


This class helps you creates and delete files while also helps you keep track
of the total number of lines in the files you created using multiple method
where each uses different way: thread-pool, threads or without  
threads at all.

#### `String[] createTextFiles(int n, int seed, int bound)`:
Method that creates "n" text files with number of rows based on
"seed" and "bound" parameters,  
where each row says "hello world!". The method names each file "file_i.txt" where i is the number of file.  
Lastly, the method stores all the files׳ names in a String array and returns it.

#### `int getNumOfLines(String[] fileNames)`:
Method that receives a String array containing files׳ names,  
and counts and returns the total number of rows in them.

#### `int getNumOfLinesThreads(String[] fileNames)`:
Method that receives a String array containing files׳ names,  
and counts and returns the total number of rows in them using threads.  
For each file, a new thread is created that counts the number of rows in it.  
Thus, we sum all the values the threads counted, meaning total rows number, and return it.

#### `int getNumOfLinesThreadPool(String[] fileNames)`:
Method that receives a String array containing files׳ names,  
and counts and returns the total number of rows in them using thread-pool.  
We first create a thread-pool called "threadPool" (ExecutorService object)
which can contain at most "MAX_THREADS" threads (Defined to be number of files).  
For each file, a new thread is created (to count it׳s rows)
and added to the threadPool using submit().  
Also, a Future array object is created to hold the future results we get from each thread.  
All of those result are summed up and returned (this is the total number of rows).  
We also perform shutdown to the threadPool in which previously submitted tasks are executed,  
but no new tasks will be accepted. 


#### `void deleteFiles(String[] fileNames)`:
Method that deletes all the files in "fileNames" array.  
This method helps clean after each test and avoid flooding the system.  
If we fail to delete a specific file, we print there was an error.
***
## [numOfLinesThreads](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_1/numOfLinesThreads.java)
This class extends the Thread class which implements Runnable.  
Thus, each instance (class object) will be a thread that will be executed upon calling start() method.  
The thread purpose is to calculate a specific file׳s number of rows. Thus, each instance has 2 fields:  
1. `String fileName` - files׳ name.
2. `int numOfLines`  - number of rows in the file.

#### `numOfLinesThreads(String fileName)`:
A constructor that creates the thread object.  
The constructor initializes the "numOfLines" field to 0 and "fileName" to the "fileName" parameter given.

#### `void run()`:
The run() method that is executed upon calling start(). This method calls calcNumOfLines() method.

#### `void calcNumOfLines()`:
This method is called whenever a thread is executed, and calculates the total number
of rows in "fileName". When the method is done, the result is stored in the "numOfLines" field. 

#### `int getNumOfLines()`:
Since this is a normal thread, run() is void and thus, we cant return right away rhe result.  
To bypass this, as we mentioned earlier, the result is stored in the "numOfLines" field of the thread object,
and so this getter method gives us access to this result when done (we use join() on each thread to make sure 
they are all done with computations before accessing this field.)

***

## [numOfLinesThreadPool](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_1/numOfLinesThreadPool.java)
This class implements the Callable interface.  
Thus, each instance (class object) will be a thread that will be executed upon calling submit() method.  
The thread will be part of the threadPool we will create, and since we use call(), we will be able to  
return the result for Future object to hold (an Integer).  
The thread purpose is to calculate a specific file׳s number of rows. Thus, each instance has 1 fields:
1. `String fileName` - files׳ name.

#### `numOfLinesThreadPool(String fileName)`:
A constructor that creates the thread object.  
The constructor initializes the "fileName" field to the "fileName" parameter given.

#### `Integer call()`:
The call() method that is executed upon calling submit().  
This method calculates the total number of rows in "fileName" and returns the result for Future object.

***

## [Ex2_1 Diagram](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_1/Ex2_1%20Diagram.uml)
![App Screenshot](https://i.ibb.co/dckFTbz/Ex2-1-Diagram.png)


## Tests
### [Part 1](https://github.com/aradbm/OOP_Ex2/blob/master/src/test/Part1Test.java)
![App Screenshot](https://i.ibb.co/JFJ7Jbd/Screenshot-2023-01-05-at-19-08-07.png)  
The times for each method we used to calculate:  
__Without Threads:__
![App Screenshot](https://i.ibb.co/cwCGYDT/Screenshot-2023-01-05-at-19-08-55.png)
__With Threads:__
![App Screenshot](https://i.ibb.co/gr51M6b/Screenshot-2023-01-05-at-19-08-40.png)
__With Thread Pool:__
![App Screenshot](https://i.ibb.co/C746Sct/Screenshot-2023-01-05-at-19-08-19.png)

What we did here is test each method with the same number of rows.  
Thus, we got a fair comparison between the time it took each method.  
Each method was tested with a large number of files (3,000) and a large bound  
on the number of rows (10,000) so that we can see a noticeable difference between them.

The times we measured ourselves is solely the time it took to measure the number of rows,  
whereas the time measured by the JVM is the time for the whole tests (including creating\deleting files).  
Thus, our timing is more accurate and relevant when comparing between them.

As expected, the ThreadPool solution came 1st with the fastest computing time,
then the thread solution and lastly, the solution with no threads at all.  
- However, it was a bit flappy, and sometimes the thread solution was head of the ThreadPool solution.  
This may be a result of our CPU computing power or optimization issue.
***

## Acknowledgements\Bibliography

- [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)
- [An Introduction to Thread in Java](https://www.simplilearn.com/tutorials/java-tutorial/thread-in-java)

***

## Feedback

If you have any feedback, please reach out at sliv22000@gmail.com

***

## 🔗 Links

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/sliv22000)

[![github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ShalevBenDavid)

[![github](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)]()