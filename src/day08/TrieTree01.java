package day08;

import java.util.HashMap;

/**
 * @Author liangbaigao
 * @Date 2023/4/2 8:13
 */
public class TrieTree01 {

    /**
     * 每个节点
     */
    public static class Node1 {

        private int pass;

        private int end;

        private Node1[] branch;

        public Node1(){
            pass = 0;
            end = 0;
            branch = new Node1[26];
        }

    }

    /**
     *
     */
    public static class Trie1 {

        private Node1 root;

        public Trie1(){
            root = new Node1();
        }


        public void insert(String word) {
            // 校验
            if(word == null){
                return;
            }
            // 迭代
            char[] alphabets = word.toCharArray();
            Node1 node = root;
            node.pass++;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                // 判断路路径是否存在
                if (node.branch[alphabet] == null) {
                    node.branch[alphabet] = new Node1();
                }
                node = node.branch[alphabet];
                node.pass++;
            }
            node.end++;
        }


        public void delete(String word) {
            // 校验 (查看这个字母存不存在)
            if(search(word) != 0) {
                // 迭代
                char[] alphabets = word.toCharArray();
                Node1 node = root;
                node.pass--;
                for (char c : alphabets) {
                    int alphabet = c - 'a';
                    // 判断路径是否存在
                    if (--node.branch[alphabet].pass == 0) {
                        node.branch[alphabet] = null;
                        return;
                    }
                    node = node.branch[alphabet];
                }
                node.end--;
            }
        }

        // 这个单词 加入的次数
        public int search(String word) {
            // 校验
            if(word == null){
                return 0;
            }
            // 迭代
            char[] alphabets = word.toCharArray();
            Node1 node = root;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                // 判断路路径是否存在
                if (node.branch[alphabet] == null) {
                    // 只要一个分支没有 ，就是没加入过
                    return 0;
                }
                node = node.branch[alphabet];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            // 校验
            if(pre == null){
                return 0;
            }
            // 迭代
            char[] alphabets = pre.toCharArray();
            Node1 node = root;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                // 判断路路径是否存在
                if (node.branch[alphabet] == null) {
                    // 没有这个前缀的字母
                    return 0;
                }
                node = node.branch[alphabet];
            }
            return node.pass;
        }
    }

    /**
     * Node2
     */
    public static class Node2 {

        private int pass;

        private int end;

        private HashMap<Integer,Node2> branch;

        public Node2(){
            pass = 0;
            end = 0;
            branch = new HashMap<>();
        }

    }

    /**
     * 哈希表实现
     */
    public static class Trie2 {

        private Node2 root;

        public Trie2(){
            root = new Node2();
        }


        public void insert(String word) {
            if(word == null){
                return;
            }
            char[] alphabets = word.toCharArray();
            Node2 node = root;
            node.pass++;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                if (node.branch.get(alphabet) == null) {
                    node.branch.put(alphabet, new Node2());
                }
                node = node.branch.get(alphabet);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if(search(word) != 0){
                char[] alphabets = word.toCharArray();
                Node2 node = root;
                node.pass--;
                for (char c : alphabets) {
                    int alphabet = c - 'a';
                    if (--node.branch.get(alphabet).pass == 0) {
                        node.branch.remove(alphabet);
                        return;
                    }
                    node = node.branch.get(alphabet);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if(word == null){
                return 0;
            }
            char[] alphabets = word.toCharArray();
            Node2 node = root;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                if (node.branch.get(alphabet) == null) {
                    return 0;
                }
                node = node.branch.get(alphabet);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null){
                return 0;
            }
            char[] alphabets = pre.toCharArray();
            Node2 node = root;
            for (char c : alphabets) {
                int alphabet = c - 'a';
                if (node.branch.get(alphabet) == null) {
                    return 0;
                }
                node = node.branch.get(alphabet);
            }
            return node.pass;
        }
    }

    public static class Right {

        private HashMap<String,Integer> box;

        public Right(){
            box = new HashMap<>();
        }



        public void insert(String word) {
          if(!box.containsKey(word)){
              box.put(word,1);
          }else {
              box.put(word,box.get(word) + 1);
          }
        }

        public void delete(String word) {
            if(box.containsKey(word)){
                if(box.get(word) == 1){
                    box.remove(word);
                }else {
                    box.put(word,box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return box.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String word : box.keySet()) {
                if(word.startsWith(pre)){
                    // 因为 word可能不止一个
                    count += box.get(word);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 25);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
