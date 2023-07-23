public class Knight extends Piece {
    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {                                                                                 //Knight only move as L shape , so it means distances should be 2 & 1 or
        Square targetLocation = location.getBoard().getSquareAt(to);                                                    // 1 & 2 squares . If it moves as L shape and does not contain same color piece at
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));                                            // targetLocation it is valid move.
        int columnDistance = Math.abs(targetLocation.getColumnDistance(location));
        return ((rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2)) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor());
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
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}