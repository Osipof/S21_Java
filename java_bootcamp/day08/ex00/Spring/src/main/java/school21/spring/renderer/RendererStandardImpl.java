package school21.spring.renderer;

import school21.spring.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void render(String message) {
        System.out.println(preProcessor.process(message));
    }
}
