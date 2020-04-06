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
#### Dummy
For debugging and comparison, the dummy heuristic assigns value `1` to all non final successful states, while for 
final assigns `1`. This heuristic is quite trivial and dosnt generate any 
considerable search optimization but is quite helpfull for debugging as each node in a path will sum 1 on costs and 
therefore the programmer can check a counter that should increment by one always.
This heuristic is admissible as it never overestimates and will always finish on `0` when on final states.
#### Manhattan
#### dummyManhattan  
#### dummyManhattan  
### Deadlock

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
`java ar.edu.itba.sia.group3.Sokoban ../boards/1.txt BFS`  

`java ar.edu.itba.sia.group3.Sokoban ../boards/1.txt ASTAR manhattan`
### Output
Nothing is output during execution.  
Once finalized, output is divided in two sections. This sections are separated by two lines of `#` symbols.
Below such line, data about the run will be displayed.  
Above such line the solution will be shown state by state. Immediately above the `#` lines will be the initial board,
scrolling upwards on the command line will show one by one the boards after every movement towards the final board 
that will appear imediatly after the line that executed the program. 
## Conclusions
  
  
  
