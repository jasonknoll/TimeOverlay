import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import javax.swing.JFrame
import javax.swing.JTextArea
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Maybe scale based on font
// This is good for Monaco 20
const val WIDTH = 100
const val HEIGHT = 80

/**
 *
 * TODO Center Text Area in JFrame window
 * TODO Place window in correct location
 *    + TODO Find monitor size and place window relative
 * TODO make window always on top
 * TODO Make window borderless
 *
 * */

fun main(args: Array<String>) {

    val textArea = JTextArea()

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss\nMM-dd-yyyy")

    val current = LocalDateTime.now()
    val formattedTime = current.format(formatter)

    textArea.text = formattedTime

    //textArea.size = Dimension()
    textArea.font = Font("Monaco", Font.PLAIN, 20)
    //textArea.setBounds()

    textArea.alignmentX = Component.CENTER_ALIGNMENT
    textArea.alignmentY = Component.CENTER_ALIGNMENT


    val frame = JFrame("Date and Time Overlay")
    frame.add(textArea)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(WIDTH, HEIGHT)

    frame.isVisible = true
}