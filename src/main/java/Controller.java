import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    Stage stage;

    @FXML
    protected TextField orgDirTxt;

    @FXML
    protected TextField tarDirTxt;

    @FXML
    protected TextArea logArea;

    private ImageSorterService imageSorterService = new ImageSorterService();

    private ImageSorterValidator imageSorterValidator = new ImageSorterValidator();


    public void setStage(Stage stage){
        this.stage = stage;
    }

    public Stage getStage(){
        return stage;
    }


    public void setLogArea(){
        imageSorterService.setLogArea(logArea);
    }

    public void openSelOrgDir(){
        imageSorterService.logAdd("openSelOrgDir");
        imageSorterService.chooseOrgDir(stage, orgDirTxt);

    }

    public void openSelTarDir(){
        imageSorterService.logAdd("openSelTarDir");
        imageSorterService.chooseTarDir(stage, tarDirTxt);
    }

    public void goImgArrange() {
        imageSorterService.logAdd("goImgArrange");
        imageSorterService.goImgArrange();
    }

    public void cancle(){
        imageSorterService.logAdd("cancle");
        System.exit(0);
    }
}
