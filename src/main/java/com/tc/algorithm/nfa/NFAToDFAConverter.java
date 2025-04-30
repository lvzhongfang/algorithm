package com.tc.algorithm.nfa;

import java.util.Arrays;
import java.util.*;

public class NFAToDFAConverter {

    // 定义NFA结构
    static class NFA {
        Set<Integer> states = new HashSet<>();
        Set<Character> alphabet = new HashSet<>();
        Map<Integer, Map<Character, Set<Integer>>> transitions = new HashMap<>();
        int startState;
        Set<Integer> acceptStates = new HashSet<>();

        void addTransition(int from, char symbol, int to) {
            transitions.putIfAbsent(from, new HashMap<>());
            transitions.get(from).putIfAbsent(symbol, new HashSet<>());
            transitions.get(from).get(symbol).add(to);
        }
    }

    // 定义DFA结构
    static class DFA {
        Map<String, Map<Character, String>> transitions = new HashMap<>();
        String startState;
        Set<String> acceptStates = new HashSet<>();
        Set<String> states = new HashSet<>();
    }

    // 主转换方法
    public static DFA convert(NFA nfa) {
        DFA dfa = new DFA();
        Map<Set<Integer>, String> stateMapping = new HashMap<>();  // NFA状态集合到DFA状态名的映射
        Queue<Set<Integer>> stateQueue = new LinkedList<>();       // 待处理的NFA状态集合队列

        // 计算初始状态的ε闭包
        Set<Integer> initialClosure = epsilonClosure(nfa,
                Collections.singleton(nfa.startState));

        String startStateName = "S" + encodeStateSet(initialClosure);
        stateMapping.put(initialClosure, startStateName);
        stateQueue.add(initialClosure);
        dfa.states.add(startStateName);

        // 处理所有状态
        while (!stateQueue.isEmpty()) {
            Set<Integer> currentNFAStates = stateQueue.poll();
            String currentDFAState = stateMapping.get(currentNFAStates);

            // 检查是否是接受状态
            for (int state : currentNFAStates) {
                if (nfa.acceptStates.contains(state)) {
                    dfa.acceptStates.add(currentDFAState);
                    break;
                }
            }

            // 处理每个输入符号
            for (char symbol : nfa.alphabet) {
                if (symbol == 'ε') continue;

                // 计算通过当前符号转移的NFA状态集合
                Set<Integer> nextNFAStates = new HashSet<>();
                for (int state : currentNFAStates) {
                    Map<Character, Set<Integer>> transitions = nfa.transitions.getOrDefault(state, new HashMap<>());
                    nextNFAStates.addAll(transitions.getOrDefault(symbol, new HashSet<>()));
                }

                // 计算ε闭包
                nextNFAStates = epsilonClosure(nfa, nextNFAStates);

                String nextDFAStateName = getOrCreateStateName(nextNFAStates, stateMapping, stateQueue, dfa);
                dfa.transitions.putIfAbsent(currentDFAState, new HashMap<>());
                dfa.transitions.get(currentDFAState).put(symbol, nextDFAStateName);
            }
        }

        return dfa;
    }

    // 计算ε闭包（使用BFS）
    private static Set<Integer> epsilonClosure(NFA nfa, Set<Integer> states) {
        Set<Integer> closure = new HashSet<>(states);
        Queue<Integer> queue = new LinkedList<>(states);

        while (!queue.isEmpty()) {
            int state = queue.poll();
            Map<Character, Set<Integer>> transitions = nfa.transitions.getOrDefault(state, new HashMap<>());

            for (int next : transitions.getOrDefault('ε', new HashSet<>())) {
                if (closure.add(next)) {
                    queue.add(next);
                }
            }
        }
        return closure;
    }

    // 生成唯一的状态名（按状态集合排序）
    private static String encodeStateSet(Set<Integer> states) {
        List<Integer> sorted = new ArrayList<>(states);
        Collections.sort(sorted);
        return sorted.toString().replaceAll("[\\$$\\$$,]", "");
    }

    // 获取或创建DFA状态名
    private static String getOrCreateStateName(Set<Integer> nfaStates,
                                               Map<Set<Integer>, String> stateMapping,
                                               Queue<Set<Integer>> stateQueue,
                                               DFA dfa) {
        String name = stateMapping.get(nfaStates);
        if (name != null) return name;

        name = "S" + encodeStateSet(nfaStates);
        stateMapping.put(nfaStates, name);
        stateQueue.add(nfaStates);
        dfa.states.add(name);
        return name;
    }

    // 测试用例
    public static void main(String[] args) {
        // 构建测试NFA
        NFA nfa = new NFA();
        nfa.states = new HashSet<>(Arrays.asList(0, 1, 2));
        nfa.alphabet = new HashSet<>(Arrays.asList('a', 'b'));
        nfa.startState = 0;
        nfa.acceptStates.add(2);

        // 添加转移
        nfa.addTransition(0, 'a', 0);
        nfa.addTransition(0, 'a', 1);
        nfa.addTransition(0, 'ε', 1);
        nfa.addTransition(1, 'b', 2);
        nfa.addTransition(2, 'a', 2);
        nfa.addTransition(2, 'b', 2);

        // 转换为DFA
        DFA dfa = convert(nfa);

        // 打印DFA
        System.out.println("DFA States:");
        for (String state : dfa.states) {
            System.out.println("State " + state + ":");
            if (dfa.acceptStates.contains(state)) {
                System.out.println("  ACCEPT");
            }
            Map<Character, String> transitions = dfa.transitions.getOrDefault(state, new HashMap<>());
            for (Map.Entry<Character, String> entry : transitions.entrySet()) {
                System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
