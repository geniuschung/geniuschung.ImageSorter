import javafx.scene.control.TextArea;
import lombok.Data;

import java.io.File;

@Data
public class ImageSorterData {
    private File orgDir;
    private File tarDir;
    private TextArea logArea;
}
