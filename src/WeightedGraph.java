import java.util.*;
public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private Map<Vertex<V>, Map<Vertex<V>, Double>> adjacencyList;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.adjacencyList = new HashMap<>();
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V destination, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, k -> new Vertex<>(source)) ;
        Vertex<V> destVertex = vertices.computeIfAbsent(destination, k -> new Vertex<>(destination));

        // Adding the edge to the adjacency list
        adjacencyList.computeIfAbsent(sourceVertex, k -> new HashMap<>()).put(destVertex, weight);

        // Adding the edge if the graph undirected
        if (!directed) {
            adjacencyList.computeIfAbsent(destVertex, k -> new HashMap<>()).put(sourceVertex, weight);
        }
        adjacencyList.get(destVertex).put(sourceVertex, weight);
    }

    public void addVertex(V data) {
        vertices.computeIfAbsent(data, k -> new Vertex<>(data));
    }

    public Double getWeight(V source, V destination) {
        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(destination);
        if (sourceVertex == null || destVertex == null) {
            return null;
        }

        Map<Vertex<V>, Double> edges = sourceVertex.getAdjacentVertices();
        if (edges.containsKey(destVertex)) {
            return edges.get(destVertex);
        } else {
            return null ;
        }
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(V data) {
        Vertex<V> vertex = vertices.get(data);
        if (vertex != null) {
            return vertex.getAdjacentVertices();
        }
        return new HashMap<>();
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Set<V> getVertices() {
        return vertices.keySet();
    }

    public Map<V, Vertex<V>> getVerticesMap() {
        return vertices;
    }
}