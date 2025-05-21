package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args){
        char[][] maze = {
                {'S', '.', '.', '#', '.', '.', '.'},
                {'#', '#', '.', '#', '.', '#', '.'},
                {'.', '.', '.', '.', '.', '#', '.'},
                {'.', '#', '#', '#', '.', '#', '.'},
                {'.', '.', '.', '#', '.', '.', 'E'}
        };

        Point pointS = new Point(0,0);
        Point pointE = new Point(4,6);

        bfs(maze, pointS, pointE);
    }

    public static void printMap(char[][] maze){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                System.out.print(maze[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void bfs(char[][] maze, Point start, Point end){
        MyQueue<Point> q = new MyQueue<>();
        HashSet<Point> visited = new HashSet<>();
        Map<Point, Point> mapMaze = new HashMap<>();
        q.add(start);
        visited.add(start);
        mapMaze.put(start, new Point(-1,-1));
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        while (!q.isEmpty()){
            Point currentPoint = q.remove();
            for (int[] direction: directions){
                if(currentPoint.row + direction[0] > -1 &&
                        currentPoint.row + direction[0] < maze.length &&
                        currentPoint.col + direction[1] > -1 &&
                        currentPoint.col + direction[1] < maze[0].length
                ) {
                    Point next = new Point(currentPoint.row + direction[0], currentPoint.col + direction[1]);
                    if (maze[next.row][next.col] != '#' && !visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                        mapMaze.put(next, currentPoint);
                    }

                    if (maze[next.row][next.col] == 'E') {
                        break;
                    }
                }
            }
        }

        if (!mapMaze.containsKey(end)) {
            System.out.println("Путь от S до E не найден.");
            return;
        }

        ArrayList<Point> path = new ArrayList<>();
        for(Point p = end; p != start; p = mapMaze.get(p)){
            path.add(new Point(p.row, p.col));
        }
        path.add(start);
        Collections.reverse(path);
        int counter = 0;
        Point last = null;

        for(Point p: path){
            if((p.row == start.row && p.col == start.col) ||
                (p.row == end.row && p.col == end.col)) {
                continue;
            } else {
                if(counter > 0) {
                    maze[last.row][last.col] = '.';
                }
                maze[p.row][p.col] = '♛';
                counter += 1;
            }
            printMap(maze);
            System.out.println();
            last = p;
        }
    }
}

class Point{
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "P{" +
                "r=" + row +
                ", c=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
