import breeze.linalg.{CSCMatrix, DenseMatrix}
import java.io._

import breeze.linalg

/**
  * Created by avalcepina on 04/10/2016.
  */
object BreezeExample extends App {

  //val denseMatrix = DenseMatrix.zeros[Int](5, 5);

  //denseMatrix(0 , 0 ) = 10;

  //Read matrix from csv file
  val denseMatrix= getMatrix("/denseMatrix.csv")

  // println(denseMatrix)

  val m1 = CSCMatrix.create(denseMatrix.rows, denseMatrix.cols,denseMatrix.toArray)

  val m2 = m1.map(filterValue(_, 5.0))

  //Size of active elements
  println(m1.activeSize)

  //Set one value manually
  m1(0,1) = 20

  //Size of active elements for filtered table
  println(m2.activeSize)

  //Print filtered matrix
  println(m2)

  def getMatrix(path : String) = linalg.csvread(new File(getClass.getResource(path).getPath),',')

  def filterValue(x : Double, thresold : Double) = if (x < thresold) 0.0 else x


}

