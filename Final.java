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
		
		//Replacing new lines
		string1Final = string1Final.replace("\n", "");
		string2Final = string2Final.replace("\n", "");
		
		//To String Array
		String[] string_arr1 = string1Final.split("");
		String[] string_arr2 = string2Final.split("");
		
		//Creating 2x2 matrix to hold sequences and scores
		int[][] matrix = new int[string_arr1.length + 1][string_arr2.length + 1];
		//Filling in original gap penalties
		for (int j = 1; j < string_arr1.length + 1; j++) {
			sum = sum - 4;
			matrix[j][0] = sum;
		}
		sum = 0;
		for (int j = 1; j < string_arr2.length + 1; j++) {
			sum = sum - 4;
			matrix[0][j] = sum;
		}
		
		//Testing Matrix
		for (int j = 0; j < string_arr1.length + 1; j++) {
			for (int k = 0; k < string_arr2.length + 1; k++) {
				System.out.print(matrix[j][k] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		//Setting up directional matrix and original gap penalty arrows
		String[][] matrix2 = new String[string_arr1.length + 1][string_arr2.length + 1];
		for (int j = 1; j < string_arr1.length; j++) { //
			matrix2[j][0] = "vert";
		}
		for (int k = 1; k < string_arr2.length; k++) {
			matrix2[0][k] = "horiz";
		}
		
		//Printing matrix 2
		for (int j = 0; j < string_arr1.length; j++) {
			for (int k = 0; k < string_arr2.length; k++) {
				System.out.print(matrix2[j][k] + " ");
			}
			System.out.println();
		}
		
		Alignment(matrix, matrix2, string_arr1.length + 1, string_arr2.length + 1);
		
		//Testing Matrix
				for (int j = 0; j < string_arr1.length + 1; j++) {
					for (int k = 0; k < string_arr2.length + 1; k++) {
						System.out.print(matrix[j][k] + " ");
					}
					System.out.println();
				}
				System.out.println();
		
	} //End Main Method
	
	//Alignment Method
	public static void Alignment(int[][] mat, String [][] mat2, int length1, int length2){ //Use Coordinates: x and y
		//Compares 3 adjacent boxes to find best score throughout matrix
		for (int j = 1; j < length1; j++){
			for (int k = 1; k < length2; k++){
				mat[j][k] = Score(mat, mat2, length1, length2, j, k); //To figure out score at (j,k), perform Score method at (j-1, k-1)
			}
		}
	} //End Alignment Method
	
	//Score Method
	public static int Score(int[][] mat, String[][] mat2, int length1, int length2, int x, int y){
		int score1 = 0;
		int score2 = 0;
		int score3 = 0;
		int score = 0;
		System.out.print(mat[x][y] + " ");
		System.out.print(x + " ");
		System.out.println(y);
		if (x < length1 - 1 && y < length2 - 1) {
			//No Gap
			if (mat[x + 1][y] == mat[x][y + 1]) { //Match
				score1 = mat[x - 1][y - 1] + 3; //Score + score at (x,y)
			}
			else { //Mismatch
				score1 = mat[x - 1][y - 1] - 1; //Score + score at (x,y)
			}
			//Gap 1
			score2 = mat[x - 1][y - 1] - 4; //////////
			//Gap 2
			score3 = mat[x - 1][y - 1] - 4; /////////
		}
		score = Math.max(Math.max(score1, score2), score3); //Finds maximum of the 3 potential values
		return score;
	} //End Score Method
}


