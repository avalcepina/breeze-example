import breeze.linalg.{CSCMatrix, DenseMatrix, SliceMatrix, View}
import java.io._

import breeze.signal._
import breeze.linalg

/**
  * Created by avalcepina on 04/10/2016.
  */
object BreezeExample extends App {

  //val denseMatrix = DenseMatrix.zeros[Int](5, 5);

  //denseMatrix(0 , 0 ) = 10;

  //Read matrix from csv file
  val denseMatrix = getMatrix("/denseMatrix.csv")

  // println(denseMatrix)

  val m1 = CSCMatrix.create(denseMatrix.rows, denseMatrix.cols, denseMatrix.toArray)

  //val m2 = m1.map(filterValue(_, 5.0))

  //println(m3)

  //Size of active elements
  println(m1.activeSize)

  //Set one value manually
  m1(0, 1) = 20

  val m2 = m1.active.values.map(filterValue(_, 5.0))

  //Print filtered matrix
  println(m2)

  //Size of active elements for filtered table
  println(m2.activeSize)

  val m3 = matrixAverage(m2)

  println(m3.toDense)

  def getMatrix(path: String) = linalg.csvread(new File(getClass.getResource(path).getPath), ',')

  def filterValue(x: Double, thresold: Double) = if (x < thresold) 0.0 else x

  def matrixAverage(matrix: CSCMatrix[Double]) = {

    var result = CSCMatrix.zeros[Double](matrix.rows, matrix.cols)

    matrix.active.pairs.foreach(v => {

      result(v._1._1, v._1._2) = v._2 + sliceMatrix(matrix, v._1._1, v._1._2).flatten(View.Copy).sum

    })

    result

  }

  def sliceMatrix(matrix: CSCMatrix[Double], x: Int, y: Int) = matrix(Math.max(0, x - 1) to Math.min(matrix.rows - 1, x + 1), Math.max(0, y - 1) to Math.min(matrix.cols - 1, y + 1))


}

