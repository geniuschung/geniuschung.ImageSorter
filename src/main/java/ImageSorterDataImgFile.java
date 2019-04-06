
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.StreamSupport;

@Data
public class ImageSorterDataImgFile {
    public static final int TAG_TYPE_CREATE_DATE = 306;

    private String createDt;
    private String createYear;
    private String createMonth;
    private LocalDateTime createDateTime;


    public ImageSorterDataImgFile(File orgFile) throws Exception{
        Metadata metadata = JpegMetadataReader.readMetadata(new FileInputStream(orgFile));
        Iterable<Directory> iter = metadata.getDirectories();

        FileTime fileTime = Files.readAttributes(orgFile.toPath(), BasicFileAttributes.class).creationTime();
        LocalDateTime.ofInstant(fileTime.toInstant(),ZoneId.systemDefault());


        LocalDateTime imgTime = StreamSupport.stream(iter.spliterator(), false)
                .filter(dir -> dir.getName().equals("Exif IFD0"))
                .map(dir -> LocalDateTime.ofInstant(dir.getDate(TAG_TYPE_CREATE_DATE).toInstant(), ZoneId.systemDefault()))
                .findFirst()
                .orElseGet(()->LocalDateTime.ofInstant(fileTime.toInstant(),ZoneId.systemDefault()));

        this.createMonth = String.format("%02d",imgTime.getMonthValue());
        this.createYear = String.valueOf(imgTime.getYear());
        this.createDateTime = imgTime;
        this.createDt = imgTime.toString();
        /*







        for(Directory dir : iter) {
            dir.getTags().stream()
                    .filter(tag -> tag.getTagType() == TAG_TYPE_CREATE_DATE)
                    .peek(tag -> System.out.println(dir.getName() +","+ tag.getTagName()))
                    .map(tag -> LocalDateTime.parse(tag.getDescription(), DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")))
                    .peek(localDateTime -> this.createMonth = String.format("%02d",localDateTime.getMonthValue()))
                    .peek(localDateTime -> this.createYear = String.valueOf(localDateTime.getYear()))
                    .findFirst()


            ;

            if (this.createDt == null){
                BasicFileAttributes attrs = Files.readAttributes(orgFile.toPath(), BasicFileAttributes.class);
                FileTime time = attrs.creationTime();
                this.createDt = time.toString().substring(0,10);
                generateImageDateInfo();
            }

         */

    }


    private void generateImageDateInfo() {
        this.createYear = this.createDt.substring(0, 4);
        this.createMonth = this.createDt.substring(5, 7);


    }
}

