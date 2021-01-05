package editor.config;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ContainersResizeListener {
    private final AnchorPane rootPane;
    private final BorderPane mainPane;
    private final SplitPane splitPane;
    private final AnchorPane splitLeftPane;
    private final AnchorPane splitRightPane;
    private final GridPane toolsPane;
    private final Canvas canvas;

    public ContainersResizeListener(AnchorPane rootPane, BorderPane mainPane, SplitPane splitPane,
                                    AnchorPane splitLeftPane, AnchorPane splitRightPane,
                                    GridPane toolsPane, Canvas canvas) {
        this.rootPane = rootPane;
        this.mainPane = mainPane;
        this.splitPane = splitPane;
        this.splitLeftPane = splitLeftPane;
        this.splitRightPane = splitRightPane;
        this.toolsPane = toolsPane;
        this.canvas = canvas;
    }

    public void resize() {
        containersResizeListener();
        splitContainersSize();
    }

    private void containersResizeListener() {
        mainPane.prefWidthProperty().bind(rootPane.widthProperty());
        mainPane.prefHeightProperty().bind(rootPane.heightProperty());
        splitPane.prefWidthProperty().bind(mainPane.widthProperty());
        splitPane.prefHeightProperty().bind(mainPane.heightProperty());
        canvas.widthProperty().bind(splitRightPane.widthProperty());
        canvas.heightProperty().bind(splitRightPane.heightProperty());
        toolsPane.prefWidthProperty().bind(splitLeftPane.widthProperty());
        toolsPane.setAlignment(Pos.CENTER);
    }

    private void splitContainersSize() {
        splitLeftPane.setMinWidth(200);
        splitLeftPane.setMaxWidth(400);
        splitRightPane.setMinWidth(200);
    }
}
