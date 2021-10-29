package pojo.enums;

public enum CarDamageStatus {
   WITHOUT_DAMAGE("БЕЗ ПОВРЕЖДЕНИЙ"), WITH_DAMAGE("С ПОВРЕЖДЕНИЯМИ");

   String annotation;

    CarDamageStatus(String annotation){
       this.annotation = annotation;
   }
}
