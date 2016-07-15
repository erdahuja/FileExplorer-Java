package Logic;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileFilter;

class MyFilter implements FilenameFilter {
	private String extension;

	MyFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File dir, String name) {
		if (extension == null) {
			return true;
		}
		return name.endsWith(extension);
	}

}

public class ScanDemo {

	public static File[] getFiles(File file, String extension) {
		File files[] = null;
		MyFilter filter = new MyFilter(extension);
		if (extension != null) {
			files = file.listFiles(filter);
		} else
			files = file.listFiles();
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		return files;

	}

}
