package future


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success, Try}


object NoFutureCoffee {
  /**
    * 1.磨咖啡豆
    * 2.烧热水
    * 3.用热水蒸煮磨好的咖啡
    * 4.给牛奶打泡
    * 5.将蒸馏好的咖啡和打泡牛奶混合
    */
  //咖啡豆
  type CoffeeBeans = String
  //磨好的咖啡
  type GrundCoffee = String

  //水
  case class Water(temperature: Int)

  //牛奶
  type Milk = String
  //打泡牛奶
  type FrothedMilk = String
  //浓咖啡
  type Espresso = String
  //卡布奇诺
  type Cappuccino = String

  /**
    * 以下首先单个步骤的虚拟实现
    */
  //磨豆子
  def grind(beans: CoffeeBeans): GrundCoffee = s"ground coffee of $beans"

  //加热
  def heatWater(water: Water): Water = water.copy(temperature = 85)

  //牛奶打泡
  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"

  //冲泡
  def brew(coffee: GrundCoffee, heatedWater: Water): Espresso = "espresso"

  //混合
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  //可能抛出的异常
  case class GrindingException(msg: String) extends Exception(msg)

  case class FrothingException(msg: String) extends Exception(msg)

  case class WaterBoilingException(msg: String) extends Exception(msg)

  case class BrewingException(msg: String) extends Exception(msg)

  //首先是没有使用Future异步非阻塞的特性，场景模拟
  def prepareCappuccino(): Try[Cappuccino] =
    for {
      ground <- Try(grind("arabica beans"))
      water <- Try(heatWater(Water(25)))
      espresso <- Try(brew(ground, water))
      foam <- Try(frothMilk("milk"))
    } yield combine(espresso, foam)
}

object FutureCoffee {
  /**
    * 使用Future进行场景模拟
    */
  //咖啡豆
  type CoffeeBeans = String
  //磨好的咖啡
  type GrundCoffee = String

  //水
  case class Water(temperature: Int)

  //牛奶
  type Milk = String
  //打泡牛奶
  type FrothedMilk = String
  //浓咖啡
  type Espresso = String
  //卡布奇诺
  type Cappuccino = String

  /**
    * 以下首先单个步骤的虚拟实现
    */
  //磨豆子
  def grind(beans: CoffeeBeans): Future[GrundCoffee] = Future {
    println("start grinding...")
        Thread.sleep(2000)
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  //加热
  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
        Thread.sleep(2000)
    println("hot,it's hot!")
    water.copy(temperature = 85)
  }

  //牛奶打泡
  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk fothing system engaged")
        Thread.sleep(2000)
    println("shuting down milk frothing system")
    s"frothed $milk"
  }

  //冲泡
  def brew(coffee: GrundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happing brewing:")
        Thread.sleep(2000)
    println("it's brewed!")
    "espresso"
  }

  //检查水温
  def tempretureOkay(water: Water): Future[Boolean] = Future {
    (80 to 85).contains(water.temperature)
  }

  //混合
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  //可能抛出的异常
  case class GrindingException(msg: String) extends Exception(msg)

  case class FrothingException(msg: String) extends Exception(msg)

  case class WaterBoilingException(msg: String) extends Exception(msg)

  case class BrewingException(msg: String) extends Exception(msg)

  //  单一Future回调
  //    grind("baked arabica beans").onComplete {
  //      case Success(ground) => println(s"got my $ground")
  //      case Failure(ex) => println("This grinder needs a replacement,seriously!")
  //    }
  //多个Future嵌套回调可以使用for或是map,flatmap,filter
  def prepareCappuccinoSequentially(): Future[Cappuccino] = {
    for {
      ground <- grind("arabica beans")
      water <- heatWater(Water(20))
      foam <- frothMilk("milk")
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabica beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    groundCoffee.onComplete{
      case Success(value) => println("grinding complete")
      case Failure(ex) => throw GrindingException("grind failed")
    }
    heatedWater.onComplete{
      case Success(value) => println("heat complete")
      case Failure(ex) => throw WaterBoilingException("heat failed")
    }
    frothedMilk.onComplete{
      case Success(value) => println("froth complete")
      case Failure(ex) => throw FrothingException("froth failed")
    }
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)

  }

  def main(args: Array[String]): Unit = {
    println("starting")

    //依次实例化必须等到前一个Futrure运行成功才可以进行，独立的线程不能保持并发的特性
//   val prepare =  prepareCappuccinoSequentially()
    //独立的Future应该在调用前全部实例化，会各自独立地运行，不分先后
    val prepare = prepareCappuccino()
    prepare.onComplete{
      case Success(prepare) =>println(s"oh!,great $prepare")
      case Failure(ex) =>throw  new RuntimeException("no coffee")
    }
    val result = Await.result(prepare,Duration.Inf)
    println(result)
  }

}

