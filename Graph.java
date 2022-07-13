// Java program for Kruskal's algorithm to find Minimum 
// Spanning Tree of a given connected, undirected and 
// weighted graph 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.Arrays;

public class Graph 
{ 
	// A class to represent a graph edge 
	class Edge implements Comparable<Edge> 
	{ 
		int src, dest, weight; 

		// Comparator function used for sorting edges 
		// based on their weight 
		public int compareTo(Edge compareEdge) 
		{ 
			return this.weight-compareEdge.weight; 
		} 
	}; 

	// A class to represent a subset for union-find 
	class subset 
	{ 
		int parent, rank; 
	}; 

	int V, E; / 
	Edge edge[]; 

	// Creates a graph with V vertices and E edges 
	Graph(int v, int e) 
	{ 
		V = v; 
		E = e; 
		edge = new Edge[E]; 
		for (int i=0; i<e; ++i) 
			edge[i] = new Edge(); 
	} 


	int find(subset subsets[], int i) 
	{ 
		
		if (subsets[i].parent != i) 
			subsets[i].parent = find(subsets, subsets[i].parent); 

		return subsets[i].parent; 
	} 


	void Union(subset subsets[], int x, int y) 
	{ 
		int xroot = find(subsets, x); 
		int yroot = find(subsets, y); 

		
		if (subsets[xroot].rank < subsets[yroot].rank) 
			subsets[xroot].parent = yroot; 
		else if (subsets[xroot].rank > subsets[yroot].rank) 
			subsets[yroot].parent = xroot; 


		else
		{ 
			subsets[yroot].parent = xroot; 
			subsets[xroot].rank++; 
		} 
	} 

	void KruskalMST() 
	{ 
		Edge result[] = new Edge[V];  
		int e = 0;  
		int i = 0;  
		for (i=0; i<V; ++i) 
			result[i] = new Edge(); 

		Arrays.sort(edge); 

		// Allocate memory for creating V ssubsets 
		subset subsets[] = new subset[V]; 
		int c1=0;
		
		for(i=0; i<V; ++i) 
			{try{subsets[i]=new subset();c1+=1;}
			catch(Exception e1){System.out.println("Error at "+ c1);}
			} 
		
		// Create V subsets with single elements 
		for (int v = 0; v < V; ++v) 
		{ 
			subsets[v].parent = v; 
			subsets[v].rank = 0; 
		} 

		i = 0;
		while (e < V - 1) 
		{ 
			Edge next_edge = new Edge(); 
			next_edge = edge[i++]; 

			int x = find(subsets, next_edge.src); 
			int y = find(subsets, next_edge.dest); 

			if (x != y) 
			{ 
				result[e++] = next_edge; 
				Union(subsets, x, y); 
			} 
		
		} 

		System.out.println("Following are the edges in " + "the constructed MST"); 
		
		Arrays.sort(result);
		for (i = 0; i < 5; ++i) 
			System.out.println(result[i].src+" -- " + 
				result[i].dest+" == " + result[i].weight); 
					
	} 
			

	// Driver Program 
	public static void main (String[] args)throws IOException  
	{ 

		FileReader file=new  FileReader("coauth-DBLP-proj-graph.txt");
		File f=new File("coauth-DBLP-proj-graph.txt");
		
		 
		int filength=(int)f.length();
		//Edges
		 int V=(filength/8);
		//Vertices
		int E=filength/8;		
		
		Graph graph = new Graph(V, E); 


		int i,j,w;
		int count=0;
		while(((i=file.read())!=-1) && ((j=file.read())!=-1) && ((w=file.read())!=1) && count<5)
		{
			graph.edge[count].src=i;
			
			graph.edge[count].dest=j;
			
			graph.edge[count].weight=w;
			count+=1;		
		}

		graph.KruskalMST(); 
		
	} 
} 

