package editor.controllers;

import editor.canvas.AddText;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class AddTextAlertController {

    @FXML
    private TextArea addTextArea;

    @FXML
    private TextField fontSizeInput;

    @FXML
    private Button addTextToCanvasBtn;

    @FXML
    private Button selectFontBtn;

    @FXML
    private TextField xPositionInput;

    @FXML
    private TextField yPositionInput;

    private double x;
    private double y;
    private int fontSize;
    private String fontFamilyPath;
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    void initialize() {
        addText();
        changeFontFamily();
    }

    private void addText() {
        addTextToCanvasBtn.setOnAction(actionEvent -> {
            fontSize = (int) setNumber(fontSizeInput);
            x = setNumber(xPositionInput);
            y = setNumber(yPositionInput);
            if (fontSize == 0) {
                fontSize = 13;
            }

            AddText.addText(addTextArea.getText(), x, y, "file:" + fontFamilyPath, fontSize);
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        });
    }

    private void changeFontFamily() {
        selectFontBtn.setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter extensionFilter =
                    new FileChooser.ExtensionFilter("font family files (*.ttf)", "*.ttf");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Open font");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());

            if (file != null) {
                fontFamilyPath = file.getAbsolutePath();
            }
        });
    }

    private double setNumber(TextField input) {
        try {
            return Double.parseDouble(input.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
