package utils;

import java.util.Random;

import math.Matrix;

public class MatrixGenerator {
	
	/**
	 * 返回随机生成的指定行列数的方阵。
	 * @param row 指定的行数和列数
	 * @return 生成的矩阵
	 */
	public static Matrix generateRandomMatrix(int row) {
		return generateRandomMatrix(row, row, 10);
	}
	
	/**
	 * 返回随机生成的指定行数和列数的矩阵。
	 * @param row 指定行数
	 * @param column 指定列数
	 * @return 生成的矩阵
	 */
	public static Matrix generateRandomMatrix(int row, int column) {
		return generateRandomMatrix(row, column, 10);
	}

	/**
	 * 返回随机生成的指定行数和列数且指定元素大小上限的矩阵。
	 * @param row 指定行数
	 * @param column 指定列数
	 * @param upperLimit 指定元素最大值
	 * @return 生成的矩阵
	 */
	public static Matrix generateRandomMatrix(int row, int column, int upperLimit) {
		Matrix matrix = new Matrix(row, column);
		Random random = new Random(Seed.next());
		for (int i=0; i<row; i++) {
			for (int j=0; j<column; j++) {
				matrix.value()[i][j] = random.nextInt(upperLimit);
			}
		}
		return matrix;
	}
	
}
