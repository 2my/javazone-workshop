package minitest

class LeftSide( val left: Int ) extends MiniTest {
  def ===( right: Int ): String = assertEq(left, right);
  def !==( right: Int ): String = assertNotEq(left, right);
}

trait MiniTest {
  val noop = () => ()
  var beforeFunc = noop
  var afterFunc = noop

  def test( spec: String )(f: => String): Unit = {
    beforeFunc ()
    print( spec )
    println( tryMe( f ) )
    afterFunc ()
  }

  def before( f: => Unit ): Unit = {
    beforeFunc  = () => f
  }
  def after( f: => Unit ): Unit = afterFunc  = () => f

  implicit def leftSide( i: Int ) = new LeftSide( i );

  def pending( msg: String ): String = {
    " NOT IMPELEMNTED YET " + msg
  }

  private def tryMe( f: => String ): String = {
    try {
      f
    } catch {
      case t: Throwable => " Got exception "
    }
  }
  def assertEq( actual: Int, reference: Int ): String = {
    if ( actual == reference )
      " SUCCESS! "
    else
      " FAILURE! " + actual + " does not equal " + reference
  };
  def assertNotEq( actual: Int, reference: Int ): String = {
    if ( actual != reference )
      " SUCCESS! "
    else
      " FAILURE! " + actual + " does equal " + reference
  };

}