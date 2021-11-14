public class IDGenerator {
    public static int id = 0;

    public static int getId(){
        id++;
        return id;
    }

    public static void setId(int id){
        IDGenerator.id = id;
    }
}
