package glo.routes.util

import org.scalatest.{Matchers, WordSpec}

class CodeGeneratorSpec extends WordSpec with Matchers {

  "generate a 24byte code" in {
    val code = CodeGenerator.generate()
    assert(code.length() > 24)
  }
}