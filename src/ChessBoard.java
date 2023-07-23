public class ChessBoard {
    private boolean whitePlaying = true;
    private Square board[][] = new Square[8][8];
    static int WHITE = 0;
    static int BLACK = 1;
    public ChessBoard(){
        Square board[][] = new Square[8][8];
        initialize();
    }
    public void nextPlayer(){           //If player played his/her turn , it changes boolean (simple form is not working , i don't know why) 
        if(whitePlaying)
            whitePlaying = false;
        else
            whitePlaying = true;
    }
    public boolean isWhitePlaying(){
        return whitePlaying;
    }
    public Piece getPieceAt(String location) {
        Square square = getSquareAt(location);
        return square.getPiece();
    }
    public void createBoard(){              //Creating Chess Board here
        char col;
        int counter = -1;
        int rows = 9;
        for(int t = 0 ; t < 8 ; t++){
            ++counter;
            rows--;
            int a = -1;
            System.out.println();
            for(col = 'A' ; col <= 'H' ; col++){                //Loop starts with 'A' and goes until 'H' (col++ changes ASCII value.)
                Square square = new Square(String.valueOf(col) , String.valueOf(rows)); //Setting squares row and column.
                a++;                                                       //Changing index every loop
                board[counter][a] = square;                             //Filling matrix with squares with row and column.
            }
        }
    }
    public Square getSquareAt(String location) {
        int column = changeColumnToNumber(location.substring(0,1).toUpperCase());           //.toUpperCase() is for Caps Lock situations , It get input any way (for ex. "a2" or "A2" , both of them are working) 
        int row = Integer.parseInt(location.substring(1));                                  //substrings is for taking first element and second element of input (first element as column , second as row)
        if (column >= 0 && column < 8 && row >= 1 && row <= 8) {
            return board[8-row][column];                                                    //returning (8-row) is because : 2. row in ChessBoard is 6th element of the matrix.
        } else {
            throw new IllegalArgumentException("Geçersiz konum: " + location);              //Returning and exception.
        }
    }
    public void printBoard(){
        String columns = "  ---------------------------------";                     //We are printing chess board between lines 34-49.
        printColumnLetters();
        System.out.println();
        int var = -1;
        for(int i = 8 ; i > 0 ; i--){
            System.out.println(columns);
            System.out.print(i + " | ");
            var++;
            for(int j = 0 ; j < 8 ; j++){
                System.out.print(board[var][j].getValue() + " | ");
            }
            System.out.print(i);
            System.out.println();
        }
        System.out.println(columns);
        printColumnLetters();
        System.out.println();
    }
    public void printColumnLetters(){                       //Printing Letters of Columns
        for(char a = 'A' ; a <= 'H' ; a++){
            System.out.print("\t" + a);
        }
    }
    public void initialize(){       //This method creates initial state of the chess board. 
        createBoard();
        //White Pieces
        board[7][0].setPiece(new Rook(ChessBoard.WHITE , board[7][0]));
        board[7][1].setPiece(new Knight(ChessBoard.WHITE , board[7][1]));
        board[7][2].setPiece(new Bishop(ChessBoard.WHITE , board[7][2]));
        board[7][3].setPiece(new Queen(ChessBoard.WHITE , board[7][3]));
        board[7][4].setPiece(new King(ChessBoard.WHITE , board[7][4]));
        board[7][5].setPiece(new Bishop(ChessBoard.WHITE , board[7][5]));
        board[7][6].setPiece(new Knight(ChessBoard.WHITE , board[7][6]));
        board[7][7].setPiece(new Rook(ChessBoard.WHITE , board[7][7]));
        for(int i = 0 ; i < 8 ; i++){                           //I used for loop because we don't need to write them one by one , just increase their column.
            board[6][i].setPiece(new Pawn(ChessBoard.WHITE , board[6][i]));
        }

        //Black Pieces
        board[0][0].setPiece(new Rook(ChessBoard.BLACK , board[0][0]));
        board[0][1].setPiece(new Knight(ChessBoard.BLACK , board[0][1]));
        board[0][2].setPiece(new Bishop(ChessBoard.BLACK , board[0][2]));
        board[0][3].setPiece(new Queen(ChessBoard.BLACK , board[0][3]));
        board[0][4].setPiece(new King(ChessBoard.BLACK , board[0][4]));
        board[0][5].setPiece(new Bishop(ChessBoard.BLACK , board[0][5]));
        board[0][6].setPiece(new Knight(ChessBoard.BLACK , board[0][6]));
        board[0][7].setPiece(new Rook(ChessBoard.BLACK , board[0][7]));
        for(int i = 0 ; i < 8 ; i++){
            board[1][i].setPiece(new Pawn(ChessBoard.BLACK , board[1][i]));
        }
    }
    public boolean isGameEnded() {                  //This method is there any pieces left from any color
        boolean whitePiecesExist = false;
        boolean blackPiecesExist = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null) {
                    if (piece.getColor() == ChessBoard.WHITE) {
                        whitePiecesExist = true;
                    } else if (piece.getColor() == ChessBoard.BLACK) {
                        blackPiecesExist = true;
                    }
                }
            }
        }

        return !whitePiecesExist || !blackPiecesExist;
    }
    public Integer changeColumnToNumber(String column){
        switch (column){
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            default:
                return -1;
        }
    }
    public String changeNumberToColumn(int num){        
        switch (num){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            default:
                return null;
        }
    }
    public Square[] getSquaresBetween(Square firstSqaure , Square lastSquare){
        int columnDistance = lastSquare.getColumnDistance(firstSqaure);             //Calculating distance between squares
        int rowDistance = lastSquare.getRowDistance(firstSqaure);
        int columnDirect;
        if(columnDistance == 0)
            columnDirect = 0;
        else if(columnDistance > 0)
            columnDirect = 1;
        else
            columnDirect = -1;
        int rowDirect;
        if(rowDistance == 0)
            rowDirect = 0;
        else if (rowDistance > 0)
            rowDirect = 1;
        else
            rowDirect = -1;
        columnDistance = Math.abs(columnDistance);
        rowDistance = Math.abs(rowDistance);
        int squares = Math.max(rowDistance , columnDistance) - 1;
        Square[] squaresBetween = new Square[squares];
        for(int i = 1 ; i <= squares ; i++){
            int row = Integer.parseInt(firstSqaure.getRow()) + (i * rowDirect);                 //(i * direct) allows us to check correct direction
            int col = changeColumnToNumber(firstSqaure.getColumn()) + (i * columnDirect);
            squaresBetween[i-1] = getSquareAt(changeNumberToColumn(col+1) + String.valueOf(row));//We get square at (col + 1) because changeNumberToColumn method designed for matrix indexes , so we should get +1.
            //Finding square with its row and column , then appending this square to Array.
        }
        return squaresBetween;

        /* We first get columnDistance and rowDistance because of the find the direction of the piece and get the right amount of square , then we get our direction with our distances 
        (Up and Right direction is + , + direction.)  After finding our direction we turn our distance variables to positive integers (because ; in line 182 , you can't return negive index) 
        then we create a for loop and take squares between first and last squares with loop (i * direction allows us go to correct direction)*/
    }
    public String toString(){
         printBoard();
         return "";
    }
}
