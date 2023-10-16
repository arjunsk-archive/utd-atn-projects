## Heuristic Network Topology Optimizer

Uses a custom [heuristic algorithm](/docs/reports/p2/sol2.pdf) to generate a low-cost network topology based on the below requirements.
- All nodes are connected
- The degree of the vertex is >= 3
- The diameter of the graph is <= 4
- Total cost (geometrical total length) is as low as possible.

The code is [here](/atn-project2).

### Naive Approach
The simplest solution that satisfies the given network constraints would be to convert the
graph to a complete graph, ie interconnect all the vertices. This would result in higher
costs, as we would have unnecessary edges, which could be dropped and yet satisfy
the graph constraints. The algorithm that we develop now evolves from this idea.


### Heuristic 1

In computation geometry, I learned a technique called Incremental Construction for
solving Linear Programming problems. Simply said, we incrementally add LP constraints
one at a time, updating the current optimal, until all the constraints are added.
We will do something similar, but instead of incrementally adding, we will delete edges
one by one from the complete graph, and validate constraints in each iteration. This
would be the exact opposite of incremental construction, hence I named it “Incremental
reduction.”
As I mentioned, in every iteration, we delete the largest edge and check the graph
constraints.
- If satisfied, we proceed to the next iteration, with the new graph.
- If not, we stop and return the old graph. (ie undoing the last edge deletion)

### Heuristic 2

Details in the [document](/docs/reports/p2/sol2.pdf)

![image](https://github.com/arjunsk-archive/utd-atn-projects/assets/9638314/bd3f3970-1413-4513-b49f-4c41cb88baea)

### Visual Comparison of Algorithms

![image](https://github.com/arjunsk-archive/utd-atn-projects/assets/9638314/2a9f5aed-9250-42ad-a7db-bd10523c1865)

![image](https://github.com/arjunsk-archive/utd-atn-projects/assets/9638314/04309010-14dc-4eb4-841c-5d459b368af5)

