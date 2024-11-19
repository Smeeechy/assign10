package assign10;

import java.util.*;

/**
 * Solves the shortest path problem for a generic, directed, weighted, sparse graphs
 * using two versions Dijkstra's algorithm.
 * 
 * @author CS 2420 course staff and ?
 * @version November 14, 2024
 */
public class GraphUtility {

	/**
	 * Gets the shortest weighted path from one vertex (starting vertex) to another 
	 * (ending vertex) in a directed graph defined by the given lists.
	 * 
	 * Uses the version of Dijkstra's algorithm implemented in Assignment 7.
	 * 
	 * @param sources - list of data for the source vertices of edges in the graph being defined
	 * @param destinations - list of data for the destination vertices of edges in the graph being defined
	 * @param weights - list of weights for the edges in the graph being defined
	 * @param srcData - data of the starting vertex of the path being sought
	 * @param dstData - data of the ending vertex of the path being sought
	 * @return ordered list of data for the vertices that make up the shortest weighted path
	 * @throws IllegalArgumentException if any of the given lists are ill-formatted or there is no such path
	 */
	public static <Type> List<Type> shortestWeightedPathWithSorting(List<Type> sources, List<Type> destinations, 
			List<Double> weights, Type srcData, Type dstData) throws IllegalArgumentException {
		return buildGraph(sources, destinations, weights).shortestWeightedPathWithSorting(srcData, dstData);
	}

	/**
	 * Gets the shortest weighted path from one vertex (starting vertex) to another
	 * (ending vertex) in a directed graph defined by the given lists.
	 *
	 * Uses the version of Dijkstra's algorithm implemented with a PriorityQueue.
	 *
	 * @param sources - list of data for the source vertices of edges in the graph being defined
	 * @param destinations - list of data for the destination vertices of edges in the graph being defined
	 * @param weights - list of weights for the edges in the graph being defined
	 * @param srcData - data of the starting vertex of the path being sought
	 * @param dstData - data of the ending vertex of the path being sought
	 * @return ordered list of data for the vertices that make up the shortest weighted path
	 * @throws IllegalArgumentException if any of the given lists are ill-formatted or there is no such path
	 */

	public static <Type> List<Type> shortestWeightedPathWithPriorityQueue(List<Type> sources, List<Type> destinations,
			List<Double> weights, Type srcData, Type dstData) throws IllegalArgumentException {
		return buildGraph(sources, destinations, weights).shortestWeightedPathWithPriorityQueue(srcData, dstData);
	}
	
	/**
	 * Constructs a directed graph, as defined by the given lists.
	 * 
	 * @param sources - list of data for the source vertices of edges in the graph being defined
	 * @param destinations - list of data for the destination vertices of edges in the graph being defined
	 * @param weights - list of weights for the edges in the graph being defined
	 * @return
	 */
	private static <Type> Graph<Type> buildGraph(List<Type> sources, List<Type> destinations, List<Double> weights) {
		if(sources.size() != destinations.size() || sources.size() != weights.size())
			throw new IllegalArgumentException();
		
		Graph<Type> g = new Graph<Type>();
		for(int i = 0; i < sources.size(); i++)
			g.addEdge(sources.get(i), destinations.get(i), weights.get(i));
		return g;
	}
	
	/**
	 * Represents a generic, directed, weighted, sparse graph.
	 */
	private static class Graph<Type> {

		/**
		 * Represents a single vertex in a generic, directed, weighted, sparse graph.
		 */
		private static class Vertex<T> {
			public T data;
			public LinkedList<Edge<T>> adjacencyList;
			public Vertex<T> previous;	
			public boolean visited;
			public double distanceFromStart;
			
			/**
			 * Creates a vertex that contains the given data.
			 * 
			 * @param data - data to be contained in this vertex
			 */
			public Vertex(T data) {
				this.data = data;
				this.adjacencyList = new LinkedList<Edge<T>>();
				this.previous = null;
				this.visited = false;
				this.distanceFromStart = Double.MAX_VALUE;
			}

			/**
			 * Adds a directed edge from this vertex to the given vertex.
			 * 
			 * @param destination - vertex to which the edge points
			 * @param weight - cost of traversing the edge
			 */
			public void addEdge(Vertex<T> destination, double weight) {
				this.adjacencyList.add(new Edge<T>(destination, weight));
			}
		}

		/**
		 * Represents a single edge in a generic, directed, weighted, sparse graph.
		 */
		private static class Edge<T> {
			public Vertex<T> destination;
			public double weight;

			/**
			 * Creates an edge to the given vertex with the given weight.
			 * 
			 * @param destination - vertex to which this edge points
			 * @param weight - cost of traversing this edge
			 */
			public Edge(Vertex<T> destination, double weight) {
				this.destination = destination;
				this.weight = weight;
			}
		}

		private HashMap<Type, Vertex<Type>> vertices;

		/**
		 * Creates an empty graph.
		 */
		public Graph() {
			vertices = new HashMap<Type, Vertex<Type>>();
		}

		/**
		 * Adds a directed edge to this graph.
		 * 
		 * @param srcData - data of the source vertex for the edge
		 * @param dstData - data of the destination vertex for the edge
		 * @param weight - weight for the edge
		 */
		public void addEdge(Type srcData, Type dstData, double weight) {
			Vertex<Type> srcVertex;
			if(vertices.containsKey(srcData))
				srcVertex = vertices.get(srcData);
			else {
				srcVertex = new Vertex<Type>(srcData);
				vertices.put(srcData, srcVertex);
			}

			Vertex<Type> dstVertex;
			if(vertices.containsKey(dstData))
				dstVertex = vertices.get(dstData);
			else {
				dstVertex = new Vertex<Type>(dstData);
				vertices.put(dstData, dstVertex);
			}

			srcVertex.addEdge(dstVertex, weight);
		}
		
		/**
		 * Determines the shortest weighted path from one vertex (starting vertex) to another 
		 * (ending vertex) in this graph, using the version of Dijkstra's algorithm implemented 
		 * in Assignment 7.
		 * 
		 * While the list of unvisited vertices is not empty, sort the list by distance from 
		 * start (descending order) and use the last vertex as the one with the smallest distance 
		 * from start, removing the vertex from the list as it is "visited".  
		 * Leverages that using Java's sort method ensures efficiency for nearly and partially sorted lists. 
		 * 
		 * @param startData - data of the starting vertex 
		 * @param endData - data of the ending vertex
		 * @return ordered list of data for the vertices that make up the shortest weighted path
		 */
		private List<Type> shortestWeightedPathWithSorting(Type startData, Type endData) {	
			Vertex<Type> startingVertex = vertices.get(startData);
			if(startingVertex == null)
				throw new IllegalArgumentException("Vertex " + startData + " does not exist.");
			Vertex<Type> endingVertex = vertices.get(endData);
			if(endingVertex == null)
				throw new IllegalArgumentException("Vertex " + endData + " does not exist.");

			List<Vertex<Type>> unvisitedVertices = new ArrayList<Vertex<Type>>();
			for(Vertex<Type> v : vertices.values())
				unvisitedVertices.add(v);
			startingVertex.distanceFromStart = 0;		
			
			while(!unvisitedVertices.isEmpty()) {
				unvisitedVertices.sort((v1, v2) -> Double.compare(v2.distanceFromStart, v1.distanceFromStart));
				Vertex<Type> currentVertex = unvisitedVertices.get(unvisitedVertices.size() - 1);
				if(currentVertex.data.equals(endData))
					return generatePath(endingVertex, startData);
				for(Edge<Type> e : currentVertex.adjacencyList) 
					if(currentVertex.distanceFromStart + e.weight < e.destination.distanceFromStart) {
						e.destination.distanceFromStart = currentVertex.distanceFromStart + e.weight;
						e.destination.previous = currentVertex;
					}
				unvisitedVertices.remove(unvisitedVertices.size() - 1);			
			}
			
			throw new IllegalArgumentException("There is no path between vertex " + startData + " and vertex " + startData + ".");
		}

		// TODO: add Javadoc comment.
		public List<Type> shortestWeightedPathWithPriorityQueue(Type startData, Type endData) {
			Vertex<Type> startingVertex = vertices.get(startData);
			if(startingVertex == null)
				throw new IllegalArgumentException("Vertex " + startData + " does not exist.");
			Vertex<Type> endingVertex = vertices.get(endData);
			if(endingVertex == null)
				throw new IllegalArgumentException("Vertex " + endData + " does not exist.");

			PriorityQueue<Vertex<Type>> unvisitedVertices = new BinaryMinHeap<>(Comparator.comparingDouble(vertex -> vertex.distanceFromStart));
			for(Vertex<Type> v : vertices.values())
				unvisitedVertices.add(v);
			startingVertex.distanceFromStart = 0;

			while(!unvisitedVertices.isEmpty()) {
				Vertex<Type> currentVertex = unvisitedVertices.peek();
				if(currentVertex.data.equals(endData))
					return generatePath(endingVertex, startData);
				for(Edge<Type> e : currentVertex.adjacencyList)
					if(currentVertex.distanceFromStart + e.weight < e.destination.distanceFromStart) {
						e.destination.distanceFromStart = currentVertex.distanceFromStart + e.weight;
						e.destination.previous = currentVertex;
					}
				unvisitedVertices.extract();
			}

			throw new IllegalArgumentException("There is no path between vertex " + startData + " and vertex " + startData + ".");
		}

		public static void main(String[] args) {

		}
		/**
		 * Finds the shortest path from the startValue to the endValue, if it exists.
		 *
		 * @param startValue the value of the vertex from which to start the search
		 * @param targetValue the value of the target vertex
		 * @return the shortest path between startValue and endValue
		 * @throws IllegalArgumentException if the Graph does not contain the start or target values
		 */

		/**
		 * Generates an ordered list of data for the vertices that make up the path defined by 
		 * each vertex's "previous" attribute.
		 * 
		 * @param endVertex - vertex at the end of this path
		 * @param startData - data of the starting vertex in the path
		 * @return ordered list of data for the vertices that make up the path
		 */
		private List<Type> generatePath(Vertex<Type> endVertex, Type startData) {
			LinkedList<Type> path = new LinkedList<Type>();
			Vertex<Type> currentVertex = endVertex;
			while(!currentVertex.data.equals(startData)) {
				if(currentVertex.previous == null)
					throw new IllegalArgumentException("There is no path between vertex " + startData + " and vertex " + endVertex.data + ".");
				path.addFirst(currentVertex.data);
				currentVertex = currentVertex.previous;
			}
			path.addFirst(startData);	
			return path;
		}
	}
}