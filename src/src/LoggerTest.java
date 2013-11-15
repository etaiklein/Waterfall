import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

 
public class LoggerTest {
 public static String PATH = "C:\\Users\\knadmin\\workspace\\www\\src\\Data\\logger";
 private static SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
 private static SimpleDateFormat nameformat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss-SSS");
 private static String fullpath = "";
 
 public LoggerTest(){}
 
 public LoggerTest(String path){
	 PATH = path;
 }
 
 public boolean init(){
//clear the file
	fullpath = PATH + "\\" + String.valueOf("log-" + nameformat.format(new Date())) + ".txt";
	File file = new File(fullpath);
	file.delete();
	boolean mybool = false;
	try {
		mybool = file.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return mybool; } 
 
 public void log(String mystring){
	 try {
		    System.out.println(mystring);
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fullpath, true)));
		    out.println(sdfDate.format(new Date()));
		    out.println(mystring);
		    out.close();
		} catch (IOException e) {
		} 
	 }
 
 public String getPath(){
	 return fullpath;
 }

 
public static void main(String[] args) {
 LoggerTest logger = new LoggerTest("C:\\wamp\\www\\src\\logger");
 logger.init();
 logger.log("message1");
 logger.log("message2");

 }
}