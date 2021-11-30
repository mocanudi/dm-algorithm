package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Graph {
    private int maxN = 10;
    private int[][] mas;
    private int[] pre;
    Vertex[] vertexList;
    private int curN;

    private MyStack stack = new MyStack();
    private MyQueue queue = new MyQueue();

    public Graph(){
        vertexList = new Vertex[maxN];
        mas = new int [maxN][maxN];
        curN = 0;
        pre = new int[10];
    }

    public void addVertex(char name){
        vertexList [curN++] = new Vertex(name);
    }

    public void addEdge(int start, int end){
        mas[start][end] = 1;
        mas[end][start] = 1;
    }

    public void Plan(int N, int M){
        int tmp = 0, status = 1, a = 0, min = 99;
        int status_P = 0;
        for (int i = 0; i < curN; i++) {
            for (int j = 0; j < curN; j++) {
//System.out.println(vertexList[i].name);
                if (mas[i][j] == 1 || mas[j][i] == 1)
                    tmp++;
            }
            if (tmp < 5)
                a++;
            tmp = 0;
        }
        if (a >= 1)
            status_P++;

        if(N > 3) {
            if (M <= 3 * N - 6) {
                status_P++;
            }
        }

        int r = 2 + M - N;
        if(status_P != 2 || r == 5)
            System.out.println("Граф не планарен");
        else
            System.out.println("Граф планарен");
    }

    public int check(int v) {
        for (int i = 0; i < curN; i++) {
            if (mas[v][i] != 0 && !vertexList[i].isVisited)
                return i;
        }
        return -1;
    }

    public void Euler(int N, int M) {
        int tmp = 0, status = 1, a;
        for (int i = 0; i < curN; i++) {
            for (int j = 0; j < curN; j++) {
//System.out.println(vertexList[i].name);
                if (mas[i][j] == 1 || mas[j][i] == 1)
                    tmp++;
            }
            System.out.println("Степень вершины "+ vertexList[i].name+": " + tmp);
            if (tmp % 2 == 1)
                status = 0;
            tmp = 0;
        }
        if(M < (N-1)) a = 0;
        else a = 1;

        if(a == 1 && status == 1)
            System.out.println("Эйлеров граф");
        else
            System.out.println("Не эйлеров граф");

    }

    public void Ham(int N){
        int tmp = 0, status = 0, a;
        int arr[] = new int[N];
        for (int i = 0; i < curN; i++) {
            for (int j = 0; j < curN; j++) {
//System.out.println(vertexList[i].name);
                if (mas[i][j] == 1 || mas[j][i] == 1)
                    tmp++;
            }
            arr[i] = tmp;
            tmp = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(mas[i][j]==0 && arr[i] + arr[j] >= N && i != j){
                    System.out.println(i + " " + j);
                    System.out.println("k: "+arr[i] + " + " + arr[j]);
                    status = 1;
                }
            }

        }
        if(status == 1)
            System.out.println("Граф гамильтонов");
        else
            System.out.println("Граф не гамильтонов");
    }

    public void Colour(int N) {
        int col[] = new int[N];


        for (int i = 0; i < N; i++) {
            col[i] = 0;
        }
        int t = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if(mas[i][j] == 1 && col[j] == t)
                    t++;
            }
            col[i] = t;
            t = 1;
        }

        int max = col[0];
        for (int i = 1; i < N; i++) {
            if(max < col[i])
                max = col[i];
        }

        System.out.println("Хроматическое число графа: " + max);
    }

    public void outputShortestPath(int des) {
        if (des == -1)
            return ;
        outputShortestPath(pre[des]);
        System.out.print(vertexList[des].name + " ");
    }
    public void BFS(char startI, char endI){
        int start = 0, end = 0;
        for(int j = 0; j < curN; j++) {
            if (vertexList[j].name == startI)
                start = j;
        }
        for(int m = 0; m < curN; m++) {
            if (vertexList[m].name == endI)
                end = m;
        }

        int queue[] = new int[curN];
            pre[start] = -1;
            int front = 0, rear = 0;
            vertexList[start].isVisited = true;
            queue[rear++] = start;
            while (front != rear) {
                int data = queue[front++];
                for (int i = 1; i <= curN; i++) {
                    if (mas[data][i] == 1 &&  vertexList[i].isVisited == false) {
                        pre[i] = data;
                        if (vertexList[i].name == endI) {
                            outputShortestPath (i); // вывод кратчайшего пути
                            return ;
                        }

                        queue[rear++] = i;
                        vertexList[i].isVisited = true;
                    }
                }
            }
        }

    public void passInDeep(int index){
        System.out.println(vertexList[index].name);
        vertexList[index].isVisited = true;
        stack.push(index);

        while(!stack.isEmpty()){
            int neigh = check(stack.peek());
            if(neigh == -1)
                neigh = stack.pop();
            else{
                System.out.println(vertexList[neigh].name);
                vertexList[neigh].isVisited = true;
                stack.push(neigh);
            }

        }

        for (int i = 0; i < curN; i++){
            vertexList[i].isVisited = false;

        }
    }

    public void passInWight(int index){
        System.out.println(vertexList[index].name);
        vertexList[index].isVisited = true;
        queue.insert(index);

        int vertex;
        while (!queue.isEmpty()){
            int temp = queue.remove();

            while ((vertex = check(temp)) != -1){
                System.out.println(vertexList[vertex].name);
                vertexList[vertex].isVisited = true;
                queue.insert(vertex);
            }

        }
        for (int i = 0; i < curN; i++){
            vertexList[i].isVisited = false;

        }
    }
}
