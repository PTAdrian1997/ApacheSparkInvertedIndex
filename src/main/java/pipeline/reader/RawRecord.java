package pipeline.reader;

import java.io.Serializable;

public class RawRecord implements Serializable {

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getActualContent() {
        return actualContent;
    }

    public void setActualContent(String actualContent) {
        this.actualContent = actualContent;
    }

    private String filename;
    private String actualContent;

    public RawRecord(){
        this.filename = null;
        this.actualContent = null;
    }

    public RawRecord(String filename, String actualContent){
        this.filename = filename;
        this.actualContent = actualContent;
    }

    @Override
    public String toString() {
        return "RawRecord{" +
                "filename='" + filename +
                "', actualContent='" + actualContent +
                "'}";
    }
}
