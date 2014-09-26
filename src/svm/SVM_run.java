package svm;

import java.io.IOException;

public class SVM_run {
	public static void main(String[] args) throws IOException {

		String[] trainArgs = {"E:/JAVA_Projects/PollutionProject/train_vector.dat"};//directory of training file
		String modelFile = SVM_train.main(trainArgs);
		String[] testArgs = {"E:/JAVA_Projects/PollutionProject/test_vector.dat", modelFile, "result.dat"};//directory of test file, model file, result file
		Double accuracy = SVM_predict.main(testArgs);
		System.out.println("Done! Accuracy: " + accuracy);
	}
}