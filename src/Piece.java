public abstract class Piece {
    protected Square location;
    protected int color ;
    public Piece(int color , Square location){
        this.location = location;
        this.color = color;
    }
    public int getColor(){
        return color;
    }
    public ChessBoard getBoard(){
        return location.getBoard();
    }
    public abstract boolean canMove(String to);
    //public abstract void move(String to);
    public abstract String toString();
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }
}
