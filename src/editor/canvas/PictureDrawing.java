package editor.canvas;

import editor.config.ChangeStage;
import editor.controllers.DrawPictureAlertController;
import editor.controllers.GraphicEditorController;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.ArcType;

import java.util.List;

import static editor.config.Colors.brushColor;
import static editor.controllers.GraphicEditorController.graphicsContext;
import static editor.controllers.GraphicEditorController.selectedPicture;

public class PictureDrawing {
    private ChoiceBox<String> pictureSelect;
    private Button drawPictureBtn;

    public PictureDrawing(ChoiceBox<String> pictureSelect, Button drawPictureBtn) {
        this.pictureSelect = pictureSelect;
        this.drawPictureBtn = drawPictureBtn;
    }

    public PictureDrawing() {}

    public void start() {
        fillPicturesSelect();
        alertForPictureInformation();
    }

    private void fillPicturesSelect() {
        pictureSelect.getItems().add("Rect");
        pictureSelect.getItems().add("Triangle");
        pictureSelect.getItems().add("Round");
        pictureSelect.getItems().add("Arc");
        pictureSelect.getItems().add("Oval");
        pictureSelect.getItems().add("Round rect");
        pictureSelect.setValue("Rect");
        selectedPicture = "Rect";
    }

    private void alertForPictureInformation() {
        drawPictureBtn.setOnAction(actionEvent -> {
            graphicsContext.setFill(brushColor);
            selectedPicture = pictureSelect.getValue();
            String[] params;
            switch (selectedPicture) {
                case "Triangle":
                    params = new String[]{"X1", "X2", "X3", "Y1", "Y2", "Y3"};
                    break;
                case "Round":
                    params = new String[]{"X", "Y", "Diameter"};
                    break;
                case "Arc":
                    params = new String[]{"X", "Y", "Width", "Height", "Start angle", "Arc extent", "Type (OPEN (default), CHORD, ROUND)"};
                    break;
                case "Round rect":
                    params = new String[]{"X", "Y", "Width", "Height", "Corners width", "Corners height"};
                    break;
                default:
                    params = new String[]{"X", "Y", "Width", "Height"};
            }

            DrawPictureAlertController.initGridPaneElements(params);
            ChangeStage.changeStage(GraphicEditorController.class, "../ui/draw_picture_alert.fxml",
                    "Draw " + selectedPicture, new int[]{600, 400}, new int[]{600, 400}, false);
        });
    }

    public void drawSelectedPicture(List<String> valuesList) {
        double[] values;
        switch (selectedPicture) {
            case "Rect":
                values = initPictureDoubleValues(valuesList);
                graphicsContext.fillRect(values[0], values[1],
                        values[2], values[3]);
                break;
            case "Triangle":
                values = initPictureDoubleValues(valuesList);
                graphicsContext.fillPolygon(new double[]{values[0], values[1], values[2]},
                        new double[]{values[3], values[4], values[5]}, 3);
                break;
            case "Round":
                values = initPictureDoubleValues(valuesList);
                graphicsContext.fillOval(values[0], values[1], values[2], values[2]);
                break;
            case "Arc":
                String type = valuesList.get(valuesList.size() - 1);
                valuesList.remove(type);
                values = initPictureDoubleValues(valuesList);
                ArcType arcType;
                switch (type.toUpperCase()) {
                    case "CHORD":
                        arcType = ArcType.CHORD;
                        break;
                    case "ROUND":
                        arcType = ArcType.ROUND;
                        break;
                    default:
                        arcType = ArcType.OPEN;
                }
                graphicsContext.fillArc(values[0], values[1], values[2],
                        values[3], values[4], values[5], arcType);
                break;
            case "Oval":
                values = initPictureDoubleValues(valuesList);
                graphicsContext.fillOval(values[0], values[1],
                        values[2], values[3]);
                break;
            case "Round rect":
                values = initPictureDoubleValues(valuesList);
                graphicsContext.fillRoundRect(values[0], values[1], values[2],
                        values[3], values[4], values[5]);
                break;
        }
    }

    private double[] initPictureDoubleValues(List<String> valuesList) {
        double[] values = new double[valuesList.size()];
        for (int i = 0; i < valuesList.size(); i++) {
            try {
                values[i] = Double.parseDouble(valuesList.get(i));
            } catch (NumberFormatException ignored) {
                values[i] = 0;
            }
        }
        return values;
    }
}
