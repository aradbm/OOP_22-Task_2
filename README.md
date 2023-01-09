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
  * > ___CustomExecutor.java___ -  
    Class that extends threadpoolexecutor, keeps a Executor and a priorityqueue that holds Tasks and callable functions.
  * >___Task.java___ -  
    Class that implements Callable and comparing, holds a Collable of some kind and a TaskType.
  * >___TaskType.uml___ -  
    Enum class. Used to define a task priority.
  * >___Part12est.java___ -  
    Tests for part 2.
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

***
# Ex2 - Part 2
## [Ex2_2](https://github.com/aradbm/OOP_Ex2/tree/master/src/Ex2_2)

In part 2 we are creating asynchronous thread pool with a priority blocking queue and a Executor service that uses the queue.
We use a custom Task with a priority based on enum TaskType.  
1 is the highest priority for computational methods, 2 is for In-Out methods, and 3 for other methods.  
Thus we create an asynchronous executor that executes methods according t their priority.


## [CustomExecutor.java](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_2/CustomExecutor.java)

This class extends ThreadPoolExecutor, and adds method to submit Task type to the Runnable queue,
while also returns the maximum priority in queue in O(1) complexity.  
Thus, each instance has few fields:
1. `static final int MAX_THREADS` - the maximus threads we allow.
2. `static final int MIN_THREADS` - the minimum threads we allow.
3. `static int[] maxPriority` - an array to keep track of the currently queued tasks and the maximum priority.
4. `static BlockingQueue<Runnable> workingBlockingQueue` - a queue for tasks waiting to be executed.
The queue keeps a priority based on the reverse Order.

#### `CustomExecutor()`:
Constructor.
#### `<T> Future<T> submit(Task taskToSubmit)`:
Submit method to add a Task to the queue, returns a future type to save the return value.
#### `<T> Future<T> submit(Callable<T> TaskMethod, TaskType tType)`:
Submit method to add a Callable of any kind with a given TaskType to the queue.
#### `<T> Future<T> submit(Callable<T> TaskMethod)`:
Submit method to add a Callable method, without a TaskType to the queue.
#### `getCurrentMax()`:
This method returns the current maximum priority in queue with O(1) complexity & O(1) space complexity.
#### `gracefullyTerminate()`:
Shutting down the executor after it makes sure every thread ended while stops adding  
more tasks to the blocking priority queue of a task in the queue.

## [Task.java](https://github.com/aradbm/OOP_Ex2/blob/master/src/Ex2_2/Task.java)

This class implements Callable, and comparable interfaces and extends FutureTask<T>.  
A Task object is a Callable method with a TaskType to know the callable priority,   
where 1 is the highest and 10 is the lowest in priority.  
Thus, each instance has few fields:
1. `final TaskType taskType` - a taskType for this task.
2. `final Callable<?> methodToExecute;` - the method associated with this task.

#### `Task(Callable<?> methodToExecute, TaskType tType)`:
Private constructor.
#### `public static <T> Task createTask(Callable<T> methodToExecute, TaskType tType)`:
Factory method to create a Task with a Callable and a TaskType.
#### `public static <T> Task createTask(Callable<T> methodToExecute)`:
Factory method to create a Task with a Callable.
#### `public int getPriority()`:
This method returns the priority of the Task through the TaskType priority.
#### `public Object call() throws Exception`:
Call method executes the method, may throw an exception from within the method to execute.
#### `public TaskType getTaskType()`:
Getter for TaskType.
#### `public int compareTo(Task other)`:
Comparing method with another Task.
Returns 1 if this Task has the higher priority (lower value),  
and -1 otherwise.
#### `public boolean equals(Object o)`:
Check if another object is a Task, with same Callable method and same TaskType.
#### `public int hashCode()`:
Returns the hashcode of the Task.


## Tests
### [Part 1](https://github.com/aradbm/OOP_Ex2/blob/master/src/test/Part1Test.java)
![App Screenshot](https://i.imgur.com/Ph2cmQ6.png)  
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

### [Part 2](https://github.com/aradbm/OOP_Ex2/blob/master/src/test/Part2Test.java)
**Total Tests**
![App Screenshot](https://i.imgur.com/TWylMFK.png)

All the tests we made worked great.  

**CustomExecutor- partialTest**
![App Screenshot](https://i.imgur.com/yI9NHAi.png)
![App Screenshot](https://i.imgur.com/U5Fz23H.png)

Here we can see that the results are what we expected.  
The maximum priority at the moment we checked was 10 (the default) since all tasks who
were in the queue were executed, making the queue empty at that moment.  

**CustomExecutor- Test1**
![App Screenshot](https://i.imgur.com/qnBbJPB.png)
![App Screenshot](https://i.imgur.com/qY9245t.png)

Here we use the customExecutor ToString() which also prints us the Max Priority of
the tasks which are currently in the queue, the priority array, and the blocking queue.  
We can see that sometimes when printing the queue, it is empty since when we called ToString()
of the queue, the tasks were already executed and pulled from the queue.  
We can also see that lastly, the queue is empty and the priority array is all 0 since no task is
in the queue and the Max Priority is set to default which is 10.  

**CustomExecutor- Test2**
![App Screenshot](https://i.imgur.com/77DQuUc.png)
![App Screenshot](https://i.imgur.com/aIQHvpx.png)

Here we use the customExecutor ToString() which also prints us the Max Priority of
the tasks which are currently in the queue, the priority array, and the blocking queue.  
We can see that each time, the queues' head has the lowest priority which mean the queue uses the
comparator as we wished he had, and executes low priority tasks first.

***
## Acknowledgements\Bibliography

- [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)
- [An Introduction to Thread in Java](https://www.simplilearn.com/tutorials/java-tutorial/thread-in-java)
- [ Custom ThreadPoolExecutor - beforeExecute()](https://howtodoinjava.com/java/multi-threading/how-to-use-blockingqueue-and-threadpoolexecutor-in-java/#1-creating-threadpoolexecutor)

***

## Feedback

If you have any feedback, please reach out at sliv22000@gmail.com

***

## 🔗 Links

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/sliv22000)

[![github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ShalevBenDavid)

[![github](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)]()