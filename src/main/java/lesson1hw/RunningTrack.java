package lesson1hw;

public class RunningTrack extends Obstacle {
    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    @Override
    public int getLength() {
        return length;
    }
}
