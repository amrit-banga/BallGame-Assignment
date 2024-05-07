import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SplitBall extends BasicBall {
    private final double initialRadius;  

    public SplitBall(double r, Color c) {
        super(r, c);
        this.initialRadius = r;
        ballType = "split";
    }

    // method to handle the ball splitting
    public List<SplitBall> split() {
        List<SplitBall> newBalls = new ArrayList<>();
        newBalls.add(new SplitBall(initialRadius, color));  //new ball spawn
        for (SplitBall ball : newBalls) {
            ball.reset();  
        }
        return newBalls;
    }

    //returns score for splitball hits
    @Override
    public int getScore() {
        return 10;  
    }
}
