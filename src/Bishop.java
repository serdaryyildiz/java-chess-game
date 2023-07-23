public class Bishop extends Piece {
    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
         boolean validMove;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int columnDistance = Math.abs(targetLocation.getColumnDistance(location));
        Square[] between = location.getBoard().getSquaresBetween(location , targetLocation);
        for(Square square : between){
            if(!square.isEmpty()){
                return false;
            }
        }                                                                                                                                   //rowDistance == columnDistance checks is it diagonal move or not
        return (rowDistance == columnDistance) && (targetLocation.isEmpty() || targetLocation.getColor() != location.getColor());           //if it is diagonal move and targetLocation does not contains
    }                                                                                                                                       // same color piece , it is valid move.

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
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}