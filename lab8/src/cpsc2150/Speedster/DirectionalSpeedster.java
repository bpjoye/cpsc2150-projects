package cpsc2150.Speedster;
import java.lang.Math;

public class DirectionalSpeedster implements IDirectionalSpeedster {

    private double totalTime;
    private double totalDistance;
    private double curXPos;
    private double curYPos;



    public DirectionalSpeedster() {
        totalTime = 0;
        totalDistance = 0;
        curXPos = 0;
        curYPos = 0;
    }

    /**
     *
     * @param xChange change in x position. Positive is East, negative is west
     * @param yChange change in y position. Positive is North, negative is south
     * @param time how long this travel took
     * @pre time > 0
     * @post curXPos = #curXPos + xChange and curYPos = #curYPos + yChange
     */
    public void addTravel(double xChange, double yChange, double time) {
        curXPos += xChange;
        curYPos += yChange;
        totalDistance += Math.sqrt((xChange*xChange) + (yChange*yChange));
        totalTime += time;
    }

    /**
     *
     * @return the total distance travelled
     * @post getTotalDistance = totalDistance
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     *
     * @return the total time
     * @post getTotalTime = totalTime
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     *
     * @return the net distance
     * @post getNetDistance = netDistance
     */
    public double getNetDistance() {
        return Math.sqrt((curXPos * curXPos) + (curYPos * curYPos));
    }

    /**
     *
     * @return the average speed over all the travel
     * @post getAverageSpeed = averageSpeed
     */
    public double getAverageSpeed() {
        return getTotalDistance()/getTotalTime();
    }

    /**
     *
     * @return the net speed of the travel
     * @post getNetSpeed = netSpeed
     */
    public double getNetSpeed() {
        return getNetDistance()/getTotalTime();
    }

    /**
     *
     * @return the current X position
     * @post  getCurXPos = curXPos
     */
    public double getCurXPos() {
        return curXPos;
    }

    /**
     *
     * @return the current Y position
     * @post getCurYPos = curYPos
     */
    public double getCurYPos() {
        return curYPos;
    }
}
