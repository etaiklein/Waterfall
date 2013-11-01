



import java.io.File;


public class Renamer implements Runnable{
	File afile = new File("");

	public Renamer(File bfile){
		afile = bfile;
	}

	public void run() {
	        long starttime = System.nanoTime();
			double name;
			//psuedocode. 
	        //get foldername. 			
			
			
	        //startwatch
			File[] myfiles;
			while(afile.exists()){
				//get all files 
				myfiles = afile.listFiles();
				for (int i = 0; i < myfiles.length; i++){
					//if it hasnt been renamed
					if(myfiles[i].getName().contains("scap")){
						System.out.println(myfiles[i].getName());
						name = System.nanoTime() -  starttime;
						name = ((long)name/1000000000.0);
						//rename it
						File myfile = new File(afile.getPath()  + "\\" +String.format("%.3f",name)+".jpg");
						boolean success = myfiles[i].renameTo(myfile);
						if (success){
							System.out.println("name changed to" + name);
							LoggerTest logger = new LoggerTest();
							logger.log("name changed to" + name);
						}
					}
					
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
	}

	
}