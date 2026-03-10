import java.util.List;

record Post(String title, List<String> tags) {}

public class b6 {
    public static void run() {
        List<Post> posts = List.of(
                new Post("Java Blog", List.of("java", "backend")),
                new Post("Python Blog", List.of("python", "data"))
        );

        List<String> allTags = posts.stream()
                .flatMap(p -> p.tags().stream())
                .toList();

        System.out.println(allTags);
    }

    public static void main(String[] args) {
        run();
    }
}