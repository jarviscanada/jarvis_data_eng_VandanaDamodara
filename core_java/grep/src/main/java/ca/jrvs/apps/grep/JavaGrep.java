package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   * Top Level search workflow
   *
   * @throws Java.io.IOException
   */
  void process() throws IOException;

  /**
   * Traverses a given directory and return all files
   *
   * @param rootDire Input directory return files under RootDir
   */

  List<File> listFiles(String rootDir);

  /**
   * Read a File and return all lines Explain Filereader,Bufferreader , and character Encoding
   *
   * @param inputfile to be read
   * @return lines
   * @throws Illegalargument Exception if a given input file is not a file
   */
  List<String> readLines(File inpputfile);

  /**
   * check if a line contans the regex pattern(passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file Explore: Fileoutputstream,Outputstreamwriter and Bufferedwriter
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  void writeTOFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutfile();

  void setOutFile(String outFile);
}


