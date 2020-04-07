# SearchEngine
Java university project for searching solutions for Sokoban board game boards.  Implements several search algorithms 
with several heuristics and handles common problems.
## Implementation remarks
Developed and tested with Java version 8 on Windows 10, Java version 13 on Mac OS Catalina and Java version 8 on Archlinux(4.19.101-1-lts).
### Assumptions
This project assumes boards always have same number of boxes and targets locations.
If it dosnt, algorithm will run indefinitely.
All boards are completely surrounded by walls.
### Algorithms
- Breath First Search
- Depth First Search
- Iterative Dipping Depth First
- Global Greedy Search
- A Star
- Iterative Dipping A Star
### Heuristics
Used by the algorithms that use heuristics or cost function.  
Each possible board is assigned a number that represents how close to the solution it is. Closer to zero
implies closer to finishing. Only finished boards should have 0.
#### Dummy
For debugging and comparison, the dummy heuristic assigns value `1` to all non final states, whereas for
final ones assigns `0`. This heuristic is quite trivial and dosnt generate any
considerable search optimization but is quite helpfull for debugging as each node in a path will sum 1 on costs and 
therefore the programmer can check a counter that should increment by one always.
This heuristic is admissible as it never overestimates the distance from the solution
 since it assigns the minimum possible value and will always finish on `0` when on final states.
#### Manhattan
The board has target cells and cells with boxes. For each box, the manhattan distance to the closest target is calculated and summed
 with the rest of the boxes distances. This sum is the resulting value of the given board. Notice multiple boxes
 could be choosing to same target cell.
 Distance is measured one step at a time horizontally and vertically.
#### dummyManhattan
For each box, the manhattan distance to the player is calculated and summed with the rest of the boxes distances
The state value will be the sum of the players distances to all boxes.
#### euclidean
Similar to Manhattan heuristic but distances are measured with Euclides formula.
### Deadlock
Understood as a possible state where no solutions will be reachable from that point on based on the distribution of 
objects on the board. Multiple types of deadlocks are possible, each having different possible approaches for handling 
with their pros and cons.
This implementation handles the case where a box is blocked and will never be able to move again. When presented with
this cases, the heuristic will give the maximum value possible to such states.
- If a box is besides two walls and is not a target cell, then that box wont be possible to be removed from that
corner ever. Will be considered as a blocked box.  
- If a box is blocked by a wall, and a box or two or more boxes, these boxes are evaluated to see if they can be eventually
moved or if are also blocked. If any of the boxes are blocked by one or more boxes, those are recursively checked.

## Running
On a terminal, position your current directory on the root folder of this project (same level as this README).
Follow the instructions on the following subsections.
### Compiling
The followint applies to unix based systems.
First create on the root of the project a classes directory `mkdir classes`.  
To comile the project run: `javac src/ar/edu/itba/sia/group3/*.java -d classes`
Then change directory to the classes directory generated. `cd classes`
Your ready to run the program with `java ar.edu.itba.sia.group3.Sokoban [board] [algorithm] [optionalHeuristic]`
### Parameters
#### Board
This mandatory argument is the path to a text file with the board.
Board symbols are as follow:
- \#: a cell with a wall
- $: a cell with a box and NOT over a target cell
- %: a cell with a box and over a target cell
- @: a cell with the player and NOT over a target cell
- +: a cell with the player and over a target cell
- .: an empty target cell    
#### Algorithms
The following is a key value list of the algorithms accepted. The key is the argument expected
 and the value y the name of the algorithm.
- BFS: breadth first search
- DFS: depth first search
- IDDFS: iterative deeping depth first search
- GGD: global greedy search
- ASTAR: A star seach
- IDASTAR: iterative deeping A star seach
#### Heuristics
Applies only for GGD, ASTAR and IDASTAR algorithms, else will be ignored.
The following is a key value list of the heuristics accepted. The key is the argument expected
 and the value y the name of the heuristic in regard to secction `Heuristics`
- manhattan: Manhattan
- dummyManhattan: Dummy Manhattan
- dummy: Dummy
- euclidean: Euclidean
### Example of running
`java ar.edu.itba.sia.group3.Sokoban ../boards/2.txt BFS`  

`java ar.edu.itba.sia.group3.Sokoban ../boards/2.txt ASTAR manhattan`
### Output
Nothing is output during execution.  
Once finalized, output is divided in two sections. This sections are separated by two lines of `#` symbols.
Below such line, data about the run will be displayed.  
Above such line the solution will be shown state by state. Immediately above the `#` lines will be the initial board,
scrolling upwards on the command line will show one by one the boards after every movement towards the final board 
that will appear imediatly after the line that executed the program. 
## Conclusions
  
  
  
