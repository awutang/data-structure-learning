/**
 * Author: Tang Yuqian
 * Date: 2023/4/7
 */
package com.datastructure.learning.algorithm;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently
 * store and retrieve keys in a dataset of strings. There are various applications of
 * this data structure, such as autocomplete and spellchecker.
 */
public class Trie {


    private TrieNode root;

    public Trie() {
        root = new TrieNode();

        // 不能赋值为0，0的话在本题中代表‘a’
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode current = root;
        // 一层一层往下插入字母
        for (int i = 0; i < word.length(); i++) {
            // 在index处的字符就是26个英文小写字母中的第index个
            char c = word.charAt(i);
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode(c);
            }
            current = current.children[c - 'a'];
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        // 一层一层往下搜索
        for (int i = 0; i < word.length(); i++) {
            // 在index处的字符就是26个英文小写字母中的第index个
            char c = word.charAt(i);
            if (current.children[c - 'a'] == null) {
                return false;
            }
            current = current.children[c - 'a'];
        }
        return current.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        // 一层一层往下匹配
        for (int i = 0; i < prefix.length(); i++) {
            // 在index处的字符就是26个英文小写字母中的第index个
            char c = prefix.charAt(i);
            if (current.children[c - 'a'] == null) {
                return false;
            }
            current = current.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));

    }


}

// 定义节点
class TrieNode {
    // 小写字母
    TrieNode[] children = new TrieNode[26];
    char val;
    Boolean endOfWord = false;

    public TrieNode() {
    }

    public TrieNode(char val) {
        this.val = val;
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */