package editor.canvas;


import editor.config.ChangeStage;
import editor.controllers.GraphicEditorController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class AddText {
    private final Button addTextBtn;
    private static GraphicsContext graphicsContext;

    public AddText(Canvas canvas, Button addTextBtn) {
        this.addTextBtn = addTextBtn;
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void openDialog() {
        addTextBtn.setOnAction(actionEvent -> {
            ChangeStage.changeStage(GraphicEditorController.class, "../ui/add_text_alert.fxml",
                    "Add Text", new int[]{600, 400}, new int[]{600, 400}, false);
        });
    }

    public static void addText(String text, double x, double y, Font font) {
        graphicsContext.setFont(font);
        graphicsContext.strokeText(text, x, y);
    }
}
