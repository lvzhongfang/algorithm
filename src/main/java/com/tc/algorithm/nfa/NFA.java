package com.tc.algorithm.nfa;

import java.util.Set;

public class NFA {

    private Vertex startVertex;

    private Vertex endVertex;

    private Set<Character> alphabets;

    public NFA() {

    }

    public NFA(Vertex startVertex, Vertex endVertex, Set<Character> alphabets) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.alphabets = alphabets;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public Set<Character> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(Set<Character> alphabets) {
        this.alphabets = alphabets;
    }
}
