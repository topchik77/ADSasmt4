import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        super();
        Vertex<V> sourceVertex = graph.getVertex(source) ;
        if (sourceVertex != null) {
            bfs(graph, sourceVertex);
        }
    }

    private void bfs(MyGraph<V> graph, Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.offer(source);
        visited.put(source, true);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            Map<Vertex<V>, Double> neighbors = current.getAdjacentVertices();
            if (neighbors != null) {
                for (Map.Entry<Vertex<V>, Double> neighbor : neighbors.entrySet()) {
                    double weight = graph.getWeight(current.getData(), neighbor.getKey().getData());
                    if (graph.getEdges().contains(new Edge<>(current, neighbor.getKey(), 1.0))) {
                        if (!visited.containsKey(neighbor.getKey())) {
                            visited.put(neighbor.getKey(), true);
                            edgeTo.put(neighbor.getKey(), current);
                            queue.offer(neighbor.getKey());
                        }
                    }
                }
            }
        }
    }
}