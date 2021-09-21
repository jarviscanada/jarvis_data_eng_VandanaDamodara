Java Grep Application
# Introduction
The Java Grep application is used to search plain-text data sets for lines that match a regular expression. This is similar to the functionality found in Unix-like operating systems. The application was was built with an interface and implemented with while/for loops along with an alternate implementation using Streams and Lambda expressions for efficiency. Project management was achived through Maven which also handled dependencies.
Linux uses the grep command to search for a string of characters in a given directory. The Java Grep Application is an implementation of the Linux grep feature, the application was created using two methods: loops and Lambda/Steam API's. The project was built with Apache Maven and deployed using docker.
The product can be used by anyone who wishes to search through a large data file for a specific text pattern and return the output to another file.

## Technologies:
ocker pull deelango/core_java/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out deelango/core_java/grep ${regex_pattern} ${src_dir} ${outfile}
Apache Maven, Git ,Docker , Java SE 8 [Lambda/Steam Functions] 

# Quick Start

The application can be run by following commands:

''''$ mvn clean compile
$ mvn package
$ java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp {regex} {rootPath} {outFile}''''

''''java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp {regex} {rootPath} {outFile}''''

## Design
Script Descriptionsocker pull deelango/core_java/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out deelango/core_java/grep ${regex_pattern} ${src_dir} ${outfile}
JavaGrepp.java; This is a public interface file with all the methods required to traverse a directory, read a file, check to see if a line contains the regex_pattern, and return it to another file.

JavaGrepImp.java; This is the loop implementation of the Java Grep Application.

JavaGrepLambdaImp.java; This is the Lambda/Steam implementation of the Java Grep Application.

# Implemenation
## Pseudocode

The process method is implemented in following way:

''''for (File file: listFiles(path))
for (String line: readLines(file))
if (containsPattern(line))
matchedLines.add(line);
writeToFile(matchedLines);'''''

## Performance Issue

Using Java Grep requires a lot of memory in the JVM especially when parsing all text to be checked by the containsPattern function. Minimum and Maximum memory can be defined by using the -Xms and -Xmx flags to set the memory sizes.

''''java -Xms5m -Xmx30m -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* {rootPath} {outFile}'''''

If the memory was 5Mb and a file was processed with 5Mb then a memory error would occur because there isn't enough memory to complete the process.
This issue can be resolved using Stream API.

# Test

The app was tested manually using sample text files that consisted of varying text patterns and comparing the results with the results obtained from using the Linux grep command on the same text file and pattern.

 ''''java -jar grep-demo.jar ${regex_pattern} ${src_dir} ${outfile}'''''
Approach 3: Dockerized Java Grep Application

# Deployment
The Java Grep Application was converted into an image using docker build and uploaded to DockerHub using docker push for easier distribution to users.

# Limitations and Improvements
The two biggest limitations have to do with memory and lack of functionality:

- The app only searches in a particular file But implemntation can be further improvised by searching and getting output in a pattern file with data.
Search within both the directory specified and its subdirectories for the matching regex pattern

- The size of the input file is limited by the heap size of the machine running the program. If we want to process a file larger than the memory available, then the program would crash as it would need to load all the data from the file before processing it.
A simple solution would be to use the Lambda/Steam implementation, this would allow only the data that is being operated on to be held in memory.