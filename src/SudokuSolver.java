public class SudokuSolver {

    private boolean solve(int[][] board, int counter){

        int col = counter / board.length;
        int row = counter % board.length;

        if (col >= board.length){
            printBoard(board);
            return true;
        }

        if (board[row][col] == 0) {

        for (int n = 1; n <= board.length; n++) {

            if (isValid(n,row,col, board)){
                board[row][col] = n;

                if (solve(board,counter+1)){
                    return true;
                }

            }
            board[row][col] = 0;

        }
        }else{
            return solve(board, counter + 1);
        }

        return false;
    }

    public void startSolving(int[][] board){
        if(!solve(board, 0)){
            System.out.println("no solution");
        }
    }

    private boolean isValid(int n, int row, int col, int[][] board){

        int i;

        for (i = 0; i < board.length; i++) {
            if(board[row][i] == n){
                return false;
            }
        }

        for (i = 0; i < board.length; i++) {
            if(board[i][col] == n){
                return false;
            }
        }

        //check if block is valid

        final int blockRow = 3 * (row / 3);
        final int blockCol = 3 * (col / 3);
        return isBlockValid(n, board, blockRow, blockRow + 2, blockCol, blockCol + 2);
    }

    private boolean isBlockValid(int n, int[][] board, int starti, int stopi, int startj, int stopj){

        for (int i = starti; i <= stopi; i++) {

            for (int j = startj; j <= stopj; j++) {

                if (board[i][j] == n) {
                    return false;
                }
            }
        }

        return true;
    }

    private void printBoard(int[][] board){

        System.out.println();

        for (int[] row : board){
            System.out.print("|");
            for (int col : row){
                System.out.print(col);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[][] board =
                {{2,0,5,0,0,0,0,0,0},
                {3,0,8,6,0,0,9,0,0},
                {0,0,0,1,0,0,4,0,0},
                {0,0,0,0,5,0,0,1,0},
                {0,0,0,0,9,0,0,2,0},
                {8,7,0,0,2,0,0,0,0},
                {0,0,0,0,8,9,0,0,3},
                {0,0,6,0,0,3,0,0,5},
                {5,0,4,0,0,0,0,0,1}};
        
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.startSolving(board);
    }
}
