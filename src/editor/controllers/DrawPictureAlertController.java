package editor.controllers;

import editor.canvas.PictureDrawing;
import editor.config.BrushWidth;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class DrawPictureAlertController {

    @FXML
    private GridPane gridPane;

    private static String[] labels;
    private final List<String> valuesList = new ArrayList<>();
    private final List<TextField> textFieldsList = new ArrayList<>();

    @FXML
    void initialize() {
        fillGridPane();
    }

    public void fillGridPane() {
        int i = 0;
        try {
            while (i < labels.length) {
                TextField textField = new TextField();
                Label label = new Label(labels[i]);
                textField.setPromptText(labels[i]);
                textField.getStyleClass().add("text_inputs");
                label.getStyleClass().add("draw_labels");
                textFieldsList.add(textField);
                gridPane.add(label, 0, i);
                gridPane.add(textFieldsList.get(i), 1, i);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CheckBox filledCB = new CheckBox();
        Label cbLabel = new Label("Fill");
        cbLabel.getStyleClass().add("draw_labels");
        gridPane.add(cbLabel, 0, i);
        gridPane.add(filledCB, 1, i++);
        Label swLabel = new Label("Stroke width");
        swLabel.getStyleClass().add("draw_labels");
        gridPane.add(swLabel, 0, i);
        TextField strokeWidth = new TextField();
        strokeWidth.setPromptText("Stroke width");
        strokeWidth.getStyleClass().add("text_inputs");
        gridPane.add(strokeWidth, 1, i++);
        Button submit = new Button("Draw");
        submit.getStyleClass().add("buttons");
        gridPane.add(submit, 0, i);
        submit.setOnAction(actionEvent -> {
            for (TextField textField : textFieldsList) {
                valuesList.add(textField.getText().trim());
            }
            try {
                BrushWidth.strokeWidth = Integer.parseInt(strokeWidth.getText());
            } catch (NumberFormatException e) {
                BrushWidth.strokeWidth = 1;
            }
            (((Node) actionEvent.getSource()).getScene().getWindow()).hide();
            PictureDrawing pictureDrawing = new PictureDrawing();
            pictureDrawing.drawSelectedPicture(valuesList, filledCB.isSelected());
        });
    }

    public static void initGridPaneElements(String[] inputLabels) {
        labels = new String[inputLabels.length];
        System.arraycopy(inputLabels, 0, labels, 0, inputLabels.length);
    }
}