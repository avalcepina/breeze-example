import java.io.File

import breeze.linalg.{CSCMatrix, View, _}


/**
  * Helper with functions to process data in matrices
  */
object MatrixOpsHelper {


  //implicit val bf = implicitly[CanMapKeyValuePairs[CSCMatrix[Double],(Int, Int),Double,Double,Double]]

  /**
    * Get a matrix from a file
    *
    * @param path
    * @return
    */
  def getMatrix(path: String) = csvread(new File(getClass.getResource(path).getPath), ',')

  private def filterValue(x: Double, thresold: Double) = if (x < thresold) 0.0 else x

  /**
    * Filters elements of the matrix according to thresold
    *
    * @param matrix
    * @param thresold
    * @return
    */
  def filterMatrix(matrix: CSCMatrix[Double], thresold: Double) = matrix.active.values.map(MatrixOpsHelper.filterValue(_, thresold))

  /**
    * Calculates the average using surrounding elements of each element
    *
    * @param matrix
    * @return
    */
  def matrixAverage(matrix: CSCMatrix[Double]) = {


    val result = CSCMatrix.zeros[Double](matrix.rows, matrix.cols)

    matrix.pairs.foreach(v => {
      result(v._1._1, v._1._2) = v._2 + sum(sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy))
    })

    result
    //
    //    matrix.pairs.iterator.map(v => {
    //      ((v._1._1,v._1._2), v._2 + sum(sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy)))
    //
    //    }).toMap[(Int,Int), Double]

  }

  private def sliceMatrix(matrix: CSCMatrix[Double], x: Int, y: Int) = matrix(Math.max(0, x - 1) to Math.min(matrix.rows - 1, x + 1), Math.max(0, y - 1) to Math.min(matrix.cols - 1, y + 1))

}
