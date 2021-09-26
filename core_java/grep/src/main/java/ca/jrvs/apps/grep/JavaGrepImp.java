package ca.jrvs.apps.grep;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;


public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  private String regex;
  private String rootPath;
  private String outFile;


  public static void main(String[] args) {
    if (args.length != 3) {

      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);
    try {
      javaGrepImp.process();
    } catch (Exception ex) {

     javaGrepImp.logger.error("Process failed", ex);

    }
  }
  @Override
  public void process() throws IOException {
    List<String> lines = new ArrayList<>();
    for (File newFiles : listFiles(getRootPath())) {
      for (String line : readLines(newFiles)) {
        if (containsPattern(line)) {
          lines.add(line);
        }
      }
    }
    writeTOFile(lines);
  }


  @Override
  public List<File> listFiles(String rootDir) {
    File dirPath = new File(rootDir);
    File[] fileList = dirPath.listFiles();
    if (fileList == null) {
      try {
        throw new FileNotFoundException("ERROR: root path is empty or cannot access root path.");
      } catch (FileNotFoundException e) {
        logger.error("FileNotFoundException", e);
      }
    }
    List<File> result = new ArrayList<>();
    for (File file : fileList)
      if (file.isFile()) {
        result.add(file);
      } else {
        String subDirName = file.getAbsolutePath();
        /*collect all files in the directory. */
        result.addAll(listFiles(subDirName));
      }

    return result;

  }


  public List<String> readLines(File inpputfile) {
    List<String> lineList = new ArrayList<>();
    try {

      BufferedReader br = new BufferedReader(new FileReader(inpputfile));  //creates a buffering character input stream
      String line;
      line = br.readLine();
      while ((line = br.readLine()) != null) {
        lineList.add(line);//appends line to string buffer

      }
      br.close();    //closes the stream and release the resources

    } catch (IOException e) {

      logger.error("IOException", e);

      logger.error("InputOutput Exception", e);

    }
    return lineList;
  }
  @Override
  public boolean containsPattern(String line) {
    return line.matches(getRegex());
  }

  @Override

  public void writeTOFile(List<String> lines) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(getOutfile()));
    for (String eachLine : lines) {
      writer.write(eachLine + System.lineSeparator());
    }
  writer.close();
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public String getRootPath() {
    return rootPath;
  }

  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  public String getOutfile() {
    return outFile;
  }

  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
