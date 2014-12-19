package svm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pdf.PDF;
import pdf.PDFPaths;

public class GetVectors
{
	public static void main(String[] args) throws IOException
	{
		boolean test = true;
		if (test)
		{
			String testPath = "pollute.dat";
			BufferedWriter bwt = new BufferedWriter(new FileWriter(testPath));
			String pathT = "/Users/chenqiming/Desktop/pollute/pollute/";
			File fileT = new File(pathT);
			File flistT[] = fileT.listFiles();
			for (File file : flistT)
			{
				System.out.println(file.getName());
				PDF pdf = new PDF(file);
				ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
				if (vector.size() > 0)
				{
					bwt.write("0");
					int n = 0;
					for (Integer k : vector)
					{
						n++;
						bwt.write("  " + n + ":" + k);
					}
					bwt.newLine();
				}
			}
			bwt.close();
		}
		String outFilePath = "vector.dat";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
		String pathMal = "/Users/chenqiming/Desktop/pollute/m/";
		File fileMal = new File(pathMal);
		File flistMal[] = fileMal.listFiles();
		String pathCln = "/Users/chenqiming/Desktop/pollute/c/";
		File fileCln = new File(pathCln);
		File flistCln[] = fileCln.listFiles();
		for (File file : flistMal)
		{
			System.out.println(file.getName());
			PDF pdf = new PDF(file);
			ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
			if (vector.size() > 0)
			{
				bw.write("1");
				int n = 0;
				for (Integer k : vector)
				{
					n++;
					bw.write("  " + n + ":" + k);
				}
				bw.newLine();
			}
		}
		for (File file : flistCln)
		{
			System.out.println(file.getName());
			PDF pdf = new PDF(file);
			ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
			if (vector.size() > 0)
			{
				bw.write("0");
				int n = 0;
				for (Integer k : vector)
				{
					n++;
					bw.write("  " + n + ":" + k);
				}
				bw.newLine();
			}
		}
		bw.close();
	}
}
