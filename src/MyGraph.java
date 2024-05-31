import java.util.*;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private List<Edge<Vertex<V>>> edges;
    private boolean directed;

    public MyGraph(boolean directed) {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
        this.directed = directed;

    }
    public double getWeight(V source, V destination) {
        return 1.0;
    }

        public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest ) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, k -> new Vertex<>(source));
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, k -> new Vertex<>(dest));
        sourceVertex.addAdjacentVertex(destVertex, 1.0);
        edges.add(new Edge<>(sourceVertex, destVertex, 1.0));
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, 1.0);
            edges.add(new Edge<>(destVertex, sourceVertex, 1.0));
        }
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
