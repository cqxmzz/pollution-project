package test;

import java.io.File;
import java.util.ArrayList;
import pdf.PDF;
import pdf.PDFPaths;

public class Test
{
	public static void main(String[] args)
	{
		/*
		 * currently use paths that occur more than 60 times, vector size is 1122 
		 * 
		 * to change that See pdf.PDFPaths
		 */
		File file = new File("/Users/chenqiming/Documents/workspace/PollutionProject/test.pdf");
		PDF pdf = new PDF(file);
		ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
		for (int i : vector)
		System.out.print(" " + i);
		//PDFPaths.storePopularPaths(100);
	}
}
