import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Map<Vertex<V>, Boolean> visited;

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        this.edgeTo = new HashMap<>();
        this.visited = new HashMap<>();

        bfs(graph, graph.getVertex(source));
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.offer(source);
        visited.put(source, true);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            Map<Vertex<V>, Double> neighbors =  current.getAdjacentVertices();
            if (neighbors != null) {
                for (Map.Entry<Vertex<V>, Double> neighbor : neighbors.entrySet()) {
                    if (!visited.containsKey(neighbor.getKey())) {
                        visited.put(neighbor.getKey(), true);
                        edgeTo.put(neighbor.getKey(), current);
                        queue.offer(neighbor.getKey());
                    }
                }
            }
        }
    }


    @Override
    public List<V> pathTo(V destination) {
        Vertex<V> destVertex = new Vertex<>(destination);
        if (!visited.containsKey(destVertex)) {
            return new ArrayList<>();
        }

        List<V> path = new ArrayList<>();
        Vertex<V> current = destVertex ;

        while (current != null && edgeTo.containsKey(current)) {
            path.add(current.getData());
            current = edgeTo.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
