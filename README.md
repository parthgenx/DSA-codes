# Sudoku Validator

This program checks if a given Sudoku board is valid according to Sudoku rules.

## Board Structure

The board is **9×9**, but it’s split into **nine 3×3 subgrids** (also called “boxes”):

Box 0 | Box 1 | Box 2
------+-------+------
Box 3 | Box 4 | Box 5
------+-------+------
Box 6 | Box 7 | Box 8

yaml
Copy
Edit

Each box is **not a single cell** — it’s a group of 9 cells.

---

## Step 2 — Formula for Box Index

We use the formula:

boxIndex = (i / 3) * 3 + (j / 3);

markdown
Copy
Edit

- **`i / 3`** → tells you which **row block of boxes** you’re in (top, middle, bottom).  
- **`j / 3`** → tells you which **column block of boxes** you’re in (left, middle, right).  
- Multiply the row block by 3, then add the column block to get the box number.

---

## Example

If `i = 4` and `j = 7`:

i / 3 = 1 (middle row of boxes)
j / 3 = 2 (right column of boxes)

boxIndex = (1 * 3) + 2 = 5

pgsql
Copy
Edit

This means the cell is in **Box 5**.

---

## Code

```java
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
                    int boxIndex = (i / 3) * 3 + (j / 3);
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
