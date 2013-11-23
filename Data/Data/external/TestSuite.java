import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;

public class TestSuite {
	//private static String SOURCE_FOLDER = "C:\\wamp\\www\\TestDataIn";
	//private static String OUTPUT_FOLDER = "C:\\wamp\\www\\src\\Data";
	//private static String PROCESS = "firefox.exe";
	//private static String FILES = "C:\\wamp\\www\\src\\src";
		private static String SOURCE_FOLDER = "C:\\KNAgent\\Data";
			private static String OUTPUT_FOLDER = "C:\\Users\\knadmin\\workspace\\www\\src\\Data";
		private static String PROCESS = "TxnPlaybackEngine.exe";
			private static String FILES = "C:\\Users\\knadmin\\workspace\\www\\src\\src";

	public TestSuite(){}

	public TestSuite(String src, String out, String pro, String fil){
		SOURCE_FOLDER = src;
		OUTPUT_FOLDER = out;
		PROCESS = pro;
		FILES = fil;

	}

	public LoggerTest LoggerTest(){
		LoggerTest logger = new LoggerTest(OUTPUT_FOLDER);
		logger.init();
		logger.log("created");
		return logger;
	}

	public LoggerTest MailTest(){
		LoggerTest logger = LoggerTest();
		logger = MailTest(logger);
		return logger;
	}

	public LoggerTest MailTest(LoggerTest logger){
		Mail2 m = new Mail2(OUTPUT_FOLDER,logger);
		System.out.println("mailing...");
		m.zipandmail();
		return logger;
	}

	public LoggerTest CopyFilesTest(){
		LoggerTest logger = LoggerTest();
		logger = CopyFilesTest(logger);
		return logger;
	}

	public LoggerTest CopyFilesTest(LoggerTest logger){
		CopyFiles c = new CopyFiles(SOURCE_FOLDER, OUTPUT_FOLDER, logger);
		LoggerTest testdir = new LoggerTest(SOURCE_FOLDER);
		logger.log("creating file...." + testdir.init());
		logger.log(testdir.getPath());
		c.run(!(new File(testdir.getPath()).exists()));
		logger.log("copied!");
		return logger;
	}


	public static void main(String args[]) throws IOException, MessagingException{
		new File(SOURCE_FOLDER).mkdir();
		DeleteDirectory d = new DeleteDirectory(SOURCE_FOLDER);
		d.Delete();
		d = new DeleteDirectory(OUTPUT_FOLDER);
		d.Delete();

		TestSuite t = new TestSuite();
		LoggerTest myLogger = t.CopyFilesTest();

		try {
			FileUtils.copyDirectory(new File(FILES), new File(OUTPUT_FOLDER));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(new File(OUTPUT_FOLDER).toString());

		t.MailTest(myLogger);

		d.Delete();
		d = new DeleteDirectory(SOURCE_FOLDER);
		d.Delete();
	}
}