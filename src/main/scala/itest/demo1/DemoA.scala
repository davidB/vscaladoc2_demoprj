package itest.demo1

import scala.reflect.BeanProperty
/**
 * Class to test class name with special character
 */
class :: {}

/**
 * Doc of DemoA (object)
 * ....
 * <pre name="code" class="scala">
 *  val x = DemoA.noArgsReturnString
 * </pre>
 *
 * @author <a href="ghgjh">au1</a>
 * @author au2 (au2@foo.bar)
 * @tags demo xxx
 * @since 0.1
 */
object DemoA {
  /** description of variable */
  var variable = "Hello"
  /** description of value */
  val value = "World"
  def noArgsReturnString = "noArg"
  def noArgsNoReturn {}
  def zeroArgsReturnString() = "zeroArgs"
  def zeroArgsNoReturn() = {}
  /**
   * method bkjjghkjhg
   * @param i an integer
   * @param s a String
   * @return concatenation of i and s
   */
  def twoArgs(i: Int, s: String) = {s.length + i}

  def apply() : DemoA = new DemoA
  
  protected def methProtected() = {}
  private def methPrivate() = {}
  private[this] def methPrivateThis() = {}
  private[demo1] def methPrivatePkg() = {}
}

/**
 * Class use to test the scaladoc 2 renderer
 */
@serializable
class DemoA {
  @BeanProperty
  var varAsBeanProperty = "beanProperty"
  var variable = "Hello"
  val value = "World"
  object objField extends InnerCaseClass("inner")
  
  def noArgsReturnString = "noArg"
  def noArgsNoReturn {}
  def zeroArgsReturnString() = "zeroArgs"
  def zeroArgsNoReturn() = {}
  /**@codeAsDoc*/
  def twoArgsAndCodeAsDoc(i: Int, s: String) = {s.length + i}
  def newInnerCaseClass(s : String) = InnerCaseClass(s)
  
  case class InnerCaseClass(s : String)
}


