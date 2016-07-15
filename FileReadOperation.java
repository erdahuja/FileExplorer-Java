package Logic;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReadOperation {

	public static String readFile(String filePath) throws IOException {
		final int EOF = -1;
		FileInputStream fi = new FileInputStream(filePath);
		int singleByte = fi.read();
		StringBuilder sb = new StringBuilder();
		while (singleByte != EOF) {
			sb.append((char) singleByte);
			singleByte = fi.read();
		}
		fi.close();
		return sb.toString();
	}

}
