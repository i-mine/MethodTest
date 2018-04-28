public class FinalInMethodTest {
    public static void main(String[] args) {
        printHello("good");
        printHello("java");
        printHello("mike");
    }

    public static void printHello(String msg){
        final String helloMsg = "hello"+msg;
        System.out.println(helloMsg);
    }
}
