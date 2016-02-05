from graph import Graph
from darray import *
import dijkstras
he = Darray(2);
he.insert(1,"hej");
print he.pop().content;

##Graph test
a = Graph(100,10);
dijkstras.run(10,a);
