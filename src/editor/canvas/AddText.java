package editor.canvas;

import editor.config.ChangeStage;
import editor.config.Colors;
import editor.controllers.GraphicEditorController;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static editor.controllers.GraphicEditorController.graphicsContext;

public class AddText {
    private final Button addTextBtn;

    public AddText(Canvas canvas, Button addTextBtn) {
        this.addTextBtn = addTextBtn;
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void openDialog() {
        addTextBtn.setOnAction(actionEvent -> ChangeStage.changeStage(GraphicEditorController.class, "../ui/add_text_alert.fxml",
                "Add Text", new int[]{600, 400}, new int[]{600, 400}, false));
    }

    public static void addText(String text, double x, double y, String fontFamilyPath, int fontSize, boolean filled) {
        graphicsContext.setFont(Font.loadFont(fontFamilyPath, fontSize));

        if (filled) {
            graphicsContext.setFill(Colors.textColor);
            graphicsContext.fillText(text, x, y);
            graphicsContext.setFill(Colors.brushColor);
        } else {
            graphicsContext.setStroke(Colors.textColor);
            graphicsContext.strokeText(text, x, y);
            graphicsContext.setStroke(Colors.brushColor);
        }
    }
}
