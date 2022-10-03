public class Demo {
    public static void main(String[] args) {
        String regexNumber = "^[\\d]+\\.{0,1}[\\d]*$";
        System.out.println("3.123".matches(regexNumber));
    }
}
