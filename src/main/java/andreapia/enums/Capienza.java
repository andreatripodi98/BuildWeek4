package andreapia.enums;

public enum Capienza {
    TRAM(30),
    AUTOBUS(10);

    private int capienza;

    Capienza(int capienza){
        this.capienza = capienza;
    }

    public int getCapienza(){
        return capienza;
    }


}
