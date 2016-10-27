package com.ucas.algorithms.utils;

import com.ucas.algorithms.math.Matrix;

public class MatrixPrinter {

	public static void print(Matrix m) {
		System.out.print("[");
		for (int i=0; i<m.row(); i++) {
			for (int j=0; j<m.column() - 1; j++) {
				System.out.print(m.value()[i][j] + ",");
			}
			System.out.println(m.value()[i][m.column() - 1] + (i == m.row() - 1 ? "]" : ""));
		}
	}
	
}
