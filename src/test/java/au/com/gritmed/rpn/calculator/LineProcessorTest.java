package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.input.InputLineParser;
import au.com.gritmed.rpn.output.Display;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LineProcessorTest {
    private Cache calculatorCache;

    @Mock
    private Display display;

    private LineProcessor lineProcessor;

    @BeforeEach
    void setUp() {
        calculatorCache = new Cache();
        lineProcessor = new LineProcessor(new InputLineParser(), display);
    }

    @Test
    void shouldBeAbleToStackOperands_Example_1() {
        lineProcessor.processLine("1 2", calculatorCache);
        verify(display).show("stack: 1 2");
   }

    @Test
    void shouldBeAbleToCombineSqrtAndClean_Example_2() {
        lineProcessor.processLine("2 sqrt", calculatorCache);
        verify(display).show("stack: 1.4142135623");

        lineProcessor.processLine("clear 9 sqrt", calculatorCache);
        verify(display).show("stack: 3");
   }

    @Test
    void shouldBeAbleToCombineSubstractAndClean_Example_3() {
        lineProcessor.processLine("5 2 -", calculatorCache);
        verify(display).show("stack: 3");

        lineProcessor.processLine("3 -", calculatorCache);
        verify(display).show("stack: 0");

        lineProcessor.processLine("clear", calculatorCache);
        verify(display).show("stack:");
   }

    @Test
    void shouldBeAbleToCascadeUndo_Example_4() {
        lineProcessor.processLine("5 4 3 2", calculatorCache);
        verify(display).show("stack: 5 4 3 2");

        lineProcessor.processLine("undo undo *", calculatorCache);
        verify(display).show("stack: 20");

        lineProcessor.processLine("5 *", calculatorCache);
        verify(display).show("stack: 100");

        lineProcessor.processLine("undo", calculatorCache);
        verify(display).show("stack: 20");
   }

    @Test
    void shouldBeAbleToPerformDivides_Example_5() {
       lineProcessor.processLine("7 12 2 /", calculatorCache);
       verify(display).show("stack: 7 6");

       lineProcessor.processLine("*", calculatorCache);
       verify(display).show("stack: 42");

       lineProcessor.processLine("4 /", calculatorCache);
       verify(display).show("stack: 10.5");
    }

    @Test
    void shouldBeAbleToCombineOperations_Example_6() {
       lineProcessor.processLine("1 2 3 4 5", calculatorCache);
       verify(display).show("stack: 1 2 3 4 5");

       lineProcessor.processLine("*", calculatorCache);
       verify(display).show("stack: 1 2 3 20");

       lineProcessor.processLine("clear 3 4 -", calculatorCache);
       verify(display).show("stack: -1");
    }

    @Test
    void shouldChainOperatore_Example_7() {
       lineProcessor.processLine("1 2 3 4 5", calculatorCache);
       verify(display).show("stack: 1 2 3 4 5");

       lineProcessor.processLine("* * * *", calculatorCache);
       verify(display).show("stack: 120");
    }

    @Test
    void shouldDetectInsufficientParameters_Example_8() {
       lineProcessor.processLine("1 2 3 * 5 +  * * 6 5", calculatorCache);
       verify(display).show("Operator * (position: 16): insuficient parameters");
       verify(display).show("stack: 11");
    }
}
