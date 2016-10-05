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

    val expected = CSCMatrix((1.4, 2.0, 2.6), (1.4285714285714286, 2.0, 2.5714285714285716), (1.4, 2.0, 2.6))

    assert(MatrixOpsHelper.matrixAverage(m).equals(expected))

  }

//  test("Should calculated better averages correctly") {
//
//    val m = CSCMatrix((1.0, 2.0, 3.0), (1.0, 2.0, 3.0), (1.0, 2.0, 3.0))
//
//    val expected = CSCMatrix((1.4, 2.0, 2.6), (1.4285714285714286, 2.0, 2.5714285714285716), (1.4, 2.0, 2.6))
//
//    println(m.toDense)
//    println(MatrixOpsHelper.betterMatrixAverage(m).toDense)
//
//
//    assert(MatrixOpsHelper.betterMatrixAverage(m).equals(expected))
//
//  }


}
