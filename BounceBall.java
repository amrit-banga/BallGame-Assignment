import java.awt.Color;

public class BounceBall extends BasicBall {
    private int bounceCount = 0;
    private final int maxBounces = 3;  // max number of bounces is 3

    public BounceBall(double r, Color c) {
        super(r, c);
        ballType = "bounce";
    }
    //move method
    @Override
    public void move() {
        rx += vx;
        ry += vy;

        // Bounce off the walls
        if (Math.abs(rx) + radius > 1.0) {
            vx = -vx;
            rx += vx;
            bounceCount++;
        }
        if (Math.abs(ry) + radius > 1.0) {
            vy = -vy;
            ry += vy;
            bounceCount++;
        }

        // check if the ball has bounced too many times
        if (bounceCount >= maxBounces) {
            isOut = true;
        }
    }

    // returns score for bounceball hits
    @Override
    public int getScore() {
        return 15;
    }
}
