import java.util.HashMap;
import java.util.Map;

public class Player {
    private int score;  // total score of the player
    private Map<String, Integer> ballHits;  // stores the number of hits per ball type
    private int totalHits;

    public Player() {
        this.score = 0;
        this.ballHits = new HashMap<>();
        this.totalHits = 0;
        ballHits.put("basic", 0);
        ballHits.put("shrink", 0);
        ballHits.put("bounce", 0);
        ballHits.put("split", 0);
    }

    // method to update score and count ball hits
    public void hitBall(String ballType) {
        ballType = ballType.toLowerCase();  
        Integer hits = ballHits.get(ballType);
        if (hits == null) {
            System.out.println("Unknown ball type: " + ballType);
            return;
        }
        ballHits.put(ballType, hits + 1);
        totalHits++; // counts up total number of hits
    
        // this uppdates the score based on ball type
        switch (ballType) {
            case "basic":
                score += 25;
                break;
            case "shrink":
                score += 20;
                break;
            case "bounce":
                score += 15;
                break;
            case "split":
                score += 10;
                break;
            default:
                System.out.println("Unhandled ball type: " + ballType);
                break;
        }
    }
    

    // method to get the total score for the player
    public int getScore() {
        return score;
    }

    // method to get the number of hits on a specific type of ball
    public int getHits(String ballType) {
        return ballHits.getOrDefault(ballType, 0);
    }

}
