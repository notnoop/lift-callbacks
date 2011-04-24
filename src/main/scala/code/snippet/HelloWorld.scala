package code
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

import net.liftweb.http._
import net.liftweb.http.js._

class HelloWorld extends StatefulSnippet {
   def dispatch = {
     case _ => render
   }

   var initialized = false
   var a = ""

   def render = {
     ".addField" #> SHtml.ajaxButton("Add field", () => {
       JsCmds.SetHtml("field", SHtml.text(a, {t=>
         initialized = true
         println("Initialized: " + t)
       }))}) &
     "type=submit" #> SHtml.onSubmitUnit(process _)
   }

   def process() = {
     println("Processing")
     if (!initialized) {
       S.error(<p>Field hasn't been initialized</p>)
     } else {
       S.notice(<p>Field has been initialized and is "{ a }"</p>)
     }
   }
}

