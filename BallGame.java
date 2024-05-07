/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *  
 *  Run the skeleton code with arguments : 1  basic  0.08
 * Amrit Banga
 *******************************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.util.*;

public class BallGame { 

    public static void main(String[] args) {
  
    	// number of bouncing balls
    	int numBalls = Integer.parseInt(args[0]);
    	//ball types
    	String ballTypes[] = new String[numBalls];
    	//sizes of balls
    	double ballSizes[] = new double[numBalls];
    	
    	//retrieve ball types
    	int index =1;
    	for (int i=0; i<numBalls; i++) {
    		ballTypes[i] = args[index];
    		index = index+2;
    	}
    	//retrieve ball sizes
    	index = 2;
    	for (int i=0; i<numBalls; i++) {
    		ballSizes[i] = Double.parseDouble(args[index]);
    		index = index+2;
    	}
     
    	//TO DO: create a Player object and initialize the player game stats.  
    	Player player = new Player();
    	
    	//number of active balls
    	int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls 
        //TO DO: Create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store them in an Arraylist
        ArrayList<BasicBall> balls = new ArrayList<BasicBall>();
        // initialize balls based on their type
        for (int i = 0; i < numBalls; i++) {
            Color color;
            switch (ballTypes[i].toLowerCase()) {
                case "basic":
                    color = StdDraw.BLACK;
                    balls.add(new BasicBall(ballSizes[i], color));
                    break;
                case "shrink":
                    color = StdDraw.GREEN;
                    balls.add(new ShrinkBall(ballSizes[i], color));
                    break;
                case "bounce":
                    color = StdDraw.RED;
                    balls.add(new BounceBall(ballSizes[i], color));
                    break;
                case "split":
                    color = StdDraw.BLUE;
                    balls.add(new SplitBall(ballSizes[i], color));
                    break;
            }
        }
        numBallsinGame = balls.size();
        StdDraw.enableDoubleBuffering(); //smoother animation

        while (numBallsinGame > 0) {
            StdDraw.clear(StdDraw.GRAY); // clear the drawing canvas
            numBallsinGame = 0;

            // iterating through each ball in the balls ArrayList
            for (int i = 0; i < balls.size(); i++) {
                BasicBall ball = balls.get(i);
                ball.move(); // Move the ball

                // checking if mouse is hit and the ball is also hit
                if (StdDraw.isMousePressed()) {
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    if (ball.isHit(x, y)) {
                        player.hitBall(ball.getBallType());
                        //be careful when clicking on the splitballs, a lot can spawn really quickly if clicking in the middle
                        if (ball instanceof SplitBall) {
                        List<SplitBall> newBalls = ((SplitBall) ball).split();
                        balls.addAll(newBalls);  // this adds new split balls to the arraylist
                        }
                    ball.reset(); 
                    }
                }

                if (!ball.isOut) {
                    ball.draw();
                    numBallsinGame++;
                } else {
                    balls.remove(i); 
                    i--;  
                }
            }
            // Display game stats
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.75, 0.95, "Balls in Play: " + numBallsinGame);
            StdDraw.text(-0.75, 0.85, "Number of hits: " + (player.getHits("basic") +player.getHits("bounce") + player.getHits("shrink") + player.getHits("split") ));
            StdDraw.text(-0.75, 0.75, "Score: " + player.getScore());
            StdDraw.show();
            StdDraw.pause(20);
        }
        //Display game over stats
        while (true) {
            StdDraw.setPenColor(StdDraw.BLUE);
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font);
            StdDraw.text(0, 0, "GAME OVER");
            StdDraw.setPenColor(StdDraw.YELLOW);
            font = new Font("Arial", Font.PLAIN, 20);
            StdDraw.setFont(font);
            StdDraw.text(0, -0.1, "Final Score: " + player.getScore());
            StdDraw.text(0, -0.2, "Basic Hits: " + player.getHits("basic"));
            StdDraw.text(0, -0.3, "Split Hits: " + player.getHits("split"));
            StdDraw.text(0, -0.4, "Shrink Hits: " + player.getHits("shrink"));
            StdDraw.text(0, -0.5, "Bounce Hits: " + player.getHits("bounce"));
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
