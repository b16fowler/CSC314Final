//Gap penalty = -4, Mismatch = -1, Match = +3
public class Final{
	//Main
	public static void main(String[] args){
		String string1 = "atcg";
		String string2 = "gcta";
		int sum = 0;
		
		//To Upper Case
		String string1Final = string1.toUpperCase();
		String string2Final = string2.toUpperCase();
		
		//To String Array
		String[] string_arr1 = string1Final.split("");
		String[] string_arr2 = string2Final.split("");
		
		
		//Creating 2x2 matrix to hold sequences and scores
		Object[][] matrix = new Object[string_arr1.length + 2][string_arr2.length + 2];
		Object[][] matrix2 = new Object[string_arr1.length + 2][string_arr2.length + 2];
		
		matrix[0][0] = -1; //-1 to represent space that will not be used
		matrix[1][0] = -1;
		matrix[0][1] = -1;
		matrix[1][1] = 0; //Starting score 0
		for (int j = 2; j < string_arr1.length + 2; j++) {
			matrix[j][0] = string_arr1[j - 2];
		}
		for (int j = 2; j < string_arr2.length + 2; j++) {
			matrix[0][j] = string_arr2[j - 2];
		}
		//Filling in original gap penalties
		for (int j = 2; j < string_arr1.length + 2; j++) {
			sum = sum - 4;
			matrix[j][1] = sum;
		}
		sum = 0;
		for (int j = 2; j < string_arr2.length + 2; j++) {
			sum = sum - 4;
			matrix[1][j] = sum;
		}
		
		//Testing Matrix
		for (int j = 0; j < string_arr1.length + 2; j++) {
			for (int k = 0; k < string_arr2.length + 2; k++) {
				System.out.println(matrix[j][k] + " ");
			}
		}
		
		//Alignment(matrix, string_arr1.length + 2, string_arr2.length + 2);
		
	} //End Main Method
	
	//Alignment Method
	public static void Alignment(Object[][] mat, int length1, int length2){ //Use Coordinates: x and y
		//Compares 3 adjacent boxes to find best score throughout matrix
		for (int j = 2; j < length1; j++){
			for (int k = 2; k < length2; k++){
				mat[j][k] = Score(mat, j - 1, k - 1); //To figure out score at (j,k), perform Score method at (j-1, k-1)
			}
		}
	} //End Alignment Method
	
	//Score Method
	public static int Score(Object[][] mat, int x, int y){
		int score1 = 0;
		int score2 = 0;
		int score3 = 0;
		int score = 0;
		//No Gap
		if (mat[x + 1][0] == mat[0][y + 1]) { //Match
			score1 = 3; //Score + score at (x,y)
		}
		else { //Mismatch
			score1 = -1; //Score + score at (x,y)
		}
		//Gap 1
		score2 = -4; //////////
		//Gap 2
		score3 = -4; /////////
		
		score = Math.max(Math.max(score1, score2), score3); //Finds maximum of the 3 potential values
		return score;
	} //End Score Method
}


