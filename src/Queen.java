public class Queen extends Piece{
    public Queen(int color , Square location){
        super(color , location);
    }

    @Override                                                                                                           //We are checking is there any piece between location , targetLocation and
    public boolean canMove(String to) {                                                                                 // is there any piece with the same color at targetLocation. If there are no
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int columnDistance = Math.abs(targetLocation.getColumnDistance(location));// piece between squares and targetLocation not contains same color piece
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
        if (rowDistance == 0 || columnDistance == 0) {   //Horizontal & Vertical moves  //Rook can't move diagonal ; so one of the distances must be 0 , if it isn't then it's not a valid move.
            return isPathEmpty(between) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor()); // targetLocation does not contains same color piece , it is valid move.
        }else if(rowDistance == columnDistance){    //Diagonal moves
            return isPathEmpty(between) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor());
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
        return color == ChessBoard.WHITE ? "Q" : "q";
    }
}
