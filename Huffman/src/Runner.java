import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException 
	{
		
		String fileName = "War and Peace";
		HuffmanCompressor compressor = new HuffmanCompressor();
		long startTime = System.nanoTime();
		compressor.compress(fileName + ".txt");
		long compressTime = System.nanoTime() - startTime;
		compressor.expand(fileName + ".code", fileName + ".new");
		long expandTime = (System.nanoTime()-startTime)-compressTime;
		System.out.println("in milliseconds : " + compressTime / 1000000 + "\nin milliseconds : " + expandTime / 1000000);
	}
}