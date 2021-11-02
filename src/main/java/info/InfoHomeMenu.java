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
        return "Rental car...\n" +
                "1. Login\n" +
                "2. Registration\n" +
                "3. Close the program...";
    }
}
