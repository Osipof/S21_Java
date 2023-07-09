package school21.spring.printer;

import school21.spring.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Renderer renderer;


    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    @Override
    public void print(String message) {
        renderer.render(LocalDateTime.now().format(FORMATTER) + " --- " + message);
    }
}
