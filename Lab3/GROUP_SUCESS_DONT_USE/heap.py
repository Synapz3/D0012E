import math

class Heap: # Super mega awesome heap class.
  def __init__(self, d):
    self.d = d
    self.content = []
    
  def deleteMin(self): # Deletes the root of the heap
    self.content[0] = self.content[len(self.content)-1]
    del self.content[len(self.content)-1]
    self.__sift(0)
    

  def __sift(self, position): # Sift through the heap. Used during deletion
    minch = self.__minchild(position)
    if (minch == float("inf")):
        return # No children; we are done.
    c = self.content[position]
    mv = self.content[minch]
    if (mv[0] < c[0]):
        temp = c
        self.content[position] = mv
        self.content[minch] = c
        self.__sift(minch)
    return
    
  def __minchild(self, position): # Get the position of the minimum child of a node.
    mchi = float("inf")
    pos = position
    
    for i in range(1, self.d+1):
      chi = self.__nthchild(pos, i)
      if (chi >= len(self.content)): # We have reached the end.
          return mchi
      if (mchi == float("inf")):
          mchi = chi
      elif (self.content[chi][0] < self.content[mchi][0]):
          mchi = chi
    return mchi
          
      
  def mch(self, position): # Return the minimum child
    p = self.__minchild(position)
    if (p == float("inf")):
        return None
    else:
        return self.content[p]
    
  
  def __nthchild(self, i, n): # Return the nth child of the heap
    return i * self.d + n
  
  def insert(self, item, value): # insert the item into the heap
    c = self.content
    c.append((item, value))
    index = len(c) - 1
    #print "inserting item", item, "at", index, "as", c[index][0]
    parent_i = int(math.floor((index - 1) / self.d)) # Magic formulae for calculating the address of the parent.
    parent = c[parent_i]
    item = (item, value)
    while (item[0] < parent[0] and parent_i >= 0):
      self.content[parent_i] = item
      self.content[index] = parent
      index = parent_i
      parent_i = int(math.floor((index - 1) / self.d))
      parent = self.content[parent_i]

  def getMin(self):
    return self.content[0]

  def isEmpty(self):
    return (len(self.content) <= 0)

  def isNotEmpty(self):
    return not self.isEmpty()
  
  def __repr_helper(self, index, level=0): # Helper method for string representation of the heap.
    if index >= len(self.content):
      return ""
    s = "|" * level + "-(" + str(self.content[index][0]) +" ~ " + str(self.content[index][1]) + "):\n"
    for i in range(0, self.d):
      s += self.__repr_helper(self.__nthchild(index, i+1), level=level+1)
    return s
    
  def __repr__(self): # A more useful string representation for viewing the heap.
   return self.__repr_helper(0)





