import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImageSorterService {
    ImageSorterData imageSorterData = new ImageSorterData();
    List<File> orgFiles;
    TextArea logArea;

    public void setLogArea(TextArea logArea) {
        this.logArea = logArea;
    }

    public TextArea getLogArea(){
        return this.logArea;
    }

    private ImageSorterValidator imageSorterValidator = new ImageSorterValidator();

    public void goImgArrange()  {
        int copyCnt = 0;
        int skipCnt = 0;
        try {
            imageSorterValidator.checkImagArrange(imageSorterData);

            orgFiles = getImgFiles();
            /*
            File tarDir = null;

            orgFiles.stream()
                    .filter(file -> {

                try {
                    tarDir  = getTarDir( new ImageSorterDataImgFile(file) );
                }catch(Exception e) {
                    e.printStackTrace();
                }
                checkTarFile(file,tarDir);

            })
            .peek(file -> copyImgFile(file, tarDir));

            */


            for (File orgFile : orgFiles) {
                ImageSorterDataImgFile imageSorterDataImgFile =  new ImageSorterDataImgFile(orgFile);
                File tarDir = getTarDir(imageSorterDataImgFile);
                if (checkTarFile(orgFile, tarDir)) {
                    copyImgFile(orgFile, tarDir);
                    logAdd(orgFile.getName() + "복사");
                    copyCnt++;
                    //logArea.setText(logArea.getText()+orgFile.getName() + "복사");
                } else {
                    logAdd(orgFile.getName() + "존재함 skip");
                    skipCnt++;
                    //logArea.setText(logArea.getText()+orgFile.getName()+"존재함 skip");
                }
            }

            logAdd("이미지 정리 완료 ========>");
            logAdd("이미지 정리 건수 : " + copyCnt);
            logAdd("이미지 Skip 건수 : " + skipCnt);

        }catch(Exception e){
            logAdd(e.getMessage());
            e.printStackTrace();
        }

    }

    public void logAdd(String log){
        StringBuffer logBuffer = new StringBuffer(logArea.getText());
        logBuffer.append(log);
        logBuffer.append("\n");
        logArea.setText(logBuffer.toString());

    }

    public boolean checkTarFile(File orgFile, File tarDir){
        File tarFile = new File(tarDir,orgFile.getName());
        if(tarFile.exists()){
            return false;
        }
        return true;
    }


    public void copyImgFile(File orgFile, File targetLeafDir){
        File tarFile = new File(targetLeafDir,orgFile.getName());

        try{
            FileInputStream is = new FileInputStream(orgFile);
            FileOutputStream out = new FileOutputStream(tarFile);

            byte[] byteArray = new byte[1024];
            int readLength = 0 ;

            while ( (readLength = is.read(byteArray,0,byteArray.length)) != -1 ){
                out.write(byteArray, 0, readLength);
            }
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public File getTarDir(ImageSorterDataImgFile imageSorterDataImgFile){
        File targetLeafYearDir = new File(imageSorterData.getTarDir(),imageSorterDataImgFile.getCreateYear());
        File targetLeafMonthDir = new File(targetLeafYearDir,imageSorterDataImgFile.getCreateMonth());

        checkTargetDir(targetLeafYearDir, targetLeafMonthDir);

        return targetLeafMonthDir;

    }

    private void checkTargetDir(File targetLeafYearDir, File targetLeafMonthDir) {
        if(!imageSorterData.getTarDir().exists()){
            imageSorterData.getTarDir().mkdir();
        }

        if(!targetLeafYearDir.exists()) {
            targetLeafYearDir.mkdir();
        }

        if(!targetLeafMonthDir.exists()){
            targetLeafMonthDir.mkdir();
        }
    }

    public void chooseOrgDir(Stage stage, TextField orgDirTxt){
        imageSorterData.setOrgDir(getDir(stage));
        orgDirTxt.setText(imageSorterData.getOrgDir().getAbsolutePath());
    }

    public void chooseTarDir(Stage stage, TextField tarDirTxt){
        imageSorterData.setTarDir(getDir(stage));
        tarDirTxt.setText(imageSorterData.getTarDir().getAbsolutePath());
    }

    private List<File> getImgFiles() {
        return Arrays.stream(imageSorterData.getOrgDir().listFiles())
                .filter(
                    file -> file.getName().toUpperCase().endsWith("JPG") ||
                            file.getName().toUpperCase().endsWith("JPEG")
                )
                .collect(Collectors.toList());
        /*

        File [] orgFiles = imageSorterData.getOrgDir().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean isImgFile = false;
                if(name.toUpperCase().endsWith("JPG") || name.toUpperCase().endsWith("JPEG")){
                    isImgFile = true;
                }
                return isImgFile;
            }
        });
        return orgFiles;
        */

    }

    private File getDir(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(stage);
    }

}
