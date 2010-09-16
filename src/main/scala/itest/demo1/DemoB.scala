package itest.demo1

trait DemoB {
  var variable = "Hello"
  val value = "World"
  //val fct : =>String = "foo"
  def f2(fct:()=>String, bool: =>Boolean)

  def noArgsReturnString = "noArg"
  def noArgsNoReturn {}
  def zeroArgsReturnString() = "zeroArgs"
  def zeroArgsNoReturn() = {}
  def twoArgs(i: Int, s: String) = {s.length + i}

  @Deprecated
  def deprecatedByAnnotation(i: Int) = i

  /**
   * @deprecated replace by ...
   */
  def deprecatedByCommentsTag(i: Int) = i

  /**
   * @todo bbmlablab
   */
  def methodWithTodoByCommentsTag(i: Int) = i

  /**
   * return this but doesn't define return type explicitly
   * @return this
   */
  def returnThis() = this

  /**
   * return this, and define return type to this.type
   * @return this
   */
  def returnThisWithCovariantType() : this.type = this
}

case class DemoCaseClass(att1: String, val att2: String, var att3: String)
case object DemoCaseObject{
 val att2 = ""
 var att3 = ""
}
