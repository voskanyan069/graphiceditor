package editor.controllers;

import editor.draw.CanvasIO;
import editor.draw.BrushDrawing;
import editor.draw.CanvasDrawing;
import editor.draw.PictureDrawing;
import editor.config.ContainersResizeListener;
import editor.ui.SetButtonsImage;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import static editor.config.Colors.*;

public class GraphicEditorController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private BorderPane mainPane;

    @FXML
    private SplitPane splitPane;

    @FXML
    private AnchorPane splitLeftPane;

    @FXML
    private AnchorPane splitRightPane;

    @FXML
    private GridPane toolsPane;

    @FXML
    private ColorPicker brushColorPicker;

    @FXML
    private Button brushBtn;

    @FXML
    private Button canvasClearBtn;

    @FXML
    private Button eraserBtn;

    @FXML
    private TextField brushWidthInput;

    @FXML
    private Button saveBrushWidthBtn;

    @FXML
    private ColorPicker canvasColorPicker;

    @FXML
    private Button canvasFillBtn;

    @FXML
    private ChoiceBox<String> pictureSelect;

    @FXML
    private Button drawPictureBtn;

    @FXML
    private Button openImageBtn;

    @FXML
    private Button exportImageBtn;

    @FXML
    private Canvas canvas;

    public static GraphicsContext graphicsContext;
    public static String selectedPicture;

    @FXML
    void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();

        final ContainersResizeListener containersResizeListener = new ContainersResizeListener(rootPane, mainPane,
                splitPane, splitLeftPane, splitRightPane, toolsPane, canvas);
        final SetButtonsImage setButtonsImage = new SetButtonsImage(brushBtn, eraserBtn, canvasFillBtn,
                canvasClearBtn, openImageBtn, exportImageBtn);
        final BrushDrawing brushDrawing = new BrushDrawing(canvas, brushBtn, eraserBtn, brushColorPicker, brushWidthInput, saveBrushWidthBtn);
        final CanvasDrawing canvasDrawing = new CanvasDrawing(canvas, canvasFillBtn, canvasClearBtn, canvasColorPicker);
        final PictureDrawing pictureDrawing = new PictureDrawing(pictureSelect, drawPictureBtn);
        final CanvasIO canvasIO = new CanvasIO(canvas, openImageBtn, exportImageBtn);

        containersResizeListener.resize();
        setButtonsImage.setIcons();
        brushDrawing.updateBrush(brushColor);
        brushDrawing.start();
        canvasDrawing.start();
        pictureDrawing.start();
        canvasIO.start();
    }
}
