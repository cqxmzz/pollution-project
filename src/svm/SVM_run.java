package svm;

import java.io.IOException;

public class SVM_run {
	public static void main(String[] args) throws IOException {

		//String[] trainArgs = {"train_vector.dat"};//directory of training file
		//String modelFile = SVM_train.main(trainArgs);
		
		String[] testArgs = {"test_vector.dat", "linear.model", "result.dat", "pollute.dat"};//directory of test file, model file, result file
		Double accuracy = SVM_predict_polluted.main(testArgs);//
		System.out.println("Done! Accuracy: " + accuracy);
	}
}
