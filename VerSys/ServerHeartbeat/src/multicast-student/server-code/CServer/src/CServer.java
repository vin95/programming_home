
public class CServer {
    public static void main(String[] args) { 
        if(args.length == 1) {
            System.out.println(CServer.class.getName() + ": "+ args[0]);
        } else {
            System.out.println(CServer.class.getName());
        }
    }
}
