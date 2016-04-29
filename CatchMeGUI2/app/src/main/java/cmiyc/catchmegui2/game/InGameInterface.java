package cmiyc.catchmegui2.game;

/**
 * Created by Computer on 28/04/2016.
 */
public interface InGameInterface {
    public void compassCalibration(double[] coordinates);
    public void setTimer(int time);
    public void setPlayerScore(int score);
    public void catchSuccess(boolean success);
    public void caught();
}
