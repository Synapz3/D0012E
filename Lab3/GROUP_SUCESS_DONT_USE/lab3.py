# -*- coding: utf-8 -*-
import math
import time
import random 
from heap import *
h = 0 # Global räknare för Vertex-ID:n
d = 2
# Djikstras algoritm
# G is the graph
# s is the starting node
# e is the ending node
def djikstra(G, s, e):
	unvisited = [] # Here we can do variations of d
	visited = []
	for i in G: # Runs in |V| time as we need to initialise the algoritm
		i.dist = float("inf")
		i.prev = None
		unvisited.append(i)
		visited.append(False)
	s.dist = 0
	visited[s.n] = True # We assume constant time for python lists
	unvisited.remove(s)
	curr = s
	global d
	adjacent = Heap(d) # We change this if we want another kind of heap than a binary one. The d in this d-ary heap is the d in log_d

	while len(unvisited) > 0: # Runs a (worst case) total of |V| times, as we may need to visit every vertex
		
		# Debug stuff. Breakpoints are for brainiacs 
		#if curr == e:
		#	print "Found ending!", curr.dist

		#print "Iteratirng on", curr.n
		#print "Unvisited:", unvisited
		#print
		#print "Visited:", visited
		#print
		#print

		# This will run |E| times
		for ed in curr.edges: # Runs at most |V| - 1 times, if the graph is well connected (worst-case scenarion if EVERY vertex is well connected)
			if not visited[ed.dest]: # We only consider the unvisited vertices.
				if (curr.dist + ed.weight < G[ed.dest].dist): # We compare the tentative distance
		#			print "Replacing tentative distances...", G[ed.dest].dist, "->", curr.dist + ed.weight, "for", G[ed.dest].n
					G[ed.dest].dist = curr.dist + ed.weight # Update it
					G[ed.dest].prev = ed.src
				adjacent.insert(G[ed.dest].dist, G[ed.dest]) # Insert it into the heap for future use. Takes log |V| time.
		# Greatest cost of entire for-loop: |E| log |V|, regardless of the amount of times the overlying while-loop is executed

		if not visited[curr.n]:
			visited[curr.n] = True
		#else:
		#	print "Found", curr.n, "in visited set."
		try:
			unvisited.remove(curr)
		except:
			pass
		if adjacent.isEmpty():
			return e.dist
		curr = get_min_unvisited(G, adjacent, visited)
		if (curr == None): # Now we have vertices that cannot be reached from our starting position..
			break
		

	# Greatest cost of entire while-loop: |V| log |V|		
		
		#print
	path = [] # Get the optimal path to the end point. This takes a magnitude of |V| time.
	if e.dist < float("inf"):
		path = get_prev(G, s.n, e.n)
	return (e.dist, path) # Every thing will take about O(|V| log_d |V| + |E| log_d |V| + 2|V|) in worst-case
def get_min_unvisited(G, heap, visited):
	try:
		(key, value) = heap.getMin()
	except:
		return None
	if not heap.isEmpty():
		heap.deleteMin()
	if visited[value.n]:
		return get_min_unvisited(G, heap, visited)
	return value
# G is the graph
# start_num is the _position_ of the starting node
# end_num is the _position_ of the goal node
def get_prev(G, start_num, end_num): # Recursively determine the path from the end to the start.
	if end_num == start_num:
		return [G[end_num].n]
	else:
		return get_prev(G, start_num, G[end_num].prev) + [G[end_num].n] # We do it in this order because python can't do tail recursion AFAWK 
		
# Links the Vertex v1 with v2. This will link them at both ends, since we want an undirected graph.
def link(v1, v2, w):
        v1.edges.append(Edge(v1.n, v2.n, w))
        v2.edges.append(Edge(v2.n, v1.n, w))

class Vertex: # Container for our vertex data
    def __init__(self, edges):
        global h # Global variable that keeps track of the IDs we should give the verticies
        self.n = h
        h += 1
        self.edges = edges
    def addEdge(self, edge):  # We keep a list of edges, an _adjacency list_ as our data structure. 
        self.edges.append(edge) 
    def __repr__(self):
        edge_desc = ""
        for e in self.edges:
            edge_desc += str(e) + "; "
        return str(self.n) + " has edges:: " + edge_desc + "$"
        
class Edge:
    def __init__(self, src, dest, weight):
        self.weight = weight
        self.src = src
        self.dest = dest
    def __repr__(self):
        return "from: " + str(self.src) + ", to: " + str(self.dest) + ", weight: " + str(self.weight)
    
# This generates a graph with n*n vertices and 2n(n-1) edges
# Runs in a magnitude of 2n^2 time. Should be used sparingly.
def generate_grid_graph(m, n):
	v = []
	global h # We want to start indexing at 0
	oh = h
	nsq = n * m
	rint_max = 20 # The maxmimum weight we want on our edges
	h = 0
	for j in range(0, m): # Sloppy implementation, we create everying before we create the connections.
		for i in range(0, n): # Generate the vertices
			v.append(Vertex([]))
	for j in range(0, n):
		for i in range(0, m):
			next_vertical = (j+1)*m+i
			next_horisontal = j*m+1+i
			current = j*m+i
			if (next_vertical < nsq):
				link(v[current], v[next_vertical], random.randint(1,rint_max))
			if (next_horisontal < nsq and next_horisontal < m*(j+1)): # We don't want the edges to wrap
				link(v[current], v[next_horisontal], random.randint(1,rint_max))
	h = oh # We restore the global variable.
	return v




# vertices = []
# for i in range(0,15):
#     vertices.append(Vertex([]))
# v = vertices
# link(v[0], v[1], 12)
# link(v[0], v[2], 60)
# link(v[0], v[3], 13)
# link(v[1], v[2], 20)
# link(v[1], v[7], 24)
# link(v[2], v[4], 13)
# link(v[3], v[8], 12)
# link(v[3], v[9], 13)
# link(v[4], v[5], 8)
# link(v[4], v[11], 24)
# link(v[5], v[6], 2)
# link(v[6], v[10], 3)
# link(v[8], v[9], 1)
# link(v[9], v[11], 32)
# link(v[10], v[11], 30)
# link(v[11], v[12], 10)
# link(v[11], v[13], 5)
# link(v[12], v[13], 4)
# link(v[12], v[14], 7)
# link(v[13], v[14], 80)

# print djikstra(v, v[0], v[14])

## Test bench for variations of n
sizes = range(2, 48)
f = open("lab3_n.csv", "w")
f.write("n;m;vertices;edges;time\n")
for i in sizes:
	avg = 0
	for j in range (0,3):
		v = generate_grid_graph(i, i)
		v.append(Vertex([]))
		startt = time.time()
		(result, path) = djikstra(v, v[0], v[i*i-1])
		endt = time.time()
		avg += (endt-startt)
	avg /= 3
	print "It takes", avg, "for n =", i
	print result
	print path
	f.write("%s;%s;%s;%s;%s\n" % (str(i), str(i), str(i*i), str(i*(i-1) + i*(i-1)), str(avg)))
f.close()
f = open("lab3_d.csv", "w")
f.write("d;time\n")
v = generate_grid_graph(8, 8)
for i in range(2, 100):
	d = i
	startt = time.time()
	(result, path) = djikstra(v, v[0], v[63])
	print result
	print path
	endt = time.time()
	print "It takes", endt-startt, "for d =", i
	f.write("%s;%s\n" % (str(d), str(endt-startt)))
f.close()
