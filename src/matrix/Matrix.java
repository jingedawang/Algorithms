/**
 * Copyright 2022 jingedawang
 */
package matrix;

import enums.MultiplierType;
import enums.Operation;
import utils.ArrayUtils;

import java.util.Arrays;

/**
 * Matrix entity class.
 *
 * @param <E> The type of the underlying elements of the matrix, which must be a kind of {@code Number}. Currently, only
 *           {@link Integer} and {@link Double} are supported.
 */
public class Matrix<E extends Number> {

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
		matrix = new Number[rows][columns];
	}

	/**
	 * Constructor. Create a matrix with a 2-dimensional integer array.
	 *
	 * @param data A 2-dimensional integer array containing the matrix data.
	 */
	public Matrix(int[][] data) {
		this((E[][]) ArrayUtils.box(data));
	}

	/**
	 * Constructor. Create a matrix with a 2-dimensional double array.
	 *
	 * @param data A 2-dimensional double array containing the matrix data.
	 */
	public Matrix(double[][] data) {
		this((E[][]) ArrayUtils.box(data));
	}

	/**
	 * Constructor. Create a matrix with given data.
	 *
	 * @param data A 2-dimensional array containing the matrix data.
	 */
	public Matrix(E[][] data) {
		if (data.length <= 0 || data[0].length <= 0) {
			throw new IllegalArgumentException("Row > 0 and column > 0 are required.");
		}
		for (int i = 1; i < data.length; i++) {
			if (data[i].length != data[0].length) {
				throw new IllegalArgumentException("Each row of the matrix must have the same length.");
			}
		}
		matrix = data;
	}

	/**
	 * Get a writable reference of the matrix values as a Number array.
	 *
	 * @return The inner 2-dimensional array that contains the matrix elements.
	 */
	public Number[][] value() {
		return matrix;
	}

	/**
	 * Get the value in specific location.
	 * @param row The row index of the item.
	 * @param column The column index of the item.
	 * @return The value of the item in specific location.
	 */
	public E get(int row, int column) {
		return (E) matrix[row][column];
	}

	/**
	 * Set the value to the item in specific location.
	 * @param value The value to be set.
	 * @param row The row index of the item.
	 * @param column The column index of the item.
	 */
	public void set(E value, int row, int column) {
		matrix[row][column] = value;
	}

	/**
	 * Add a matrix to this matrix.
	 *
	 * @param m The matrix added to this matrix.
	 * @return A new matrix represents the sum.
	 */
	public Matrix<E> add(Matrix<E> m) {
		return addOrSubtract(m, Operation.ADD);
	}

	/**
	 * Subtract a matrix from this matrix.
	 *
	 * @param m The matrix subtracted from this matrix.
	 * @return A new matrix represents the difference.
	 */
	public Matrix<E> subtract(Matrix<E> m) {
		return addOrSubtract(m, Operation.SUBTRACT);
	}

	/**
	 * Multiply a matrix to this matrix.
	 *
	 * @param m The matrix multiplied to this matrix.
	 * @return A new matrix represents the product.
	 */
	public Matrix<E> multiply(Matrix<E> m) {
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
	public Matrix<E> multiply(Matrix<E> m, MultiplierType type) {
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
	public Matrix<E>[] split() {
		if (rows() < 2 || columns() < 2) {
			throw new IllegalArgumentException("The rows and the columns of the matrix must be at least 2.");
		}
		Matrix<E> A11 = new Matrix<E>((rows() + 1) / 2, (columns() + 1) / 2);
		Matrix<E> A12 = new Matrix<E>((rows() + 1) / 2, columns() / 2);
		Matrix<E> A21 = new Matrix<E>(rows() / 2, (columns() + 1) / 2);
		Matrix<E> A22 = new Matrix<E>(rows() / 2, columns() / 2);
		for (int i = 0; i < rows() / 2; i++) {
			for (int j = 0; j < columns() / 2; j++) {
				A11.matrix[i][j] = matrix[i][j];
				A12.matrix[i][j] = matrix[i][j + A11.columns()];
				A21.matrix[i][j] = matrix[i + A11.rows()][j];
				A22.matrix[i][j] = matrix[i + A11.rows()][j + A11.columns()];
			}
		}
		if (rows() % 2 != 0) {
			for (int j = 0; j < A12.columns(); j++) {
				A11.matrix[A11.rows() - 1][j] = matrix[A11.rows() - 1][j];
				A12.matrix[A11.rows() - 1][j] = matrix[A11.rows() - 1][j + A11.columns()];
			}
		}
		if (columns() % 2 != 0) {
			for (int i = 0; i < A21.rows(); i++) {
				A11.matrix[i][A11.columns() - 1] = matrix[i][A11.columns() - 1];
				A21.matrix[i][A11.columns() - 1] = matrix[i + A11.rows()][A11.columns() - 1];
			}
		}
		if (rows() % 2 != 0 && columns() % 2 != 0) {
			A11.matrix[A11.rows() - 1][A11.columns() - 1] = matrix[A11.rows() - 1][A11.columns() - 1];
		}
		return new Matrix[]{A11, A12, A21, A22};
	}

	/**
	 * Check if the matrix is empty.
	 *
	 * @return {@code true} if this matrix has no elements.
	 */
	public boolean empty() {
		return matrix == null || matrix.length == 0 || matrix[0].length == 0;
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
	public Matrix<E> transform() {
		Matrix<E> transformed = new Matrix<E>(columns(), rows());
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
		Matrix<E> matrix1 = (Matrix<E>) o;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] instanceof Double) {
					if (Math.abs(matrix[i][j].doubleValue() - matrix1.matrix[i][j].doubleValue()) > 0.0001) {
						return false;
					}
				}
				else {
					if (!matrix[i][j].equals(matrix1.matrix[i][j])) {
						return false;
					}
				}
			}
		}
		return true;
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
	public static <E extends Number> Matrix<E> merge(Matrix<E> A11, Matrix<E> A12, Matrix<E> A21, Matrix<E> A22) {
		if (!A11.isSquare() || !A12.isSquare() || !A21.isSquare() || !A22.isSquare()) {
			throw new IllegalArgumentException("Matrices to be merged must be square matrices.");
		}
		if (!(A11.rows() == A12.rows() && A11.rows() == A21.rows() && A11.rows() == A22.rows())) {
			throw new IllegalArgumentException("Matrices to be merged must have the same row number and column number");
		}
		Matrix<E> A = new Matrix<E>(A11.rows() * 2, A11.rows() * 2);
		for (int i = 0; i < A11.rows(); i++) {
			for (int j = 0; j < A11.columns(); j++) {
				A.matrix[i][j] = A11.matrix[i][j];
				A.matrix[i][j + A11.columns()] = A12.matrix[i][j];
				A.matrix[i + A11.rows()][j] = A21.matrix[i][j];
				A.matrix[i + A11.rows()][j + A11.columns()] = A22.matrix[i][j];
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
	private Matrix<E> addOrSubtract(Matrix<E> m, Operation operation) {
		if (operation != Operation.ADD && operation != Operation.SUBTRACT) {
			throw new UnsupportedOperationException("Only ADD and SUBTRACT operations are supported here.");
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		if (m.rows() != rows || m.columns() != columns) {
			throw new IllegalArgumentException("Operands of matrix addition have different dimension.");
		}
		Matrix<E> result = new Matrix<E>(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				switch (operation) {
					case ADD:
						if (matrix[i][j] instanceof Integer) {
							result.matrix[i][j] = matrix[i][j].intValue() + m.matrix[i][j].intValue();
						}
						else if (matrix[i][j] instanceof Double) {
							result.matrix[i][j] = matrix[i][j].doubleValue() + m.matrix[i][j].doubleValue();
						}
						else {
							throw new IllegalArgumentException("Number type " + matrix[i][j].getClass().getName() + " is not supported in matrix add operation.");
						}
						break;
					case SUBTRACT:
						if (matrix[i][j] instanceof Integer) {
							result.matrix[i][j] = matrix[i][j].intValue() - m.matrix[i][j].intValue();
						}
						else if (matrix[i][j] instanceof Double) {
							result.matrix[i][j] = matrix[i][j].doubleValue() - m.matrix[i][j].doubleValue();
						}
						else {
							throw new IllegalArgumentException("Number type " + matrix[i][j].getClass().getName() + " is not supported in matrix subtract operation.");
						}
						break;
				}
			}
		}
		return result;
	}

	// Underlying matrix data
	private final Number[][] matrix;

}