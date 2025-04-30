package com.tc.algorithm.nfa;

import java.util.Set;

public class Edge {

    private Vertex from;

    private Vertex to;

    private Set<Character> symbols;

    public Edge(Vertex from, Vertex to, Set<Character> symbols) {
        this.from = from;
        this.to = to;
        this.symbols = symbols;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public Set<Character> getSymbols() {
        return symbols;
    }

    public void setSymbols(Set<Character> symbols) {
        this.symbols = symbols;
    }
}
