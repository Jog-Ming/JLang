public class Generic15 {
    public interface A<T> {}
    public static class B<T> {}
    private static <T> void f(B<A<? extends T>> ignored) {}
    public static <S> void main(String[] args) {
        f(new B<A<? extends S>>());
    }
}