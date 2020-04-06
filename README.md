# SearchEngine

Java university project for searching solutions for Sokoban board game boards.  Implements several search algorithms 
with several heuristics and handles common problems.

## Implementation remarks
### Assumptions
This project assumes boards always have same number of boxes and targets locations.
If it dosnt, algorithm will run indefinitely.
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

## How to use/run
On a terminal, position your current directory on the root folder of this project (same level as this README).
Follow the instructions on the following subsections.
### Compiling
To comile the project run: `javac src/ar/edu/itba/sia/group3/*.java -d classes`
### Input

### Run
### Output
## Conclusions
  
  
  
