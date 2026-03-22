package clase;
public class PreparatIndisponibilException extends Exception{
    public PreparatIndisponibilException(String nume)
    {
        super(nume +" nu este disponibil");
    }
}