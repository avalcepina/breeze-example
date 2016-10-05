import breeze.linalg.CSCMatrix
import org.scalatest.FunSuite;

/**
  * Created by avalcepina on 05/10/2016.
  */
class MatrixOpsHelperSpec extends FunSuite {

  test("Should read matrix from file correctly") {

    val expected = CSCMatrix((1.0, 2.0, 3.0), (4.0, 5.0, 6.0), (7.0, 8.0, 9.0))

    assert(MatrixOpsHelper.getMatrix("/testMatrix.csv").equals(expected))

  }

  test("Should find matrix to be valid") {

    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 6.0, 7.0), (4.0, 8.0, 9.0))

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

    val expected = CSCMatrix((7.0, 14.0, 13.0), (10.0, 20.0, 18.0), (7.0, 14.0, 13.0))

    assert(MatrixOpsHelper.matrixAverage(m).equals(expected))

  }
  //
  //  test("Should still calculated averages correctly") {
  //
  //    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 2.0, 3.0), (1.0, 2.0, 3.0))
  //
  //    val rows: (Double, Double, Double) = (7.0, 14.0, 13.0)
  //    val expected = CSCMatrix(rows, (10.0, 20.0, 18.0), (7.0, 14.0, 13.0))
  //
  //    println(expected.toDense.toDenseVector)
  //    println(expected.toDense);
  //    println(MatrixOpsHelper.betterMatrixAverage(m).toDense);
  //
  //    assert(MatrixOpsHelper.betterMatrixAverage(m).equals(expected))
  //
  //  }

}
