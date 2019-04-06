import javafx.scene.control.TextArea;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageSorterServiceTest {


    ImageSorterService imageSorterService = new ImageSorterService();

    @Test
    public void goImgArrange() throws Exception {
        imageSorterService.getImageSorterData().setOrgDir(new File("/home/geniuschung/사진/"));
        imageSorterService.getImageSorterData().setTarDir(new File("/home/geniuschung/사진/resize"));

        //TextArea mock = mock(TextArea.class);
        //가상객체를 주입
        //imageSorterService.setLogArea(mock);

        //when(mock.setText("test")).thenReturn(projects);

        imageSorterService.goImgArrange();
    }
}

