import java.util.*;

public class DepthFirstSearch<V> extends Search<V> {

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        super();
        Vertex<V> sourceVertex = graph.getVertex(source);
        if (sourceVertex != null ) {
            dfs(graph, sourceVertex);
        }
    }

    private void dfs(MyGraph<V> graph, Vertex<V> current) {
        visited.put( current, true ) ;

        for (Map.Entry<Vertex<V>, Double> neighbor : current.getAdjacentVertices().entrySet()) {
            if (graph.getEdges().contains(new Edge<>(current, neighbor.getKey(), 1.0))) {
                if (!visited.containsKey(neighbor.getKey())) {
                    edgeTo.put(neighbor.getKey(), current);
                    dfs(graph, neighbor.getKey());
                }
            }
        }
    }
}