import java.awt.*;

public class Score  {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    int player1;

    int player2;



Score(int GAME_WIDTH, int GAME_HEIGHT){

    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;

}

public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.drawString("P1: " + " " + player1,  250,50); // player 1
    g.drawString("P2: " + " " + player2, 650,50); // player 2

}

}
