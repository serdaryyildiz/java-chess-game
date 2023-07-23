import java.util.Objects;

public class Square {               //Methods in this class are simple methods , so i described them in documentation.
    private String row;
    private String column;
    private Piece piece;
    String location;
    String value;
    public Square(String column, String row) {
        this.row = row;
        this.column = column;
    }
    public String getValue() {
        if (value == null)
            return " ";
        else
            return value;
    }
    public String getRow() {
        return row;
    }
    public String getColumn() {
        return column;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.value = piece.toString();
    }
    public ChessBoard getBoard() {
        return Main.board;
    }
    public boolean isAtSameColumn(Square s) {
        return Objects.equals(this.getColumn(), s.getColumn());
    }
    public boolean isEmpty() {
        if (this.piece == null) {
            return true;
        } else
            return false;
    }
    public void clear() {
        piece = null;
        value = null;
    }
    public int getRowDistance(Square square) {
        return (Integer.parseInt(this.getRow())) - (Integer.parseInt(square.getRow()));
    }
    public int getColumnDistance(Square square) {
        return (changeColumnToNumber(this.getColumn())) - (changeColumnToNumber(square.getColumn()));
    }
    public boolean isAtLastRow(int color) {
        if (color == 1) {                           //For white pawns
            if (Integer.parseInt(this.getRow()) == 1)
                return true;
        }
        if (Integer.parseInt(this.getRow()) == 8)   //For black pawns
            return true;
        return false;
    }
    public boolean isNeighborColumn(Square square) {
        int columnDistance = Math.abs(changeColumnToNumber(this.getColumn()) - changeColumnToNumber(square.getColumn()));
        return columnDistance == 1;
    }
    public int getColor(){
        if(this.getPiece() == null)
            return -1;
        else if(this.getPiece().getColor() == 0)
            return 0;
        else
            return 1;
    }
    public void putNewQueen(int color) {
        this.setPiece(new Queen(color , this));
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
}
