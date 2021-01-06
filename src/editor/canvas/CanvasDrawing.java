package editor.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import static editor.config.Colors.canvasColor;
import static editor.controllers.GraphicEditorController.graphicsContext;

public class CanvasDrawing {
    private final Canvas canvas;
    private final Button canvasFillBtn;
    private final Button canvasClearBtn;
    private final ColorPicker canvasColorPicker;

    public CanvasDrawing(Canvas canvas, Button canvasFillBtn, Button canvasClearBtn, ColorPicker canvasColorPicker) {
        this.canvas = canvas;
        this.canvasFillBtn = canvasFillBtn;
        this.canvasClearBtn = canvasClearBtn;
        this.canvasColorPicker = canvasColorPicker;
    }

    public void start() {
        fillCanvas();
        clearCanvas();
        changeCanvasColor();
    }

    private void fillCanvas() {
        canvasFillBtn.setOnAction(actionEvent -> {
            graphicsContext.setFill(canvasColor);
            graphicsContext.fillRect(0, 0,
                    canvas.getWidth(), canvas.getHeight());
            System.out.println("Canvas was filled");
        });
    }

    private void clearCanvas() {
        canvasClearBtn.setOnAction(actionEvent -> graphicsContext.clearRect(0, 0,
                canvas.getWidth(), canvas.getHeight()));
    }

    private void changeCanvasColor() {
        canvasColorPicker.setOnAction(actionEvent -> {
            canvasColor = canvasColorPicker.getValue();
            System.out.println("New canvas color: " + canvasColor);
        });
    }
}
