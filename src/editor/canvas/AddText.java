package editor.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

public class AddText {
    private final Canvas canvas;
    private final ColorPicker textColorPicker;

    public AddText(Canvas canvas, ColorPicker colorPicker) {
        this.canvas = canvas;
        this.textColorPicker = colorPicker;
    }
}
