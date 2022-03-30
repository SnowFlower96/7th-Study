package study_03_30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1991_트리순회_정원석 {

	static class Node {
		String data, left, right;

		public Node(String data, String left, String right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int NodeNum = Integer.parseInt(br.readLine());
		
//		Node[] nodelist = new Node[NodeNum];
		
		String[][] list = new String[NodeNum][3];
		
		for(int i = 0; i< NodeNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				String data = st.nextToken();
				list[i][j] = data;
//				Node insert = new Node(data, left, right);
//				nodelist[i] = insert;
			}
		}
		
//		Preorder(nodelist, nodelist[0].data);
//		System.out.println(sb.toString());
//		sb.setLength(0);
//		inorder(nodelist, nodelist[0].data);
//		System.out.println(sb.toString());
//		sb.setLength(0);
//		Postorder(nodelist, nodelist[0].data);
//		System.out.println(sb.toString());
		//2차원 배열
		Preorder(list, list[0][0]);
		sb.append("\n");
		inorder(list, list[0][0]);
		sb.append("\n");
		Postorder(list, list[0][0]);
		System.out.println(sb.toString());
	}
	
	static StringBuilder sb = new StringBuilder();
//	배열
	public static void Preorder(String[][] list, String current) {
		if(current.equals(".")) return;
		String[] num = new String[3];
		
		for(int i = 0; i < list.length; i++) {
			if(current.equals(list[i][0])) { 
				num[0] = list[i][0];
				num[1] = list[i][1];
				num[2] = list[i][2];
			};
		}
		
		sb.append(num[0]);
		Preorder(list, num[1]);
		Preorder(list, num[2]);
	}
	public static void inorder(String[][] list, String current) {
		if(current.equals(".")) return;
		String[] num = new String[3];
		
		for(int i = 0; i < list.length; i++) {
			if(current.equals(list[i][0])) { 
				num[0] = list[i][0];
				num[1] = list[i][1];
				num[2] = list[i][2];
			};
		}
		
		inorder(list, num[1]);
		sb.append(num[0]);
		inorder(list, num[2]);
	}
	public static void Postorder(String[][] list, String current) {
		if(current.equals(".")) return;
		String[] num = new String[3];
		
		for(int i = 0; i < list.length; i++) {
			if(current.equals(list[i][0])) { 
				num[0] = list[i][0];
				num[1] = list[i][1];
				num[2] = list[i][2];
			};
		}
		
		Postorder(list, num[1]);
		Postorder(list, num[2]);
		sb.append(num[0]);
	}
//  객체 사용
//	public static void Preorder(Node[] list, String current) {
//		if(current.equals(".")) return ;
//		Node cur = null;
//		
//		for(Node a : list) {
//			if(a.data.equals(current))
//				cur = new Node(a.data, a.left, a.right);
//		}
//		
//		sb.append(cur.data);
//		Preorder(list, cur.left);
//		Preorder(list, cur.right);
//	}
//	객체사용
//	public static void inorder(Node[] list, String current) {
//		
//		if(current.equals(".")) return;
//		Node cur = null;
//		
//		for(Node a : list) {
//			if(a.data.equals(current))
//				cur = new Node(a.data, a.left, a.right);
//		}
//		
//		inorder(list, cur.left);
//		sb.append(current);
//		inorder(list, cur.right);
//		
//	}
//	객체사용
//	public static void Postorder(Node[] list, String current) {
//		if(current.equals(".")) return ;
//		
//		
//		Node cur = null;
//		
//		for(Node a : list) {
//			if(a.data.equals(current))
//				cur = new Node(a.data, a.left, a.right);
//		}
//		
//		Postorder(list, cur.left);
//		Postorder(list, cur.right);
//		sb.append(current);
//		
//		
//	}

}
