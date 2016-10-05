/**
  * Created by avalcepina on 05/10/2016.
  */
import breeze.linalg.CSCMatrix
import org.scalatest.{FlatSpec, FunSuite};

class MatrixOpsHelperSpec extends FunSuite {

  test("Should calculated averages correctly") {

    val m = CSCMatrix((1.0,2.0,3.0),(1.0,2.0,3.0),(1.0,2.0,3.0))

    val expected = CSCMatrix((7.0,14.0,13.0),(10.0,20.0,18.0),(7.0,14.0,13.0))

    assert(MatrixOpsHelper.matrixAverage(m).equals(expected))

  }

}
