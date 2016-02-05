
import math;
class Darray:
    def __init__(self,d):
        self.d = d;
        self.size = 0;
        self.heap = [];

    def insert(self,weight,content):
        self.heap = self.heap + [_Element(weight,content)];
        self.size += 1;
        self.ensureSmallest(self.size-1);
    def getParent(self,pos):
        return int(math.floor((pos-1)/self.d));
    def getChildren(self,pos):
        childernPos = [];
        i = 1;
        while i<=self.d:
            if self.d*pos+i >= self.size :
                return childrenPos;
            else:
                childrenPos = childrenPos + [self.d*pos+i];
            i += 1;
        return childrenPos;
    def ensureSmallest(self,pos):
        parent = 0;
        while pos>0:
            parent = self.getParent(pos);
            if self.heap[parent].weight > self.heap[pos].weight:
                tmp = self.heap[parent];
                self.heap[parent] = self.heap[pos];
                self.heap[pos] = tmp;
                pos = parent;
            else:
                return;

    #def relax(self):
     #   return;

    def pop(self):
        if self.size == 0:
            return _Element(-1,"Empty");

        root = self.heap[0];
        oldHeap = self.heap[:];
        self.clear();
        for element in oldHeap[1:]:
            self.insert(element.weight,element.content);
        return root;
    def clear(self):
        self.heap = [];
        self.size = 0;

class _Element:
    def __init__(self,weight,content):
        self.weight = weight;
        self.content = content;
