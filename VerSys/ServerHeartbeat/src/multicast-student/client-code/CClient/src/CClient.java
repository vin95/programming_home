

public class CClient {
    
    public static void main(String[] args) { 
        if(args.length == 1) {
            System.out.println(CClient.class.getName() + ": "+ args[0]);
        } else {
            System.out.println(CClient.class.getName());
        }
    }
}
