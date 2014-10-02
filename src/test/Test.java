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
		File file = new File("/Users/chenqiming/Desktop/c/749ac90e059e432bd582e23437be1445a84c8d443b3b47a9eda6c86dfcf01914");
		PDF pdf = new PDF(file);
		ArrayList<Integer> vector = PDFPaths.getPathsVector(pdf);
		for (int i : vector)
		System.out.print(" " + i);
		//PDFPaths.storePopularPaths(100);
	}
}
