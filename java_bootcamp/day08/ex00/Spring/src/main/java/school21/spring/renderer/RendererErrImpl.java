package school21.spring.renderer;

import school21.spring.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void render(String message) {
        System.err.println(preProcessor.process(message));
    }
}
