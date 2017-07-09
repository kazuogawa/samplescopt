import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object samplescopt {
  case class CommandLineArgs(
    //引数が設定されていなかった場合は現在時間の1日前を入れるみたいです
    from: DateTime = DateTime.now.minusDays(1),
    to: DateTime = DateTime.now.minusDays(1))
  def main(args: Array[String]): Unit = {
    //scopt.OptionParserにcase classの型を入れるみたいです。そのあとにパーサーの名前を入れるみたいです
    val parser = new scopt.OptionParser[CommandLineArgs]("your program name") {
      //--helpしたときに表示されるやつ？
      head("scopt", "3.6.0")

      val dtf = DateTimeFormat.forPattern("yyyyMMdd")
      //引数で設定する「-ほげほげ 値」またはの部分を作っているみたいですね
      //値の部分はcase classで設定した値を指定すればいいみたいですね
      //xには値が、cにはcase classに設定した型にパースされた値が入っているみたいです
      opt[String]('f', "from") action { (x, c) =>
        //println("x = "+ x)
        //println("c = "+ c)
        c.copy(from = DateTime.parse(x, dtf))
        //--helpを使った場合のメッセージが書かれるみたいです
      } text ("from is date")

      //上と同じようにしましょう
      opt[String]('t', "to") action { (x, c) =>
        c.copy(to = DateTime.parse(x, dtf))
      } text ("to is date")

      //--helpを出す場合に書くみたいですね
      help("help") text("print this usage text.")
    }
    println(parser.parse(args, CommandLineArgs()))
  }
}
