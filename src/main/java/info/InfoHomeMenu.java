package info;

public class InfoHomeMenu {

    private static InfoHomeMenu infoHomeMenu = null;

    public static InfoHomeMenu getInfoHomeMenu() {
        if (infoHomeMenu == null){
            infoHomeMenu = new InfoHomeMenu();
        }
        return infoHomeMenu;
    }


    public String input(){
        return "Who are you?\n" +
                "1. Client\n" +
                "2. Admin\n" +
                "3. Close the program...";
    }
}
