import breeze.linalg.{CSCMatrix, View}

/**
  * Created by avalcepina on 05/10/2016.
  */
object MatrixOpsHelper {


  def matrixAverage(matrix: CSCMatrix[Double]) = {

    var result = CSCMatrix.zeros[Double](matrix.rows, matrix.cols)

    matrix.active.pairs.foreach(v => {

      result(v._1._1, v._1._2) = (v._2 + sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy).sum)

    })

    result

  }

  def sliceMatrix(matrix: CSCMatrix[Double], x: Int, y: Int) = matrix(Math.max(0, x - 1) to Math.min(matrix.rows - 1, x + 1), Math.max(0, y - 1) to Math.min(matrix.cols - 1, y + 1))


}
