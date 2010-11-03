package itest.p2 

import _root_.itest.demo1.MiniTrait

/**
 * * class that extends a trait define into an other package with longer name.
 * * define the abstract method from trait
 */ 
class MiniTraitImplNoOverride extends MiniTrait {
  def abstractMethod() : Int = 10
}
     
/**
 * * class that extends a trait define into an other package with longer name.
 * * define the abstract method from trait
 * * override method define in the trait
 */ 
class MiniTraitImplOverride extends MiniTrait {
  def abstractMethod() : Int = 22
  override def nonAbstractMethod() : Int = 22
}

/**
 * * extends an other trait
 * * define the abstract method from parent trait
 */ 
trait MiniTrait2 extends MiniTrait {
  def abstractMethod() : Int = 44
}

