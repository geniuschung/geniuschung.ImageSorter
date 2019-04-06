
public class ImageSorterValidator {

    public void checkImagArrange(ImageSorterData imageSorterData) throws Exception {
        if(imageSorterData.getOrgDir() == null){
            throw new Exception("원본 디렉토리 선택");
        }

        if(imageSorterData.getOrgDir() == null){
            throw new Exception("대상 디렉토리 선택");
        }
    }
}
