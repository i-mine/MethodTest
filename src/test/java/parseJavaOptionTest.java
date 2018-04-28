import java.util.HashMap;
import java.util.Map;
public class parseJavaOptionTest {
    public static void main(String[] args) {
        try {

        String str = "-XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:MaxHeapFreeRatio=70 -XX:+CMSClassUnloadingEnabled -XX:OnOutOfMemoryError='kill -9 %p'";
        parseJavaOptions(str);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public static Map<String, String> parseJavaOptions(String str) {
        Map<String, String> options = new HashMap<String, String>();
        String[] tokens = str.trim().split("\\s");
        for (String token : tokens) {
            if (token.isEmpty()|| token.startsWith("-X")||!token.startsWith("-D")) {
                System.out.println(token);
                continue;
            }
//            if (!token.startsWith("-D")) {
//                throw new IllegalArgumentException(
//                        "Cannot parse java option string [" + str + "]. Some options does not begin with -D prefix.");
//            }
//            String[] parts = token.substring(2).split("=", 2);
//            if (parts.length != 2) {
//                throw new IllegalArgumentException(
//                        "Cannot parse java option string [" + str + "]. The part [" + token + "] does not contain a =.");
//            }
//
//            options.put(parts[0], parts[1]);
        }
        return options;
    }
}

