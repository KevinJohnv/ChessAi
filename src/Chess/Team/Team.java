package Chess.Team;

import Chess.Pieces.BasePiece;
import Engine_v2.Engine;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public enum Controller{
        player,bot
    }

    private Controller player;
    private List<BasePiece> chess_pieces;



    public Team(Controller player){
        setPlayer(player);
        setChess_Pieces(new ArrayList<BasePiece>());
    }


    public List<BasePiece> get_Chess_Pieces(){return this.chess_pieces;}
    public Controller get_Player(){return this.player;}

    public void setPlayer(Controller player){this.player = player;}
    public void setChess_Pieces(List<BasePiece> chess_pieces){this.chess_pieces = chess_pieces;}
    public void addChess_Piece(BasePiece piece){
        this.chess_pieces.add(piece);
        piece.setCurrent_Team(this);
    }
    public void removePiece(BasePiece piece){
        this.chess_pieces.remove(piece);
        if(piece.getName().equals("King")){
            Engine.state = Engine.GameState.end;
        }
    }
    public void resetPreviousMoveAll(){
        for(int i = 0; i < chess_pieces.size(); i++){
            chess_pieces.get(i).setPreviousMove("");
        }

    }
}
