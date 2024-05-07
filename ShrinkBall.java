import java.awt.Color;

public class ShrinkBall extends BasicBall {
    private final double originalRadius;  

    public ShrinkBall(double r, Color c) {
        super(r, c);
        this.originalRadius = r;  
        ballType = "shrink";
    }

    //method to handle ball shrinking
    @Override
    public void reset() {
        radius *= 0.67;  
        if (radius <= originalRadius * 0.25) {
            radius = originalRadius;  
        }
        super.reset();  
    }

    //returns score for shrink ball hits
    @Override
    public int getScore() {
        return 20;  
    }
}
