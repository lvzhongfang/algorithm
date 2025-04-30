package com.tc.algorithm.nfa;

import java.util.*;
import java.util.stream.Collectors;

public class NFA2DFA {

    public static Set<Vertex> epsilonClosure(Set<Vertex> vertices) {
        Set<Vertex> closure = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>(vertices);

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            closure.add(v);
            Set<Vertex> nextVertices = v.getNextVertices('ε');
            if (nextVertices != null && !nextVertices.isEmpty()) {
                queue.addAll(nextVertices);
            }
        }
        return closure;
    }

    public static Set<Vertex> move(Set<Vertex> vertices, char symbol) {
        Set<Vertex> nextVertices = new HashSet<>();
        for (Vertex v : vertices) {
            Set<Vertex> list = v.getNextVertices(symbol);
            if (list != null && !list.isEmpty()) {
                nextVertices.addAll(list);
            }
        }
        return nextVertices;
    }

    public static String encodeStateSet(Set<Vertex> stateSet) {
        List<Vertex> list = stateSet.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Vertex vertex : list) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(vertex.getName());
        }
        sb.append("]");
        return sb.toString();
    }

    public static Vertex createDFAState(Set<Vertex> stateSet, Integer id) {
        Vertex vertex = new Vertex(encodeStateSet(stateSet), id);
        for (Vertex v : stateSet) {
            if (v.getAcceptVertex()) {
                vertex.setAcceptVertex(true);
                break;
            }
        }
        return vertex;
    }

    public static DFA nfa2dfa(NFA nfa) {
        DFA dfa = new DFA();

        for (Character symbol : nfa.getAlphabets()) {
            if (!symbol.equals('ε')) {
                dfa.getAlphabets().add(symbol);
            }
        }

        Vertex startVertex = nfa.getStartVertex();
        Set<Vertex> epsilonClosureVertices = epsilonClosure(Collections.singleton(startVertex));
        Map<String, Set<Vertex>> dfaVertexAndNfaVertexMap = new HashMap<>();
        Map<String, Vertex> dfaVertexMap = new HashMap<>();

        Vertex dfaStartVertex = createDFAState(epsilonClosureVertices, 1);
        dfa.setStartVertex(dfaStartVertex);

        dfaVertexAndNfaVertexMap.put(dfaStartVertex.getName(), epsilonClosureVertices);
        dfaVertexMap.put(dfaStartVertex.getName(), dfaStartVertex);

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(dfaStartVertex);

        while (!queue.isEmpty()) {
            Vertex dfaVertex = queue.poll();
            Set<Vertex> nfaVertices = dfaVertexAndNfaVertexMap.get(dfaVertex.getName());
            for (Character symbol : dfa.getAlphabets()) {
                Set<Vertex> nextNFAVertices = move(nfaVertices, symbol);
                Set<Vertex> nextEpsilonClosureVertices = epsilonClosure(nextNFAVertices);

                if (!nextEpsilonClosureVertices.isEmpty()) {
                    Vertex nextDFAVertex = null;
                    String nextDFAStateName = encodeStateSet(nextEpsilonClosureVertices);
                    if (dfaVertexAndNfaVertexMap.containsKey(nextDFAStateName)) {
                        nextDFAVertex = dfaVertexMap.get(nextDFAStateName);
                    } else {
                        nextDFAVertex = createDFAState(nextEpsilonClosureVertices, nextEpsilonClosureVertices.size() + 1);
                        dfaVertexAndNfaVertexMap.put(nextDFAStateName, nextEpsilonClosureVertices);
                        dfaVertexMap.put(nextDFAStateName, nextDFAVertex);
                        if (nextDFAVertex.getAcceptVertex()) {
                            dfa.setEndVertex(nextDFAVertex);
                        }
                        queue.add(nextDFAVertex);
                    }
                    // 构建DFA的边
                    Edge edge = new Edge(dfaVertex, nextDFAVertex, Collections.singleton(symbol));
                    dfaVertex.getEdges().add(edge);
                }
            }
        }

        dfa.setNfaVertexMap(dfaVertexAndNfaVertexMap);
        return dfa;
    }

    public static Set<Vertex> getNextVerticesWithoutSelf(Vertex vertex, char symbol, Map<String, Integer> visited) {
        Set<Vertex> vertices = new HashSet<>();
        for (Edge edge : vertex.getEdges()) {
            Set<Character> symbols = edge.getSymbols();
            if (symbols.contains(symbol)) {
                if (visited.containsKey(edge.getTo().getName())) {
                    continue;
                }
                if (vertex.getName().equals(edge.getTo().getName())) {
                    continue;
                }
                vertices.add(edge.getTo());
            }
        }
        return vertices;
    }

    public static void printDFA(DFA dfa) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(dfa.getStartVertex());
        Map<String, Integer> visited = new HashMap<>();

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            System.out.println(vertex.getName() + " -> " + vertex.getEdges().stream().map(edge -> edge.getTo().getName() + "(" + edge.getSymbols() + ")").collect(Collectors.joining(", ")));
            visited.put(vertex.getName(), 1);
            for (Character symbol : dfa.getAlphabets()) {
                Set<Vertex> nextVertices = getNextVerticesWithoutSelf(vertex, symbol, visited);
                if (!nextVertices.isEmpty()) {
                    queue.addAll(nextVertices);
                }
            }
        }
    }

    public static void main(String[] args) {
        Vertex v0 = new Vertex("0", 1);
        Vertex v1 = new Vertex("1", 2);
        Vertex v2 = new Vertex("2", 3);
        Vertex v3 = new Vertex("3", 4);
        Vertex v4 = new Vertex("4", 5);
        Vertex v5 = new Vertex("5", 6);
        Vertex v6 = new Vertex("6", 7);
        Vertex v7 = new Vertex("7", 8);
        Vertex v8 = new Vertex("8", 9);
        Vertex v9 = new Vertex("9", 10);
        Vertex v10 = new Vertex("10", 11);
        v10.setAcceptVertex(true);

        Edge e0 = new Edge(v0, v1, Collections.singleton('ε'));
        Edge e1 = new Edge(v0, v7, Collections.singleton('ε'));
        Edge e2 = new Edge(v1, v2, Collections.singleton('ε'));
        Edge e3 = new Edge(v1, v4, Collections.singleton('ε'));
        Edge e4 = new Edge(v2, v3, Collections.singleton('a'));
        Edge e5 = new Edge(v3, v6, Collections.singleton('ε'));
        Edge e6 = new Edge(v4, v5, Collections.singleton('b'));
        Edge e7 = new Edge(v5, v6, Collections.singleton('ε'));
        Edge e8 = new Edge(v6, v1, Collections.singleton('ε'));
        Edge e9 = new Edge(v6, v7, Collections.singleton('ε'));
        Edge e10 = new Edge(v7, v8, Collections.singleton('a'));
        Edge e11 = new Edge(v8, v9, Collections.singleton('b'));
        Edge e12 = new Edge(v9, v10, Collections.singleton('b'));

        v0.getEdges().add(e0);
        v0.getEdges().add(e1);
        v1.getEdges().add(e2);
        v1.getEdges().add(e3);
        v2.getEdges().add(e4);
        v3.getEdges().add(e5);
        v4.getEdges().add(e6);
        v5.getEdges().add(e7);
        v6.getEdges().add(e8);
        v6.getEdges().add(e9);
        v7.getEdges().add(e10);
        v8.getEdges().add(e11);
        v9.getEdges().add(e12);

        NFA nfa = new NFA();
        nfa.setStartVertex(v0);
        nfa.setEndVertex(v10);
        Set<Character> alphabets = new HashSet<>();
        alphabets.add('a');
        alphabets.add('b');
        alphabets.add('ε');
        nfa.setAlphabets(alphabets);

        /*Set<Vertex> epsilonClosureVertices = epsilonClosure(Collections.singleton(v0));

        String epsilonClosureString = NFA2DFA.encodeStateSet(epsilonClosureVertices);

        System.out.println("epsilon closure for : " + v0.getName() + "is " + epsilonClosureString);

        Set<Vertex> nextVertices = move(epsilonClosureVertices, 'a');

        System.out.println("move for : " + epsilonClosureString + " is " + NFA2DFA.encodeStateSet(nextVertices));*/

        DFA dfa = NFA2DFA.nfa2dfa(nfa);
        NFA2DFA.printDFA(dfa);
    }
}
