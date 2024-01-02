import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Panel extends JPanel implements Runnable {


    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.55555);

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    static final int PADDLE_HEIGHT = 75;

    static final int PADDLE_WIDTH = 20;


    static final int BALL_DIAMETER = 20;


    Thread thread;

    Image image;


    Ball ball;

    Score score;

    Paddles paddle1;

    Paddles paddle2;


    Panel() {

        newPaddles();
        newBall();
        score();

        this.setFocusable(true);
        thread = new Thread(this);
        thread.start();

        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);


    }

    public void paint(Graphics g) {
        image = createImage(getHeight(), getWidth());

        g.drawImage(image, 0, 0, this); // first paddle placement
        g.drawImage(image, GAME_WIDTH / 2 - PADDLE_WIDTH / 2, 0, this); //second paddle placement
        g.drawImage(image, GAME_WIDTH / 2 - BALL_DIAMETER / 2, GAME_HEIGHT / 2 - BALL_DIAMETER / 2, this);  // ball placement
        g.drawImage(image, 250,50,this); // player 1 score placement
        g.drawImage(image, 650,50,this ); // player 2 score placement

        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);


    }


    public void newPaddles() {
        paddle1 = new Paddles(0, GAME_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddles(GAME_WIDTH - PADDLE_WIDTH, GAME_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paddleBoundary() {
        if (paddle1.y <= 0) {
            paddle1.y = 1;

        }
        if (paddle1.y >= 498) {
            paddle1.y = 497;

        }
        if (paddle2.y <= 0) {
            paddle2.y = 1;

        }
        if (paddle2.y >= 498) {
            paddle2.y = 497;

        }

    }


    public void newBall() {
        ball = new Ball(GAME_WIDTH / 2 - BALL_DIAMETER / 2, GAME_HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, BALL_DIAMETER, Color.WHITE,4,4);
    }


    public void move() {
        ball.move();
        paddle1.move();
        paddle2.move();
    }


    public void checkCollision() {

        if (ball.y <= 0 - BALL_DIAMETER) {
            ball.deltaY = -ball.deltaY;
        }

        if (ball.y > GAME_HEIGHT - BALL_DIAMETER) {
            ball.deltaY = -ball.deltaY;
        }

        if (paddle1.contains(ball.x, ball.y)) {

            ball.deltaX *= -1;
        }

        if(paddle2.contains(ball.x + BALL_DIAMETER, ball.y + BALL_DIAMETER )){

            ball.deltaX *= -1;
        }
    }

    public void score(){
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
    }

    public void checkScoreAndReset() {
        if (ball.x < 0 - BALL_DIAMETER) {
             score.player1 += 1;

             try {
                 thread.sleep(1000);
             } catch (Exception e) {

             }

              ball.x = GAME_WIDTH / 2 - BALL_DIAMETER / 2;
             ball.y = GAME_HEIGHT / 2 - BALL_DIAMETER / 2;
        }

        if(ball.x > GAME_WIDTH){
            score.player2 += 1;

            try {
                thread.sleep(1000);
            } catch (Exception e) {

            }

           ball.x = GAME_WIDTH / 2 - BALL_DIAMETER / 2;
          ball.y = GAME_HEIGHT / 2 - BALL_DIAMETER / 2;

        }
    }

    @Override
    // game loop
    public void run() {

        long lastLoopTime = System.nanoTime();
        final double TARGET_FPS = 60.0;
        final double OPTIMAL_TIME = 1_000_000_000 / TARGET_FPS;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastLoopTime) / OPTIMAL_TIME;
            lastLoopTime = now;
            if (delta >= 1) {
                repaint();
                paddleBoundary();
                move();
                checkCollision();
                checkScoreAndReset();
                delta--;
            }
        }
    }


    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }
}

