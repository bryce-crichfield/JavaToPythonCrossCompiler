package parse

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parse.antlr.{Java8Lexer, Java8Parser}

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

// acts as a container for the ANTLR logics
// this is likely very inefficient but it works for now
object TranslationUnit {

  // internal representation of current parsing scope
  private var currentScope: Int = 0
  def enterScope(): Unit = currentScope += 1
  def exitScope(): Unit = currentScope -= 1

  // internal representation of current parsing output
  private val stringBuilder = new StringBuilder()

  // given some input, will parse it, returning python output and a possible syntax error
  def process(input: String): (String, Option[SyntaxError]) = {
    stringBuilder.clear()
    val lexer = new Java8Lexer(input.toCharStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new Java8Parser(tokens)
    val tree = parser.compilationUnit()
    val walker = new ParseTreeWalker()
    val errorListener = new ErrorListener
    parser.addErrorListener(errorListener)
    val parserListener = new ParserListener(parser)
    walker.walk(parserListener, tree)
    val error = errorListener.syntaxErrors().headOption
    val output = stringBuilder.mkString
    (output, error)
  }

  // just some useful string extension methods
  implicit class stringExtension(str: String) {
    def toCharStream: CharStream = {
      val stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))
      CharStreams.fromStream(stream)
    }

    def prependTabs(n: Int): String = {
      List.fill(n)("\t").mkString + str
    }
  }

  def outputNoTab(str: String): Unit = {
    stringBuilder.append(str)
  }
  def outputWithTab(str: String): Unit = {
    stringBuilder.append(str.prependTabs(currentScope))
  }
}


