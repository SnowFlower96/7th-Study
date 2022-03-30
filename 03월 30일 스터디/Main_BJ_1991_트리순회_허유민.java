package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1991_트리순회_허유민 {

    static class Node {
        char data;
        Node left, right;
        Node(char data){
            this.data = data;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node[] tree = new Node[N];
        // A부터 Z까지 이므로 tree에 순서대로 A부터 N-1번째까지 입력
        char start = 'A';
        for (int i =0;i<N;i++){
            tree[i] = new Node(start++);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parents = st.nextToken().charAt(0);
            char child1 = st.nextToken().charAt(0);
            char child2 = st.nextToken().charAt(0);
            // A부터 순서대로 들어갔으므로 A의 아스키코드인 65를 빼면 인덱스값을 구할수 있습니다.
            tree[parents-65].left = child1 == '.' ? null : tree[child1 - 65];
            tree[parents-65].right = child2 == '.' ? null : tree[child2 - 65];
        }

        System.out.println(preorder(tree[0]));
        System.out.println(inorder(tree[0]));
        System.out.println(postorder(tree[0]));
    }

    //전위
    public static String preorder(Node node){
        String result = "";
        result += node.data;
        if(node.left != null) result += preorder(node.left); // 부터 시작시 B, D 끝나고
        if(node.right != null) result += preorder(node.right); // C E F G
        return result; // ABD CEFG
    }

    //중위
    public static String inorder(Node node){
        String result = "";
        if(node.left != null) result += inorder(node.left); // 루트 기준 왼쪽으로 계속들어가서 확인후 점점 올라옵니다.
        result += node.data;
        if(node.right != null) result += inorder(node.right);
        return result; // DBAECFG
    }

    //후위
    public static String postorder(Node node){
        String result = "";
        if(node.left != null) result += postorder(node.left); // DB
        if(node.right != null) result += postorder(node.right); // EGFCA
        result += node.data;
        return result; // DBEGFCA
    }
}
