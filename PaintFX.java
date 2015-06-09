import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX initializer which mimics a Paint application
 * @author Zack Littke-Smith
 * @version 1.0
 */
public class PaintFX extends Application {

    private Tool currentTool = null;

    /**
     * start method which initializes the main content of the program
     *
     * @param stage The Application's stage
     */
    public void start(Stage stage) {
        final Canvas canvas = new Canvas(600, 600);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        final Canvas tempCanvas = new Canvas(600, 600);
        StackPane canvasStack = new StackPane(canvas, tempCanvas);
        canvasStack.setStyle("-fx-background-color: #FFFFFF");
        tempCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                if (currentTool != null) {
                    currentTool.onPress(e, graphics);
                }
            });
        tempCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
                if (currentTool != null) {
                    currentTool.onDrag(e, graphics);
                }
            });
        tempCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
                if (currentTool != null) {
                    currentTool.onRelease(e, graphics);
                }
            });

        final Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> graphics.clearRect(0, 0,
            canvas.getWidth(), canvas.getHeight()));


        final CheckBox fillBox = new CheckBox("Fill");
        fillBox.setSelected(true);
        final ColorPicker fillColorPicker = new ColorPicker(Color.BLUE);
        graphics.setFill(fillColorPicker.getValue());
        fillColorPicker.setOnAction(e ->
            graphics.setFill(fillBox.isSelected()
                ? fillColorPicker.getValue() : Color.TRANSPARENT));
        fillBox.setOnAction(e ->
            graphics.setFill(fillBox.isSelected()
                ? fillColorPicker.getValue() : Color.TRANSPARENT));

        final CheckBox strokeBox = new CheckBox("Stroke");
        strokeBox.setSelected(true);
        final ColorPicker strokeColorPicker = new ColorPicker(Color.RED);
        graphics.setStroke(strokeColorPicker.getValue());
        strokeColorPicker.setOnAction(e ->
            graphics.setStroke(strokeBox.isSelected()
                ? strokeColorPicker.getValue() : Color.TRANSPARENT));
        strokeBox.setOnAction(e ->
            graphics.setStroke(strokeBox.isSelected()
                ? strokeColorPicker.getValue() : Color.TRANSPARENT));

        final Pencil pencilTool = new Pencil();
        final Rectangle rectTool
            = new Rectangle(tempCanvas.getGraphicsContext2D());
        final Oval ovalTool = new Oval(tempCanvas.getGraphicsContext2D());
        final Line lineTool = new Line(tempCanvas.getGraphicsContext2D());
        final Eraser eraserTool = new Eraser();

        final Button pencil = new Button(pencilTool.getName());
        pencil.setOnAction(e -> { currentTool = pencilTool; });
        final Button rectangle = new Button(rectTool.getName());
        rectangle.setOnAction(e -> { currentTool = rectTool; });
        final Button oval = new Button(ovalTool.getName());
        oval.setOnAction(e -> { currentTool = ovalTool; });
        final Button line = new Button(lineTool.getName());
        line.setOnAction(e -> { currentTool = lineTool; });
        final Button eraser = new Button(eraserTool.getName());
        eraser.setOnAction(e -> { currentTool = eraserTool; });

        VBox buttons = new VBox(5, new Text("Tools"), clearButton, fillBox, 
            fillColorPicker, strokeBox, strokeColorPicker, pencil, rectangle,
            oval, line, eraser);
        buttons.setStyle("-fx-background-color: #DDFFDD");
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setMinWidth(100);

        Scene scene = new Scene(new HBox(buttons, canvasStack));

        stage.setScene(scene);
        stage.setTitle("JavaFX Paint");
        stage.show();
    }
}