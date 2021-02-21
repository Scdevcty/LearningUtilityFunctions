import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class linereader {
	public static void main(String[] args) throws IOException
	{
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("TOVOR_MIX_78.txt")));
		lnr.skip(Long.MAX_VALUE);
		System.out.println(lnr.getLineNumber() + 1);
		lnr.close();
	}
}
