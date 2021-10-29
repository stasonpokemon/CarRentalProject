package pojo.enums;

public enum OrderStatus {

    APPROVES("ОДОБРЕНО"), REJECT("ОТКЛОНЕНО"), UNDER_CONSIDERATION("НА РАССМОТРЕНИИ"), REFUND("ВОЗВРАТ");
    String abbreviation;
    OrderStatus(String abbreviation){
        this.abbreviation = abbreviation;
    }
}
