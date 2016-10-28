package com.ucas.algorithms.math;

import com.ucas.algorithms.enums.MultiplierType;
import com.ucas.algorithms.enums.Operation;
import com.ucas.algorithms.matrix.MatrixFactory;
import com.ucas.algorithms.matrix.MatrixMultiplier;

public class Matrix {

	private double[][] matrix;
	
	/**
	 * 创建<code>row</code>行<code>column</code>列的矩阵。
	 * @param row 行数
	 * @param column 列数
	 */
	public Matrix(int row, int column) {
		if (row <= 0 || column <= 0) {
			throw new IllegalArgumentException("Row > 0 and column > 0 are required.");
		}
		matrix = new double[row][column];
	}
	
	/**
	 * 返回包含矩阵所有元素的二维数组，可向该数组写入新值。
	 * @return 包含矩阵所有元素的二维数组
	 */
	public double[][] value() {
		return matrix;
	}
	
	/**
	 * 返回加上矩阵<code>m</code>后的结果，结果以新矩阵返回。
	 * @param m 待相加的矩阵
	 * @return 相加后的新矩阵
	 */
	public Matrix add(Matrix m) {
		return operate(m, Operation.ADD);
	}
	
	/**
	 * 返回减去矩阵<code>m</code>后的结果，结果以新矩阵返回。
	 * @param m 待相减的矩阵
	 * @return 相减后的新矩阵
	 */
	public Matrix subtract(Matrix m) {
		return operate(m, Operation.SUBTRACT);
	}
	
	/**
	 * 返回乘以矩阵<code>m</code>后的结果，结果以新矩阵返回。
	 * @param m 待相乘的矩阵
	 * @return 相乘后的新矩阵
	 */
	public Matrix multiply(Matrix m) {
		MatrixMultiplier multiplier = MatrixFactory.getMutiplier();
		return multiplier.multiply(this, m);
	}
	
	/**
	 * 返回乘以矩阵<code>m</code>后的结果，结果以新矩阵返回。使用type指定的乘法器。
	 * @param m 待相乘的矩阵
	 * @param type 指定的乘法器枚举类型
	 * @return 相乘后的新矩阵
	 */
	public Matrix multiply(Matrix m, MultiplierType type) {
		MatrixMultiplier multiplier = MatrixFactory.getMutiplier(type);
		return multiplier.multiply(this, m);
	}
	
	/**
	 * 暂时只支持加法和乘法，请勿使用该方法做其它操作。
	 * 计算乘法请直接使用<code>multiply</code>方法。
	 */
	private Matrix operate(Matrix m, Operation operation) {
		int row = matrix.length;
		int column = matrix[0].length;
		if (m.value().length != row || m.value()[0].length != column) {
			throw new UnsupportedOperationException("Operands of matrix addition have different dimension.");
		}
		Matrix result = new Matrix(row, column);
		for (int i=0; i<row; i++) {
			for (int j=0; j<column; j++) {
				switch (operation) {
				case ADD:
					result.value()[i][j] = matrix[i][j] + m.value()[i][j];
					break;
				case SUBTRACT:
					result.value()[i][j] = matrix[i][j] - m.value()[i][j];
					break;
				case DIVIDE:
					throw new UnsupportedOperationException("Matrix division is not supported now.");
				}
				
			}
		}
		return result;
	}

	/**
	 * 将矩阵分割为均等的四块。要求被分割矩阵为方阵、行数至少为2且行数为2的幂。
	 * @return 包含分割后的四个矩阵的数组
	 */
	public Matrix[] split() {
		int row = matrix.length;
		int column = matrix[0].length;
		if (row != column) {
			throw new IllegalArgumentException("Two dimensions of matrix must be the same.");
		}
		if (row <= 1) {
			throw new IllegalArgumentException("1x1 matrix can not be split.");
		}
		if ((row & -row) != row || (column & -column) != column) {
			throw new IllegalArgumentException("Dimension of matrix split must be a power of 2.");
		}
		Matrix A11 = new Matrix(row / 2, column / 2);
		Matrix A12 = new Matrix(row / 2, column / 2);
		Matrix A21 = new Matrix(row / 2, column / 2);
		Matrix A22 = new Matrix(row / 2, column / 2);
		for (int i=0; i<row/2; i++) {
			for (int j=0; j<column/2; j++) {
				A11.value()[i][j] = matrix[i][j];
				A12.value()[i][j] = matrix[i][j + column / 2];
				A21.value()[i][j] = matrix[i + row / 2][j];
				A22.value()[i][j] = matrix[i + row / 2][j + column / 2];
			}
		}
		return new Matrix[] { A11, A12, A21, A22 };
	}

	/**
	 * 判断矩阵是否为方阵。
	 * @return true， 如果是方阵；否则为false。
	 */
	public boolean isSquare() {
		return row() == column();
	}

	/**
	 * 返回矩阵的行数。
	 * @return 矩阵的行数
	 */
	public int row() {
		return matrix.length;
	}
	
	/**
	 * 返回矩阵的列数
	 * @return 矩阵的列数
	 */
	public int column() {
		return matrix[0].length;
	}
	
}
