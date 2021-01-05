package editor.draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static editor.config.Colors.*;
import static editor.config.BrushWidth.*;
import static editor.controllers.GraphicEditorController.graphicsContext;

public class BrushDrawing {
    private final Canvas canvas;
    private Button brushBtn;
    private Button eraserBtn;
    private TextField brushWidthInput;
    private Button saveBrushWidthBtn;
    private ColorPicker brushColorPicker;

    public BrushDrawing(Canvas canvas, Button brushBtn, Button eraserBtn, ColorPicker brushColorPicker,
                        TextField brushWidthInput, Button saveBrushWidthBtn) {
        this.canvas = canvas;
        this.brushColorPicker = brushColorPicker;
        this.brushBtn = brushBtn;
        this.eraserBtn = eraserBtn;
        this.brushWidthInput = brushWidthInput;
        this.saveBrushWidthBtn = saveBrushWidthBtn;
        brushColorPicker.setValue(Color.BLACK);
    }

    public BrushDrawing(Canvas canvas) {
        this.canvas = canvas;
    }

    public void start() {
        changeBrushColor();
        changeBrushWidth();
        drawWithBrush();
        drawMode();
        eraseMode();
    }

    public void updateBrush(Color brushColor) {
        graphicsContext.setStroke(brushColor);
        graphicsContext.setLineWidth(brushWidth);
    }

    private void changeBrushColor() {
        brushColorPicker.setOnAction(actionEvent -> {
            System.out.println("New brush color: " + brushColorPicker.getValue());
            brushColor = brushColorPicker.getValue();
            updateBrush(brushColor);
        });
    }

    private void changeBrushWidth() {
        saveBrushWidthBtn.setOnAction(actionEvent -> {
            String lineWidthStr = brushWidthInput.getText();
            try {
                brushWidth = Integer.parseInt(lineWidthStr.trim());
                if (brushWidth < BRUSH_MIN_WIDTH) {
                    brushWidth = BRUSH_MIN_WIDTH;
                } else if (brushWidth > BRUSH_MAX_WIDTH) {
                    brushWidth = BRUSH_MAX_WIDTH;
                }
                brushWidthInput.setText(String.valueOf(brushWidth));
                System.out.println("New brush line width: " + brushWidth);
                BrushDrawing brushDrawing = new BrushDrawing(canvas);
                brushDrawing.updateBrush(brushColor);
            } catch (NumberFormatException e) {
                brushWidthInput.setText(String.valueOf(BRUSH_MIN_WIDTH));
            }
        });
    }

    private void drawWithBrush() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });
    }

    private void drawMode() {
        brushBtn.setOnAction(actionEvent -> updateBrush(brushColor));
    }

    private void eraseMode() {
        eraserBtn.setOnAction(actionEvent -> {
            eraserColor = canvasColor;
            updateBrush(eraserColor);
        });
    }
}
