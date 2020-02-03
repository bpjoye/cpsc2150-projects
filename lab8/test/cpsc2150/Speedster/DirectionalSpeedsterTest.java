package cpsc2150.Speedster;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionalSpeedsterTest {

    double EPSILON = 0.0001;

    // constructor test
    @Test
    public void testDirectionalSpeedster() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        double totalTime = speedster.getTotalTime();
        double totalDistance = speedster.getTotalDistance();
        double curXPos = speedster.getCurXPos();
        double curYPos = speedster.getCurYPos();

        assertTrue(totalTime == 0 && totalDistance == 0 && curXPos == 0 && curYPos == 0);
    }

    /**
     *      testing addTravel
     */

    // adding zero to both x and y, sometimes zero causes problems
    @Test
    public void testAddTravel_x0_y0_t1() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(0,0,1);

        assertTrue(speedster.getCurXPos() == 0 && speedster.getCurYPos() == 0 && speedster.getTotalTime() == 1);
    }

    // adding a distance, then going back to the origin. makes sure you can travel back to the origin
    @Test
    public void testAddTravel_backToOrigin() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,10,10);
        speedster.addTravel(-10,-10,10);

        assertTrue(speedster.getCurXPos() == 0 && speedster.getCurYPos() == 0);
    }

    // makes sure it can handle two negative values. negative values could cause problems
    @Test
    public void testAddTravel_negX_negY() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(-10,-10,10);

        assertTrue(speedster.getCurXPos() == -10 && speedster.getCurYPos() == -10);
    }

    // tests positive decimals for x and y
    @Test
    public void testAddTravel_posDecX_posDecY() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(12.5,12.5,10);

        assertTrue(speedster.getCurXPos() == 12.5 && speedster.getCurYPos() == 12.5);
    }

    // tests negative decimals for x and y
    @Test
    public void testAddTravel_negDecX_negDecY() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(-12.5,-12.5,10);

        assertTrue(speedster.getCurXPos() == -12.5 && speedster.getCurYPos() == -12.5);
    }

    // makes sure the time can be calculated with a decimal
    @Test
    public void testAddTravel_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(1,1,0.5);

        assertTrue(speedster.getTotalTime() == 0.5);
    }

    // tests x being a value while y is zero
    @Test
    public void testAddTravel_x10_y0() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,0,10);

        assertTrue(speedster.getCurXPos() == 10 && speedster.getCurYPos() == 0);
    }

    // tests y being a value while x is zero
    @Test
    public void testAddTravel_x0_y10() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(0,10,10);

        assertTrue(speedster.getCurXPos() == 0 && speedster.getCurYPos() == 10);
    }

    // tests going between quadrants 1 and 3 on the coordinate plane
    @Test
    public void testAddTravel_xPosToNeg_yPosToNeg() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,20,10);
        speedster.addTravel(-40,-40,10);

        assertTrue(speedster.getCurXPos() == -20 && speedster.getCurYPos() == -20);
    }

    // tests going between quadrants 4 to 2 on the coordinate plane
    @Test
    public void testAddTravel_xPosToNeg_yNegToPos() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,-20,10);
        speedster.addTravel(-40,40,10);

        assertTrue(speedster.getCurXPos() == -20 && speedster.getCurYPos() == 20);
    }

    /**
     *      testing getTotalDistance
     */

    // tests negative decimals for x and y
    @Test
    public void testGetTotalDistance_negDecX_negDecY() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(-12.5,-12.5,10);

        assertEquals(speedster.getTotalDistance(),17.67767,EPSILON);
    }

    // tests going between quadrants 1 and 3 on the coordinate plane
    @Test
    public void testGetTotalDistance_xPosToNeg_yPosToNeg() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,20,10);
        speedster.addTravel(-40,-40,10);

        assertEquals(speedster.getTotalDistance(), 84.8528,EPSILON);
    }

    // tests going between quadrants 4 to 2 on the coordinate plane
    @Test
    public void testGetTotalDistance_xPosToNeg_yNegToPos() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,-20,10);
        speedster.addTravel(-40,40,10);

        assertEquals(speedster.getTotalDistance(), 84.8528,EPSILON);
    }

    // tests adding 3 different travels, including negatives and decimals
    @Test
    public void testGetTotalDistance_3travels() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(25,-12.5,10);
        speedster.addTravel(-5,9,10);
        speedster.addTravel(3,-3,10);

        assertEquals(speedster.getTotalDistance(),42.48912,EPSILON);
    }

    // tests that totaldistance isn't 0 when it returns to the origin
    @Test
    public void testGetTotalDistance_backToOrigin() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,10,10);
        speedster.addTravel(-10,-10,10);

        assertEquals(speedster.getTotalDistance(), 28.28427,EPSILON);
    }

    /**
     *      testing getNetDistance
     */

    // tests negative decimals for x and y
    @Test
    public void testGetNetDistance_negDecX_negDecY() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(-12.5,-7.2,10);

        assertEquals(speedster.getNetDistance(),14.4253, EPSILON);
    }

    // tests going between quadrants 1 and 3 on the coordinate plane
    @Test
    public void testGetNetDistance_xPosToNeg_yPosToNeg() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,20,10);
        speedster.addTravel(-40,-40,10);

        assertEquals(speedster.getNetDistance(), 28.28427, EPSILON);
    }

    // tests going between quadrants 4 to 2 on the coordinate plane
    @Test
    public void testGetNetDistance_xPosToNeg_yNegToPos() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,-20,10);
        speedster.addTravel(-40,40,10);

        assertEquals(speedster.getNetDistance(), 28.28427,EPSILON);
    }

    // tests adding 3 different travels, including negatives and decimals
    @Test
    public void testGetNetDistance_3travels() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(25,-12.5,10);
        speedster.addTravel(-5,9,10);
        speedster.addTravel(3,-3,10);

        assertEquals(speedster.getNetDistance(),23.9008,EPSILON);
    }

    // tests that net distance is 0 when it returns to the origin
    @Test
    public void testGetNetDistance_backToOrigin() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,10,10);
        speedster.addTravel(-10,-10,10);

        assertEquals(speedster.getNetDistance(), 0,EPSILON);
    }

    /**
     *      testing getAverageSpeed
     */

    // tests that average speed isn't 0 when it returns to the origin
    @Test
    public void testGetAverageSpeed_backToOrigin() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,10,10);
        speedster.addTravel(-10,-10,10);

        assertEquals(speedster.getAverageSpeed(), 1.4142,EPSILON);
    }

    // tests adding 3 different travels, including negatives and decimals
    @Test
    public void testGetAverageSpeed_3travels() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(25,-12.5,10);
        speedster.addTravel(-5,9,10);
        speedster.addTravel(3,-3,2);

        assertEquals(speedster.getAverageSpeed(),1.9313,EPSILON);
    }

    // makes sure the time can be calculated with a decimal
    @Test
    public void testGetAverageSpeed_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(4,3,0.5);
        speedster.addTravel(6,7,0.8);

        assertEquals(speedster.getAverageSpeed(),10.9381,EPSILON);
    }

    // tests going between quadrants 1 and 3 on the coordinate plane with decimal time
    @Test
    public void testGetAverageSpeed_xPosToNeg_yPosToNeg_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,20,10.2);
        speedster.addTravel(-40,-40,9.05);

        assertEquals(speedster.getAverageSpeed(), 4.4079,EPSILON);
    }

    // tests going between quadrants 4 to 2 on the coordinate plane with decimal time
    @Test
    public void testGetAverageSpeed_xPosToNeg_yNegToPos_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,-20,15.97);
        speedster.addTravel(-40,40,13.101);

        assertEquals(speedster.getAverageSpeed(), 2.9188,EPSILON);
    }


    /**
     *      testing getNetSpeed
     */

    // tests that net speed is 0 when it returns to the origin
    @Test
    public void testGetNetSpeed_backToOrigin() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(10,10,10);
        speedster.addTravel(-10,-10,10);

        assertEquals(speedster.getNetSpeed(), 0,EPSILON);
    }

    // tests adding 3 different travels, including negatives and decimals
    @Test
    public void testGetNetSpeed_3travels() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(25,-12.5,10);
        speedster.addTravel(-5,9,10);
        speedster.addTravel(3,-3,2);

        assertEquals(speedster.getNetSpeed(),1.0864,EPSILON);
    }

    // makes sure the time can be calculated with a decimal
    @Test
    public void testGetNetSpeed_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(4,3,0.5);
        speedster.addTravel(6,7,0.8);

        assertEquals(speedster.getNetSpeed(),10.8785,EPSILON);
    }

    // tests going between quadrants 1 and 3 on the coordinate plane with decimal time
    @Test
    public void testGetNetSpeed_xPosToNeg_yPosToNeg_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,20,10.2);
        speedster.addTravel(-40,-40,9.05);

        assertEquals(speedster.getNetSpeed(), 1.4693,EPSILON);
    }

    // tests going between quadrants 4 to 2 on the coordinate plane with decimal time
    @Test
    public void testGetNetSpeed_xPosToNeg_yNegToPos_dec_t() {

        IDirectionalSpeedster speedster = new DirectionalSpeedster();
        speedster.addTravel(20,-20,15.97);
        speedster.addTravel(-40,40,13.101);

        assertEquals(speedster.getNetSpeed(), 0.9729,EPSILON);
    }

}
