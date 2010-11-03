/* ♥
 * Showing off most special features of the Scala language,
 * to test in my lexer for Scintilla/SciTE, and, why not, other lexers!
 */
/*
Author: Philippe Lhoste <PhiLho(a)GMX.net> http://Phi.Lho.free.fr
Copyright notice: For details, see the following file:
http://Phi.Lho.free.fr/softwares/PhiLhoSoft/PhiLhoSoftLicense.txt
This program is distributed under the zlib/libpng license.
Copyright (c) 2010 Philippe Lhoste / PhiLhoSoft
*/

package org.philhosoft.experiments

// Illustrating import features...
import java.util.{ Date, Locale }
import java.util.regex._
import java.util.zip
import javax.swing.{ Action => _ /* discard */, _ }

// Testing ScalaDoc
/** Just a typical main class as entry point of an application.
  * Silly class just exercice the Scala syntax and API.
  * @author PhiLho
  * @example /* This is a code example */
  *          var +++ = 0
  * @date This doc keyword isn't official, so should be highlighted differently.
  * @note End of ScalaDoc is on last line of comment. */
object TestLexer
{
	// Stacking declarations only for screen estate (for screenshot!)
	val CONSTANT = "Scala"; val ich_bin_a_berliner = "Quote" * 2;
	var variable = 21; var scalaIsNice = true; var dontDoThat = null
	var +++ = -0.444e7 + .77E-2
	val mixed_with_op_<> = 0xF00D / 044
	var u_u = 'c'; val _da2 = '\u2620'; var déjà = '\uuu263C' // As much u as we want...
	val 愛_あい_♥ = "Love\uuu2661" // One identifier hard to use!

	/* Class entry point
	 * See: /* We can nest block comments! /**/ */
	 */
	def main(args: Array[String]): Unit =
	{
		=====("Test Lexer")
		println(scala.util.Properties.versionMsg)
		mandatory
		args.foreach(println)

		val rude_%!^*# = 123456789L / 55l
		val ^-^ = 0.101010101D * 9f
		val `|(.)/` = "Wild! ♥ " + _da2 + " " + déjà
		val #=# = rude_%!^*# + " " + ^-^ + " " + `|(.)/` + " " + 愛_あい_♥
		Console println #=#
		this showOff "Look, ma, no parentheses for method call with one parameter!"

		// Valid identifiers
		{
		val a = 1; val aa = 1; val a1 = 1; val a_b = 1; val a_b_c = 1
		var _a = 1; var __a = 1; var a_ = 1; var a__ = 1; var _a_b_ = 1
		val a_* = 1; val a__* = 1; val ** = 1
		// Invalid
//~ 		val a* = 1
//~ 		val a_*_* = 1
//~ 		val *_* = 1
//~ 		val *a = 1
//~ 		val *_a = 1
//~ 		val _* = 1
		}

		val list = List(101, 102, 103, 104, 105)
		val concatList = List(1) ::: list
		println("Init with cons: " + (11 :: 22 :: 33 :: 44 :: Nil))

		val lsd = """
"Lucy in the Sky with Diamonds"

Picture yourself in a boat on a river with tangerine trees
And marmalade skies.
Somebody calls you, you answer quite slowly, a girl with
Kaleidoscope eyes.
//"""
		println("Multiline strings: " + lsd + "...")
		val mls = """|\n|\t|\|\x63|\u00A3|"|""|"""
		println("Literal strings: " + mls + "...")
		val code = """	§  if (bFoo) {
						§    doStuff();
						§  }"""
		println(code stripMargin '§')

		val stuff = List('Albert, 'Bruce, 'Caroline, '#--#, 'a_b, 'F1, 'x_*)
		println("List of symbols: " + stuff)

		val xmlFragment =
<jnlp spec="1.0+" codebase="http://my.site/" href="gah/Some.jsp">
	<information>
		<vendor>My Site</vendor>
		<icon kind='splash' href='gah/img/splash.png'/>
		<offline-allowed/>
		<title>My site is Rich!</title>
	</information>
</jnlp> // Must be completely closed
		println(xmlFragment)
		println

		val ix = <foo>Foo</foo><foo>Bar</foo><foo>Baz</foo>;
		println(ix)
		val cx =
		<enclosing>
			<boo>{ix}Ah! {stuff(2)}</boo>
			<boo>{val x = <sc/>; x}</boo>
			<boo>{
				(1 to 17 by 2).toList.map(i => <sub-boo>Item #{i}</sub-boo>)
			}</boo>
		</enclosing>
		println(cx)
		println

		// Making Java-like bean
		class SimpleClass(var count: Int, val msg: String)
		{
			// Secondary constructor, delegating to the main constructor with a default value
			def this(msg: String) = this(1, msg)
		}
		import scala.reflect.BeanProperty
		class BeanClass(@BeanProperty var param: SimpleClass)
		{
			@BeanProperty var bear = param.msg
		}

		class X(value: Int)
		{
			// Infix (regular operator/method way)
			def +*(y: Int) = value + y * y
			// Prefix. Works only for + - ~ ! operators. No parameters
			def unary_- = if (value == 0) -1 else 1000/value // Whatever...
			// Postfix is any operator without parameters
			def ?? = 666 * value
			// Operator ending with a colon is right-associative:
			// its object must be on the right instead of being on the left
			def ?:?:(y: Int) = 1000 * y + value
			// Utility
			override def toString = value.toString
		}

		val x = new X(11)
		val r1 = x +* 2
		val r2 = -x
		val r3 = x??; // Need semi-colon there...
		val r4 = 1221 ?:?: x

		//### Code by Rex Kerr
		def cfor[@specialized T](zero: T, okay: T => Boolean, inc: T => T)(act: T => Unit)
		{
			var i = zero
			while (okay(i))
			{
				act(i)
				i = inc(i)
			}
		}
		var sum = 0
		cfor[Int](0, _ < 1000000, _+1) { sum += _ }
	}
	def =====(message: String) = { println("\n\n=====\uu266A " + message) }
	def mandatory = Console.println("Hello World!")
	def showOff(m: String): Unit = println("Look: " + m)
}

/** Just a test of ScalaDoc.
  * = Testing ScalaDoc markup (wiki style) =
  * == Inline elements ==
  * This ''is'' displayed as ''italic'', while '''this''' one is '''bold'''.
  * We can also  __underline__ or have `monospaced` text.
  * For the math inclined,we can ^superscript^ text or have it ,,subscripted,,.
  * We can link to a Scala class like [[scala.collection.Set]] and
  * we can link to a site, like the [[http://scala-lang.org Scala web site]].
  *
  * == Block elements ==
  * A code sample: {{{ val truck: Moving = new Car with Big }}}
  *
  * Plain list:
  *  - One
  *  - Two
  *    - Beyond Two
  *    - And more
  *  - Three
  *
  * Auto-incremented list:
  *  1. One
  *  1. Two
  *     a. Beyond Two
  *     a. And more
  *  1. Three

  * Another code sample: {{{
    var sum = 0
    cfor[Int](0, _ < 1000000, _+1)
    {
      sum += _
    }
}}}
  */
object TextScalaDoc
{
	/** An uninteresting function. */
	def foo(x: Int) = "foo" * x
}
