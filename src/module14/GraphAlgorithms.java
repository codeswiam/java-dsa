package module14;

import module13.Vertex;
import module13.VertexDistance;

import java.util.*;

/**
 * Your implementation of Prim's algorithm.
 */
public class GraphAlgorithms {

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops or parallel edges in the MST.
     * <p>
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        Queue<Edge<T>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        getIncidentEdges(start, adjList, visitedSet, priorityQueue);

        visitedSet.add(start);

        int verticesNumber = graph.getVertices().size();

        while (!priorityQueue.isEmpty() && visitedSet.size() < verticesNumber) {
            Edge<T> edge = priorityQueue.remove();
            Vertex<T> destVertex = edge.getV();

            if (!visitedSet.contains(destVertex)) {
                visitedSet.add(destVertex);
                mst.add(edge); mst.add(getOppositeEdge(edge));

                getIncidentEdges(destVertex, adjList, visitedSet, priorityQueue);
            }
        }

        if (mst.size() != 2 * (verticesNumber - 1)) {
            return null;
        }

        return mst;
    }

    private static <T> Edge<T> getOppositeEdge(Edge<T> edge) {
        return new Edge<>(edge.getV(), edge.getU(), edge.getWeight());
    }

    private static <T> void getIncidentEdges(Vertex<T> srcVertex, Map<Vertex<T>, List<VertexDistance<T>>> adjList, Set<Vertex<T>> visitedSet, Queue<Edge<T>> priorityQueue ) {
        List<VertexDistance<T>> neighbors = adjList.get(srcVertex);

        for (VertexDistance<T> neighbor : neighbors) {
            Vertex<T> destVertex = neighbor.getVertex();
            if (!visitedSet.contains(destVertex)) {
                int weight = neighbor.getDistance();
                priorityQueue.add(new Edge<>(srcVertex, destVertex, weight));
            }

        }
    }
}

