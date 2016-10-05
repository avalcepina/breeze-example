import breeze.linalg.CSCMatrix

/**
  * Breeze demo app
  */
object BreezeExample extends App {

  //val denseMatrix = DenseMatrix.zeros[Int](5, 5);

  //denseMatrix(0 , 0 ) = 10;

  //Read matrix from csv file
  val denseMatrix = MatrixOpsHelper.getMatrix("/denseMatrix.csv")

  val m1 = CSCMatrix.create(denseMatrix.rows, denseMatrix.cols, denseMatrix.toArray)

  //Size of active elements
  println(m1.activeSize)

  //Set one value manually
  m1(0, 1) = 20

  val m2 = MatrixOpsHelper.filterMatrix(m1, 5.0)

  //Print filtered matrix
  println(m2)

  //Size of active elements for filtered table
  println(m2.activeSize)

  val m3 = MatrixOpsHelper.matrixAverage(m2)

  println(m3)


}

