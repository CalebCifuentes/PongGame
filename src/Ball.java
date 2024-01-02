import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle  {

     int x;

     int y;

     int width;

     int height;

     Color color;

     int deltaX;

     int deltaY;





     Ball(int x, int y, int width, int height,Color color, int deltaX, int deltaY) {
          this.x = x;
          this.y = y;
          this.width = width;
          this.height = height;
          this.color = color;
          this.deltaX = deltaX;
          this.deltaY = deltaY;
     }







void move(){
     x += deltaX;
     y += deltaY;
}

public void draw(Graphics g){
    g.setColor(color);
    g.fillOval(x,y,width,height);
}



}
