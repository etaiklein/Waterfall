package stopwatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.File;
import java.io.IOException;


class test{
	public static void main(String[] args) throws IOException, InterruptedException{
				
	        //psuedocode. 
	        //get foldername. 
	        String path = "";
	        File Folder = File.createTempFile(path, null);
	        //startwatch
			StopWatch watch1 = new StopWatch("task1");
			File[] myfiles;
			watch1.start();
			while(Folder.exists()){
				//get all files 
				myfiles = Folder.listFiles();
				for (int i = 0; i < myfiles.length; i++){
					//if it hasnt been renamed
					if(myfiles[i].getName().contains(path)){
						
						long name = watch1.getElapsed(MILLISECONDS);
						//rename it
						File myfile = new File(String.valueOf(name));
						boolean success = myfiles[i].renameTo(myfile);
					}
					
				}
				Thread.sleep(10);
				
				
			}
			
		
	}
}