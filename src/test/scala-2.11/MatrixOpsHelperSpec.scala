import breeze.linalg.{CSCMatrix, DenseMatrix, View}
import org.scalatest.FunSuite
import org.scalatest.concurrent.TimeLimitedTests;
import org.scalatest.time.SpanSugar._


/**
  * Created by avalcepina on 05/10/2016.
  */
class MatrixOpsHelperSpec extends FunSuite with TimeLimitedTests {

  //Time limit that should not be exceeded
  val timeLimit = 10000 millis

  test("Should read matrix from file correctly") {

    val expected = CSCMatrix((1.0, 2.0, 3.0), (4.0, 5.0, 6.0), (7.0, 8.0, 9.0))

    assert(MatrixOpsHelper.getMatrix("/testMatrix.csv").equals(expected))

  }

  test("Should find matrix to be valid") {

    val m = CSCMatrix((0.0, 2.0, 3.0), (1.0, 6.0, 7.0), (4.0, 8.0, 10.0))

    assert(MatrixOpsHelper.validateMatrix(m, 0 to 10))

  }

  test("Should find matrix to be invalid") {

    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 6.0, 7.0), (4.0, 8.0, 9.0))

    assert(MatrixOpsHelper.validateMatrix(m, 3 to 10) == false)

    assert(MatrixOpsHelper.validateMatrix(m, 0 to 7) == false)

  }

  test("Should filter values correctly") {

    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 6.0, 7.0), (4.0, 8.0, 9.0))

    val expected = CSCMatrix((0.0, 0.0, 0.0), (0.0, 6.0, 7.0), (4.0, 8.0, 0.0))

    assert(MatrixOpsHelper.filterMatrix(m, 3 to 9).equals(expected))

  }

  test("Should calculated averages correctly") {

    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 2.0, 3.0), (1.0, 2.0, 3.0))

    val expected = CSCMatrix((1.4, 2.0, 2.6), (1.4285714285714286, 2.0, 2.5714285714285716), (1.4, 2.0, 2.6))

    assert(MatrixOpsHelper.matrixAverage(m).equals(expected))

  }

  test("Should calculated averages correctly on large matrix") {

    val m = MatrixOpsHelper.getMatrix("/testSparseMatrix.csv")

    val m2 = CSCMatrix.create[Double](m.rows, m.cols, m.toArray)

    MatrixOpsHelper.matrixAverage(m2)

  }


  test("Should find large matrix to be valid") {

    val m = MatrixOpsHelper.getMatrix("/testSparseMatrix.csv")

    val m2 = CSCMatrix.create[Double](m.rows, m.cols, m.toArray)

    assert(MatrixOpsHelper.validateMatrix(m2, -100 to 100))

  }

  test("Should filter correctly on large matrix") {

    val m = MatrixOpsHelper.getMatrix("/testSparseMatrix.csv")

    val m2 = CSCMatrix.create[Double](m.rows, m.cols, m.toArray)

    MatrixOpsHelper.filterMatrix(m2, 0 to 20)

  }

}
