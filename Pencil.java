import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Pencil tool which draws a path as the user drags the mouse
 * @author Zack Littke-Smith
 * @version 1.0
 */
public class Pencil implements Tool {

    /**
     * Blank Pencil constructor
     */
    public Pencil() {
    }

    /**
     * Begins the path of the line
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        g.beginPath();
    }

    /**
     * Draws a line between the previous point and current point
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.lineTo(e.getX(), e.getY());
        g.stroke();
    }

    /**
     * Stops the path
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.closePath();
    }

    /**
     * The name of this tool.
     *
     * @return This tool's name.
     */
    public String getName() {
        return "Pencil";
    }
}