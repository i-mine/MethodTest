object MapToList {
  def main(args: Array[String]): Unit = {
    val lostCounts = 5
    val lastJobId =1
   val list =  (1 to lostCounts).map(_ + lastJobId).toList
    list.foreach(i=>println(i))
  }
}
