import container.BTree;
import container.BinarySearchTree;
import container.RedBlackTree;
import org.junit.jupiter.api.Test;
import utils.ArrayGenerator;
import utils.TreePrinter;

/**
 * Test class for {@link TreePrinter}.
 */
public class TreePrinterTest {

	@Test
	void print() {
		int[] arr = ArrayGenerator.randomArray(20, 20);
		BinarySearchTree binarySearchTree = new BinarySearchTree(arr);
		BTree btree = new BTree(arr);
		RedBlackTree redBlackTree = new RedBlackTree(arr);

		TreePrinter.print(binarySearchTree);
		TreePrinter.print(btree);
		TreePrinter.print(redBlackTree);
	}

}