package com.company;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        //graph.addVertex('A'); //0
        //graph.addVertex('B'); //1
        //graph.addVertex('C'); //2
        //graph.addVertex('D'); //3
        //graph.addVertex('E'); //4
        //graph.addVertex('F'); //5


        // graph.addEdge(0, 1);
        // graph.addEdge(1, 2);
        // graph.addEdge(2, 3);
        // graph.addEdge(0, 4);
        // graph.addEdge(4, 5);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество вершин и сами вершины: ");
        int N = in.nextInt();
        char v;

        for (int i = 0; i < N; i++) {
            v = in.next().charAt(0);
            graph.addVertex(v);
        }

        System.out.println("Введите количество ребер и связь между вершинами по индексу: ");
        int M = in.nextInt();
        char x, y, t, h;
        int k = 0, l = 0;
        for (int i = 0; i < M; i++) {
            x = in.next().charAt(0);
            y = in.next().charAt(0);
            for (int j = 0; j < N; j++) {
                if (graph.vertexList[j].name == x)
                    k = j;
            }
            for (int m = 0; m < N; m++) {
                if (graph.vertexList[m].name == y)
                    l = m;
            }

            graph.addEdge(k, l);
        }

        if (M < (N - 1))
            System.out.println("Несвязный граф");
        else {
            System.out.println("Связный граф");
        }
       System.out.println("Введите вершины, между которыми надо найти кратчайший путь: ");
       t = in.next().charAt(0);
       h = in.next().charAt(0);
        graph.BFS(t, h);

        //graph.Plan(N, M);
        System.out.println("\nКакой тип обхода выберете: в ширину или глубину? (W or D): ");
        char choice = in.next().charAt(0);
        System.out.println("Индекс точки входа: ");
        int index = in.nextInt();

        if (choice == 'W')
            graph.passInWight(index);
        else
            graph.passInDeep(index);


        System.out.println(" ");
    }
}

