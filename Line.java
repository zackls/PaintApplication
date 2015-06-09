import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Line tool which draws lines between the mouse click and release
 * @author Zack Littke-Smith
 * @version 1.0
 */
public class Line implements Tool {

    private double startingX;
    private double startingY;
    private GraphicsContext gAlt;
    /**
     * Line constructor which takes a GraphicsContext argument to trace on
     *
     * @param gAlt the GraphicsContext which the line is traced on
     */
    public Line(GraphicsContext gAlt) {
        this.gAlt = gAlt;
    }

    /**
     * Begins the path and sets the beginning point
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startingX = e.getX();
        startingY = e.getY();
        g.beginPath();
        g.moveTo(startingX, startingY);
    }

    /**
     * Traces the line on the alternate GraphicsContext so the user may see it
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        gAlt.clearRect(0, 0, gAlt.getCanvas().getWidth(),
                             gAlt.getCanvas().getHeight());
        gAlt.beginPath();
        gAlt.moveTo(startingX, startingY);
        gAlt.lineTo(e.getX(), e.getY());
        gAlt.stroke();
        gAlt.closePath();
    }

    /**
     * Draws the line when the user releases the mouse
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        gAlt.clearRect(0, 0, gAlt.getCanvas().getWidth(),
                             gAlt.getCanvas().getHeight());
        g.lineTo(e.getX(), e.getY());
        g.stroke();
        g.closePath();
    }

    /**
     * The name of this tool.
     *
     * @return This tool's name.
     */
    public String getName() {
        return "Line";
    }
}