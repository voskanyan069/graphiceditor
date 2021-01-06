package editor.controllers;

import editor.canvas.AddText;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

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

            AddText.addText(addTextArea.getText(), x, y, new Font(fontSize));
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        });
    }

    private void changeFontFamily() {
        selectFontBtn.setOnAction(actionEvent -> {

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
