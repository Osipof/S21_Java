package school21.spring.application;

import school21.spring.preprocessor.PreProcessor;
import school21.spring.preprocessor.PreProcessorToUpperImpl;
import school21.spring.printer.Printer;
import school21.spring.printer.PrinterWithPrefixImpl;
import school21.spring.renderer.Renderer;
import school21.spring.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        System.out.println("Usual method:");
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println("Spring beans method:");
        Printer prefixPrinter = context.getBean("printerWithPrefix", Printer.class);
        prefixPrinter.print("Hello!");

        prefixPrinter = context.getBean("printerWithPrefixStd", Printer.class);
        prefixPrinter.print("Hello!");

        Printer datePrinter = context.getBean("printerWithDate", Printer.class);
        datePrinter.print("Hello!");

        datePrinter = context.getBean("printerWithDateErr", Printer.class);
        datePrinter.print("Hello!");
    }
}
