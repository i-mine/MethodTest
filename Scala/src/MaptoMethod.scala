import scala.collection.mutable
/**
  * author : dulei
  * date : 2017-11-01
  * desc: 该测试用来检验将一个map当做方法的参数，在方法内部对map进行更改。
  * conclusion: 在方法 内部对map进行更改，map并不是方法的局部变量
 */
object MaptoMethod {
  def main(args: Array[String]): Unit = {
    val map:mutable.HashMap[String, Int] = mutable.HashMap()
    putMethod(map)
    println(map.size)
    for(i<-map){
      val (key,value) = i
      println(key+":"+value)
    }

  }
  def putMethod(map: mutable.HashMap[String, Int]): Unit ={
    for(value<- 1 to 10){
      map.put("value"+value,value)
    }
    map.remove("value1")
  }
}
