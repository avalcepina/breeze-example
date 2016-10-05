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

  private def filterValue(x: Double, range: Range) = if (x <= range.inclusive.start || x >= range.inclusive.end) 0.0 else x


  private def isInvalidValue(x: Double, range: Range) = (x <= range.start || x >= range.end)


  /**
    * Reduces to 0 all values not in the specified range or equal to the bounds
    *
    * @param matrix
    * @param range
    * @return
    */
  def filterMatrix(matrix: CSCMatrix[Double], range: Range) = matrix.active.values.map(MatrixOpsHelper.filterValue(_, range))

  /**
    * Check for values outside the specified range
    *
    * @param matrix
    * @param range
    * @return
    */
  def validateMatrix(matrix: CSCMatrix[Double], range: Range) = !matrix.flatten(View.Copy).exists(isInvalidValue(_, range))

  /**
    * Calculates the average using surrounding elements of each element
    *
    * @param matrix
    * @return
    */
  def matrixAverage(matrix: CSCMatrix[Double]) = {

    val result = CSCMatrix.zeros[Double](matrix.rows, matrix.cols)

    matrix.pairs.foreach(v => {
      result(v._1._1, v._1._2) = calculateSum(v._2, sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy))
    })

    result

  }

  // TODO maybe investigate more to try and implement the nicer solution
  //  def betterMatrixAverage(matrix: CSCMatrix[Double]) = {

  //
  //    CSCMatrix.zeros[Double](matrix.rows, matrix.cols).active.pa
  //
  //    //
  //
  //    matrix.pairs.iterator.map(v => {
  //
  //      calculateSum(v._2, sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy))
  //
  //    }).toArray.foreach(v => println(v))
  //
  //    matrix.pairs.iterator.map(v => {
  //      calculateSum(v._2, sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy))
  //    }).toArray.grouped(matrix.cols).foreach(v => {
  //      print("(")
  //      v.foreach(c => print(c+","))
  //      print(")")
  //    }
  //
  //    CSCMatrix.create(matrix.cols, matrix.rows,
  //      matrix.pairs.iterator.map(v => {
  //
  //        calculateSum(v._2, sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy))
  //      }).toArray)
  //
  //  }

  private def sliceMatrix(matrix: CSCMatrix[Double], x: Int, y: Int) = matrix(Math.max(0, x - 1) to Math.min(matrix.rows - 1, x + 1), Math.max(0, y - 1) to Math.min(matrix.cols - 1, y + 1))

  private def calculateSum(value: Double, slice: Vector[Double]) = {
    (value + sum(slice)) ///(1+slice.length)
  }

}
