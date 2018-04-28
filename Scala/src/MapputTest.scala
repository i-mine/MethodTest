import scala.collection.mutable

object MapputTest {
  /**
    * author : dulei
    * date : 2017-11-01
    * desc: 该测试检验了map的value为List类型的时候
    * value不可变，即list不能再添加值或删除值，
    * 因为Scala中list是不可变类型，set可以为可变集合
    * @param args
    */
  def main(args: Array[String]): Unit = {
//    val map = mutable.HashMap[String,List[Int]]()
    val map = mutable.HashMap[String,mutable.Set[Int]]()
    val list1 = List[Int](1,2)
    val set  = mutable.Set[Int]()
    map.put("key1",set)

    set.add(1)
    set.add(4)
    set.add(5)
    for(i<-map){
      println(i)

    }
    println(list1)
    println(set)
  }
}
