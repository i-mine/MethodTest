

object MyForeach {
  def main(args: Array[String]): Unit = {
    val list = List("abs","1234","dfdfdsf","fdf","d")
//    list.foreach{ string: String=>
//      breakable {
//          if(string.length>4) break()
//          print("\n"+string)
//      }
//    }
//
//      list.foreach{string=>
//        if(string.length>4) break()
//        print("\n"+string)
//      }
    list.map(string=>{
       (formethod(string))
    })

  }

  private def formethod(string: String):Unit = {
    if (string.length > 4) return
    print("\n" + string)

  }
}
