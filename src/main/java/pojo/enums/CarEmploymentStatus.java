package pojo.enums;

public enum CarEmploymentStatus {
    FREE("СВОБОДНО"), OCCUPIED("ЗАНЯТО");

    String annotation;
    CarEmploymentStatus(String annotation){
        this.annotation = annotation;
    }
}
