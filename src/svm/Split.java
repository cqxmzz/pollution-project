package svm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Split {
	public static void main(String[] arg) throws IOException
	{
		String inFilePath = "vector.dat";
		String outTrainPath = "train_vector.dat";
		String outTestPath = "test_vector.dat";
		BufferedReader br = new BufferedReader(new FileReader(inFilePath));
		BufferedWriter bw_train = new BufferedWriter(new FileWriter(outTrainPath));
		BufferedWriter bw_test = new BufferedWriter(new FileWriter(outTestPath));

        Random random=new Random(10000);
        int randNum = 0;
        String string;
		while ((string = br.readLine()) != null)
		{
			randNum = Math.abs(random.nextInt());
			System.out.println(randNum % 10);
			if (randNum % 10 > 5)
			{
			    bw_test.write(string);
			    bw_test.newLine();
			}
			else
			{
				bw_train.write(string);
			    bw_train.newLine();
			}
		}
		br.close();
		bw_train.close();
		bw_test.close();
	}
}
