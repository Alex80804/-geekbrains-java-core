package lesson1hw;

public class Cat implements Overcome {

    private String name;

    private int runningLimit;
    private int jumpingLimit;

    private boolean failedOnce;

    public Cat(String name, int runningLimit, int jumpingLimit) {
        this.name=name;
        this.runningLimit = runningLimit;
        this.jumpingLimit = jumpingLimit;
    }

    public void run(RunningTrack runningTrack) {
        if (this.runningLimit < runningTrack.getLength()) {
            failedOnce = true;
            System.out.println(name + " failed to run the track: length " + runningTrack.getLength());
        } else {
            System.out.println(name + " successfully runs the track: length  " + runningTrack.getLength());
        }
    }

    public void jump(Wall wall) {
        if (this.jumpingLimit < wall.getHeight()) {
            failedOnce = true;
            System.out.println(name + " failed to jump over the wall: height " + wall.getHeight());
        } else {
            System.out.println(name + " successfully jumps over the wall: height " + wall.getHeight());
        }

    }

    @Override
    public void overcome(Obstacle obstacle) {
        if (failedOnce == true) return;
        if (obstacle instanceof Wall) {
            jump((Wall) obstacle);
        } else {
            run((RunningTrack) obstacle);
        }
    }
}
