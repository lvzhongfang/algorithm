package com.tc.algorithm.nfa;

import java.util.HashSet;
import java.util.Set;

public class Vertex implements Comparable<Vertex> {

    private String name;

    // edges from this vertex to other vertices
    private Set<Edge> edges;
    // if this vertex is an accept vertex
    boolean acceptVertex;

    private Integer id;

    public Vertex() {
        edges = new HashSet<>();
        acceptVertex = false;
    }

    public Vertex(String name) {
        this.name = name;
        edges = new HashSet<>();
        acceptVertex = false;
    }

    public Vertex(String name, Integer id) {
        this.name = name;
        edges = new HashSet<>();
        acceptVertex = false;
        this.id = id;
    }

    /**
     * Get the next vertices of this vertex.
     * @return the next vertices of this vertex
     */
    public Set<Vertex> getNextVertices() {
        Set<Vertex> vertices = new HashSet<>();
        for (Edge edge : edges) {
            vertices.add(edge.getTo());
        }

        return vertices;
    }

    /**
     * Get the next vertices of this vertex with the given symbol.
     * @param symbol the symbol to match
     * @return the next vertices of this vertex with the given symbol
     */
    public Set<Vertex> getNextVertices(char symbol) {
        Set<Vertex> vertices = new HashSet<>();
        for (Edge edge : edges) {
            Set<Character> symbols = edge.getSymbols();
            if (symbols.contains(symbol)) {
                vertices.add(edge.getTo());
            }
        }
        return vertices;
    }

    public Set<Vertex> getNextVerticesWithoutSelf(char symbol) {
        Set<Vertex> vertices = new HashSet<>();
        for (Edge edge : edges) {
            Set<Character> symbols = edge.getSymbols();
            if (symbols.contains(symbol)) {
                if (name.equals(edge.getTo().getName())) {
                    continue;
                }
                vertices.add(edge.getTo());
            }
        }
        return vertices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public boolean getAcceptVertex() {
        return acceptVertex;
    }

    public void setAcceptVertex(boolean acceptVertex) {
        this.acceptVertex = acceptVertex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Vertex o) {
        if (o == null) {
            return 1;
        }

        if (this.id > o.getId()) {
            return 1;
        } else if (this.id < o.getId()) {
            return -1;
        }

        return 0;
    }
}

