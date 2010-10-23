package itest.demo1

/**
 * Doc of DemoC (object)
 * ....
 *
 * @author <a href="ghgjh">au1</a>
 * @author au2 (au2@foo.bar)
 * @tags tagA, tagB
 */
object DemoC extends DemoC(0, "0", "0")

/**
 * Class use to test override, inheritance,... (with object DemoC and child class DemoC2)
 */
class DemoC[T](private val ctorArg1 : Int, val ctorArg2 : String, ctorArg3 : T) {
  def doStuff(i : Int) : String = doStuff(i.toString)

  /**
   * sample doStuff method
   *
   * @param s
   * @return
   * @tags tagA, tagCCC
   */
  def doStuff(s : String) : String = "zzz..." +s

  @deprecated("replace by nothing")
  def methodDeprecatedByAnnot() {}
  /**
  * @deprecated replace by nothing
  */
  def methodDeprecatedByDoc() {}
}

/**
 * override method from parent DemoC and set type parameter to String
 *
 * @author david.bernard
 *
 */
class DemoC2(ctorArg1 : Int, ctorArg2 : String, ctorArg3 : String) extends DemoC[String](ctorArg1, ctorArg2, ctorArg3) {
	/**
	 * override method, try to inherit documentation
	 * {@inheritDoc}
	 */
  override def doStuff(s : String) : String = "xxx..." + s
}

