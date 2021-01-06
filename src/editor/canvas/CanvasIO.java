package editor.canvas;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static editor.controllers.GraphicEditorController.graphicsContext;

public class CanvasIO {
    private final Canvas canvas;
    private final Button open;
    private final Button export;
    private final FileChooser fileChooser = new FileChooser();

    public CanvasIO(Canvas canvas, Button open, Button export) {
        this.canvas = canvas;
        this.open = open;
        this.export = export;
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public void start() {
        openImage();
        exportImage();
    }

    private void openImage() {
        open.setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter extensionFilter =
                    new FileChooser.ExtensionFilter("image files (*.png, *.jpg, *.jpeg, *.svg)",
                            "*.png", "*.jpg", "*.jpeg", "*.svg");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Open image");
            File file = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());

            if (file != null) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    graphicsContext.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void exportImage() {
        export.setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter extensionFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Save image");
            fileChooser.setInitialFileName("image.png");
            File file = fileChooser.showSaveDialog((((Node) actionEvent.getSource()).getScene().getWindow()));

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
