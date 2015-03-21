

public class DataGenerator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 10000000 points will yield approximately 100MB data.
		
		PointsGenerator generator = new PointsGenerator(10000000);
		generator.generate();
		int k = 10;
		InitialPoints kGenerator = new InitialPoints(k);
		kGenerator.generate();
	}

}
