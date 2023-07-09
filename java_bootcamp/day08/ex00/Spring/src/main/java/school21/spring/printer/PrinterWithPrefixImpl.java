package school21.spring.printer;

import school21.spring.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private final Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.render(prefix + message);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
