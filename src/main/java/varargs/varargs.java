package varargs;

public class varargs {
    public static void main(String[] args) {
        sample("test1", "test2", "test3");

    }
    private static void sample (String... a){  //varargs
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
    }

}
