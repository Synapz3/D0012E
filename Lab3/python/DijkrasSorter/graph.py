from random import randint
class Graph:
    nodes = [];
    edges = [];
    nodeCount = 0;
    EdgeCount = 0;
    def __init__(self,numEdges,numNodes):
        for nodeId in range(0,numNodes):
            node = Node();
            nodes += node;
            if nodeId != 0:
                rnd = randint(0,100);
                node.connectTo(nodes[nodeId-1],rnd);
                nodes[nodeId-1].connectTo(node,rnd);
        
        print "Make the graph";
    def getNode(self,nodeId):
        if nodeId >= nodeCount:
            return null;
        else:
            return nodes[nodeId];
    def getEdge(self,edgeId):
        if edgeId >= edgeCount:
            return null;
        else:
            return edge[edgeId];
class Node:
    def __init__(self,content):
        connections = []
        return;
    def connectTo(self,node,weight):
        connections += [node,weight];
class Edge:
    connections = [];
    weight = 0;
    def __init__(self,weight,a,b):
        self.connections += [a,b];
        self.weight = weight;
        return;
   
