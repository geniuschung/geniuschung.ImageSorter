import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ImageSorterDataImgFileTest {

    @Test
    public void Test_ImageSorterDataImgFile() {
        try {
            System.out.println("/home/geniuschung/사진/IMG_20190126_185555.jpg");

            File f = new File("/home/geniuschung/사진/IMG_20190126_185555.jpg");
            ImageSorterDataImgFile imageSorterDataImgFile = new ImageSorterDataImgFile(f);

            System.out.println(imageSorterDataImgFile.getCreateDt());
            System.out.println(imageSorterDataImgFile.getCreateMonth());
            System.out.println(imageSorterDataImgFile.getCreateYear());

            assertEquals(imageSorterDataImgFile.getCreateMonth().length(),2);
            assertEquals(imageSorterDataImgFile.getCreateYear().length(),4);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}