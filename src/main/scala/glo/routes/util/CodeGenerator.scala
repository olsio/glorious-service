package glo.routes.util

import java.security.SecureRandom

object CodeGenerator {

  private val randomizer = new SecureRandom()

  def generate(): String = {
    val randomBytes: Array[Byte] = new Array(24)
    randomizer.nextBytes(randomBytes)
    convertBytesToHex(randomBytes)
  }

  def convertBytesToHex(bytes: Seq[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x", Byte.box(b)))
    }
    sb.toString
  }

}
