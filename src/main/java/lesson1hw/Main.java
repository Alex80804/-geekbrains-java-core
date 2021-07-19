package lesson1hw;

public class Main {
    public static void main(String[] args) {

        // Generate random obstacles
        Obstacle[] obstacles = new Obstacle[10];

        for (int i = 0; i < obstacles.length; i++) {
            int obstacleSize = (int) (Math.random() * 30);
            int obstacleType = (int) (Math.random() * 10);
            if (obstacleType <= 5) {
                obstacles[i] = new Wall(obstacleSize);
            } else {
                obstacles[i] = new RunningTrack(obstacleSize);
            }
        }

        // Generate random competitors
        Object[] competitors = new Object[10];

        for (int i = 0; i < competitors.length; i++) {
            int competitorJumpHeight = (int) (Math.random() * 30);
            int competitorRunLength = (int) (Math.random() * 30);
            int competitorType = (int) (Math.random() * 10);
            if (competitorType <= 3) {
                competitors[i] = new Cat("Cat#" + i, competitorRunLength, competitorJumpHeight);
            } else if (competitorType >= 7) {
                competitors[i] = new Human("Human#" + i, competitorRunLength, competitorJumpHeight);
            } else {
                competitors[i] = new Robot("Robot#" + i, competitorRunLength, competitorJumpHeight);
            }
        }

    //  The race!

        for (int i = 0; i < competitors.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (competitors[i] instanceof Cat) {
                    ((Cat) competitors[i]).overcome(obstacles[j]);
                } else if (competitors[i] instanceof Human) {
                    ((Human) competitors[i]).overcome(obstacles[j]);
                } else if (competitors[i] instanceof Robot) {
                    ((Robot) competitors[i]).overcome(obstacles[j]);
                }
            }
        }
    }
}
