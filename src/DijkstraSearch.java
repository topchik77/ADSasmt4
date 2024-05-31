import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo;
    private PriorityQueue<Vertex<V>> pq;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super();
        this.distTo = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));

        Vertex<V> sourceVertex = graph.getVertex(source);
        if (sourceVertex != null) {
            for (Vertex<V> vertex : graph.getVerticesMap().values()) {
                distTo.put(vertex, Double.POSITIVE_INFINITY);
            }
            distTo.put(sourceVertex, 0.0);

            pq.add(sourceVertex);
            while (!pq.isEmpty()) {
                Vertex<V> current = pq.poll();
                for (Map.Entry<Vertex<V>, Double> neighbor : current.getAdjacentVertices().entrySet()) {
                    relax(current, neighbor.getKey(), neighbor.getValue());
                }
            }
        }
    }

    private void relax(Vertex<V> u, Vertex<V> v, double weight) {
        if (distTo.get(v) > distTo.get(u) + weight) {
            distTo.put(v, distTo.get(u) + weight);
            edgeTo.put(v, u);
            pq.add(v);
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        Vertex<V> destVertex = new Vertex<>(destination);
        if (!distTo.containsKey(destVertex) || distTo.get(destVertex) == Double.POSITIVE_INFINITY) {
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