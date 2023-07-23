import java.util.Scanner;

public class Main {
static ChessBoard board;
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        board = new ChessBoard();
        System.out.println(board);
        while (!board.isGameEnded()){
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn");
            Piece piece = null;
            do {
                System.out.print("Enter the location of the piece: ");
                String from = reader.nextLine();
                piece = board.getPieceAt(from);
                //System.out.println(piece.getColor());
            }while(piece == null || piece.getColor()!=(board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK));
            String to = null;
            do {
                System.out.print("Enter the new location of the piece:");
                to = reader.nextLine();
            }while(!piece.canMove(to));
            piece.move(to);
            System.out.println(board);
        }
        reader.close();
        System.out.println((!board.isWhitePlaying() ? "White" : "Black") + "won. \nGame Over gg wp");
    }
}