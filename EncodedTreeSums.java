package hackerRank;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;


class EncodedTreeSums {
        private static class Pair<T, U> {
            private final T first;
            private final U second;

            public Pair(T first, U second) {
                this.first = first;
                this.second = second;
            }

            public T getFirst() {
                return first;
            }

            public U getSecond() {
                return second;
            }
        }

        public static String encodedTreeSums(String input) {
            Pair<Integer, String> result = computeSubtreeSums(input);
            String res= result.getSecond();
            System.out.println("res --->"+res);
            return res;
        }

        private static Pair<Integer, String> computeSubtreeSums(String tree) {
            Pattern pattern = Pattern.compile("(\\d+)(\\{[^{}]*\\})?");
            Matcher matcher = pattern.matcher(tree);
            matcher.find();
            String rootVal = matcher.group(1);
            String children = matcher.group(2);
            System.out.println("rootVal --->"+rootVal);
            System.out.println("children --->"+children);

            if (children == null) {
                int sum = Integer.parseInt(rootVal);
                return new Pair<>(sum, tree);
            } else {
                String[] childTrees = children.substring(1, children.length() - 1).split(",");
                int sum = Integer.parseInt(rootVal);
                StringBuilder newTree = new StringBuilder();
                newTree.append(rootVal); // or sum
                newTree.append("{");
                for (String childTree : childTrees) {
                    Pair<Integer, String> childResult = computeSubtreeSums(childTree);
                    sum += childResult.getFirst();
                    newTree.append(childResult.getSecond());
                    newTree.append(",");
                }
                newTree.setCharAt(newTree.length() - 1, '}');
                System.out.println("newtree--->" +newTree.toString());

                return new Pair<>(sum, newTree.toString());
            }
        }
    public static void main(String[] args) throws Exception {
        encodedTreeSums("1{2{3,4},5{6}}");
        }
    }


