# Bus Management System
The bus management system is represented using a directed, edge-weighted Graph object. The Graph contains an Adjacency List of directed Edge objects.

An Adjacency List was chosen for this representation instead of an Adjacency Matrix due to the large size of the input files. The space complexity of an Adjacency List is Θ(V + E), while an Adjacency Matrix takes up Θ(|V|^2) space. Although in the worst case (dense), an Adjacency List may still require the same amount of space. However, an Adjacency List performs better when adding or deleting a node, as it has a complexity of O(n) compared to O(n^2) for an Adjacency Matrix. Moreover, Adjacency Lists are more efficient in finding adjacent vertices, which is crucial in this context.

Each Edge in the Graph contains information about its origin, destination, and distance (direction and weight), along with their respective getters.

To enable easy retrieval of stop information later, the Graph uses HashMaps. The use of HashMaps is generally preferred over HashTable when thread synchronization is not required, as HashMaps offer better performance due to non-blocking behavior.

This Graph receives the input file names as parameters. The files are read using BufferedReader instead of FileReader because wrapping FileReader in a BufferedReader improves performance. BufferedReader can read one line at a time using readLine(), making it more suitable for reading large input files with reduced memory overhead compared to Scanner.
