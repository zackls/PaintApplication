import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Rectangle tool which draws rectangles between the mouse click and release
 * @author Zack Littke-Smith
 * @version 1.0
 */
public class Rectangle implements Tool {

    private double startingX;
    private double startingY;
    private GraphicsContext gAlt;
    private double width;
    private double height;
    private double topLeftX;
    private double topLeftY;
    /**
     * Rectangle constructor which takes a GraphicsContext argument to trace on
     *
     * @param gAlt the GraphicsContext which the rectangle is traced on
     */
    public Rectangle(GraphicsContext gAlt) {
        this.gAlt = gAlt;
    }

    /**
     * Saves the initial click point
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startingX = e.getX();
        startingY = e.getY();
    }

    /**
     * Traces the rectangle so the user can see it before they place it. If
     * shift is held down, the aspect ratio is set to 1:1
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        gAlt.clearRect(0, 0, gAlt.getCanvas().getWidth(),
                             gAlt.getCanvas().getHeight());
        width = Math.abs(e.getX() - startingX);
        height = Math.abs(e.getY() - startingY);
        topLeftX = startingX < e.getX() ? startingX : e.getX();
        topLeftY = startingY < e.getY() ? startingY
            : e.isShiftDown() ? startingY - width : e.getY();
        gAlt.strokeRect(topLeftX, topLeftY, width,
            e.isShiftDown() ? width : height);
    }

    /**
     * Draws a rectangle spanning between the initial click and final release
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        gAlt.clearRect(0, 0, gAlt.getCanvas().getWidth(),
                             gAlt.getCanvas().getHeight());
        g.fillRect(topLeftX, topLeftY, width,
            e.isShiftDown() ? width : height);
        g.strokeRect(topLeftX, topLeftY, width,
            e.isShiftDown() ? width : height);
    }

    /**
     * The name of this tool.
     *
     * @return This tool's name.
     */
    public String getName() {
        return "Rectangle";
    }
}