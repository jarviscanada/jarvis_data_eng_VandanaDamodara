Java Grep Application
Introduction
Linux uses the grep command to search for a string of characters in a given directory. The Java Grep Application is an implementation of the Linux grep feature, the application was created using two methods: loops and Lambda/Steam API's. The project was built with Apache Maven and deployed using docker.

Targeted User: The product can be used by anyone who wishes to search through a large data file for a specific text pattern and return the output to another file.

Technologies:

Git | Docker | Java SE 8 [Lambda/Steam Functions] | Apache Maven

Design
Script Descriptions
JavaGrepp.java; This is a public interface file with all the methods required to traverse a directory, read a file, check to see if a line contains the regex_pattern, and return it to another file.

JavaGrepImp.java; This is the loop implementation of the Java Grep Application.

JavaGrepLambdaImp.java; This is the Lambda/Steam implementation of the Java Grep Application.

Pseudocode
Loop Implementation:

matchedLines = []
for file in listFilesRecursively(rootDir)
for line in readLines(file)
if containsPattern(line)
matchedLines.add(line)
writeToFile(matchedLines)
Lambda/Steam Implementation:

lines = new ArrayList[]
lines = lines.getRootPath()
Lines = lines.filter(containsPattern())
writeToFile(lines)
Product Usage
The Java Grep Application takes three arguments:

{regex_pattern} ${src_dir} ${outfile}

regex_pattern: the text pattern you wish to find
src_dir: root directory path
outfile: output file name
Approach 1: Linux Grep command

regex_pattern=".*Romeo.*Juliet.*"
src_dir="./data"
egrep -r ${regex_pattern} ${src_dir}
Approach 2: Java Grep Application

java -jar grep-demo.jar ${regex_pattern} ${src_dir} ${outfile}
Approach 3: Dockerized Java Grep Application

docker pull deelango/core_java/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out deelango/core_java/grep ${regex_pattern} ${src_dir} ${outfile}
Deployment
The Java Grep Application was converted into an image using docker build and uploaded to DockerHub using docker push for easier distribution to users.

Limitations and Improvements
The two biggest limitations have to do with memory and lack of functionality:

This application can only search through a file and return a matching string, but what if I want to search through a file and return all the words that start with a capital A.

The size of the input file is limited by the heap size of the machine running the program. If we want to process a file larger than the memory available, then the program would crash as it would need to load all the data from the file before processing it.

A simple solution would be to use the Lambda/Steam implementation, this would allow only the data that is being operated on to be held in memory.