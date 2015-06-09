import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Oval tool which draws ovals between the mouse click and release
 * @author Zack Littke-Smith
 * @version 1.0
 */
public class Oval implements Tool {

    private double startingX;
    private double startingY;
    private GraphicsContext gAlt;
    private double width;
    private double height;
    private double topLeftX;
    private double topLeftY;
    /**
     * Oval constructor which takes a GraphicsContext argument to trace on
     *
     * @param gAlt the GraphicsContext which the oval is traced on
     */
    public Oval(GraphicsContext gAlt) {
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
     * Traces the oval so the user can see it before they place it. If shift is
     * held down, the aspect ratio is set to 1:1
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
        gAlt.strokeOval(topLeftX, topLeftY, width,
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
        g.fillOval(topLeftX, topLeftY, width,
            e.isShiftDown() ? width : height);
        g.strokeOval(topLeftX, topLeftY, width,
            e.isShiftDown() ? width : height);
    }

    /**
     * The name of this tool.
     *
     * @return This tool's name.
     */
    public String getName() {
        return "Oval";
    }
}