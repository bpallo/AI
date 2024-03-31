package Work; 
import java.util.*;

public class EightPuzzleSolver {

    // Class representing a state of the puzzle
    static class State {
        int[] board; // 1D array representing the puzzle configuration
        int cost; // Cost associated with reaching this state

        State(int[] board, int cost) {
            this.board = board.clone();
            this.cost = cost;
        }

        // Method to check if the current state is the goal state
        boolean isGoal() {
            int[] goal = {1, 2, 3, 8, 0, 4, 7, 6, 5}; // Goal configuration
            return Arrays.equals(board, goal);
        }

        // Method to get possible successor states by moving the blank tile
        List<State> successors() {
            List<State> successors = new ArrayList<>();
            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // Possible directions: down, right, up, left

            int blankIndex = -1;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    blankIndex = i;
                    break;
                }
            }

            int x = blankIndex % 3;
            int y = blankIndex / 3;

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    int[] newBoard = board.clone();
                    int newIndex = newX + newY * 3;
                    newBoard[blankIndex] = newBoard[newIndex];
                    newBoard[newIndex] = 0;
                    successors.add(new State(newBoard, this.cost + 1));
                }
            }

            return successors;
        }
    }

    // Heuristic function for A* Algorithm
    static int heuristic(State state) {
        int[] goal = {1, 2, 3, 8, 0, 4, 7, 6, 5}; // Goal configuration
        int misplacedTiles = 0;
        for (int i = 0; i < 9; i++) {
            if (state.board[i] != goal[i]) {
                misplacedTiles++;
            }
        }
        return misplacedTiles;
    }
    // Depth-First Search (DFS)
    static boolean dfs(State startState, int[] results) {
        Stack<State> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(startState);

        int nodesVisited = 0;
        long totalTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            nodesVisited++;
            if (currentState.isGoal()) {
                long elapsedTime = System.nanoTime() - results[0];
                totalTime += elapsedTime;
                bestTime = Math.min(bestTime, elapsedTime);
                worstTime = Math.max(worstTime, elapsedTime);
                results[1] = nodesVisited;
                results[2] = (int) bestTime;
                results[3] = (int) worstTime;
                results[4] = (int) (totalTime / nodesVisited);
                return true;
            }
            for (State successor : currentState.successors()) {
                String hash = Arrays.toString(successor.board);
                if (!visited.contains(hash)) {
                    visited.add(hash);
                    stack.push(successor);
                }
            }
        }
        return false;
    }
    
    // Uniform-Cost Search (UCS)
    static boolean ucs(State startState, int[] results) {
        Queue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        Set<String> visited = new HashSet<>();
        queue.add(startState);

        int nodesVisited = 0;
        long totalTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            nodesVisited++;
            if (currentState.isGoal()) {
                long elapsedTime = System.nanoTime() - results[0];
                totalTime += elapsedTime;
                bestTime = Math.min(bestTime, elapsedTime);
                worstTime = Math.max(worstTime, elapsedTime);
                results[1] = nodesVisited;
                results[2] = (int) bestTime;
                results[3] = (int) worstTime;
                results[4] = (int) (totalTime / nodesVisited);
                return true;
            }
            for (State successor : currentState.successors()) {
                String hash = Arrays.toString(successor.board);
                if (!visited.contains(hash)) {
                    visited.add(hash);
                    queue.add(successor);
                }
            }
        }
        return false;
    }

    // Best-First Search (BFS)
    static boolean bfs(State startState, int[] results) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(startState);

        int nodesVisited = 0;
        long totalTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            nodesVisited++;
            if (currentState.isGoal()) {
                long elapsedTime = System.nanoTime() - results[0];
                totalTime += elapsedTime;
                bestTime = Math.min(bestTime, elapsedTime);
                worstTime = Math.max(worstTime, elapsedTime);
                results[1] = nodesVisited;
                results[2] = (int) bestTime;
                results[3] = (int) worstTime;
                results[4] = (int) (totalTime / nodesVisited);
                return true;
            }
            for (State successor : currentState.successors()) {
                String hash = Arrays.toString(successor.board);
                if (!visited.contains(hash)) {
                    visited.add(hash);
                    queue.add(successor);
                }
            }
        }
        return false;
    }

    // A* Algorithm
    static boolean aStar(State startState, int[] results) {
        Queue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> (s.cost + heuristic(s))));
        Set<String> visited = new HashSet<>();
        queue.add(startState);

        int nodesVisited = 0;
        long totalTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            nodesVisited++;
            if (currentState.isGoal()) {
                long elapsedTime = System.nanoTime() - results[0];
                totalTime += elapsedTime;
                bestTime = Math.min(bestTime, elapsedTime);
                worstTime = Math.max(worstTime, elapsedTime);
                results[1] = nodesVisited;
                results[2] = (int) bestTime;
                results[3] = (int) worstTime;
                results[4] = (int) (totalTime / nodesVisited);
                return true;
            }
            for (State successor : currentState.successors()) {
                String hash = Arrays.toString(successor.board);
                if (!visited.contains(hash)) {
                    visited.add(hash);
                    queue.add(successor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] initialState = {2, 8, 3, 1, 6, 4, 7, 0, 5}; // Initial configuration (random)
        State startState = new State(initialState, 0);

        for (int i = 0; i < 5; i++) {
            int[] results = new int[5]; // Stores [startTime, nodesVisited, bestTime, worstTime, averageTime]
            results[0] = (int) System.nanoTime();

            // Perform DFS
            dfs(startState, results);
            System.out.println("DFS - Iteration " + (i + 1) + ": Nodes visited=" + results[1] +
                    ", Best Time=" + results[2] + " nanoseconds, Worst Time=" + results[3] +
                    " nanoseconds, Average Time=" + results[4] + " nanoseconds");

            // Perform UCS
            ucs(startState, results);
            System.out.println("UCS - Iteration " + (i + 1) + ": Nodes visited=" + results[1] +
                    ", Best Time=" + results[2] + " nanoseconds, Worst Time=" + results[3] +
                    " nanoseconds, Average Time=" + results[4] + " nanoseconds");

            // Perform BFS
            bfs(startState, results);
            System.out.println("BFS - Iteration " + (i + 1) + ": Nodes visited=" + results[1] +
                    ", Best Time=" + results[2] + " nanoseconds, Worst Time=" + results[3] +
                    " nanoseconds, Average Time=" + results[4] + " nanoseconds");

            // Perform A*
            aStar(startState, results);
            System.out.println("A* - Iteration " + (i + 1) + ": Nodes visited=" + results[1] +
                    ", Best Time=" + results[2] + " nanoseconds, Worst Time=" + results[3] +
                    " nanoseconds, Average Time=" + results[4] + " nanoseconds");

            System.out.println();
        }
    }
}

