import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class HuffmanCompressor 
{
	public HuffmanTree decoder;
	/**
	 * Compresses the passed file
	 * @param filename	The name of the original file
	 * @throws IOException
	 */
	public void compress(String filename) throws IOException 
	{
		Scanner scan = new Scanner(new File(filename));
		int[] asciiValues = new int[256];
		while(scan.hasNextLine()) 
		{
			String line = scan.nextLine();
			if(!line.equals("")) 
			{
				String[] parts = line.split("");
				for(String temp : parts) 
				{
					asciiValues[(int) temp.charAt(0)]++;
				}
			}
			asciiValues[(int)'\n']++;
		}
		scan.close();
		decoder = new HuffmanTree(asciiValues);
		decoder.write(filename.substring(0, filename.indexOf('.')) + ".code");

		TreeMap<Character, String> charToPath = new TreeMap<Character, String>();
		scan = new Scanner(new File(filename.substring(0, filename.indexOf('.')) + ".code"));
		while(scan.hasNextLine()) 
		{
			char currChar = (char) Integer.parseInt(scan.nextLine());
			String path = scan.nextLine();
			charToPath.put(currChar, path);
		}
		
		BitOutputStream out = new BitOutputStream(filename.substring(0, filename.indexOf('.')) + ".short");
		scan = new Scanner(new File(filename));
		while(scan.hasNextLine()) 
		{
			String line = scan.nextLine();
			if(!line.equals("")) 
			{
				String[] parts = line.split("");
				for(String temp : parts) 
				{
					String[] path = (charToPath.get(temp.charAt(0))).split("");
					for(String bit : path)
						out.writeBit(Integer.parseInt(bit));
				}
			}
			String[] newLinePath = charToPath.get('\n').split("");
			for(String bit : newLinePath)
				out.writeBit(Integer.parseInt(bit));
		}
		scan.close();
		String[] path = charToPath.get((char) 256).split("");
		for(String bit : path) {
			out.writeBit(Integer.parseInt(bit));
		}
		out.close();
		
	}
	/**
	 * Taking in the compressed filename and expanding it
	 * @param codeFile	The name of the compressed file
	 * @param fileName	The name of the file to output to
	 * @throws IOException
	 */
	public void expand(String codeFile, String fileName) throws IOException 
	{
		decoder.decode(new BitInputStream(codeFile.substring(0, codeFile.indexOf('.')) + ".short"), fileName);
	}
}