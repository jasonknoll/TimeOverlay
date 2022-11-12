import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JTextArea
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val WIDTH = 600
const val HEIGHT = 400

fun main(args: Array<String>) {

    val textArea = JTextArea()



    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss\nMM-dd-yyyy")

    val current = LocalDateTime.now()
    val formattedTime = current.format(formatter)

    textArea.text = formattedTime

    //textArea.setBounds()


    val frame = JFrame("Hello Swing!")
    frame.add(textArea)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(WIDTH,HEIGHT)

    frame.isVisible = true
}