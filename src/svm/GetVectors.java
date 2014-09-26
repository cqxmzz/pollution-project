package svm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pdf.PDF;
import pdf.PDFPaths;

public class GetVectors {
	public static void main(String[] args) throws IOException {
		String outFilePath = "vector.dat";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
	    String pathMal = "F:/data/malware/";
	    File fileMal = new File(pathMal);
	    File flistMal[] = fileMal.listFiles();
	    String pathCln = "F:/data/clean/";
	    File fileCln = new File(pathCln);
	    File flistCln[] = fileCln.listFiles();
	    for (File file : flistMal)
	    {
			PDF pdf = new PDF(file);
			ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
			if (vector.size() > 0)
			{
				bw.write("1");
				int n = 0;
				for (Integer k: vector)
				{
					n++;
					bw.write("  "+n+":"+k);
				}
				bw.newLine();
			}
	    }
	    for (File file : flistCln)
	    {
			PDF pdf = new PDF(file);
			ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
			if (vector.size() > 0)
			{
				bw.write("0");
				int n = 0;
				for (Integer k: vector)
				{
					n++;
					bw.write("  "+n+":"+k);
				}
				bw.newLine();
			}
	    }
	    bw.close();
	}
}
