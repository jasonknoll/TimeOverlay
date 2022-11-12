import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JFrame
import javax.swing.JTextArea
import kotlin.system.exitProcess

// Maybe scale based on font
// This is good for Monaco 20
const val WIDTH = 120
const val HEIGHT = 45

/**
 *
 * TODO Center Text Area in JFrame window
 * TODO Place window in correct location
 *    + TODO Find monitor size and place window relative
 * TODO make window always on top
 * TODO Make window borderless
 *
 * */

class ClockThread(runFlag: Boolean, textArea: JTextArea, formatter: DateTimeFormatter):Runnable {
    var _flag: Boolean = false
    var _formatter = formatter
    var _textArea = textArea

    init {
        this._flag = runFlag
    }

    override fun run() {
        while(_flag) {
            val current = LocalDateTime.now()
            val formattedTime = current.format(_formatter)
            _textArea.text = formattedTime
        }
    }
}

class JKKeyListener(frame: JFrame): KeyListener {
    var ctrlPressed = false
    var shiftPressed = false
    private var frame: JFrame
    init {
        this.frame = frame
    }
    override fun keyTyped(e: KeyEvent) {

    }

    override fun keyPressed(e: KeyEvent?) {

        if (e != null) {
            when (e.keyCode)  {
                KeyEvent.VK_CONTROL -> this.ctrlPressed = true
                KeyEvent.VK_SHIFT -> this.shiftPressed = true
                KeyEvent.VK_Q ->  {
                    if (this.shiftPressed && this.ctrlPressed) {
                        frame.dispose()
                        exitProcess(0)
                    }
                }
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e != null) {
            when (e.keyCode)  {
                KeyEvent.VK_CONTROL -> this.ctrlPressed = false
                KeyEvent.VK_SHIFT -> this.shiftPressed = false
            }
        }
    }
}


fun updateClock(textArea: JTextArea, formatter: DateTimeFormatter, flag: Boolean) {
    val clockThread = ClockThread(flag, textArea, formatter)
    val thread = Thread(clockThread)

    thread.start()
    thread.join()
}


fun main(args: Array<String>) {

    // Setup
    val textArea = JTextArea()

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss\nMM-dd-yyyy")

    //textArea.size = Dimension()
    textArea.font = Font("Monaco", Font.PLAIN, 20)
    //textArea.setBounds()

    // Don't do anything??
    textArea.alignmentX = 10.0F
    textArea.alignmentY = Component.CENTER_ALIGNMENT


    val frame = JFrame("Date and Time Overlay")
    frame.add(textArea)
    textArea.addKeyListener(JKKeyListener(frame))
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(WIDTH, HEIGHT)

    textArea.background = java.awt.Color(0, 128, 128)
    textArea.foreground = Color.WHITE

    frame.isUndecorated = true
    frame.isAlwaysOnTop = true

    val screenSize = Toolkit.getDefaultToolkit().screenSize


    val x = screenSize.getWidth()
    //val y = screenSize.getHeight().toInt()

    frame.setLocation((x.toFloat() * 0.75).toInt(), 0)


    frame.isVisible = true

    val isRunning = true
    updateClock(textArea, formatter, isRunning)
}