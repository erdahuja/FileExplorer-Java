package gui;

import java.io.File;

public class ScanDemo {

	static File[] getFiles(File file) {
		File files = new File(file, null);
		File fileArray[] = files.listFiles();
		for(File f:fileArray){
			System.out.println(f.getAbsolutePath());
		}
		return fileArray;

	}

}
