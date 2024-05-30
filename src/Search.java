import java.util.*;

public class Search<V> {
    protected Map<Vertex<V>, Vertex<V>> edgeTo;
    protected Map<Vertex<V>, Boolean> visited;

    public Search() {
        this.edgeTo = new HashMap<>();
        this.visited = new HashMap<>();
    }

    public List<V> pathTo(V destination) {
        Vertex<V> destVertex = new Vertex<>(destination);
        if (!visited.containsKey(destVertex)) {
            return new ArrayList<>();
        }

        List<V> path = new ArrayList<>();
        Vertex<V> current = destVertex;

        while (current != null && edgeTo.containsKey(current)) {
            path.add(current.getData());
            current = edgeTo.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
