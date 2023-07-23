public class Rook extends Piece {
    public Rook(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int columnDistance = targetLocation.getColumnDistance(location);
        if (rowDistance == 0 || columnDistance == 0) {                                                                  //Rook can't move diagonal ; so one of the distances must be 0 , if it isn't then it's not a valid move.
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);                         //If there are no piece between squares and
            return isPathEmpty(between) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor()); // targetLocation does not contains same color piece , it is valid move.
        }
        return false;
    }
    private boolean isPathEmpty(Square[] squares) {         //This method checks is there any piece between squares .
        for (Square square : squares) {
            if (!square.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}