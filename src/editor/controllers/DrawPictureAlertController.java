package editor.controllers;

import editor.draw.PictureDrawing;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
                textField.setPromptText(labels[i]);
                textFieldsList.add(textField);
                gridPane.add(new Label(labels[i]), 0, i);
                gridPane.add(textFieldsList.get(i), 1, i);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Button submit = new Button("Draw");
        gridPane.add(submit, 0, i);
        submit.setOnAction(actionEvent -> {
            for (TextField textField : textFieldsList) {
                valuesList.add(textField.getText().trim());
            }
            (((Node) actionEvent.getSource()).getScene().getWindow()).hide();
            PictureDrawing pictureDrawing = new PictureDrawing();
            pictureDrawing.drawSelectedPicture(valuesList);
        });
    }

    public static void initGridPaneElements(String[] inputLabels) {
        labels = new String[inputLabels.length];
        System.arraycopy(inputLabels, 0, labels, 0, inputLabels.length);
    }
}