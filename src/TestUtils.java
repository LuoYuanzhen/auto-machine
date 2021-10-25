public class TestUtils {
    public static String generateReplicateChars(char c, int times){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++){
            sb.append(c);
        }

        return sb.toString();
    }

    public static String generateTMLanguage(int m, int n, int k, char dot){
        return "#" +
                generateReplicateChars(dot, m) + "+" + generateReplicateChars(dot, n) + "=" +
                generateReplicateChars(dot, k) +
                " ";
    }

}
