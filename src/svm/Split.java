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

		boolean datacount = true;
		
        Random random = new Random(10000);
        Random random1 = new Random(10000);
        int randNum = 0;
        int randNum1 = 0;
        String string;
        int train_one = 0;
        int train_zero = 0;
        int test_one = 0;
        int test_zero = 0;
		while ((string = br.readLine()) != null)
		{
			randNum = Math.abs(random.nextInt());
			randNum1 = Math.abs(random1.nextInt());
			if (randNum1 % 6 > 4)
				continue;
			if (randNum % 10 > 5)
			{
				if (string.charAt(0) == '0')
				{
					if (train_zero >= 5000 && datacount)
						continue;
					else
						train_zero++;
				}
				if (string.charAt(0) == '1')
				{
					if (train_one >= 5000 && datacount)
						continue;
					else
						train_one++;
				}
				bw_test.write(string);
			    bw_test.newLine();
			}
			else
			{
				if (string.charAt(0) == '0')
				{
					if (test_zero >= 5000 && datacount)
						continue;
					else
						test_zero++;
				}
				if (string.charAt(0) == '1')
				{
					if (test_one >= 5000 && datacount)
						continue;
					else
						test_one++;
				}
				bw_train.write(string);
			    bw_train.newLine();
			}
		}
		br.close();
		bw_train.close();
		bw_test.close();
	}
}
