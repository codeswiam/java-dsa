package module13;

import module13.Graph;
import module13.Vertex;
import module13.VertexDistance;

import java.util.*;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        //WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<Vertex<T>> visitedSet = new ArrayList<>();
        Queue<Vertex<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        visitedSet.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.remove();
            for (VertexDistance<T> neighborDistance: adjList.get(vertex)) {
                Vertex<T> neighbor = neighborDistance.getVertex();
                if (!visitedSet.contains(neighbor)) {
                    visitedSet.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visitedSet;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     * <p>
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<Vertex<T>> visitedSet = new ArrayList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        dfs(start, graph, visitedSet, adjList);

        return visitedSet;
    }

    private static <T> void dfs(Vertex<T> vertex, Graph<T> graph, List<Vertex<T>> visitedSet, Map<Vertex<T>, List<VertexDistance<T>>> adjList) {
        visitedSet.add(vertex);
        for (VertexDistance<T> neighborDistance: adjList.get(vertex)) {
            Vertex<T> neighbor = neighborDistance.getVertex();
            if (!visitedSet.contains(neighbor)) {
                dfs(neighbor, graph, visitedSet, adjList);
            }
        }
    }
}