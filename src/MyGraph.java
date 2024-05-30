import java.util.*;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private List<Edge<Vertex<V>>> edges;

    public MyGraph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, k -> new Vertex<>(source));
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, k -> new Vertex<>(dest));
        sourceVertex.addAdjacentVertex(destVertex, weight);
        edges.add(new Edge<>(sourceVertex, destVertex, weight));
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Set<V> getVertices() {
        return vertices.keySet();
    }

    public List<Edge<Vertex<V>>> getEdges() {
        return edges;
    }
}
