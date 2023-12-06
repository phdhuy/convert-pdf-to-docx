package model.bean;

public class fileUpload {

    private int fileId;
    
    private int userId;
    
    private String fileName;
    
    private int fileStatus;
    
    public int getFid() {
        return this.fileId;
    }

    public void setFid(int fileId) {
        this.fileId = fileId;
    }

    public int getUid() {
        return this.userId;
    }

    public void setUid(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return this.fileName;
    }

    public void setFname(String fileName) {
        this.fileName = fileName;
    }

    public int getFstatus() {
        return this.fileStatus;
    }

    public void setFstatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }
    
}
