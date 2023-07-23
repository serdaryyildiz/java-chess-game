public class King extends Piece {
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {                                                                                 //King can't move than 1 square , if it's not more than 2 squares and
        Square targetLocation = location.getBoard().getSquareAt(to);                                                    // targetLocation does not contains same color piece , it is valid move.
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int columnDistance = Math.abs(targetLocation.getColumnDistance(location));
        return (rowDistance <= 1 && columnDistance <= 1) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor());
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
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
