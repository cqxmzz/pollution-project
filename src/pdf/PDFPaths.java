package pdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
	
	class Score
	{
		String pathString;
		public double num;
		public Score(String p, double n)
		{
			pathString = p;
			num = n;
		}
	};
	
	public void storePopularPaths(int i)
	{
		try
		{
			List<Score> scoreList = new ArrayList<Score>();
			//(new File("paths")).delete();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("paths.cln")));
			HashMap<String, Integer> malPathsCount = getAllMalPathsCount();
			HashMap<String, Integer> clnPathsCount = getAllClnPathsCount();
			for (String string : malPathsCount.keySet())
			{
				double mal = 1.0;
				double cln = 1.0;
				if (malPathsCount.keySet().contains(string))
					mal += malPathsCount.get(string);
				if (clnPathsCount.keySet().contains(string))
					cln += clnPathsCount.get(string);
				double score = cln/mal;
				scoreList.add(new Score(string, score));
			}
			Collections.sort(scoreList, new Comparator<Score>() {
		        public int compare(Score o1, Score o2) {
		        	if (o1.num - o2.num > 0)
		        		return -1;
		        	if (o1.num - o2.num < 0)
		        		return 1;
		        	return 0;
		        }
		    });
			int k = 0;
			for (Score score : scoreList)
			{
				bw.write(score.pathString + "\n", 0, score.pathString.length() + 1);
				k++;
				if (k > i)
					break;
			}
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public static HashMap<String, Integer> getAllMalPathsCount()
	{
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		String pathMal = "/Users/chenqiming/Desktop/m/";
		File fileMal = new File(pathMal);
		File flistMal[] = fileMal.listFiles();
		//String pathCln = "/Users/chenqiming/Desktop/pollute/c/";
		//File fileCln = new File(pathCln);
		//File flistCln[] = fileCln.listFiles();
//		for (File file : flistCln)
//		{
//			try
//			{
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				String line;
//				while ((line = br.readLine()) != null)
//				{
//					int sep = line.lastIndexOf(':');
//					if (sep < 0)
//						continue;
//					String path = line.substring(0, sep);
//					String countString = line.substring(sep + 1);
//					int count = Integer.parseInt(countString);
//					count = 1;
//					/*
//					 * count what?
//					 */
//					if (ret.containsKey(path))
//						ret.put(path, ret.get(path) + count);
//					else
//						ret.put(path, count);
//				}
//				br.close();
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
		for (File file : flistMal)
		{
			System.out.println("!");
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

	public static HashMap<String, Integer> getAllClnPathsCount()
	{
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
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
		return ret;
	}

	public static void storePaths()
	{
		String pathMal1 = "/Users/chenqiming/Desktop/pollute/c/";
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
