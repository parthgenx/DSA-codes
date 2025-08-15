class Solution {
    public boolean isValidSudoku(char[][] board) {

        HashSet<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            boxes[i] = new HashSet<>();
        }

       for(int i = 0; i < 9; i++) {
        HashSet<Character> row = new HashSet<>();
        HashSet<Character> col = new HashSet<>();

        for(int j = 0; j < 9; j++) {

            char numRow = board[i][j];
            if(numRow != '.') {
                if(row.contains(numRow)) return false;
                row.add(numRow);

                // box check
                int boxIndex = (i/3)*3+(j/3);
                if(boxes[boxIndex].contains(numRow)) return false;
                boxes[boxIndex].add(numRow);
            }

            char numCol = board[j][i];
            if(numCol != '.') {
                if(col.contains(numCol)) return false;
                col.add(numCol);
            }
        }
       }
       return true;
    }
}