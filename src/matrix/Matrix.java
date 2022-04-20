/**
 * Copyright 2022 jingedawang
 */
package matrix;

import enums.MultiplierType;
import enums.Operation;

import java.util.Arrays;

/**
 * Matrix entity class
 */
public class Matrix {

	/**
	 * Constructor. Create a matrix with given rows and columns.
	 *
	 * @param rows    The number of rows of the matrix.
	 * @param columns The number of columns of the matrix.
	 */
	public Matrix(int rows, int columns) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException("Row > 0 and column > 0 are required.");
		}
		matrix = new double[rows][columns];
	}

	/**
	 * Constructor. Create a matrix with given data.
	 *
	 * @param data A 2-dimensional array containing the matrix data.
	 */
	public Matrix(double[][] data) {
		matrix = data;
	}

	/**
	 * Get a writable reference of the matrix values.
	 *
	 * @return The inner 2-dimensional array that contains the matrix elements.
	 */
	public double[][] value() {
		return matrix;
	}

	/**
	 * Add a matrix to this matrix.
	 *
	 * @param m The matrix added to this matrix.
	 * @return A new matrix represents the sum.
	 */
	public Matrix add(Matrix m) {
		return addOrSubtract(m, Operation.ADD);
	}

	/**
	 * Subtract a matrix from this matrix.
	 *
	 * @param m The matrix subtracted from this matrix.
	 * @return A new matrix represents the difference.
	 */
	public Matrix subtract(Matrix m) {
		return addOrSubtract(m, Operation.SUBTRACT);
	}

	/**
	 * Multiply a matrix to this matrix.
	 *
	 * @param m The matrix multiplied to this matrix.
	 * @return A new matrix represents the product.
	 */
	public Matrix multiply(Matrix m) {
		if (isSquare() && m.isSquare() && rows() == m.rows() && (rows() & -rows()) == rows() &&
				(m.rows() & -m.rows()) == m.rows() && rows() >= 512) {
			return multiply(m, MultiplierType.STRASSEN);
		} else {
			return multiply(m, MultiplierType.PLAIN);
		}
	}

	/**
	 * Multiply a matrix to this matrix using the specified multiplier.
	 *
	 * @param m    The matrix multiplied to this matrix.
	 * @param type The type of the multiplier.
	 * @return A new matrix represents the product.
	 */
	public Matrix multiply(Matrix m, MultiplierType type) {
		if (columns() != m.rows()) {
			throw new IllegalArgumentException("The columns of the first matrix must be the same as the rows of the " +
					"second matrix.");
		}
		MatrixMultiplier multiplier = MatrixFactory.getMutiplier(type);
		return multiplier.multiply(this, m);
	}

	/**
	 * Split the matrix into four equal-sized blocks. The matrix to be split must be square and its side length must be
	 * a power of 2.
	 *
	 * @return An array containing the four sub-matrices.
	 */
	public Matrix[] split() {
		if (rows() < 2 || columns() < 2) {
			throw new IllegalArgumentException("The rows and the columns of the matrix must be at least 2.");
		}
		Matrix A11 = new Matrix((rows() + 1) / 2, (columns() + 1) / 2);
		Matrix A12 = new Matrix((rows() + 1) / 2, columns() / 2);
		Matrix A21 = new Matrix(rows() / 2, (columns() + 1) / 2);
		Matrix A22 = new Matrix(rows() / 2, columns() / 2);
		for (int i = 0; i < rows() / 2; i++) {
			for (int j = 0; j < columns() / 2; j++) {
				A11.value()[i][j] = matrix[i][j];
				A12.value()[i][j] = matrix[i][j + A11.columns()];
				A21.value()[i][j] = matrix[i + A11.rows()][j];
				A22.value()[i][j] = matrix[i + A11.rows()][j + A11.columns()];
			}
		}
		if (rows() % 2 != 0) {
			for (int j = 0; j < A12.columns(); j++) {
				A11.value()[A11.rows() - 1][j] = matrix[A11.rows() - 1][j];
				A12.value()[A11.rows() - 1][j] = matrix[A11.rows() - 1][j + A11.columns()];
			}
		}
		if (columns() % 2 != 0) {
			for (int i = 0; i < A21.rows(); i++) {
				A11.value()[i][A11.columns() - 1] = matrix[i][A11.columns() - 1];
				A21.value()[i][A11.columns() - 1] = matrix[i + A11.rows()][A11.columns() - 1];
			}
		}
		if (rows() % 2 != 0 && columns() % 2 != 0) {
			A11.value()[A11.rows() - 1][A11.columns() - 1] = matrix[A11.rows() - 1][A11.columns() - 1];
		}
		return new Matrix[]{A11, A12, A21, A22};
	}

	/**
	 * Check if the matrix is a square matrix.
	 *
	 * @return {@code true} if this is a square matrix, {@code false} otherwise.
	 */
	public boolean isSquare() {
		return rows() == columns();
	}

	/**
	 * Get the row number of the matrix.
	 *
	 * @return The number of the rows.
	 */
	public int rows() {
		return matrix.length;
	}

	/**
	 * Get the column number of the matrix.
	 *
	 * @return The number of the columns.
	 */
	public int columns() {
		return matrix[0].length;
	}

	/**
	 * Transform the matrix.
	 *
	 * @return A new matrix which is the transform of this matrix.
	 */
	public Matrix transform() {
		Matrix transformed = new Matrix(columns(), rows());
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				transformed.matrix[j][i] = matrix[i][j];
			}
		}
		return transformed;
	}

	/**
	 * Check if the matrix param equals this matrix.
	 *
	 * @param o The matrix object.
	 * @return {@code true} if the matrix object equals this matrix, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix matrix1 = (Matrix) o;
		return Arrays.deepEquals(matrix, matrix1.matrix);
	}

	/**
	 * Get the hash code of this matrix.
	 *
	 * @return A hash code of this matrix.
	 */
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(matrix);
	}

	/**
	 * Merge four matrices to one matrix.
	 *
	 * @param A11 The top left matrix to be merged.
	 * @param A12 The top right matrix to be merged.
	 * @param A21 The bottom left matrix to be merged.
	 * @param A22 The bottom right matrix to be merged.
	 * @return A big matrix merged by four matrices.
	 */
	public static Matrix merge(Matrix A11, Matrix A12, Matrix A21, Matrix A22) {
		if (!A11.isSquare() || !A12.isSquare() || !A21.isSquare() || !A22.isSquare()) {
			throw new IllegalArgumentException("Matrices to be merged must be square matrices.");
		}
		if (!(A11.rows() == A12.rows() && A11.rows() == A21.rows() && A11.rows() == A22.rows())) {
			throw new IllegalArgumentException("Matrices to be merged must have the same row number and column number");
		}
		Matrix A = new Matrix(A11.rows() * 2, A11.rows() * 2);
		for (int i = 0; i < A11.rows(); i++) {
			for (int j = 0; j < A11.columns(); j++) {
				A.value()[i][j] = A11.value()[i][j];
				A.value()[i][j + A11.columns()] = A12.value()[i][j];
				A.value()[i + A11.rows()][j] = A21.value()[i][j];
				A.value()[i + A11.rows()][j + A11.columns()] = A22.value()[i][j];
			}
		}
		return A;
	}

	/**
	 * Implementation of {@link #add} and {@link #subtract} methods.
	 *
	 * @param m         The matrix added or subtracted to this matrix.
	 * @param operation The operation type. Only {@link Operation#ADD} and {@link Operation#SUBTRACT} are allowed here.
	 */
	private Matrix addOrSubtract(Matrix m, Operation operation) {
		if (operation != Operation.ADD && operation != Operation.SUBTRACT) {
			throw new UnsupportedOperationException("Only ADD and SUBTRACT operations are supported here.");
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		if (m.rows() != rows || m.columns() != columns) {
			throw new IllegalArgumentException("Operands of matrix addition have different dimension.");
		}
		Matrix result = new Matrix(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				switch (operation) {
					case ADD:
						result.value()[i][j] = matrix[i][j] + m.value()[i][j];
						break;
					case SUBTRACT:
						result.value()[i][j] = matrix[i][j] - m.value()[i][j];
						break;
				}
			}
		}
		return result;
	}

	// Underlying matrix data
	private final double[][] matrix;

}