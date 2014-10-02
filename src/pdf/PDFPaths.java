package pdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class PDFPaths
{
	static ArrayList<String> pathsVector = null;
	
	public static ArrayList<Integer> getPathsVector(PDF pdf)
	{
		if (pathsVector == null)
			pathsVector = getPaths();
		HashMap<String, Integer> mapOfPaths = pdf.getSetOfPaths();
		ArrayList<Integer> vector = new ArrayList<Integer>();
		if (mapOfPaths == null) return vector;
		for (String string : pathsVector)
		{
			if (mapOfPaths.containsKey(string))
				vector.add(1);
			else
				vector.add(0);
		}
		return vector;
	}
	
	public static ArrayList<String> getPaths()
	{
		ArrayList<String> ret = new ArrayList<String>();
		File file = new File("paths");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String string;
			while ((string = br.readLine()) != null)
			{
				ret.add(string);
			}
			br.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	public static void storePopularPaths(int i)
	{
		try
		{
			(new File("paths")).delete();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("paths")));
			HashMap<String, Integer> allPathsCount = getAllPathsCount();
			for (String string : allPathsCount.keySet())
			{
				if (allPathsCount.get(string) > i) bw.write(string + "\n", 0, string.length() + 1);
			}
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public static HashMap<String, Integer> getAllPathsCount()
	{
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		String pathMal = "/Users/chenqiming/Desktop/m/";
		File fileMal = new File(pathMal);
		File flistMal[] = fileMal.listFiles();
		String pathCln = "/Users/chenqiming/Desktop/c/";
		File fileCln = new File(pathCln);
		File flistCln[] = fileCln.listFiles();
		for (File file : flistCln)
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null)
				{
					int sep = line.lastIndexOf(':');
					if (sep < 0)
						continue;
					String path = line.substring(0, sep);
					String countString = line.substring(sep + 1);
					int count = Integer.parseInt(countString);
					count = 1;
					/*
					 * count what?
					 */
					if (ret.containsKey(path))
						ret.put(path, ret.get(path) + count);
					else
						ret.put(path, count);
				}
				br.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for (File file : flistMal)
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null)
				{
					int sep = line.lastIndexOf(':');
					String path = line.substring(0, sep);
					String countString = line.substring(sep + 1);
					int count = Integer.parseInt(countString);
					if (ret.containsKey(path))
						ret.put(path, ret.get(path) + count);
					else
						ret.put(path, count);
				}
				br.close();
			} catch (Exception e)
			{
			}
		}
		return ret;
	}

	public static void storePaths()
	{
		String pathMal1 = "/Users/chenqiming/Desktop/intelligencefiles/20141002T021020";
		File fileMal1 = new File(pathMal1);
		File flistMal1[] = fileMal1.listFiles();
		for (File file : flistMal1)
		{
			File pathsFile = new File("/Users/chenqiming/Desktop/c/" + file.getName() + ".txt");
			try
			{
				if (!pathsFile.createNewFile()) throw new Exception("new file failed");
				PDF testPdf = new PDF(file);
				HashMap<String, Integer> mapOfPaths = testPdf.getSetOfPaths();
				BufferedWriter out = new BufferedWriter(new FileWriter(pathsFile));
				for (String st : mapOfPaths.keySet())
				{
					String line = st + ":" + mapOfPaths.get(st);
					out.write(line + "\n");
				}
				out.flush();
				out.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
